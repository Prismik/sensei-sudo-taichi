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
	volatile private boolean solutionFound;
	
	public SudokuSolver() {
		this.solutionFound = false;
	}
	
	synchronized public void setFoundSolution(boolean foundIt) {
		this.solutionFound = foundIt;
	}
	
	public boolean solve(Sudoku sudoku) {
		return trySolve(sudoku.iterator());
	}
	
	protected boolean trySolve(ISudokuIterator currentTile) {
			if (currentTile == null)
				return true;
			
			boolean hasSolution = false;
			if (currentTile.value() != 0)
				hasSolution = trySolve(currentTile.next());
			else {
				for (int i = 1; i <= Sudoku.SIZE && !hasSolution; ++i) {
					if (currentTile.set(i)) {
						hasSolution = trySolve(currentTile.next());
					}
				}
				
				if (!hasSolution)
					currentTile.set(0);
			}
			
			return hasSolution;
	}	
}