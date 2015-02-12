package sst;

import sst.Sudoku;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Main entry point of the program.
 */
public class Main {
	public static void main(String[] args) {
		if (args.length != 1)
			System.out.println("Usage: sst filename");
		else {
			String filename = args[0];
			Sudoku sudoku = new Sudoku(filename);
			Interval i = new Interval();
			SudokuSolver solver = new SudokuSolver();
			if (solver.solve(sudoku)) {
				i.stop();
				System.out.print(Long.toString(i.current()) + " milliseconds to solve the sudoku.");
			}
			else
				System.out.println("Aucune solution");

			sudoku.toHtml();
		}
	}
}