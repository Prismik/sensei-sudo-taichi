package sst;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class which represent a sudoku. It can read it from a file and fill the empty spaces.
 */
public class Sudoku {
	private final int SIZE = 9;
	
	private int[][] board = new int[SIZE][SIZE];
	
	public Sudoku(String filename) {
		deserialize(filename);
	}
	
	public void deserialize(String filename) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filename);
			
			int row = 0;
			int column = 0;
			int current;
			while ((current = fis.read()) != -1 || row == 9) {
				board[row][column] = current;
				
				if (column == 8) {
					column = 0;
					++row;
				}
			}
		} catch (IOException io) {
			System.out.println("File not found");
			io.printStackTrace();
		} finally {
			
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
}