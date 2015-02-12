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
	
	private int nbThreadAuthorized;
	private final Object nbThreadAuthorizedLock = new Object();
	
	public SudokuSolver(int nbThreads) {
		this.solution = null;
		this.nbThreadAuthorized = nbThreads;
	}
	
	public void setSolution(Sudoku sudoku) {
		synchronized(solutionLock) {
			this.solution = sudoku;
		}
	}
	
	public Sudoku getSolution() {
		synchronized(solutionLock) {
			return this.solution;
		}
	}
	
	public int addThreadAuthorized() {
		synchronized(nbThreadAuthorizedLock) {
			return ++this.nbThreadAuthorized;
		}
	}
	
	public int removeNbThreadAuthorized() {
		synchronized(nbThreadAuthorizedLock) {
			return (this.nbThreadAuthorized > 0) ? --this.nbThreadAuthorized
																						 : -1;
		}
	}
	
	public Sudoku solve(Sudoku sudoku) {
		SudokuSolverThread ss = new SudokuSolverThread(this, new SudokuIterator(sudoku));
		
		ss.start();
		
		while(getSolution() == null && ss.isAlive());
		
		if (ss.isAlive())
			ss.interrupt();
		
		return this.solution;
	}
}