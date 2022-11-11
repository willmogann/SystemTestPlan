package edu.ncsu.csc216.stp.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
//import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
//import edu.ncsu.csc216.stp.model.util.ISortedList;
//import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests the TestPlanManager class
 * 
 * @author William Morgan
 *
 */
class TestPlanManagerTest {
	
	/** file path for actual file */
	private String actFileName = "test-files/actual_out.txt";
	
	/** file path for expected file */
	private String expFileName = "test-files/expected_out.txt";

	/**
	 * Tests the TestPlanManager constructor
	 */
	@Test
	void testTestPlanManager() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		assertEquals(FailingTestList.FAILING_TEST_LIST_NAME, tpm.getCurrentTestPlan().getTestPlanName());
		assertFalse(tpm.isChanged());
	}

	/**
	 * Tests the loadTestPlans() method
	 */
	@Test
	void testLoadTestPlans() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		tpm.loadTestPlans(new File(expFileName));
		
		assertEquals(2, tpm.getTestPlanNames().length);
		assertEquals("TestPlan1", tpm.getTestPlanNames()[0]);
		assertEquals("TestPlan2", tpm.getTestPlanNames()[1]);
		// check that failing tests list is the one set to current
		assertEquals(2, tpm.getCurrentTestPlan().getTestCases().size());
	}

	/**
	 * Tests the saveTestPlans() method
	 */
	@Test
	void testSaveTestPlans() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		tpm.addTestPlan("TestPlan1");
		tpm.addTestCase(new TestCase("ID 0", "type 0", "description 0", "expected results 0"));
		tpm.addTestResult(0, false, "actual results 0-1");
		tpm.addTestCase(new TestCase("ID 1", "type 1", "description 1", "expected results 1"));
		tpm.addTestResult(1, true, "actual results 1-1");
		tpm.addTestResult(1, false, "actual results 1-2");
		tpm.addTestCase(new TestCase("ID 2", "type 2", "description 2", "expected results 2"));
		tpm.addTestResult(1, true, "actual results 2-1");
		tpm.addTestResult(1, true, "actual results 2-2");
		tpm.addTestPlan("TestPlan2");
