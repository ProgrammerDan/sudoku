package com.programmerdan.euler;

/**
 * A "Box" in Sudoku is a 3x3 cell box. For a box to be full,
 *   every cell must contain a number. For a box to be valid, it
 *   must contain every number from 1 to 9. No numbers can be duplicated.
 *
 * @author Daniel Boston <programmerdan@gmail.com>
 * @version 1.0 April 2, 2013
 */
public class SudokuBox extends SudokuSet {
	/**
	 * Simple constructor for this box.
	 */
	public SudokuBox() {
		super();
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
			return super.setCell(y * 3 + x, cell);
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
			return super.getCell(y * 3 + x);
		} else {
			return null;
		}
	}
}