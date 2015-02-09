package sst;

import java.awt.Point;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class which can solve and fill a sudoku
 */
public class SudokuSolver {
	public static void solve(Sudoku sudoku) {

		trySolve(new SudokuIterator(sudoku), 1);
		
	}
	
	private static ISudokuIterator trySolve(ISudokuIterator currentTile, int nbToAdd) {
			if (currentTile == null)
				return null;
		
			// Si la case actuelle est libre, on essaie d'y mettre un chiffre.
			if (currentTile.value() == '0') {
				/*if (currentTile.set(nbToAdd))
					trySolve(currentTile.next(), nbToAdd);
				else
					trySolve(currentTile, (nbToAdd + 1) % 9);*/
			}
			
			
			return trySolve(currentTile.next(), nbToAdd);
	}
	
	
	/*
	 
	Ce que j'ai noté du tableau
	AjouterNombre(int[] sudoku, int nbToAdd, int casePresente){
		sudoku[casePresente] = nbToAdd;
		
		if (EstValide(sudoku)) {
			++casePresente;
			AjouterNombre(sudoku, nbToAdd, casePresente);
		} else {
			if (nbToAdd++ == 9) {
				sudoku[casePresente] = 0;
				return sudoku;
			}
		}
		...
	} 
	*/
	
}