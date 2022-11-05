package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the TestResult class
 * 
 * @author William Morgan
 *
 */
class TestResultTest {

	/**
	 * Tests the TestResult() constructor
	 */
	@Test
	void testTestResult() {
		TestResult tr = new TestResult(false, "Actual Results");
		assertFalse(tr.isPassing());
		assertEquals("Actual Results", tr.getActualResults());
		
		// test invalid actual results
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TestResult(false, ""));
		assertEquals("Invalid test results.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new TestResult(false, null));
		assertEquals("Invalid test results.", e2.getMessage());
	}

	/**
	 * Tests the toString() method
	 */
	@Test
	void testToString() {
		TestResult tr = new TestResult(false, "Actual Results");
		assertEquals("FAIL: Actual Results", tr.toString());
		TestResult tr1 = new TestResult(true, "Actual Results");
		assertEquals("PASS: Actual Results", tr1.toString());
	}

}
