package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the FailingTestList class
 * 
 * @author William Morgan
 *
 */
class FailingTestListTest {
	
	/**
	 * Tests the failingTestList() constructor
	 */
	@Test
	void testFailingTestList() {
		FailingTestList ft = new FailingTestList();
		assertEquals(FailingTestList.FAILING_TEST_LIST_NAME, ft.getTestPlanName());
		assertEquals(0, ft.getTestCases().size());
	}

	/**
	 * Tests the setTestPlanName() method
	 */
	@Test
	void testSetTestPlanName() {
		FailingTestList ft = new FailingTestList();
		// test invalid name to set
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> ft.setTestPlanName("Name"));
		assertEquals("The Failing Tests list cannot be edited.", e1.getMessage());
	}

	/**
	 * Tests the addTestCase() method
	 */
	@Test
	void testAddTestCase() {
		FailingTestList ft = new FailingTestList();
		// test add passing test - should throw IAE
		TestCase testCase = new TestCase("id", "type", "description", "expected results");
		testCase.addTestResult(true, "Actual result");
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> ft.addTestCase(testCase));
		assertEquals("Cannot add passing test case.", e1.getMessage());
		
		// test valid test case
		TestCase testCase1 = new TestCase("id", "type", "description", "expected results");
		testCase1.addTestResult(false, "Actual result");
		ft.addTestCase(testCase1);
		assertEquals(1, ft.getTestCases().size());
		assertEquals("id", ft.getTestCases().get(0).getTestCaseId());
	}

	/**
	 * Tests the getTestCasesAsArray() method
	 */
	@Test
	void testGetTestCasesAsArray() {
		String[][] expected = { { "id", "type", "Test Plan 1" },
								{ "id1", "type1", "" } }; 
		
		// construct test cases
		TestPlan tp = new TestPlan("Test Plan 1");
		tp.addTestCase(new TestCase("id", "type", "description", "expected results"));
		tp.addTestResult(0, false, "Actual results");
		TestCase tc = new TestCase("id1", "type1", "description1", "expected results1");
		tc.addTestResult(false, "Actual results1");
		
		FailingTestList ft = new FailingTestList();
		ft.addTestCase(tp.getTestCase(0));
		ft.addTestCase(tc);
		
		String[][] actual = ft.getTestCasesAsArray();
	
		assertAll("Checking that arrays are equal",
				() -> assertEquals(expected[0][0], actual[0][0]),
				() -> assertEquals(expected[0][1], actual[0][1]),
				() -> assertEquals(expected[0][2], actual[0][2]),
				() -> assertEquals(expected[1][0], actual[1][0]),
				() -> assertEquals(expected[1][1], actual[1][1]),
				() -> assertEquals(expected[1][2], actual[1][2]));
	}

	/**
	 * Tests the clearTests() method
	 */
	@Test
	void testClearTests() {
		FailingTestList ft = new FailingTestList();
		TestCase tc = new TestCase("id1", "type1", "description1", "expected results1");
		tc.addTestResult(false, "Actual results1");
		ft.addTestCase(tc);
		TestCase tc1 = new TestCase("id1", "type1", "description1", "expected results1");
		tc1.addTestResult(false, "Actual results1");
		ft.addTestCase(tc1);
		assertEquals(2, ft.getTestCases().size());
		ft.clearTests();
		assertEquals(0, ft.getTestCases().size());
	}

}
