package sst;

import java.awt.Point;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class that iterate a sudoku from the bottom right to the top left
 */
public class SudokuReverseIterator implements ISudokuIterator {
	private Sudoku sudoku;
	private int row;
	private int column;
	
	public SudokuReverseIterator(Sudoku sudoku) {
		this.sudoku = sudoku;
		row = Sudoku.SIZE - 1;
		column = Sudoku.SIZE - 1;
	}
	
	/**
	 * Return the next iterator and null if there is no more next
	 */
	public ISudokuIterator next() {
		SudokuReverseIterator iter = new SudokuReverseIterator(sudoku);
		
		if (this.column != 0) 
			iter.column = this.column - 1;
		else {
			if (this.row == 0)
				iter = null;
			else {
				iter.column = Sudoku.SIZE - 1;
				
				iter.row = this.row - 1;
			}
		}
		
		return iter;
		
	}
	
	public char value() {
		return sudoku.getTileValue(row, column);
	}
}