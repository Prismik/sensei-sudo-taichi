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
	public static boolean solve(Sudoku sudoku) {
		return trySolve(sudoku.iterator());
	}
	
	private static boolean trySolve(ISudokuIterator currentTile) {
			if (currentTile == null)
				return true;
		  
			
			boolean hasSolution = false;
			// Si la case actuelle est libre, on essaie d'y mettre un chiffre.
			if (currentTile.value() != 0)
				hasSolution = trySolve(currentTile.next());
			else {
				int i;
				for (i = 1; i <= Sudoku.SIZE && !hasSolution; ++i) {
					if (currentTile.set(i))
						hasSolution = trySolve(currentTile.next());
				}
			}
			
			return hasSolution;
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