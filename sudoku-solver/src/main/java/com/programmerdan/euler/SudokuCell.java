package com.programmerdan.euler;

import java.util.Set;
import java.util.TreeSet;

/**
 * A cell in a {@link SudokuPuzzle}. It can be uninitialized (value of 0,
 *   all other numbers 1-9 possible options), or initialized (specific value,
 *   no other number possible).
 *
 * @author Daniel Boston <programmerdan@gmail.com>
 * @version 1.0 April 3, 2012
 */
public class SudokuCell {
	private Integer value;
	private Set<Integer> options;

	/**
	 * Set up a cell with no value (0).
	 */
	public SudokuCell() {
		setup(0);
	}

	/**
	 * Set up a cell with the specified value (0-9).
	 * If 0, all numbers 1-9 are added as options. Else,
	 * the value is set, and no other numbers are options.
	 *
	 * @param	value	The initializing value of this cell.
	 */
	public SudokuCell(Integer value) {
		setup(value);
	}

	/**
	 * Sets up this cell based on the incoming value. If 0,
	 *   all other numbers are options. Else, just the passed
	 *   number is set as value, and nothing else is an option.
	 *
	 * @param	value	The initializing value of this cell.
	 */
	private void setup(Integer value) {
		if (value < 0 || value > 9) { // error state!
			return; // TODO: logger.
		}

		this.value = value;
		options = new TreeSet<Integer>();
		if (value == 0) {
			for (int option = 1; option <= 9; option++) {
				options.add(option);
			}
		}
	}

	/**
	 * Gets the current set of options available to this cell.
	 *
	 * @return	The set of Integer options.
	 */
	public Set<Integer> getOptions() {
		return options;
	}

	/**
	 * Tests if a particular integer is still an option for this cell.
	 *
	 * @param	option	The integer to test if its an option.
	 * @return	True if the integer is an option, false otherwise.
	 */
	public boolean hasOption(Integer option) {
		if (option != null && options.contains(option)) {
			return true;
		}

		return false;
	}

	/**
	 * Remove an option for this cell.
	 *
	 * @param	option	The integer option to remove.
	 * @return	True if the option is contained and was removed, false otherwise.
	 */
	public boolean removeOption(Integer option) {
		if (option != null && options.contains(option)) {
			return options.remove(option);
		} else {
			return false;
		}
	}

	/**
	 * Add an option for this cell.
	 *
	 * @param	option	The integer option to add.
	 * @return	True if added as an option, false otherwise.
	 */
	public boolean addOption(Integer option) {
		if (option != null && options > 0 && options <9 && !options.contains(option) ) {
			return options.add(option);
		}
		return false;
	}

	/**
	 * Get the value of this cell.
	 *
	 * @return value.
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * Set the value of this cell.
	 *   Can only set the cell to a value in the list of options.
	 *
	 * @param	value	The value to set this cell to.
	 * @return	True if successfully set the value. False otherwise.
	 */
	public boolean setValue(Integer value) {
		if (value != null && value > 0 && value <= 9) {
			if (hasOption(value)) {
				this.value = value;
				return true;
			}
		}
		return false;
	}

	}
}