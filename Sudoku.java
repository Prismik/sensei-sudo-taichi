package sst;

import java.awt.Point;
import java.io.*;

/**
 * Author: Francis Beauchamp et William Brossard
 * Date: 15-02-07
 * Time: 6:00 PM
 * 
 * Class which represent a sudoku. It can read it from a file and fill the empty spaces.
 */
public class Sudoku {
	public static final int SIZE = 9;
	
	private int[][] board = new int[SIZE][SIZE];
	
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
					board[row][column] = Integer.parseInt("" + line.charAt(column));
				
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
	
	public boolean set(int row, int column, int value) {
		boolean isValid = isValid(row, column, value);
		
		if (isValid)
			board[row][column] = value;
			
		return isValid;
	}
	
	public int getTileValue(int row, int column) {
		return board[row][column];
	}
	
	/**
	 * Determines if, for a given position in the sudoku, a given number is valid.
	 * @param x: The vertical position.
	 * @param y: The horizontal position.
	 * @param n: The number for which we test the validity.
	 */
	public boolean isValid(int x, int y, int n) {
		if (x < 1 || x > 9 || y < 1 || y > 9 || n < 1 || n > 9)
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

	public ISudokuIterator iterator() {
		return new SudokuIterator(this);
	}
	
	@Override
	public String toString() {
		String sudoku = "";
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j)
				sudoku += board[i][j];
			
			sudoku += '\n';
		}
		
		return sudoku;
	}

	public void toHtml() {
		String out = "<html><head><style>body { background-color: whitesmoke; } " + 
									"table { border-collapse: collapse; font-family: Calibri, sans-serif; } " +
									"colgroup, tbody { border: solid 2px; } " +
									"td { background-color: #FFF985; border: solid 1px orange; height: 1.4em; " +
									"width: 1.4em; text-align: center; padding: 0; } " +
									"</style><meta http-equiv='content-type' content='text/html; charset=windows-1252'>" +
									"<title>Sudoku</title></head><body><center><table>" +
									"<caption>Sudoku</caption><colgroup><col><col><col><colgroup><col><col><col>" +
  								"<colgroup><col><col><col>";

		for (int i = 0; i < SIZE; ++i) {
			if (i % 3 == 0)
				out += "<tbody>";

			out += "<tr>";
			for (int j = 0; j < SIZE; ++j)
				out += "<td><div>" + board[i][j] + "</div></td>";
			
			if (i % 3 == 0)
				out += "</tbody>";

			out += "</tr>";
		}

		out += "</table></center></body></html>";
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("solve.htm"), "utf-8"));
			writer.write(out);
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public char getTileValue(int row, int column) {
		return board[row][column];
	}
}