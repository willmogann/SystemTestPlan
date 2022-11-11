package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * This class handles the logic of creating removing and editing
 * data that is displayed in the GUI. The TestPlanManager class handles
 * each operation that can be done by the GUI which would alter the data
 * in the System Test Plan program. This class interacts with the
 * ISortedList interface, the FailingTestList class, and the AbstractTestPlan
 * class.
 * The fields of this class include a list of testPlans in the program,
 * the current test plan, the failing test list, and a boolean stating whether 
 * the system state has been changed or not.
 * There is file IO behavior that allows loading and saving test plan records
 * to a text file according to the requirements. These methods use the 
 * test plan reader and writer IO classes.
 * This class also keeps track of whether the system has been changed since
 * the last system save.
 * The behavior that allows for adding data to the program includes adding
 * a test plan, adding a test case, and adding a test result to a test case.
 * Data removal behavior includes clearing the test plans and removing a test plan.
 * This class is able to display and edit information with the GUI through editing 
 * a test plan name, setting the current test plan, getting the test plan names and
 * getting the current test plan.
 * 
 * @author William Morgan
 *
 */
public class TestPlanManager {
	
	/**
	 * Field to hold the list of test plans currently active in the System test plan manager
	 */
	private ISortedList<TestPlan> testPlans;
	
	/**
	 * Field to hold the failing test list, which includes test cases from each active project
	 */
	private FailingTestList failingTestList;
	
	/**
	 * Field to hold the currently active test plan
	 */
	private AbstractTestPlan currentTestPlan;
	
	/**
	 * Field to hold the state of whether the manager has been changed or not since last use
	 */
	private boolean isChanged;
	
	/**
	 * Constructs a new TestPlanManager. The testPlans field is set to a new SortedList
	 * and the failingTests field is constructed and set as the currentTestPlan.
	 * isChanged is initialized to the false state. 
	 */
	public TestPlanManager() {
		clearTestPlans();
	}
	
	/**
	 * Loads a test plan from the file menu. This method uses the TestPlanReader static methods.
	 * The list of potential test plans is checked with the current list and only test plans
	 * that don't already exist are added into the program. The currentTestPlan is set to the 
	 * FailingTests test plan. isChanged is set to true.
	 * 
	 * @param testPlanFile the file that contains the test plans to load
	 */
	public void loadTestPlans(File testPlanFile) {
		ISortedList<TestPlan> potentialTestPlans = TestPlanReader.readTestPlansFile(testPlanFile);
		for (int i = 0; i < testPlans.size(); i++) {
			try {
				testPlans.add(potentialTestPlans.get(i));
			} catch (IllegalArgumentException e) {
				// skip this test plan, duplicate
			}
		}
		setCurrentTestPlan(FailingTestList.FAILING_TEST_LIST_NAME);
		isChanged = true;
	}
	
	/**
	 * Saves the current TestPlans to the given file using the TestPlanWriter static method.
	 * isChanged is set to false
	 * 
	 * @param testPlanFile the file to save the test plans to
	 */
	public void saveTestPlans(File testPlanFile) {
		TestPlanWriter.writeTestPlanFile(testPlanFile, testPlans);
		isChanged = false;
	}
	
	/**
	 * Returns the status of whether the system has been changed
	 * @return isChanged field
	 */
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	 * Attempts to add a test plan to the list of active test plans in the system.
	 * If the given name is equal to the failing test name or a duplicate of an existing
	 * test name, an IAE is thrown. If the name is valid, the test is added to the list of test plans
	 * and the current test plan is updated to the new test plan. isChanged will be updated
	 * to true.
	 * 
	 * @param testPlanName the name of the test plan to add
	 * @throws IllegalArgumentException if there is an error adding the test plan
	 */
	public void addTestPlan(String testPlanName) {
		if (testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		try {
			testPlans.add(new TestPlan(testPlanName));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		isChanged = true;
	}
	
	/**
	 * Returns an array with the names of all the active test plan names
	 * 
	 * @return an array containing the names of the active test plans
	 */
	public String[] getTestPlanNames() {
		String[] names = new String[testPlans.size()];
		names[0] = FailingTestList.FAILING_TEST_LIST_NAME;
		for (int i = 1; i < names.length; i++) {
			names[i] = testPlans.get(i).getTestPlanName();
		}
		return names;
	}
	
	/**
	 * Private helper method that is used to work with the FailingTestList.
	 * Helps maintain the order of the failing test list, which is by test plan first
	 * and then order of test cases within that test plan. 
	 */
	private void getFailingTests() {
		failingTestList = new FailingTestList();
		for (int i = 0; i < testPlans.size(); i++) {
			for (int j = 0; j < testPlans.get(i).getTestCases().size(); j++) {
				TestCase tc = testPlans.get(i).getTestCase(j);
				if (!tc.isTestCasePassing()) {
					failingTestList.addTestCase(tc);
				}
			}
		}
	}
	
	/**
	 * Sets the current test plan to the AbstractTestPlan with the given name.
	 * If the test plan with the given name is not found, the current test plan is set
	 * to the failingTestList after updating it.
	 * 
	 * @param testPlanName the test plan name to set as the current test plan
	 */
	public void setCurrentTestPlan(String testPlanName) {
		boolean flag = false;
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equalsIgnoreCase(testPlanName)) {
				currentTestPlan = testPlans.get(i);
				flag = true;
			}
		}
		if (!flag) {
			getFailingTests();
			currentTestPlan = failingTestList;
		}
	}
	
