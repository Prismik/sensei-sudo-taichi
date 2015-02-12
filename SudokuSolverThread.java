package sst;

import java.awt.Point;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class which can solve and fill a sudoku
 */
public class SudokuSolverThread extends Thread {
	ISudokuIterator iter;
	SudokuSolver sudokuSolver;
	
	public SudokuSolverThread(SudokuSolver sudokuSolver, ISudokuIterator iter) {
		this.sudokuSolver = sudokuSolver;
		this.iter = iter;
	}
	
	public void run() {
		if (this.sudokuSolver.trySolve(iter))
			this.sudokuSolver.setFoundSolution(true);
	}
}