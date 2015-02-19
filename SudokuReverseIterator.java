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
	private boolean hasEnded;
	
	public SudokuReverseIterator(Sudoku sudoku) {
		this.sudoku = new Sudoku(sudoku);
		this.row = Sudoku.SIZE - 1;
		this.column = Sudoku.SIZE - 1;
		this.hasEnded = false;
	}
	
	public SudokuReverseIterator(SudokuReverseIterator iter) {
		this.sudoku = new Sudoku(iter.sudoku);
		row = iter.row;
		column = iter.column;
		this.hasEnded = iter.hasEnded;
	}
	
	/**
	 * Return the next iterator and null if there is no more next
	 */
	public ISudokuIterator next() {
		SudokuReverseIterator iter = new SudokuReverseIterator(sudoku);
		
		if (this.column != 0) {
			iter.column = this.column - 1;
			iter.row = this.row;
		} 
		else {
			if (this.row == 0) {
				iter = this;
				iter.hasEnded = true;
			}	
		else {
				iter.column = Sudoku.SIZE - 1;
				
				iter.row = this.row - 1;
			}
		}
		
		return iter;
		
	}
	
	public boolean end() {
		return this.hasEnded;
	}
	
	public ISudokuIterator getCopy() {
		return new SudokuReverseIterator(this);
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