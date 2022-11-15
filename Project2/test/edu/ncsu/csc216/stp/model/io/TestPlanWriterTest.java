package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests the TestPlanWriter class
 * 
 * @author William Morgan
 *
 */
class TestPlanWriterTest {
	
	/** file path for actual file */
	private String actFileName = "test-files/actual_out.txt";
	
	/** file path for expected file */
	private String expFileName = "test-files/expected_out.txt";
	
	/**
	 * test constructor
	 */
	@Test
	void testTestPlanWriter() {
		TestPlanWriter tpw = new TestPlanWriter();
		assertNotEquals(null, tpw);
	}

	/**
	 * Tests the writeTestPlanFile() method
	 */
	@Test
	void testWriteTestPlanFile() {
		TestPlan testPlan1 = new TestPlan("TestPlan1");
		TestCase tc0 = new TestCase("ID 0", "type 0", "description 0", "expected results 0");
		tc0.addTestResult(false, "actual results 0-1");
		testPlan1.addTestCase(tc0);
		TestCase tc1 = new TestCase("ID 1", "type 1", "description 1", "expected results 1");
		tc1.addTestResult(true, "actual results 1-1");
		tc1.addTestResult(false, "actual results 1-2");
		testPlan1.addTestCase(tc1);
		TestCase tc2 = new TestCase("ID 2", "type 2", "description 2", "expected results 2");
		tc2.addTestResult(true, "actual results 2-1");
		tc2.addTestResult(true, "actual results 2-2");
		testPlan1.addTestCase(tc2);
		
		TestPlan testPlan2 = new TestPlan("TestPlan2");
		
		ISortedList<TestPlan> testPlans = new SortedList<>();
		testPlans.add(testPlan1);
		testPlans.add(testPlan2);
		
		try {
			TestPlanWriter.writeTestPlanFile(new File(actFileName), testPlans);
		} catch (IllegalArgumentException e) {
			fail("Cannot write to file.");
		}
		
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

}
