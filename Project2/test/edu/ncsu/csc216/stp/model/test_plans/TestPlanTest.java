package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the TestPlan class
 * 
 * @author William Morgan
 *
 */
class TestPlanTest {

	/**
	 * Tests the TestPlan() constructor
	 */
	@Test
	void testTestPlan() {
		TestPlan tp = assertDoesNotThrow(() -> new TestPlan("Test Plan Name"), "Should not throw exception");
		assertEquals("Test Plan Name", tp.getTestPlanName());
		assertEquals(0, tp.getTestCases().size());
		
		// test invalid name
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TestPlan(FailingTestList.FAILING_TEST_LIST_NAME));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new TestPlan("failing tests"));
		assertEquals("Invalid name.", e2.getMessage());
	}
	
	/**
	 * Tests the addTestCase() method
	 */
	@Test
	void testAddTestCase() {
		TestPlan tp = assertDoesNotThrow(() -> new TestPlan("Test Plan Name"), "Should not throw exception");
		tp.addTestCase(new TestCase("id", "type", "description", "expected results"));
		assertEquals(1, tp.getTestCases().size());
		assertEquals(tp, tp.getTestCase(0).getTestPlan());
		tp.addTestCase(new TestCase("id1", "type1", "description1", "expected results1"));
		assertEquals(2, tp.getTestCases().size());
		assertEquals(tp, tp.getTestCase(1).getTestPlan());
		// make sure it was added to the back
		assertEquals("id", tp.getTestCase(0).getTestCaseId());
		assertEquals("id1", tp.getTestCase(1).getTestCaseId());
	}

	/**
	 * Tests the getTestCasesAsArray() method
	 */
	@Test
	void testGetTestCasesAsArray() {
		String[][] expected = { { "id", "type", "FAIL" },
								{ "id1", "type1", "PASS" } }; 
		TestPlan tp = assertDoesNotThrow(() -> new TestPlan("Test Plan Name"), "Should not throw exception");
		tp.addTestCase(new TestCase("id", "type", "description", "expected results"));
		tp.addTestResult(0, false, "Actual results");
		tp.addTestCase(new TestCase("id1", "type1", "description1", "expected results1"));
		tp.addTestResult(1, true, "Actual results1");
		String[][] actual = tp.getTestCasesAsArray();
		assertAll("Checking that arrays are equal",
				() -> assertEquals(expected[0][0], actual[0][0]),
				() -> assertEquals(expected[0][1], actual[0][1]),
				() -> assertEquals(expected[0][2], actual[0][2]),
				() -> assertEquals(expected[1][0], actual[1][0]),
				() -> assertEquals(expected[1][1], actual[1][1]),
				() -> assertEquals(expected[1][2], actual[1][2]));
	}

	/**
	 * Tests the compareTo() method
	 */
	@Test
	void testCompareTo() {
		TestPlan tp = assertDoesNotThrow(() -> new TestPlan("Test Plan Name"), "Should not throw exception");
		TestPlan tp1 = assertDoesNotThrow(() -> new TestPlan("Test Plan Name"), "Should not throw exception");
		TestPlan tp2 = assertDoesNotThrow(() -> new TestPlan("Different Test Plan Name"), "Should not throw exception");
		TestPlan tp3 = assertDoesNotThrow(() -> new TestPlan("Test Plan Name with extra text"), "Should not throw exception");
		
		assertEquals(0, tp.compareTo(tp1));
		assertEquals(1, tp.compareTo(tp2));
		assertEquals(-1, tp2.compareTo(tp));
		assertEquals(-1, tp.compareTo(tp3));
		assertEquals(1, tp3.compareTo(tp));
		
	}

}