	/**
	 * Returns the current test plan of the system. Public because this is displayed 
	 * in the GUI on the menu bar.
	 * 
	 * @return the current test plan of the system
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}
	
	/**
	 * Attempts to change the name of the current test plan. 
	 * If the currentTestPlan is the FailingTestList, if the name matches the failing test
	 * list, or if the name is a duplicate of an existing name, an IAE is thrown.
	 * The edit is done by removing the test plan, changing the name, and then adding it back
	 * to the sorted list. isChanged is updated to true.
	 * 
	 * @param testPlanName the new name of the current test plan
	 * @throws IllegalArgumentException if there is an error editing the test plan
	 */
	public void editTestPlan(String testPlanName) {
		if (currentTestPlan instanceof FailingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		if (testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		int currentTestIndex = 0;
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlanName.equalsIgnoreCase(testPlans.get(i).getTestPlanName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
			if (testPlans.get(i).getTestPlanName().equalsIgnoreCase(currentTestPlan.getTestPlanName())) {
				currentTestIndex = i;
			}
		}
		testPlans.remove(currentTestIndex);
		currentTestPlan.setTestPlanName(testPlanName);
		testPlans.add((TestPlan) currentTestPlan);
		isChanged = true;
	}
	
	/**
	 * Attempts to remove the current test plan from the list of active test plans.
	 * If the currentTestPlan is FailingTestList, then an IAE is thrown.
	 * 
	 * @throws IllegalArgumentException if the current test plan is of type FailingTestList
	 */
	public void removeTestPlan() {
		if (currentTestPlan instanceof FailingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		} else {
			int currentTestIndex = 0;
			for (int i = 0; i < testPlans.size(); i++) {
				if (testPlans.get(i).getTestPlanName().equalsIgnoreCase(currentTestPlan.getTestPlanName())) {
					currentTestIndex = i;
				}
			}
			testPlans.remove(currentTestIndex);
			isChanged = true;
		}
	}
	
	
	/**
	 * Attempts to add a test case to the currentTestPlan. TestCases can only be added
	 * to the currentTestPlan when it is of type TestPlan. If the currentTestPlan is not 
	 * of type TestPlan, do not add the test case, otherwise add the test case and check if
	 * it is failing. If the test case is failing, update the failingTestList. isChanged is
	 * updated to true.
	 * 
	 * @param testCase the test case to add to the currentTestPlan
	 */
	public void addTestCase(TestCase testCase) {
		if (currentTestPlan instanceof TestPlan) {
			currentTestPlan.addTestCase(testCase);
			if (!testCase.isTestCasePassing()) {
				getFailingTests();
			}
			isChanged = true;
		}
	}
	
	/**
	 * Adds the test result to the test case at the given index in the currentTestPlans list.
	 * If the tests are failing, the failingTestList is updated.
	 * 
	 * @param idx the index of the test case to add results to
	 * @param passing the passing status of the test case
	 * @param actualResults the actual results of the test execution
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		
	}
	
	/**
	 * Clears out TestPlanManager by setting testPlans to an empty SortedList.
	 * Sets failingTestList to an empty FailingTestList() and currentTestPlan to this new list.
	 * isChanged is set to false.
	 */
	public void clearTestPlans() {
		testPlans = new SortedList<>();
		failingTestList = new FailingTestList();
		currentTestPlan = failingTestList;
		isChanged = false;
	}
}