//		TestPlan testPlan1 = new TestPlan("TestPlan1");
//		TestCase tc0 = new TestCase("ID 0", "type 0", "description 0", "expected results 0");
//		tc0.addTestResult(false, "actual results 0-1");
//		testPlan1.addTestCase(tc0);
//		TestCase tc1 = new TestCase("ID 1", "type 1", "description 1", "expected results 1");
//		tc1.addTestResult(true, "actual results 1-1");
//		tc1.addTestResult(false, "actual results 1-2");
//		testPlan1.addTestCase(tc1);
//		TestCase tc2 = new TestCase("ID 2", "type 2", "description 2", "expected results 2");
//		tc2.addTestResult(true, "actual results 2-1");
//		tc2.addTestResult(true, "actual results 2-2");
//		testPlan1.addTestCase(tc2);
//		TestPlan testPlan2 = new TestPlan("TestPlan2");
		
		assertEquals("TestPlan2", tpm.getCurrentTestPlan().getTestPlanName());
		assertDoesNotThrow(() -> tpm.saveTestPlans(new File(actFileName)));
		checkFiles(expFileName, actFileName);
	}
	
	/**
     * Helper method to compare two files for the same contents
     * 
     * @param expFile expected output
     * @param actFile actual output
     */
    private void checkFiles(String expFile, String actFile) {
        try (Scanner expScanner = new Scanner(new File(expFile));
                Scanner actScanner = new Scanner(new File(actFile));) {

            while (expScanner.hasNextLine()) {
                assertEquals(expScanner.nextLine(), actScanner.nextLine());
            }

            expScanner.close();
            actScanner.close();
        } catch (IOException e) {
            fail("Error reading files.");
        }
    }

	/**
	 * Tests the isChanged() method
	 */
	@Test
	void testIsChanged() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		assertFalse(tpm.isChanged());
		tpm.addTestPlan("TestPlan1");
		assertTrue(tpm.isChanged());
	}

	/**
	 * Tests the addTestPlan() method
	 */
	@Test
	void testAddTestPlan() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		assertEquals(0, tpm.getTestPlanNames().length);
		tpm.addTestPlan("TestPlan1");
		assertEquals(1, tpm.getTestPlanNames().length);
		assertEquals("TestPlan1", tpm.getCurrentTestPlan().getTestPlanName());
	}

	/**
	 * Tests the getTestPlanNames() method
	 */
	@Test
	void testGetTestPlanNames() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		assertEquals(0, tpm.getTestPlanNames().length);
		tpm.addTestPlan("TestPlan1");
		assertEquals(1, tpm.getTestPlanNames().length);
		assertEquals("TestPlan1", tpm.getTestPlanNames()[0]);
	}

	/**
	 * Tests the setCurrentTestPlan() method
	 */
	@Test
	void testSetCurrentTestPlan() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		tpm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tpm.getCurrentTestPlan().getTestPlanName());
		tpm.addTestPlan("TestPlan2");
		assertEquals("TestPlan2", tpm.getCurrentTestPlan().getTestPlanName());
		tpm.setCurrentTestPlan("TestPlan1");
		assertEquals("TestPlan1", tpm.getCurrentTestPlan().getTestPlanName());
		
	}

	/**
	 * Tests the getCurrentTestPlan() method
	 */
	@Test
	void testGetCurrentTestPlan() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		assertEquals(FailingTestList.FAILING_TEST_LIST_NAME, tpm.getCurrentTestPlan().getTestPlanName());
		tpm.addTestPlan("TestPlan1");
		assertEquals("TestPlan1", tpm.getCurrentTestPlan().getTestPlanName());
	}

	/**
	 * Tests the editTestPlan() method
	 */
	@Test
	void testEditTestPlan() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		// invalid case: editing failing tests
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> tpm.editTestPlan("Example Name"));
		assertEquals("The Failing Tests list may not be edited.", e1.getMessage());
		tpm.addTestPlan("TestPlan1");
		tpm.editTestPlan("New Test Plan Name");
		assertEquals("New Test Plan Name", tpm.getCurrentTestPlan().getTestPlanName());
	}

	/**
	 * Tests the removeTestPlan() method
	 */
	@Test
	void testRemoveTestPlan() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		// invalid case: remove failing tests
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> tpm.removeTestPlan());
		assertEquals("The Failing Tests list may not be deleted.", e1.getMessage());
		tpm.addTestPlan("TestPlan1");
		tpm.removeTestPlan();
		assertEquals(0, tpm.getTestPlanNames().length);
	}

	/**
	 * Tests the addTestCase() method
	 */
	@Test
	void testAddTestCase() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		assertDoesNotThrow(() -> tpm.addTestCase(new TestCase("ID 0", "type 0", "description 0", "expected results 0")));
		tpm.addTestPlan("TestPlan1");
		tpm.addTestCase(new TestCase("ID 0", "type 0", "description 0", "expected results 0"));
		assertEquals("ID 0", tpm.getCurrentTestPlan().getTestCases().get(0).getTestCaseId());
	}

	/**
	 * Tests the addTestResult() method
	 */
	@Test
	void testAddTestResult() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		assertDoesNotThrow(() -> tpm.addTestCase(new TestCase("ID 0", "type 0", "description 0", "expected results 0")));
		tpm.addTestPlan("TestPlan1");
		tpm.addTestCase(new TestCase("ID 0", "type 0", "description 0", "expected results 0"));
		assertEquals("ID 0", tpm.getCurrentTestPlan().getTestCases().get(0).getTestCaseId());
		tpm.addTestResult(0, false, "Test Results");
		assertEquals("- FAIL: Test Results", tpm.getCurrentTestPlan().getTestCases().get(0).getActualResultsLog());
		tpm.setCurrentTestPlan(FailingTestList.FAILING_TEST_LIST_NAME);
		assertEquals("- FAIL: Test Results", tpm.getCurrentTestPlan().getTestCases().get(0).getActualResultsLog());
	}

	/**
	 * Tests the clearTestPlans() method
	 */
	@Test
	void testClearTestPlans() {
		TestPlanManager tpm = assertDoesNotThrow(() -> new TestPlanManager());
		tpm.addTestPlan("TestPlan1");
		tpm.addTestCase(new TestCase("ID 0", "type 0", "description 0", "expected results 0"));
		tpm.addTestResult(0, false, "Test Results");
		assertEquals("ID 0", tpm.getCurrentTestPlan().getTestCases().get(0).getTestCaseId());
		assertEquals("- FAIL: Test Results", tpm.getCurrentTestPlan().getTestCases().get(0).getActualResultsLog());
		
		tpm.clearTestPlans();
		assertFalse(tpm.isChanged());
		assertEquals(FailingTestList.FAILING_TEST_LIST_NAME, tpm.getCurrentTestPlan().getTestPlanName());
		assertEquals(0, tpm.getTestPlanNames().length);
		assertEquals(0, tpm.getCurrentTestPlan().getTestCases().size());
	}

}
