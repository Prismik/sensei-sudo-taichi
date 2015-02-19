package sst;

import java.util.ArrayList;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class which can solve and fill a sudoku
 */
public class SudokuSolverReverseThread extends Thread {
	SudokuSolver sudSol;
	ISudokuIterator iter;
	
	public SudokuSolverReverseThread(SudokuSolver sudSol, ISudokuIterator iter) {
		this.sudSol = sudSol;
		this.iter = iter;
	}

	public void run() {
		trySolve(iter);
	}
	
	private Sudoku trySolve(ISudokuIterator currentTile) {
		if (currentTile.end()) {
			sudSol.setSolution(currentTile.getSudoku());
			return currentTile.getSudoku();
		}
		
		Sudoku foundSolution = null;
		if (currentTile.value() != 0)
			foundSolution = trySolve(currentTile.next());
		else {
			for (int i = Sudoku.SIZE; i >= 1  && foundSolution == null; --i) {
				if (currentTile.set(i)) {
						foundSolution = trySolve(currentTile.next());
				}
			}
		
			if (foundSolution == null)
				currentTile.set(0);
		}
		
		return foundSolution;
	}	
}