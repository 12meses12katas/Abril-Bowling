module Bowling where
           
type Throw = Int
type Score = Int
type ErrorMsg = String

data Frame = Frame Throw (Maybe Throw) 
           deriving (Show)
type Bonus = Frame

data FrameResult = Regular | Spare | Strike
                 deriving (Show)

type Game = [Frame] 
                   
totalPins = 10
bonus = 10
showErrorMsg = putStrLn

result :: Frame -> Either ErrorMsg FrameResult

result (Frame 10 Nothing)= Right Strike
result (Frame x (Just y)) 
  | knocked == totalPins = Right Spare
  | knocked > 0 && knocked < 10 = Right Regular
    where knocked = x + y
result frame = Left $ "Frame invalid: " ++ show frame                

frameScore :: Frame -> [Frame] -> Either ErrorMsg Score 

frameScore frame bonusFrames = 
  either Left calcScore $ result frame
  where 
    bonusThrows = throws bonusFrames
    calcScore res = 
          case (res,frame,bonusThrows) of  
            (Regular,Frame x (Just y), _) -> 
              Right $ x+y 
            (Spare,_,(x:_)) -> 
              Right $ bonus + x  
            (Strike,_,(x:y:_)) -> 
              Right $ bonus + x + y
            _ -> Left $ "Input invalid. Frame: " ++ show frame ++
                        "Bonus: " ++ show bonusThrows

throws :: [Frame] -> [Throw]
throws [] = []
throws (Frame x (Just y) : xs) = x:y:throws xs 
throws (Frame x Nothing : xs) = x:throws xs 


gameScore :: Game -> Either ErrorMsg Score
gameScore game = undefined

test= [Frame 10 Nothing, Frame 10 Nothing, Frame 5 (Just 5)]

