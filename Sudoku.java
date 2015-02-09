package sst;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class which represent a sudoku. It can read it from a file and fill the empty spaces.
 */
public class Sudoku {
	public static final int SIZE = 9;
	
	private char[][] board = new char[SIZE][SIZE];
	
	public Sudoku(String filename) {
		deserialize(filename);
	}
	
	public void deserialize(String filename) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			
			int row = 0;
			String line;
			while ((line = reader.readLine()) != null && row != 9) {
				
				for (int column = 0; column < SIZE; ++column)
					board[row][column] = line.charAt(column);
				
				++row;
			}
		} catch (IOException io) {
			System.out.println("File not found");
			io.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Determines if, for a given position in the sudoku, a given number is valid.
	 * @param x: The vertical position.
	 * @param y: The horizontal position.
	 * @param n: The number for which we test the validity.
	 */
	public boolean isValid(int x, int y, int n) {
		if (n < 1 || n > 9)
			return false;

		for (int i = 0; i != SIZE; ++i) {
			if (board[i][y] == n)
				return false;

			if (board[x][i] == n)
				return false;

			if (board[x/3 * 3 + i/3][y/3 * 3 + i/3] == n)
				return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String sudoku = new String();
		
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j)
				sudoku += board[i][j];
			
			sudoku += '\n';
		}
		
		return sudoku;
	}
	
	public char getTileValue(int row, int column) {
		return board[row][column];
	}
}