package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * This is an abstract class at the top of the inheritance hierarchy for
 * test plans. Each test plan knows its testPlanName and the ISwapList of TestCases.
 * This class interacts with the ISwapList interface and the TestCase class through
 * the list of testCases field. This class is also found on the TestPlanManager class
 * through the field of the currentTestPlan.
 * This class supports the following behaviors: setting and getting the test plan name,
 * getting the list of test cases, adding a test case, removing a test case,
 * getting a test case at an index, get the number of failing tests, add test result.
 * There is an abstract method getTestCasesAsArray that each child class will have to 
 * implement.
 * 
 * The child classes of this class include TestPlan and FailingTestList.
 * 
 * @author William Morgan
 *
 */
public abstract class AbstractTestPlan {
	
	/**
	 * Field that holds the name of the test plan
	 */
	private String testPlanName;
	
	/**
	 * field that holds the list of test cases for this test plan
	 */
	private ISwapList<TestCase> testCases;
	
	/**
	 * Constructs a new abstract test plan. Uses the parameter to set
	 * the name of the test plan and creates a new empty ISwapList 
	 * for the list of test cases.
	 * 
	 * @param testPlanName the name of the test plan to set
	 * @throws IllegalArgumentException if the test plan name is null or empty
	 */
	public AbstractTestPlan(String testPlanName) {
		setTestPlanName(testPlanName);
		testCases = new SwapList<TestCase>();
	}
	
	/**
	 * Sets the test plan name to the given parameter
	 * @param testPlanName the name of the test plan to set
	 * @throws IllegalArgumentException if the test plan name is null or empty
	 */
	public void setTestPlanName(String testPlanName) {
		if (testPlanName == null || testPlanName.length() == 0) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.testPlanName = testPlanName;
	}
	
	/**
	 * Returns the name of the test plan
	 * 
	 * @return the name of the test plan
	 */
	public String getTestPlanName() {
		return this.testPlanName;
	}
	
	/**
	 * Returns the list of test cases for the test plan
	 * @return the list of test cases for the test plan
	 */
	public ISwapList<TestCase> getTestCases() {
		return this.testCases;
	}
	
	/**
	 * Adds the test case given in the parameter to the end of the testCases list.
	 * Any exception thrown by the test cases list is propagated to the client caller.
	 * @param testCaseToAdd the test case to be added to the list
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException if the element cannot be added
	 */
	public void addTestCase(TestCase testCaseToAdd) {
		testCases.add(testCaseToAdd);
	}
	
	/**
	 * Searches the test cases list and removes the test case at the index.
	 * This method will use the method provided in SwapList to complete this task.
	 * @param idx the index of the test case to remove
	 * @return the test case that is removed
	 * @throws IndexOutOfBoundsException if the index is out of bounds of the list
	 */
	public TestCase removeTestCase(int idx) {
		return testCases.remove(idx);
	}
	
	/**
	 * Returns the test case at the given index. This method uses the method 
	 * described in SwapList.
	 * @param idx the index of the test case to look for
	 * @return the test case at the given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds of the list
	 */
	public TestCase getTestCase(int idx) {
		return testCases.get(idx);
	}
	
	/**
	 * Parses the testCases list and counts the number of failing tests and returns
	 * this value.
	 * @return the number of failing tests in the test plan
	 */
	public int getNumberOfFailingTests() {
		int counter = 0;
		for (int i = 0; i < testCases.size(); i++) {
			if (!testCases.get(i).isTestCasePassing()) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Sends the given parameters to the test case at the given index. The test case
	 * will then use its add test result method to add a test result to the log
	 * of that specific test case 
	 * @param idx the index of the test case to add results to
	 * @param passing the passing status of the test case
	 * @param actualResults the actual results of the test execution
	 * @throws IllegalArgumentException if the TestResult cannot be constructed
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		testCases.get(idx).addTestResult(passing, actualResults);
	}
	
	/**
	 * Abstract method that returns a 2D string array with information about
	 * the test cases in the test plan. This will be used to populate the GUI table
	 * with information.
	 * @return a 2D string array with information to display in the GUI
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
	 * Override of the hashCode() method. Creates a unique identifier for 
	 * each test plan object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testPlanName == null) ? 0 : testPlanName.hashCode());
		return result;
	}

	/**
	 * Override of the equals method. Checks equality based on the name of the
	 * test plan with no case sensitivity.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTestPlan other = (AbstractTestPlan) obj;
		if (testPlanName == null) {
			if (other.testPlanName != null)
				return false;
		} else if (!testPlanName.equalsIgnoreCase(other.testPlanName))
			return false;
		return true;
	}

	
}
