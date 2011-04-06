data Result = Strike | Spare | Normal deriving (Eq, Show)

extract1 (x,y,z,w) = x 
extract2 (x,y,z,w) = y
extract3 (x,y,z,w) = z
extract4 (x,y,z,w) = w -- No sé si existe algo similar...

read' :: String -> Int
read' "X" = 10
read' "-" = 0
read' x = read x

frames :: String -> Result -> Int
frames [] _ = 0
frames [x] _ = read' [x]
frames (x:y:ys) prev_result =
	let bonus = length (x:y:ys) <= 3 -- Si estamos en el último frame
	in let result -- (puntos 1º roll, puntos 2º roll, tipo roll, resto lista)
		| x == 'X' = if bonus then (10, read' [y], Normal, ys)
			 			else (10, 0, Strike, y:ys)
		| y == '/' = (read' [x], 10 - read' [x], if bonus then Normal else Spare, ys)
		| otherwise = (read' [x], read' [y], Normal, ys)
	in let value = case prev_result of -- Puntuación a añadir
		Normal -> extract1 result + extract2 result
		Spare -> extract1 result * 2 + extract2 result
		Strike -> extract1 result * 2 + extract2 result * 2 +
					if y == 'X' && not bonus then 10 else 0
	in value + frames (extract4 result) (extract3 result)
	
puntuacion :: String -> Int
puntuacion xs = frames xs Normal
		
-- Pruebas

main = do
	putStrLn . show $ puntuacion "X7/9-X-8-/-6XXX81"
	putStrLn . show $ puntuacion "XXXXXXXXXXXX"
	putStrLn . show $ puntuacion "9-9-9-9-9-9-9-9-9-9-"
	putStrLn . show $ puntuacion "5/5/5/5/5/5/5/5/5/5/5"