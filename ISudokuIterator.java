package sst;

import java.awt.Point;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Interface for a sudoku iterator
 */
public interface ISudokuIterator {
	public ISudokuIterator next();
	public boolean end();
	public ISudokuIterator getCopy();
	public Sudoku getSudoku();
	public int value();
	public boolean set(int value);
}