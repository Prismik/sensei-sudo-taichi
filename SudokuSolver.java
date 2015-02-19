package sst;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class which can solve and fill a sudoku
 */
public class SudokuSolver {
	private Sudoku solution;
	private final Object solutionLock = new Object();
	
	public void setSolution(Sudoku solution) {
		synchronized(solutionLock) {
			
		}
	}
	
	public Sudoku getSolution() {
		synchronized(solutionLock) {
			return this.solution;
		}
	}
	
	public SudokuSolver() {
		solution = null;
	}
	
	public boolean solve(Sudoku sudoku) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		threads.add(new SudokuSolverThread(this, sudoku.iterator()));
		threads.add(new SudokuSolverThread(this, sudoku.reverseIterator()));
		
		boolean isAlive = true;
		while(getSolution() == null && isAlive) {
			isAlive = false;
			for (Thread th : threads)
				if (th.isAlive())
					isAlive = true;
		}
				
		for (Thread th : threads)
			if (th.isAlive())
				th.interrupt();
		
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