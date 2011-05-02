module Bowling where
import Data.List (tails)
import Data.Either           
import Test.HUnit

type Throw = Int
type Score = Int
type ErrorMsg = String

data Frame = Frame Throw (Maybe Throw) 
           deriving (Show,Eq)

data FrameResult = Regular | Spare | Strike
                 deriving (Show,Eq)

type Game = [Frame] 
                   
totalPins = 10
bonus = 10
showErrorMsg = putStrLn

strike = Frame 10 Nothing

result :: Frame -> Either ErrorMsg FrameResult
result (Frame 10 Nothing)= Right Strike
result (Frame x (Just y)) 
  | knocked == totalPins = Right Spare
  | knocked >= 0 && knocked < totalPins = Right Regular
    where knocked = x + y
result frame = Left $ "Frame invalid: " ++ show frame                

frameScore :: Frame -> [Frame] -> Either ErrorMsg Score 
frameScore frame bonusFrames = 
  either Left calcScore $ result frame
  where 
    bonusThrows = throws bonusFrames
    calcScore res = 
          case (res,frame,bonusThrows) of  
            (Regular,Frame x (Just y), _) -> Right $ x+y 
            (Spare,_,(x:_)) -> Right $ bonus + x  
            (Strike,_,(x:y:_)) -> Right $ bonus + x + y
            _ -> Left $ "Input invalid. Frame: " ++ show frame ++
                        ". Bonus: " ++ show bonusThrows ++ "."

throws :: [Frame] -> [Throw]
throws [] = []
throws (Frame x (Just y) : xs) = x:y:throws xs 
throws (Frame x Nothing : xs) = x:throws xs 

gameScore :: Game -> Either [ErrorMsg] Score
gameScore game = 
  let frs= take 10 $ tails game    
      score (h:t)= frameScore h t 
      scores = map score frs 
  in case partitionEithers scores of 
    ([],scores) -> Right $ sum scores 
    (errors,_) -> Left errors
               
testResult = TestCase $ do
  assertEqual
    "Devuelve que un turno ha sido un strike si en el primer (y unico) lanzamiento se derriban todos los bolos"
    (result $ Frame totalPins Nothing) (Right Strike)
  
  assertEqual
    "El turno sera un spare si en los dos lanzamientos se derriban todos los bolos"
    (result $ Frame (div totalPins 2) $ Just (div totalPins 2)) 
    (Right Spare)
  
  assertEqual
    "Si en los dos lanzamientos no se derriban todos el turno sera normal"
    (result $ Frame 0 $ Just 0) (Right Regular)
  
  assertEqual
    "Si la representacion del turno no es correcta devuelve un mensaje de error"
    (map ((either (take 13) undefined).result)  
     [Frame 5 Nothing,Frame 5 $ Just 6,
      Frame 11 Nothing]) 
    (replicate 3 "Frame invalid")
    
testFrameScore = TestCase $ do
  assertEqual
    "Si el lanzamiento es normal, el resultado del turno sera la suma de los bolos derribados en ese turno"
    (frameScore (Frame 5 $ Just 4) [undefined]) (Right 9)
    
  assertEqual
    "Si el Lanzamiento es un spare el resultado es la suma del bonus mas los bolo derribados en el siguiente lanzamiento"
    (frameScore (Frame 5 $ Just 5) [Frame 10 Nothing]) (Right 20)
    
  assertEqual
    "Si el lanzamiento es un strike el resultado es la suma del bonus mas los bolos derribados en los dos lanzamientos siguientes"
    (map (frameScore (Frame 10 Nothing)) 
     [[Frame 5 $ Just 4],
      [Frame 10 Nothing, Frame 5 $ Just 5]]) 
    ([Right 19,Right 25])
  
  assertEqual
    "Si el tipo de resultado del turno necesita los lanzamientos para calcular la puntuacion y no se proporcionan, devuelve un mensaje de error"
    (frameScore (Frame 10 Nothing) []) 
    (Left "Input invalid. Frame: Frame 10 Nothing. Bonus: [].")
  
testThrows = TestCase $
             assertEqual
             "Devuelve los lanzamientos (bolos derribados) de los turnos"
             (map throws 
              [[Frame 10 Nothing, Frame 5 $ Just 5],
               [Frame 5 $ Just 5, Frame 0 $ Just 0]])
             ([[10,5,5],[5,5,0,0]])

testGameScore = 
  let game 1 = take 12 $ repeat strike
      game 2 = take 11 $ repeat $ Frame 5 $ Just 5
      game 3 = take 10 $ repeat $ Frame 9 $ Just 0 
      game 4 = take 10 $ repeat $ Frame 1 $ Just 1
      game 5 = replicate 3 strike ++ (replicate 7 $ Frame 0 $ Just 0)
      game 6 = [strike,Frame 7 $ Just 3,Frame 9 $ Just 0,strike,
                Frame 0 $ Just 8, Frame 8 $ Just 2, Frame 0 $ Just 6,
                strike,strike,Frame 7 $ Just 2]
      game 7 = replicate 9 (Frame 0 $ Just 0) ++ [strike,Frame 2 $ Just 3]
      game 8 = replicate 7 (Frame 0 $ Just 0) ++ [strike,Frame 2 $ Just 3]
  in TestCase $ 
     
     assertEqual 
     "Devuelve el resultado del juego completo segun las reglas"
     (map gameScore $ map game $ [1..8]) 
     ([Right 300, Right 150,Right 90,Right 20,
       Right 60,Right 145,Right 15,Right 20])
    

main= runTestTT $ TestList [testResult,testFrameScore,testThrows,testGameScore]

