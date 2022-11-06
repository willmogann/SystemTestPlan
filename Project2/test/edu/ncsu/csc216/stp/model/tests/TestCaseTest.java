package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

/**
 * Tests the TestCase class
 * 
 * @author William Morgan
 *
 */
class TestCaseTest {
	
	/** test id */
	private static final String TEST_CASE_ID = "testcase1";
	
	/** test type */
	private static final String TEST_TYPE = "Equivalence Class";
	
	/** test description */
	private static final String TEST_DESCRIPTION = "Preconditions: None\n" 
												 + "1. Run GUI\n"
											     + "2. Check Results";
	
	/** expected results */
	private static final String EXPECTED_RESULTS = "GUI Opens to starter page";

	/**
	 * Tests the TestCase() constructor
	 */
	@Test
	void testTestCase() {
		TestCase tc = new TestCase(TEST_CASE_ID, TEST_TYPE, TEST_DESCRIPTION, EXPECTED_RESULTS);
		assertNull(tc.getTestPlan());
		assertEquals("", tc.getActualResultsLog());
		assertEquals(TEST_CASE_ID, tc.getTestCaseId());
		assertEquals(TEST_TYPE, tc.getTestType());
		assertEquals(TEST_DESCRIPTION, tc.getTestDescription());
		assertEquals(EXPECTED_RESULTS, tc.getExpectedResults());
		
		// test invalid cases
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new TestCase(null, TEST_TYPE, TEST_DESCRIPTION, EXPECTED_RESULTS));
		assertEquals("Invalid test information.", e1.getMessage());
	}

//	/**
//	 * Tests the getTestCaseId() method
//	 */
//	@Test
//	void testGetTestCaseId() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Tests the getTestType() method
//	 */
//	@Test
//	void testGetTestType() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Tests the getTestDescription() method
//	 */
//	@Test
//	void testGetTestDescription() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Tests the getExpectedResults() method
//	 */
//	@Test
//	void testGetExpectedResults() {
//		fail("Not yet implemented");
//	}

	/**
	 * Tests the addTestResult() method
	 */
	@Test
	void testAddTestResult() {
		TestCase tc = new TestCase(TEST_CASE_ID, TEST_TYPE, TEST_DESCRIPTION, EXPECTED_RESULTS);
		tc.addTestResult(true, "Actual Results Here");
		assertEquals("- PASS: Actual Results Here", tc.getActualResultsLog());
	}

	/**
	 * Tests the isTestCasePassing() method
	 */
	@Test
	void testIsTestCasePassing() {
		TestCase tc = new TestCase(TEST_CASE_ID, TEST_TYPE, TEST_DESCRIPTION, EXPECTED_RESULTS);
		assertFalse(tc.isTestCasePassing());
		tc.addTestResult(false, "Actual Results Here");
		assertFalse(tc.isTestCasePassing());
		tc.addTestResult(true, "Passing Results Here");
		assertTrue(tc.isTestCasePassing());
	}

	/**
	 * Tests the getStatus() method
	 */
	@Test
	void testGetStatus() {
		TestCase tc = new TestCase(TEST_CASE_ID, TEST_TYPE, TEST_DESCRIPTION, EXPECTED_RESULTS);
		assertEquals(TestResult.FAIL, tc.getStatus());
		tc.addTestResult(false, "Actual Results Here");
		assertEquals(TestResult.FAIL, tc.getStatus());
		tc.addTestResult(true, "Passing Results Here");
		assertEquals(TestResult.PASS, tc.getStatus());
	}

//	/**
//	 * Tests the getActualResultsLog() method
//	 */
//	@Test
//	void testGetActualResultsLog() {
//		fail("Not yet implemented");
//	}

	/**
	 * Tests the setTestPlan() method
	 */
	@Test
	void testSetTestPlan() {
		TestCase tc = new TestCase(TEST_CASE_ID, TEST_TYPE, TEST_DESCRIPTION, EXPECTED_RESULTS);
		TestPlan tp = new TestPlan("Title");
		tc.setTestPlan(tp);
		assertEquals(tp, tc.getTestPlan());
		// test invalid test plan
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> tc.setTestPlan(null));
		assertEquals("Invalid test plan.", e1.getMessage());
	}

//	/**
//	 * Tests the getTestPlan() method
//	 */
//	@Test
//	void testGetTestPlan() {
//		fail("Not yet implemented");
//	}

	/**
	 * Tests the toString() method
	 */
	@Test
	void testToString() {
		TestCase tc = new TestCase(TEST_CASE_ID, TEST_TYPE, TEST_DESCRIPTION, EXPECTED_RESULTS);
		tc.addTestResult(false, "Actual Results Here");
		tc.addTestResult(true, "Passing results");
		String expected = "# testcase1,Equivalence Class\n"
						+ "* Preconditions: None\n" 
						+ "1. Run GUI\n"
					    + "2. Check Results\n"
						+ "* GUI Opens to starter page\n"
					    + "- FAIL: Actual Results Here\n"
						+ "- PASS: Passing results";
		assertEquals(expected, tc.toString());
	}

}
