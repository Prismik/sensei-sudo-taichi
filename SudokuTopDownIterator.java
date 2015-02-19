package sst;

import java.awt.Point;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class that iterate a sudoku from the top left to the bottom right
 */
public class SudokuTopDownIterator implements ISudokuIterator {
	private Sudoku sudoku;
	private int row;
	private int column;
	private boolean hasEnded;
	
	public SudokuTopDownIterator(Sudoku sudoku) {
		this.sudoku = new Sudoku(sudoku);
		this.row = 0;
		this.column = 0;
		this.hasEnded = false;
	}
	
	public SudokuTopDownIterator(SudokuTopDownIterator iter) {
		this.sudoku = new Sudoku(iter.sudoku);
		this.row = iter.row;
		this.column = iter.column;
		this.hasEnded = iter.hasEnded;
	}
	
	/**
	 * Return the next iterator and null if there is no more next
	 */
	public ISudokuIterator next() {
		SudokuTopDownIterator iter = new SudokuTopDownIterator(sudoku);
		
		if (this.row != Sudoku.SIZE - 1){
			iter.column = this.column;
			iter.row = this.row + 1;
		} 
		else {
			if (this.column == Sudoku.SIZE - 1) {
				iter = this;
				iter.hasEnded = true;
			}
			else {
				iter.column = this.column + 1;
				iter.row = 0;
			}
		}
		
		return iter;
	}
	
	public boolean end() {
		return this.hasEnded;
	}
	
	public ISudokuIterator getCopy() {
		return new SudokuTopDownIterator(this);
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