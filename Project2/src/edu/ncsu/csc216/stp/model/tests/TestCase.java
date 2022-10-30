package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;

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
	 * The testPlan field is set to null
	 * 
	 * @param testCaseId the test case Id to set
	 * @param testType the test type to set
	 * @param testDescription the test description to set
	 * @param expectedResults the expected results to set
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		
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
		this.expectedResults = expectedResults;
	}
	
	/**
	 * Creates a test result from the given values and adds it to the end of
	 * the log.
	 */
	public void addTestResult(boolean passing, String actualResults) {
		
	}
	
}
