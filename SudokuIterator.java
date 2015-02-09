package sst;

import java.awt.Point;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class that iterate a sudoku from the top left to the bottom right
 */
public class SudokuIterator implements ISudokuIterator {
	private Sudoku sudoku;
	private int row;
	private int column;
	
	public SudokuIterator(Sudoku sudoku) {
		this.sudoku = sudoku;
		row = 0;
		column = 0;
	}
	
	/**
	 * Return the next iterator and null if there is no more next
	 */
	public ISudokuIterator next() {
		SudokuIterator iter = new SudokuIterator(sudoku);
		
		if (this.column != Sudoku.SIZE - 1) 
			iter.column = this.column + 1;
		else {
			if (this.row == Sudoku.SIZE - 1)
				iter = null;
			else {
				iter.row = this.row + 1;
				iter.column = 0;
			}
		}
		
		return iter;
		
	}
	
	public int value() {
		return sudoku.getTileValue(row, column);
	}
	
	public Sudoku getSudoku() {
		return sudoku;
	}
	
	public boolean set(int value) {	
		return sudoku.set(row, column, value);
	}
}