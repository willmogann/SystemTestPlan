package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * This class represents the list of failing test cases that will be displayed in the GUI.
 * This is the list of test cases that will be displayed upon starting the program. This class
 * interacts with the TestPlanManager class through the field of failingTestCases. It also inherits most
 * of it's functionality from its parent class, AbstractTestPlan. 
 * FailingTestList has one final field, which holds the expected name of the failing tests list.
 * Along with the functionality inherited from AbstractTestPlan, failing tests overrides 3 methods
 * from its parent class: getTestCasesAsArray, addTestCase and setTestPlanName. 
 * The additional functionality includes being able to display information about the test cases to the GUI,
 * adding a test case to the list ONLY if it is failing, making sure the test plan name is correct when being set,
 * and being able to clear the failing tests list.
 * 
 * @author William Morgan
 *
 */
public class FailingTestList extends AbstractTestPlan {

	/**
	 * Final field to hold the expected name for the failing test list
	 */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/**
	 * Constructs a new failing test list and uses the expected name to set the name of the list.
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a 2D string array with information about the failing tests
	 * list to display in the GUI. The columns for each test case are as follows:
	 * 1. test case id 2. test type 3. test plan name 
	 * @return the 2D array of information for the failing tests
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Overrides the method given in AbstractTestPlan. Ensures that a test case
	 * is in the FAILING state before being added to the list.
	 * @param testCaseToAdd the test case to add
	 * @throws IllegalArgumentException if the test is not failing
	 */
	@Override
	public void addTestCase(TestCase testCaseToAdd) {
		
	}
	
	/**
	 * Overrides the method given in AbstractTestPlan. Ensures that the 
	 * plan name input is the same as the expected name (case insensitive). 
	 * If it is the same, it sets the name to the constant
	 * @param testPlanName the name to set
	 * @throws IllegalArgumentException if the test plan name does not match expected
	 */
	@Override
	public void setTestPlanName(String testPlanName) {
		
	}
	
	/**
	 * Clears the FailingTestList of all TestCases. Sets the testCases field to an empty
	 * SwapList.
	 */
	public void clearTests() {
		
	}

}
