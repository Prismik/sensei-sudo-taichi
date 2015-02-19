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
	
	public synchronized void setSolution(Sudoku solution) {
		synchronized(solutionLock) {
			this.solution = solution;
		}
	}
	
	public synchronized Sudoku getSolution() {
		synchronized(solutionLock) {
			return this.solution;
		}
	}
	
	public SudokuSolver() {
		solution = null;
	}
	
	public Sudoku solve(Sudoku sudoku, int threadNb) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		if (threadNb > 0)
			threads.add(new SudokuSolverThread(this, sudoku.iterator()));
		
		if (threadNb > 1)
			threads.add(new SudokuSolverThread(this, new Sudoku(sudoku).reverseIterator()));
		
		if (threadNb > 2)
			threads.add(new SudokuSolverThread(this, new Sudoku(sudoku).topDownIterator()));
		
		if (threadNb > 3)
			threads.add(new SudokuSolverReverseThread(this, new Sudoku(sudoku).iterator()));
		
		for (Thread th : threads)
			th.start();
		
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
		
		return getSolution();
	}
}