package com.programmerdan.euler;

import java.util.ArrayList;


/**
 * Problem 96 of Project Euler, a Sudoku puzzle solver.
 * This is the central solver. Methods to solve a puzzle plug in here
 *   and are used by the Solver against Puzzles.
 *
 * @author Daniel Boston <programmerdan@gmail.com>
 * @version 1.0 April 2, 2013
 */
public class SudokuSolver {

	/**
	 * Singleton.
	 */
	private static SudokuSolver instance = new SudokuSolver();

	/**
	 * Get the singleton instance of this solver.
	 *
	 * @return	the {@link SudokuSolver} singleton.
	 */
	public static SudokuSolver getSolver() {
		return instance;
	}

	/**
	 * A list of methods to be used in solving puzzles.
	 * Solvers are applied in a sequence, and the same
	 * solver can be run more than once.
	 */
	private ArrayList<SudokuSolverMethod> methods;

	/**
	 * Add a method to the solver for use in solving puzzles.
	 *
	 * @param	method	a solver method to add.
	 * @return	true if the method was added, false otherwise.
	 */
	public boolean addMethod(SudokuSolverMethod method) {
		if (method != null) {
			return methods.add(method);
		} else {
			return false;
		}
	}

	/**
	 * Private default constructor, sets things up.
	 */
	private SudokuSolver() {
		methods = new ArrayList<SudokuSolverMethod>();
	}

	/**
	 * Attempt to solve a puzzle, using the current set of methods.
	 * If at any point, a method solves the puzzle, solve ends early.
	 * Otherwise, at the very end of every method, if solved, returns true,
	 *  else false.
	 *
	 * @param	puzzle	The puzzle to solve
	 * @return	true if solution succeeded, false otherwise.
	 */
	public boolean solve(SudokuPuzzle puzzle) {
		for (SudokuSolverMethod method : methods) {
			methods.solve(puzzle);

			if (puzzle.isComplete()) {
				return true;
			}
		}

		return puzzle.isComplete();
	}

}