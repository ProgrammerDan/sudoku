package com.programmerdan.euler;

import java.util.TreeSet;

/**
 * A "Box" in Sudoku is a 3x3 cell box. For a box to be full,
 *   every cell must contain a number. For a box to be valid, it
 *   must contain every number from 1 to 9. No numbers can be duplicated.
 *
 * @author Daniel Boston <programmerdan@gmail.com>
 * @version 1.0 April 2, 2013
 */
public class SudokuBox {
	private SudokuCell[][] cellMap;

	/**
	 * Simple constructor for this box.
	 */
	public SudokuBox() {
		init();
	}

	/**
	 * Initializes the cellMap inside this box.
	 */
	private void init() {
		cellMap = new SudokuCell[3][3];
	}

	/**
	 * Sets a cell in this box to be a {@link SudokuCell}.
	 *
	 * @param	x		Horizontal alignment (which column)
	 * @param	y		Vertical alignment (which row)
	 * @param	cell	The {@link SudokuCell} to assign to this spot.
	 * @return	true if successful, false if not.
	 */
	public boolean setCell(int x, int y, SudokuCell cell) {
		if (x >= 0 && x <= 2 && y >= 0 && y <= 2 && cell != null) {
			cellMap[x][y] = cell;
			return true;
		}

		return false;
	}

	/**
	 * Returns a {@link SudokuCell} from the box.
	 *
	 * @param	x	Horizontal alignment (which column)
	 * @param	y	Vertical alignment (which row)
	 *
	 * @return	the {@SudokuCell} referenced, or null.
	 */
	public SudokuCell getCell(int x, int y) {
		if (x >= 0 && x <= 2 && y >= 0 && y <= 2) {
			return cellMap[x][y];
		} else {
			return null;
		}
	}

	/**
	 * Validator method making sure this "box" is full of cells.
	 *
	 * @return	true if all cells have a {@link SudokuCell}, false otherwise.
	 */
	public boolean isFullWithCells() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if ( cellMap[x][y] == null ) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Validator method making sure this "box" is full of cells that have a number other
	 *   than 0.
	 *
	 * @return	true if all cells have a {@link SudokuCell} with a value > 0, false otherwise.
	 */
	public boolean isFull() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if ( cellMap[x][y] == null || cellMap[x][y].getValue() == 0) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Simple validator for this "box". Basically, it puts
	 * all the values of the cells into a set. It then
	 * iterates over the set in sorted order, making sure that
	 * the distance between each element is 1. If this is true,
	 * the "Box" is valid.
	 *
	 * @return	true if the box holds all number 1-9, with no duplicates
	 *            and no gaps.
	 */
	public boolean isValid() {
		TreeSet<Integer> values = new TreeSet<Integer>();
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (cellMap[x][y] != null) {
					if (cellMap[x][y].getValue() == 0 ||
							values.contains(cellMap[x][y].getValue()) ) {
						return false; // the value is undefined or we already have this value.
					} else {
						values.add( cellMap[x][y].getValue() );
					}
				} else {
					return false;
				}
			}
		}

		if (values.size() != 9) {
			return false; // should have 9 unique numbers
		}

		Integer prior = 0;
		for (Integer value : values.iterator() ) {
			if (value - prior != 1) {
				return false; // we're missing something.
			} else {
				prior = value; // okay so far.
			}
		}

		return true;
	}
}