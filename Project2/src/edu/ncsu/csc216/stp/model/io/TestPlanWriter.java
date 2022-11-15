package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Writes Test Plans to a given filename using the toString methods. This
 * class interacts with the GUI and the TestPlanManager to write
 * test plan records to a given file name. 
 * This class writes test plan records according to the data storage
 * requirements.
 * 
 * @author William Morgan
 *
 */
public class TestPlanWriter {
	
	/**
	 * Constructor for test plan writer
	 */
	public TestPlanWriter() {
		// empty constructor
	}

	/**
	 * Uses the input file with the file name and the list of testPlans,
	 * this method writes each test plan to the file given according to the requirements.
	 * Each test case is written using the toString method and then each test case are put
	 * together into each test plan.
	 * 
	 * @param file the file to write the test plans to
	 * @param testPlans the list of test plans to write
	 * @throws IllegalArgumentException if there is an error saving the file
	 */
	public static void writeTestPlanFile(File file, ISortedList<TestPlan> testPlans) {
		try {
			PrintStream fileWriter = new PrintStream(file);
			for (int i = 0; i < testPlans.size(); i++) {
				TestPlan current = testPlans.get(i);
				fileWriter.print("! " + current.getTestPlanName() + "\n");
				for (int j = 0; j < current.getTestCases().size(); j++) {
					fileWriter.print(current.getTestCases().get(j));
				}
			}
			//fileWriter.print("\n");
			fileWriter.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
