package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SortedList;

import org.junit.jupiter.api.Test;

/**
 * Tests the TestPlanReader class
 * 
 * @author William Morgan
 *
 */
class TestPlanReaderTest {

	/**
	 * Tests the readTestPlansFile() method
	 */
	@Test
	void testReadTestPlansFile() {
		// create objects for first test plan
		TestPlan wolfScheduler = new TestPlan("WolfScheduler");
		TestCase test1 = 
				new TestCase("test1", "Equivalence Class", "description\nwith multiple lines", "expected results\nwith multiple lines");
		test1.addTestResult(true, "actual results");
		test1.addTestResult(false, "actual results on\nmultiple lines");
		test1.addTestResult(true, "actual results\non three\nlines");
		wolfScheduler.addTestCase(test1);
		TestCase test2 = 
				new TestCase("test2", "Boundary Value", "description", "expected results");
		wolfScheduler.addTestCase(test2);
		TestCase test3 = 
				new TestCase("test3", "Requirements", "description\non multiple lines", "expected results");
		test3.addTestResult(false, "actual results");
		wolfScheduler.addTestCase(test3);
		
		// second test plan
		TestPlan packScheduler = new TestPlan("PackScheduler");
		TestCase test0a = 
				new TestCase("test0", "Invalid", "description", "expected results\nwith multiple lines");
		test0a.addTestResult(true, "actual results");
		test0a.addTestResult(false, "actual results");
		packScheduler.addTestCase(test0a);
		TestCase test1a =
				new TestCase("test1", "Equivalence Class", "description", "expected results");
		test1a.addTestResult(true, "actual results");
		packScheduler.addTestCase(test1a);
		
		// create expected list and add test plans
		ISortedList<TestPlan> expected = new SortedList<>();
		expected.add(wolfScheduler);
		expected.add(packScheduler);
		
		// create actual list
		ISortedList<TestPlan> actual = TestPlanReader.readTestPlansFile(new File("test-files/test-plans0.txt"));
		
		// check project list properties
		assertEquals(expected.size(), actual.size());
		assertAll("Check that project names are the same",
				() -> assertEquals(expected.get(0).getTestPlanName(), actual.get(0).getTestPlanName()),
				() -> assertEquals(expected.get(1).getTestPlanName(), actual.get(1).getTestPlanName()));
		
		// check lists of test cases
		ISwapList<TestCase> expectedCases1 = expected.get(0).getTestCases();
		ISwapList<TestCase> actualCases1 = actual.get(0).getTestCases();
		assertEquals(expectedCases1.size(), actualCases1.size());
		assertAll("Checking fields for case 1 in first project",
				() -> assertEquals(expectedCases1.get(0).getTestCaseId(), actualCases1.get(0).getTestCaseId()),
				() -> assertEquals(expectedCases1.get(0).getTestDescription(), actualCases1.get(0).getTestDescription()),
				() -> assertEquals(expectedCases1.get(0).getExpectedResults(), actualCases1.get(0).getExpectedResults()),
				() -> assertEquals(expectedCases1.get(0).getActualResultsLog(), actualCases1.get(0).getActualResultsLog()));
		
		ISwapList<TestCase> expectedCases2 = expected.get(1).getTestCases();
		ISwapList<TestCase> actualCases2 = actual.get(1).getTestCases();
		assertEquals(expectedCases2.size(), actualCases2.size());
		assertAll("Checking fields for case 1 in second project",
				() -> assertEquals(expectedCases2.get(0).getTestCaseId(), actualCases2.get(0).getTestCaseId()),
				() -> assertEquals(expectedCases2.get(0).getTestDescription(), actualCases2.get(0).getTestDescription()),
				() -> assertEquals(expectedCases2.get(0).getExpectedResults(), actualCases2.get(0).getExpectedResults()),
				() -> assertEquals(expectedCases2.get(0).getActualResultsLog(), actualCases2.get(0).getActualResultsLog()));
	}
	
	/**
	 * Test read of invalid file
	 */
	@Test
	void testReadInvalidFile() {
		// IAE should be thrown here
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> TestPlanReader.readTestPlansFile(new File("test-files/test-plans3.txt")));
		assertEquals("Unable to load file.", e1.getMessage());
		
		// test-plans8 file, should create a test plan with no cases
		ISortedList<TestPlan> expected = new SortedList<>();
		expected.add(new TestPlan("WolfScheduler"));
		ISortedList<TestPlan> actual = TestPlanReader.readTestPlansFile(new File("test-files/test-plans8.txt"));
		assertEquals(1, actual.size());
		assertEquals(0, actual.get(0).getTestCases().size());
		assertEquals(expected.get(0).getTestPlanName(), actual.get(0).getTestPlanName());
	}
}
