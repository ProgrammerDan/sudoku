package com.programmerdan.euler;

/**
 * Abstract class base for all solver methods.
 * Includes some helpers for self-registration and singleton bootstrapping.
 *
 * @author Daniel Boston <programmerdan@gmail.com>
 * @version 1.0 April 2, 2013
 */
public abstract class SudokuSolverMethod {

	/**
	 * Helper method that registers a method with the solver.
	 */
	protected static void register() {
		SudokuSolver solver = SudokuSolver.getInstance();
		solver.addMethod(this);
	}

	/**
	 * Private instance Constructors should call this method.
	 * Calls the {@link setup()} method, then calls the
	 * {@link register()} method.
	 */
	private void init() {
		setup();
		register();
	}

	/**
	 * Any class-specific setup should happen here.
	 * Implementing classes should override this.
	 */
	private abstract void register();

	/**
	 * This is kept general, but a method should take in a puzzle
	 * and attempt to solve it. The method returns a puzzle, but
	 * an assumption is that the input puzzle is modified by the
	 * solve method.
	 *
	 * @param	puzzle	the {@link SudokuPuzzle} to solve.
	 * @return	a reference to the puzzle this method attempts to solve.
	 */
	public abstract SudokuPuzzle solve(SudokuPuzzle puzzle);
}