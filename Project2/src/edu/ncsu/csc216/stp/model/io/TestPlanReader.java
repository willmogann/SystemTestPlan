package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.tests.TestResult;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * The TestPlanReader class is one of two file IO classes in the
 * System Test Plan project. This class has one public method, readTestPlansFile.
 * There are two private helper methods to help with reading the file input. 
 * This class is used with the GUI and with TestPlanManager in order to 
 * read in information and display it in the GUI. All methods are static,
 * so there are no compositional relationships with any other classes.
 * 
 * @author William Morgan
 *
 */
public class TestPlanReader {
	
	/**
	 * Constructor for test plan reader
	 */
	public TestPlanReader() { }

	/**
	 * This method receives a file and reads the contents of it
	 * as described in the requirements. It creates a new TestPlan for
	 * each test plan entry in the file and adds it to a sorted list of test plans.
	 * Any invalid test plans are ignored and are not added to the list.
	 * 
	 * @param file the file that is input into the program
	 * @return a sorted list of test plans for each valid test plan
	 * @throws IllegalArgumentException if there are any issues loading the file, or
	 * if it does not exist.
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File file) {
//		if (!file.exists()) {
//			throw new IllegalArgumentException("Unable to load file.");
//		}
		// scanner to input whole file into a string
		Scanner scnr1 = null;
		// scanner to process each test plan
		Scanner scnr2 = null;
		ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		
		try {
			scnr1 = new Scanner(file);
		} catch (FileNotFoundException e) {
			// throw IAE if file not found
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		// read in file as a string
		String fileString = "";
		while (scnr1.hasNextLine()) {
			fileString += scnr1.nextLine() + "\n";
			if (fileString.charAt(0) != '!') {
				scnr1.close();
				throw new IllegalArgumentException("Unable to load file.");
			}
		}
		scnr1.close();
		
		try {
			// break string into test plans
			scnr2 = new Scanner(fileString);
			scnr2.useDelimiter("\\r?\\n?[!]");
			while (scnr2.hasNext()) {
				testPlans.add(processTestPlan(scnr2.next()));
			}
			scnr2.close();
		} catch (IllegalArgumentException e) {
			// skip test plan
		}
		return testPlans;
	}
	
	/**
	 * Private helper method to process each test plan within the input file.
	 * The readTestPlansFile method will call this method after each test plan string 
	 * is processed and then this method will return a test plan for each test plan
	 * string processed. 
	 * 
	 * @param testPlanString the input test plan string to create a test plan with
	 * @return a test plan with the information in the test plan string
	 * @throws IllegalArgumentException if there is an issue creating the test plan or
	 * there is not enough information to create a test plan
	 */
	private static TestPlan processTestPlan(String testPlanString) {
		Scanner scnr = new Scanner(testPlanString);
		TestPlan testPlan = null;
		try {
			String firstLine = scnr.nextLine();
			if (firstLine.contains(",")) {
				scnr.close();
				throw new IllegalArgumentException("Need test plan name.");
			}
			firstLine = firstLine.substring(1).trim();
			testPlan = new TestPlan(firstLine);
			
			// break test plan into test cases
			scnr.useDelimiter("\\r?\\n?[#]");
			while (scnr.hasNext()) {
				try {
					testPlan.addTestCase(processTest(testPlan, scnr.next()));
				} catch (IllegalArgumentException e) {
					// skip this test case
				}
			}
		} catch (NoSuchElementException e) {
			scnr.close();
			throw new IllegalArgumentException("Need test plan information.");
		}
		scnr.close();
		return testPlan;
	}
	
	/**
	 * Private helper method that takes in a string for each test case and the abstract 
	 * test plan that the test case belongs to. This method is called by the processTestPlan
	 * method to create a test case for each string that is parsed within the test plan.
	 * 
	 * @param testPlan the test plan that the test case created belongs to
	 * @param testCaseString the string in which this method creates a test case
	 * @return a test case created with the information in the testCaseString
	 * @throws IllegalArgumentException if there is an issue creating the test case or if
	 * there is any information missing to create the test case
	 */
	private static TestCase processTest(AbstractTestPlan testPlan, String testCaseString) {
		Scanner scnr = new Scanner(testCaseString);
		TestCase testCase = null;
		try {
			// parse first line for information (test id, test type)
			String testCaseInfo = scnr.nextLine().substring(1).trim();
			Scanner scnr1 = new Scanner(testCaseInfo);
			scnr1.useDelimiter(",");
			String testCaseId = scnr1.next();
			String testType = scnr1.next();
			if (scnr1.hasNext()) {
				scnr1.close();
				throw new IllegalArgumentException("Too much information.");
			}
			scnr1.close();
			
			// process test case description, expected results
			scnr.useDelimiter("\\r?\\n?[-]");
			Scanner scnr2 = new Scanner(scnr.next());
			scnr2.useDelimiter("\\r?\\n?[*]");
			String description = scnr2.next().trim();
			String expectedResults = scnr2.next().trim();
			if (scnr2.hasNext()) {
				scnr2.close();
				throw new IllegalArgumentException("Too much information.");
			}
			testCase = new TestCase(testCaseId, testType, description, expectedResults);
			testCase.setTestPlan((TestPlan) testPlan);
			while (scnr.hasNext()) {
				String actualResult = scnr.next();
				boolean passing = actualResult.substring(1, 5).trim().equals(TestResult.PASS);
				String actualResultsText = actualResult.substring(7).trim();
				testCase.addTestResult(passing, actualResultsText);
			}
		} catch (NoSuchElementException e) {
			scnr.close();
			throw new IllegalArgumentException("Need more information.");
		}
		scnr.close();
		return testCase;
	}
}
