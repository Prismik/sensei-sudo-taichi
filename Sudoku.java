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
	 * @param x: The horizontal position.
	 * @param y: The vertical position.
	 * @param n: The number for which we test the validity.
	 */
	public boolean isValid(int x, int y, int n) {
		if (x < 0 || x > 8 || y < 0 || y > 8 || n < 0 || n > 8)
			return false;

		for (int i = 0; i != SIZE; ++i) {
			if (board[i][y] == n)
				return false;

			if (board[x][i] == n)
				return false;

			if (board[x/3 * 3 + i/3][y/3 * 3 + i%3] == n)
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
		String out = "<html><head><style>body { background-color: #292700; } " + 
									"table { border-collapse: collapse; font-family: Calibri, sans-serif; } " +
									"colgroup, tbody { border: solid 2px #292700; } " +
									"td { background-color: #FFF985; border: solid 1px #E0D500; height: 2.4em; " +
									"width: 2.4em; text-align: center; padding: 0; } " +
									"caption { color: white; font-weight: bold; }" +
									"</style><meta http-equiv='content-type' content='text/html; charset=windows-1252'>" +
									"<title>Sudoku</title></head><body><center><table>" +
									"<caption>Sudoku</caption><colgroup><col><col><col><colgroup><col><col><col>" +
  								"<colgroup><col><col><col>";

  	int rows = 0;
		for (int i = 0; i < SIZE; ++i) {
			if (i % 3 == 0)
				out += "<tbody>";

			out += "<tr>";
			for (int j = 0; j < SIZE; ++j) {
				String tile = board[i][j] == 0 ? "" : Integer.toString(board[i][j]);
				out += "<td><div>" + tile + "</div></td>";
			}

			out += "</tr>";
		 	++rows;
			if (rows == 3) {
				out += "</tbody>";
				rows = 0;
			}
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
}