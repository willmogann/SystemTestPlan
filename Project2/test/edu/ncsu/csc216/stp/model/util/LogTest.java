package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Log class
 * 
 * @author William Morgan
 *
 */
class LogTest {

	/**
	 * Tests the Log() constructor
	 */
	@Test
	void testLog() {
		Log<String> log = assertDoesNotThrow(() -> new Log<>(), "Should not throw exception");
		assertEquals(0, log.size());
	}

	/**
	 * Tests the add() method
	 */
	@Test
	void testAdd() {
		Log<String> log = assertDoesNotThrow(() -> new Log<>(), "Should not throw exception");
		
		// test for null pointer exception
		Exception e1 = assertThrows(NullPointerException.class, () -> log.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());
		
		log.add("Hello");
		assertEquals("Hello", log.get(0));
		log.add("World!");
		assertEquals("World!", log.get(1));
		// add filler to test grow array
		log.add("World!");
		assertEquals("World!", log.get(2));
		log.add("World!");
		assertEquals("World!", log.get(3));
		log.add("World!");
		assertEquals("World!", log.get(4));
		log.add("World!");
		assertEquals("World!", log.get(5));
		log.add("World!");
		assertEquals("World!", log.get(6));
		log.add("World!");
		assertEquals("World!", log.get(7));
		log.add("World!");
		assertEquals("World!", log.get(8));
		log.add("World!");
		assertEquals("World!", log.get(9));
		
		log.add("Filler");
		assertEquals(11, log.size());
	}

	/**
	 * Tests the get() method
	 */
	@Test
	void testGet() {
		// test for out of bounds
		Log<String> log = assertDoesNotThrow(() -> new Log<>(), "Should not throw exception");
		log.add("Hello");
		assertEquals("Hello", log.get(0));
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> log.get(1));
		assertEquals("Invalid index.", e1.getMessage());
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> log.get(-1));
		assertEquals("Invalid index.", e2.getMessage());
	}

}
