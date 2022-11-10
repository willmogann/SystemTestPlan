package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SortedList class
 * 
 * @author William Morgan
 *
 */
class SortedListTest {

	/**
	 * Tests the SortedList() constructor
	 */
	@Test
	void testSortedList() {
		SortedList<String> list = assertDoesNotThrow(() -> new SortedList<>(), "Should not throw exception");
		assertEquals(0, list.size());
	}

	/**
	 * Tests the add() method
	 */
	@Test
	void testAdd() {
		SortedList<String> list = assertDoesNotThrow(() -> new SortedList<>(), "Should not throw exception");
		list.add("Hello");
		// test size
		assertEquals(1, list.size());
		// ensure was added to back
		assertEquals("Hello", list.get(0));
		list.add("World");
		// test size
		assertEquals(2, list.size());
		// ensure was added to back
		assertEquals("World", list.get(1));
		list.add("Apple");
		// test size
		assertEquals(3, list.size());
		// ensure was added to back
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
//		System.out.println(list.get(2));
		assertEquals("Apple", list.get(0));
		
		
		list.add("All");
		assertEquals(4, list.size());
		assertEquals("All", list.get(0));
		
		// test invalid cases
		Exception e1 = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> list.add("World"));
		assertEquals("Cannot add duplicate element.", e2.getMessage());
		
	}

	/**
	 * Tests the remove() method
	 */
	@Test
	void testRemove() {
		SortedList<String> list = assertDoesNotThrow(() -> new SortedList<>(), "Should not throw exception");
		list.add("hello");
		// test size
		assertEquals(1, list.size());
		// ensure was added to back
		assertEquals("hello", list.get(0));
		list.add("world");
		// test size
		assertEquals(2, list.size());
		// ensure was added to back
		assertEquals("world", list.get(1));
		list.add("!");
		// test size
		assertEquals(3, list.size());
		// ensure was added to back
		assertEquals("!", list.get(0));
		list.add("filler1");
		// test size
		assertEquals(4, list.size());
		// ensure was added to back
		assertEquals("filler1", list.get(1));
		list.add("filler2");
		// test size
		assertEquals(5, list.size());
		// ensure was added to back
		assertEquals("filler2", list.get(2));
		assertEquals("filler1", list.get(1));
		// test remove from front
		assertEquals("!", list.remove(0));
		assertEquals(4, list.size());
		assertEquals("filler1", list.get(0));
		
		// test remove from end
		assertEquals("filler2", list.remove(1));
		assertEquals(3, list.size());
		assertEquals("filler1", list.get(0));
		
		// test remove from middle
		assertEquals("hello", list.remove(1));
		assertEquals(2, list.size());
		assertEquals("filler1", list.get(0));
		assertEquals("world", list.get(1));
		
		// test invalid index
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals("Invalid index.", e1.getMessage());
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
		assertEquals("Invalid index.", e2.getMessage());
	}

	/**
	 * Tests the contains() method
	 */
	@Test
	void testContains() {
		SortedList<String> list = assertDoesNotThrow(() -> new SortedList<>(), "Should not throw exception");
		list.add("Hello");
		assertTrue(list.contains("Hello"));
		assertFalse(list.contains("World"));
	}
	
	/**
	 * tests exception for get method
	 */
	@Test
	public void testGetInvalid() {
		SortedList<String> list = assertDoesNotThrow(() -> new SortedList<>(), "Should not throw exception");
		list.add("Hello");
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertEquals("Invalid index.", e1.getMessage());
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
		assertEquals("Invalid index.", e2.getMessage());
	}

}
