# language: es
Característica: Calcular la puntuación de una partida de bolos
	Para poder presumir delante de los amigotes
	Como jugador de bolos
	Quiero saber el valor numérico 
	
	Esquema del escenario: sumar la puntuación
		Dado que las tiradas han sido "<tiradas>"
		Cuando calcule la sume
		Entonces el resultado es "<suma>"

		Ejemplos:
			| tiradas                | suma |
			| 45454545454545454545   | 90   |
			| 0-0-0-0-0-0-0-0-0-0-   | 0    |
			| 1-0-0-0-0-0-0-0-0-0-   | 1    |
			| 3-0-0-0-0-0-0-0-0-0-   | 3    |
			| 5/1-0-0-0-0-0-0-0-0-   | 12   |
			| 0-0-0-0-0-0-0-0-0-5/1- | 11   |
			| X1-1-0-0-0-0-0-0-0-    | 13   |
			| XXXXXXXXXXXX           | 300  |
			| 9-9-9-9-9-9-9-9-9-9-   | 90   |
			| 5/5/5/5/5/5/5/5/5/5/5  | 150  |
			| 11111111111111111111   | 20   |
      | 22222222222222222222   | 40   |
      | --------------------   | 0    |
      | 9-9-9-9-9-9-9-9-9-9-   | 90   |
      | 4/-6----------------   | 16   |
      | 7/-6----------------   | 16   |
      | XXX00000000000000      | 60   |
      | X7/9-X-88/-6XX72       | 145  |
      | X34----------------    | 24   |
      | XXXXXXXXX00            | 240  |
      | 4/4/4/4/4/4/4/4/4/4/4  | 140  |
      | 5/5/5/5/5/5/5/5/5/5/5  | 150  |
      | X4/X4/X4/X4/X4/X       | 200  |
      | XXXXXXXXXXXX           | 300  |
      | 14456/5/X-17/6/X2/6    | 133  |
      | X7/9-X-88/-6XXX81      | 167  |
      | ------------------X23  | 15   |
      | ----------------X23    | 20   |
