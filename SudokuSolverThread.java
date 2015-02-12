package sst;

import java.util.ArrayList;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class which can solve and fill a sudoku
 */
public class SudokuSolverThread extends Thread {
	SudokuSolver sudSol;
	ISudokuIterator iter;
	
	private ArrayList<Thread> threads;
	private final Object threadsLock = new Object();
	
	public SudokuSolverThread(SudokuSolver sudSol, ISudokuIterator iter) {
		this.sudSol = sudSol;
		this.iter = iter;
		this.threads = new ArrayList<Thread>();
	}

	public void run() {
		Sudoku foundSolution = null;
		if ((foundSolution = trySolve(iter)) != null)
			sudSol.setSolution(foundSolution);
	}
	
	private Sudoku trySolve(ISudokuIterator currentTile) {
		if (currentTile == null)
			return currentTile.getSudoku();
		
		Sudoku foundSolution = null;
		if (currentTile.value() != 0)
			foundSolution = trySolve(currentTile.next());
		else {
			for (int i = 1; i <= Sudoku.SIZE && foundSolution == null; ++i) {
				if (currentTile.set(i)) {
					if (sudSol.removeNbThreadAuthorized() != -1) {
						threads.add(new SudokuSolverThread(sudSol, currentTile.getCopy().next()));
					}
					else {
						foundSolution = trySolve(currentTile.next());
					}
				}
			}
			
			for (int i = 0; i < threads.size(); ++i) {
				try {
					threads.get(i).join();
				} catch (InterruptedException e) {
				}
				sudSol.addThreadAuthorized();
			}
		
			if (foundSolution == null)
				currentTile.set(0);
		}
		
		return foundSolution;
}	
}