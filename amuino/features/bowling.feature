# language: es
Característica: Calcular la puntuación de una partida de bolos
	Para poder presumir delante de los amigotes
	Como jugador de bolor
	Quiero saber el valor numérico 
	
	Esquema del escenario: sumar la puntuación
		Dado que las tiradas han sido "<tiradas>"
		Cuando calcule la sume
		Entonces el resultado es "<suma>"

		Ejemplos:
			| tiradas                | suma |
			| 0-0-0-0-0-0-0-0-0-0-   | 0    |
			| 1-0-0-0-0-0-0-0-0-0-   | 1    |
			| 3-0-0-0-0-0-0-0-0-0-   | 3    |
			| 5/1-0-0-0-0-0-0-0-0-   | 12   |
			| 0-0-0-0-0-0-0-0-0-5/1- | 11   |
			| X1-1-0-0-0-0-0-0-0-    | 14   |
			| XXXXXXXXXXXX           | 300  |
			| 9-9-9-9-9-9-9-9-9-9-   | 90   |
			| 5/5/5/5/5/5/5/5/5/5/5  | 150  |
