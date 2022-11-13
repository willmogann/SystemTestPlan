package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * The test case class represents each test case in a given test plan.
 * Each test case has a field for its id, test type, description, expected results,
 * and the log of actual test results. The log of actual test results is of type ILog and
 * it holds TestResult objects. Each test case also keeps track of what test plan
 * it belongs to. 
 * The behavior of each test case involves the standard getters and setters for each of the fields.
 * Additional behavior includes adding a test result, checking if test case is passing,
 * getting the status of the test case, getting the test result log, and converting the
 * test case to a string representation.
 * The test case class has a compositional relationship with the ILog interface and the TestResult
 * interface by association. 
 * This class also interacts with the TestPlan class through the testPlan field.
 * 
 * @author William Morgan
 */
public class TestCase {

	/**
	 * The test case id field
	 */
	private String testCaseId;
	
	/**
	 * The test case type field
	 */
	private String testType;
	
	/**
	 * The test description field
	 */
	private String testDescription;
	
	/**
	 * The expected results field
	 */
	private String expectedResults;
	
	/**
	 * The test plan field
	 */
	private TestPlan testPlan;
	
	/**
	 * The test results log field that holds the list of TestResult objects
	 */
	private ILog<TestResult> testResults;
	
	/**
	 * Constructs a new TestCase. Sets each field to the given parameters.
	 * The testResults field is constructed to an empty Log of TestResult objects.
	 * The testPlan field is set to null. 
	 * 
	 * @param testCaseId the test case Id to set
	 * @param testType the test type to set
	 * @param testDescription the test description to set
	 * @param expectedResults the expected results to set
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		setTestCaseId(testCaseId);
		setTestType(testType);
		setTestDescription(testDescription);
		setExpectedResults(expectedResults);
		testResults = new Log<TestResult>();
		this.testPlan = null;
	}
	
	/**
	 * Method to assist with error checking
	 * @param parameter the parameter to error check
	 * @throws IllegalArgumentException if parameter is null or empty
	 */
	private void checkParameter(String parameter) {
		if (parameter == null || parameter.length() == 0) {
			throw new IllegalArgumentException("Invalid test information.");
		}
	}

	/**
	 * Returns the test case id
	 * 
	 * @return the testCaseId
	 */
	public String getTestCaseId() {
		return testCaseId;
	}

	/**
	 * Sets the test case id
	 * 
	 * @param testCaseId the testCaseId to set
	 * @throws IllegalArgumentException if the testCaseId is null or empty
	 */
	private void setTestCaseId(String testCaseId) {
		checkParameter(testCaseId);
		this.testCaseId = testCaseId;
	}

	/**
	 * Returns the test type
	 * 
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * Sets the test type
	 * 
	 * @param testType the testType to set
	 * @throws IllegalArgumentException if the testType is null or empty
	 */
	private void setTestType(String testType) {
		checkParameter(testType);
		this.testType = testType;
	}

	/**
	 * Returns the test description
	 * 
	 * @return the testDescription
	 */
	public String getTestDescription() {
		return testDescription;
	}

	/**
	 * Sets the test description
	 * 
	 * @param testDescription the testDescription to set
	 * @throws IllegalArgumentException if the testDescription is null or empty
	 */
	private void setTestDescription(String testDescription) {
		checkParameter(testDescription);
		this.testDescription = testDescription;
	}

	/**
	 * Returns the expected results
	 * 
	 * @return the expectedResults
	 */
	public String getExpectedResults() {
		return expectedResults;
	}

	/**
	 * Sets the expected results
	 * 
	 * @param expectedResults the expectedResults to set
	 * @throws IllegalArgumentException if the expectedResults is null or empty
	 */
	private void setExpectedResults(String expectedResults) {
		checkParameter(expectedResults);
		this.expectedResults = expectedResults;
	}
	
	
	/**
	 * Creates a test result from the given values and adds it to the end of
	 * the log. If the Test Result cannot be constructed, an IllegalArgumentException
	 * is thrown. 
	 * 
	 * @param passing the boolean of whether the test is passing or not
	 * @param actualResults the actual results of the test execution
	 * @throws IllegalArgumentException if the TestResult cannot be constructed
	 */
	public void addTestResult(boolean passing, String actualResults) {
		TestResult tr = new TestResult(passing, actualResults);
		testResults.add(tr);
	}
	
	/**
	 * Checks whether the test case is passing or not. 
	 * This method will return true if the last TestResult in the log is passing.
	 * If there are no TestResults in the log, then the test case is considered failing.
	 * 
	 * @return true if test case is passing, false if not
	 */
	public boolean isTestCasePassing() {
		if (testResults.size() == 0) {
			return false;
		} else {
			return testResults.get(testResults.size() - 1).isPassing();
		}
	}
	
	/**
	 * Returns the status of the test case as PASS or FAIL. uses static fields from
	 * test case class.
	 * 
	 * @return the status of the test case
	 */
	public String getStatus() {
		if (isTestCasePassing()) {
			return TestResult.PASS;
		} else {
			return TestResult.FAIL;
		}
	}
	
	/**
	 * Creates and returns a string representation of the testResults log. 
	 * Each test result has a leading "- " and a newline is added to the end.
	 * This method uses the TestResult.toString() method to help convert each
	 * test result to a string.
	 * 
	 * @return the string representation of the testResults log
	 */
	public String getActualResultsLog() {
		String returnString = "";
		for (int i = 0; i < testResults.size(); i++) {
			returnString += "- " + testResults.get(i).toString() + "\n";
		}
		return returnString.trim();
	}
	
	/**
	 * Sets the current test plan to the test plan value in the field.
	 * 
	 * @param testPlan the test plan that will be set
	 * @throws IllegalArgumentException if the testPlan parameter is null
	 */
	public void setTestPlan(TestPlan testPlan) {
		if (testPlan == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}
		this.testPlan = testPlan;
	}
	
	/**
	 * Returns the current test plan
	 * @return the current test plan
	 */
	public TestPlan getTestPlan() {
		return this.testPlan;
	}
	
	/**
	 * Creates and returns a string representation of the current test case. 
	 * This method will use the getActualResultsLog method in order to help build the string.
	 * This method creates a string based on the data storage requirement and is used for file IO.
	 * 
	 * @return the string representation of the test case.
	 */
	public String toString() {
		String returnString = "# " + testCaseId + "," + testType + "\n"
				 + "* " + testDescription.trim() + "\n"
				 + "* " + expectedResults.trim() + "\n"
				 + getActualResultsLog();
		return returnString.trim();
	}
}
