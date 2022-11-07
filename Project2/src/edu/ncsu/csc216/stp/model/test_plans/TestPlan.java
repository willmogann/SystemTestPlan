package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * The TestPlan class represents each user defined test plan in the System Test Plan program.
 * This class extends AbstractTestPlan so it inherits the behavior and fields of the parent class.
 * Additional behavior that is overridden from the parent class is changing the way tests are
 * added to the test plan, and the 2D array of strings that is displayed in the GUI is different.
 * This class also implements the Comparable<> interface, so it has the ability to compare two objects
 * and add new test cases into the list in sorted order. There are no additional fields other
 * than the inherited ones. 
 * 
 * @author William Morgan
 *
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {

	/**
	 * Creates a new test plan with the given name as the input parameter.
	 * If the name is the same as the expected name for FailingTests, then an IAE is thrown
	 * 
	 * @param testPlanName the test plan name to set
	 * @throws IllegalArgumentException if the name is the same as the failing tests name
	 */
	public TestPlan(String testPlanName) {
		super(testPlanName);
		if (testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}

	/**
	 * Creates a 2D array of strings to represent the current test plan in the GUI.
	 * Each row has information about each test case within the test plan.
	 * The columns of the array are as follows:
	 * 1. test case id 2. test type 3. status (P/F)
	 * @return the 2D array with information about the test cases
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		String[][] returnArray = new String[getTestCases().size()][3];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i][0] = getTestCases().get(i).getTestCaseId();
			returnArray[i][1] = getTestCases().get(i).getTestType();
			returnArray[i][2] = getTestCases().get(i).getStatus();
		}
		return returnArray;
	}

	/**
	 * Overrides the add test case method from the parent class.
	 * This method will call the parent method but then set the TestCase current test 
	 * plan to the current test case with the "this" keyword.
	 * @param testCaseToAdd the test case to add
	 */
	@Override
	public void addTestCase(TestCase testCaseToAdd) {
		super.addTestCase(testCaseToAdd);
		testCaseToAdd.setTestPlan(this);
	}
	
	/**
	 * Compare the name of two test plans without case sensitivity. 
	 * If this test plan name is less than the other test plan name, return -1.
	 * If this test plan name is greater than the other test plan name, return 1.
	 * If the names are equal return 0.
	 * @param o the other test plan to compare
	 * @return -1 if this is less than, 1 if this is greater than, 0 if equal
	 */
	@Override
	public int compareTo(TestPlan o) {
		if (!this.getTestPlanName().equalsIgnoreCase(o.getTestPlanName())) {
			String thisPlanName = this.getTestPlanName().toLowerCase();
			String otherPlanName = o.getTestPlanName().toLowerCase();
			int shorterLength = thisPlanName.length() < otherPlanName.length() ? thisPlanName.length() : otherPlanName.length();
			for (int i = 0; i < shorterLength; i++) {
				if (thisPlanName.charAt(i) < otherPlanName.charAt(i))
					return -1;
				if (thisPlanName.charAt(i) > otherPlanName.charAt(i))
					return 1;
			}
			if (thisPlanName.length() < otherPlanName.length()) {
				return -1;
			} else {
				return 1;
			}
		}
		return 0;
	}

}
