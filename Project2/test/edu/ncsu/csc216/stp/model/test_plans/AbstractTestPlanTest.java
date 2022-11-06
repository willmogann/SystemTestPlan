package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;


/**
 * Tests the AbtractTestPlan class
 * 
 * @author William Morgan
 *
 */
class AbstractTestPlanTest {

	/**
	 * Tests the AbstractTestPlan() constructor
	 */
	@Test
	void testAbstractTestPlan() {
		AbstractTestPlan ats = new TestPlan("Test Plan Name");
		assertEquals("Test Plan Name", ats.getTestPlanName());
		assertEquals(0, ats.getTestCases().size());
	}

	/**
	 * Tests the setTestPlanName() method
	 */
	@Test
	void testSetTestPlanName() {
		AbstractTestPlan ats = new TestPlan("Test Plan Name");
		ats.setTestPlanName("Other plan name");
		assertEquals("Other plan name", ats.getTestPlanName());
		
		// test invalid plan name
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TestPlan(null));
		assertEquals("Invalid name.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new TestPlan(""));
		assertEquals("Invalid name.", e2.getMessage());
	}

//	/**
//	 * Tests the getTestPlanName() method
//	 */
//	@Test
//	void testGetTestPlanName() {
//		fail("Not yet implemented");
//	}

//	/**
//	 * Tests the getTestCases() method
//	 */
//	@Test
//	void testGetTestCases() {
//		fail("Not yet implemented");
//	}

	/**
	 * Tests the addTestCase() method
	 */
	@Test
	void testAddTestCase() {
		AbstractTestPlan ats = new TestPlan("Test Plan Name");
		ats.addTestCase(new TestCase("id", "type", "description", "expected results"));
		assertEquals(1, ats.getTestCases().size());
		ats.addTestCase(new TestCase("id1", "type1", "description1", "expected results1"));
		assertEquals(2, ats.getTestCases().size());
		// make sure it was added to the back
		assertEquals("id", ats.getTestCase(0).getTestCaseId());
		assertEquals("id1", ats.getTestCase(1).getTestCaseId());
	}

	/**
	 * Tests the removeTestCase() method
	 */
	@Test
	void testRemoveTestCase() {
		AbstractTestPlan ats = new TestPlan("Test Plan Name");
		ats.addTestCase(new TestCase("id", "type", "description", "expected results"));
		ats.addTestCase(new TestCase("id1", "type1", "description1", "expected results1"));
		ats.addTestCase(new TestCase("id2", "type2", "description2", "expected results2"));
		
		// remove from middle
		ats.removeTestCase(1);
		assertEquals("id2", ats.getTestCase(1).getTestCaseId());
		assertEquals("id", ats.getTestCase(0).getTestCaseId());
	}

//	/**
//	 * Tests the getTestCase() method
//	 */
//	@Test
//	void testGetTestCase() {
//		AbstractTestPlan ats = new TestPlan("Test Plan Name");
//	}

	/**
	 * Tests the getNumberOfFailingTests() method
	 */
	@Test
	void testGetNumberOfFailingTests() {
		AbstractTestPlan ats = new TestPlan("Test Plan Name");
		ats.addTestCase(new TestCase("id", "type", "description", "expected results"));
		ats.addTestResult(0, false, "Actual result");
		ats.addTestCase(new TestCase("id1", "type1", "description1", "expected results1"));
		ats.addTestResult(1, true, "Actual result");
		ats.addTestCase(new TestCase("id2", "type2", "description2", "expected results2"));
		ats.addTestResult(2, false, "Actual result");
		assertEquals(2, ats.getNumberOfFailingTests());
	}

	/**
	 * Tests the addTestResult() method
	 */
	@Test
	void testAddTestResult() {
		AbstractTestPlan ats = new TestPlan("Test Plan Name");
		ats.addTestCase(new TestCase("id", "type", "description", "expected results"));
		ats.addTestResult(0, false, "Actual result");
		assertEquals("- FAIL: Actual result", ats.getTestCase(0).getActualResultsLog());
	}
	
	/**
	 * Tests the hashCode() method
	 */
	@Test
	void testHashCode() {
		AbstractTestPlan ats = new TestPlan("Test Plan Name");
		AbstractTestPlan ats1 = new TestPlan("Test Plan Name");
		AbstractTestPlan ats2 = new TestPlan("Another Test Plan Name");		
		assertEquals(ats.hashCode(), ats1.hashCode());
		assertNotEquals(ats.hashCode(), ats2.hashCode());
	}
	
	/**
	 * Tests the equals() method
	 */
	@Test
	void testEquals() {
		AbstractTestPlan ats = new TestPlan("Test Plan Name");
		AbstractTestPlan ats1 = new TestPlan("Test Plan Name");
		AbstractTestPlan ats2 = new TestPlan("Another Test Plan Name");	
		AbstractTestPlan ats3 = new TestPlan("test plan name");
		//TestCase tc = new TestCase("id", "type", "description", "expected results");
		assertTrue(ats.equals(ats1));
		assertTrue(ats.equals(ats3));
		assertTrue(ats.equals(ats1));
		// not true cases
		//assertFalse(ats.equals(null));
		assertFalse(ats.equals(ats2));
		//assertFalse(ats.equals(tc));
	}

}
