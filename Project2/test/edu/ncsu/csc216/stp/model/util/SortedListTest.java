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
		SortedList<String> list = new SortedList<>();
		assertEquals(0, list.size());
	}

	/**
	 * Tests the add() method
	 */
	@Test
	void testAdd() {
		SortedList<String> list = new SortedList<>();
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
		list.add("!");
		// test size
		assertEquals(3, list.size());
		// ensure was added to back
		assertEquals("!", list.get(2));
		
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
		SortedList<String> list = new SortedList<>();
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
		list.add("!");
		// test size
		assertEquals(3, list.size());
		// ensure was added to back
		assertEquals("!", list.get(2));
		list.add("filler1");
		// test size
		assertEquals(4, list.size());
		// ensure was added to back
		assertEquals("filler1", list.get(3));
		list.add("filler2");
		// test size
		assertEquals(5, list.size());
		// ensure was added to back
		assertEquals("filler2", list.get(4));
		
		// test remove from front
		assertEquals("Hello", list.remove(0));
		assertEquals(4, list.size());
		assertEquals("World", list.get(0));
		
		// test remove from end
		assertEquals("filler2", list.remove(3));
		assertEquals(3, list.size());
		assertEquals("filler1", list.get(2));
		
		// test remove from middle
		assertEquals("!", list.remove(1));
		assertEquals(2, list.size());
		assertEquals("World", list.get(0));
		assertEquals("filler1", list.get(1));
		
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
		SortedList<String> list = new SortedList<>();
		list.add("Hello");
		assertTrue(list.contains("Hello"));
		assertFalse(list.contains("World"));
	}
	
	/**
	 * tests exception for get method
	 */
	@Test
	public void testGetInvalid() {
		SortedList<String> list = new SortedList<>();
		list.add("Hello");
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertEquals("Invalid index.", e1.getMessage());
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
		assertEquals("Invalid index.", e2.getMessage());
	}

}
