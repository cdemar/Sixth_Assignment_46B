package sudoku;

import java.util.*;

public class Grid {
	private int[][] values;

	/*
	 * DON'T CHANGE THIS.
	 * 
	 * Constructs a Grid instance from a string[] as provided by
	 * TestGridSupplier. See TestGridSupplier for examples of input. Dots in
	 * input strings represent 0s in values[][].
	 */
	public Grid(String[] rows) {
		values = new int[9][9];
		for (int j = 0; j < 9; j++) {
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i = 0; i < 9; i++) {
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}

	// DON'T CHANGE THIS.
	
	public String toString() {
		String s = "";
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char) ('0' + n);
			}
			s += "\n";
		}
		return s;
	}

	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. You'll call this 9 times in
	// next9Grids.

	Grid(Grid src) {
		values = new int[9][9];
		for (int j = 0; j < 9; j++)
			for (int i = 0; i < 9; i++)
				values[j][i] = src.values[j][i];
	}

	/*
	 * COMPLETE THIS
	 * 
	 * Finds an empty member of values[][]. Returns an array list of 9 grids
	 * that look like the current grid, except the empty member contains 1, 2, 3
	 * .... 9. Returns null if the current grid is full. Don't change "this"
	 * grid. Build 9 new grids.
	 * 
	 * Example: if this grid = 1........ ......... ......... ......... .........
	 * ......... ......... ......... .........
	 * 
	 * Then the returned array list would contain:
	 * 
	 * 11....... 12....... 13....... 14....... and so on 19....... .........
	 * ......... ......... ......... ......... ......... ......... .........
	 * ......... ......... ......... ......... ......... ......... .........
	 * ......... ......... ......... ......... ......... ......... .........
	 * ......... ......... ......... ......... ......... ......... .........
	 * ......... ......... ......... ......... ......... ......... .........
	 * ......... ......... ......... .........
	 */
	
	public ArrayList<Grid> next9Grids() {
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;

		// Find x,y of an empty cell.
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				int num = values[j][i];
				if (num == 0) {
					xOfNextEmptyCell = j;
					yOfNextEmptyCell = i;
					j=8; // The bug!
					break;
				}
			}
		}

//		 This is if using a while loop
	/*	
		int cell = values[xOfNextEmptyCell][yOfNextEmptyCell];
		while (cell != 0) {
			if (yOfNextEmptyCell == 8) {
				yOfNextEmptyCell = 0;
				xOfNextEmptyCell++;
			} else {
				yOfNextEmptyCell++;
			}
			cell = values[xOfNextEmptyCell][yOfNextEmptyCell];
		}
	*/
		
		// Construct array list to contain 9 new grids.
		//
		// Create 9 new grids as described in the comments above.
		// Add them to grids.
		
		ArrayList<Grid> grids = new ArrayList<Grid>();
		for (int n = 1; n <= 9; n++) {
			Grid newGrid = new Grid(this);
			newGrid.values[xOfNextEmptyCell][yOfNextEmptyCell] = n;
			grids.add(newGrid);
		}
		return grids;
	}

	// COMPLETE THIS
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.

	private boolean containsNonZeroRepeate(int[] ints) {
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++)
				if (ints[j] != 0 && j != i && ints[j] == ints[i])
					return true;
		}
		return false;
	}

	public boolean isLegal() {

		// Check every row. If you find an illegal row, return false.
		for (int j = 0; j < 9; j++) {
			int[] ints = new int[9];
			for (int i = 0; i < 9; i++)
				ints[i] = values[j][i];
			if (containsNonZeroRepeate(ints))
				return false;
		}

		// Check every column. If you find an illegal column, return false.
		for (int j = 0; j < 9; j++) {
			int[] ints = new int[9];
			for (int i = 0; i < 9; i++)
				ints[i] = values[i][j];
			if (containsNonZeroRepeate(ints))
				return false;
		}

		// Check every block. If you find an illegal block, return false.
		for (int j = 0; j < 9; j += 3) {
			for (int i = 0; i < 9; i += 3) {
				int[] ints = new int[9];
				int n = 0;
				for (int jj = j; jj < j + 3; jj++) {
					for (int ii = i; ii < i + 3; ii++) {
						ints[n++] = values[jj][ii];
					}
				}
				if (containsNonZeroRepeate(ints))
					return false;
			}
		}

		// All rows/cols/blocks are legal.
		return true;
	}

	// COMPLETE THIS
	// Returns true if every cell member of values[][] is a digit from 1-9.

	public boolean isFull() {
		for (int j = 0; j < 9; j++)
			for (int i = 0; i < 9; i++)
				if (values[j][i] == 0) {
					return false;
				}
		return true;
	}

	// COMPLETE THIS
	// Returns true if x is a Grid and, for every (i,j),
	// x.values[i][j] == this.values[i][j].

	public boolean equals(Object x) {
		Grid that = (Grid) x;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				if (this.values[i][j] != that.values[i][j]) {
					return false;
				}
			}
		return true;
	}
	
	public static void main(String[] args){
		System.out.println(TestGridSupplier.getReject1().next9Grids());
		}
}
