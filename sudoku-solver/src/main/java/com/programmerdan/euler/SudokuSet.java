package com.programmerdan.euler;

import java.util.TreeSet;

/**
 * At the heart of a Sudoku Puzzle is a 9-cell construct
 *   I am calling a "set". Traditionally there are rows, columns,
 *   and boxes. Since the only difference in their validation is
 *   orientation, I'm considering them as subclasses of this, with
 *   unique functionality in the instances.
 */
public abstract class SudokuSet {

	private SudokuCell[] cellMap;

	/**
	 * Simple constructor for this box.
	 */
	protected SudokuSet() {
		init();
	}

	/**
	 * Initializes the cellMap inside this box.
	 */
	private void init() {
		cellMap = new SudokuCell[9];
	}

	/**
	 * Sets a cell in this set to be a {@link SudokuCell}.
	 *
	 * @param	i		Which cell to set (between 0, 8)
	 * @param	cell	The {@link SudokuCell} to assign to this spot.
	 * @return	true if successful, false if not.
	 */
	public boolean setCell(int i, SudokuCell cell) {
		if (i >= 0 && i <= 8 && cell != null) {
			cellMap[i] = cell;
			return true;
		}

		return false;
	}

	/**
	 * Returns a {@link SudokuCell} from the set.
	 *
	 * @param	i	Which cell to get (between 0, 8)
	 *
	 * @return	the {@SudokuCell} referenced, or null.
	 */
	public SudokuCell getCell(int i) {
		if (i >= 0 && i <= 8) {
			return cellMap[i];
		} else {
			return null;
		}
	}

	/**
	 * Validator method making sure this "set" is full of cells.
	 *
	 * @return	true if all cells have a {@link SudokuCell}, false otherwise.
	 */
	public boolean isFullWithCells() {
		for (int i = 0; i < 9; i++) {
			if ( cellMap[i] == null ) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Validator method making sure this "set" is full of cells that have a number other
	 *   than 0.
	 *
	 * @return	true if all cells have a {@link SudokuCell} with a value > 0, false otherwise.
	 */
	public boolean isFull() {
		for (int i = 0; i < 9; i++) {
			if ( cellMap[i] == null || cellMap[i].getValue() == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Simple validator for this "set". Basically, it puts
	 * all the values of the cells into a TreeSet. It then
	 * iterates over the set in sorted order, making sure that
	 * the distance between each element is 1. If this is true,
	 * the "set" is valid.
	 *
	 * @return	true if the set holds all number 1-9, with no duplicates
	 *            and no gaps.
	 */
	public boolean isValid() {
		TreeSet<Integer> values = new TreeSet<Integer>();
		for (int i = 0; i < 9; i++) {
			if (cellMap[i] != null) {
				if (cellMap[i].getValue() == 0 ||
						values.contains(cellMap[i].getValue()) ) {
					return false; // the value is undefined or we already have this value.
				} else {
					values.add( cellMap[i].getValue() );
				}
			} else {
				return false;
			}
		}

		if (values.size() != 9) {
			return false; // should have 9 unique numbers
		}

		Integer prior = 0;
		for (Integer value : values ) {
			if (value - prior != 1) {
				return false; // we're missing something.
			} else {
				prior = value; // okay so far.
			}
		}

		return true;
	}
}