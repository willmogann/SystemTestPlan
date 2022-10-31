package edu.ncsu.csc216.stp.model.tests;

/**
 * This class contains information about each execution of a TestCase. Each
 * TestResult knows if it is passing or failing and has details about the actual
 * results of the execution. This class interacts with the Log class because
 * each log holds the list of test results for each test case. 
 * The fields of this class include a boolean field to hold whether or not a
 * test case is passing, and an actual results field to hold the contents
 * of the actual results. There are also static final fields to hold the strings
 * for passing and failing.
 * The behaviors of this class include the getters and setters for each of the fields,
 * as well as a method to convert the test result into a string.
 * 
 * @author William Morgan
 */
public class TestResult {

	/**
	 * static final field for a passing test
	 */
	public static final String PASS = "PASS";
	
	/**
	 * static final field for a failing test
	 */
	public static final String FAIL = "FAIL";
	
	/**
	 * boolean field to hold whether a test case is passing or failing
	 */
	private boolean passing;
	
	/**
	 * field to hold the actual results of the test execution
	 */
	private String actualResults;
	
	/**
	 * Constructs a new TestResult with the given parameters.
	 * 
	 * @param passing the boolean for if the test is passing or not
	 * @param actualResults the actual results of the test execution
	 */
	public TestResult(boolean passing, String actualResults) {
		setActualResults(actualResults);
	}

	/**
	 * Returns whether test is passing or not
	 * @return whether test is passing or not
	 */
	public boolean isPassing() {
		return passing;
	}

	/**
	 * Sets the passing boolean field
	 * @param passing the passing boolean to set
	 */
	private void setPassing(boolean passing) {
		this.passing = passing;
	}

	/**
	 * Returns the actual results of the text execution
	 * @return the actualResults of the test execution
	 */
	public String getActualResults() {
		return actualResults;
	}

	/**
	 * Sets the actual results of the test execution
	 * @param actualResults the actualResults to set
	 * @throws IllegalArgumentException if the actual results are null or empty string
	 */
	private void setActualResults(String actualResults) {
		if (actualResults == null || actualResults.length() == 0) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		this.actualResults = actualResults;
	}
	
	/**
	 * Constructs a string representation of the TestResult for printing to file
	 * and the GUI. This method will use the static constants to fill fields.
	 * 
	 * @return the string representation of the test result
	 */
	public String toString() {
		return null;
	}
	
}
