package com.programmerdan.euler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Test for {@link SudokuCell}.
 *
 * @author Daniel Boston <programmerdan@gmail.com>
 * @version 1.0 April 4, 2013
 */
@RunWith(JUnit4.class)
public class SudokuCellTest {

	/**
	 * Logger for this class.
	 */
	private final Logger log = LoggerFactory.getLogger(SudokuCellTest.class);

	/**
	 * Tests the no-arg constructor.
	 */
	@Test
	public void basicConstructorTest() {
		log.info("*** Basic Constructor Test ***");

		log.trace("Creating cell");
		SudokuCell testCell = new SudokuCell();

		log.trace("Validating 0 value");
		assertEquals("failed - value not zero", 0, testCell.getValue().intValue() );

		Set<Integer> options = testCell.getOptions();

		assertNotNull("failed -- no options", options);

		log.trace("Validating full set of options.");
		assertThat("failed -- not all options represented", options, hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9));

		log.info("*** Basic Constructor Test Ended ***");
	}

	/**
	 * Tests the arg constructor. When a Cell is initialized to a value above 0,
	 *   it is considered as having no other options.
	 */
	@Test
	public void argConstructorTest() {
		log.info("*** Arg Constructor Test ***");

		log.trace("Run against every legal initialization value.");
		for (int i = 0; i < 10; i ++ ) {
			argConstructorSubtest(i);
		}

		log.info("*** Arg Constructor Test Ended ***");
	}

	/**
	 * Support method for the {@link argConstructorTest()}, just does the
	 *   work of testing a particular input number.
	 *
	 * @param	val		the value to initialize a {@link SudokuCell} to and test
	 * 					  the consequence.
	 */
	private void argConstructorSubtest(Integer val) {
		log.trace("Initializing to a value of " + val.toString() );
		SudokuCell testCell = new SudokuCell(val);

		log.trace("Testing cell value");
		assertEquals("failed - value not as given - " + val.toString(),
				val, testCell.getValue() );

		Set<Integer> options = testCell.getOptions();

		log.trace("Testing cells initialized");
		assertNotNull("failed - options not initialized", options);

		log.trace("Testing options list");
		if (val == 0) {
			assertThat("failed -- not all options represented", options, hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9));
		} else {
			assertEquals("failed - options not empty", 0, options.size());
		}
	}

}