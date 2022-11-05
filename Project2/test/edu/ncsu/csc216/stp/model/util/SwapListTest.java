package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SwapList class
 * 
 * @author William Morgan
 *
 */
class SwapListTest {
	
	/**
	 * Tests the SwapList constructor
	 */
	@Test
	public void testSwapList() {
		SwapList<String> list = new SwapList<>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for add().
	 */
	@Test
	public void testAdd() {
		SwapList<String> list = new SwapList<>();
		assertEquals(0, list.size());
		
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
		
		// add filler to test check capacity
		list.add("!");
		list.add("!");
		list.add("!");
		list.add("!");
		list.add("!");
		list.add("!");
		list.add("!");
		
		list.add("Filler");
		assertEquals(11, list.size());
	}

	/**
	 * Test method for remove().
	 */
	@Test
	public void testRemove() {
		SwapList<String> list = new SwapList<>();
		assertEquals(0, list.size());
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
	 * Test method for moveUp().
	 */
	@Test
	public void testMoveUp() {
		SwapList<String> list = new SwapList<>();
		assertEquals(0, list.size());
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
		
		// make sure list does not get changed
		list.moveUp(0);
		assertEquals("Hello", list.get(0));
		
		// move up one 
		list.moveUp(1);
		assertEquals("World", list.get(0));
		assertEquals("Hello", list.get(1));
		
		// move up one 
		list.moveUp(4);
		assertEquals("filler2", list.get(3));
		assertEquals("filler1", list.get(4));
	}

	/**
	 * Test method for moveDown().
	 */
	@Test
	public void testMoveDown() {
		SwapList<String> list = new SwapList<>();
		assertEquals(0, list.size());
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
		
		// move down one
		list.moveDown(0);
		assertEquals("World", list.get(0));
		assertEquals("Hello", list.get(1));		
		
		// move down one 
		list.moveDown(2);
		assertEquals("filler1", list.get(2));
		assertEquals("!", list.get(3));
		
		// make sure list is not changed
		list.moveDown(4);
		assertEquals("filler2", list.get(4));
	}

	/**
	 * Test method for moveToFront().
	 */
	@Test
	public void testMoveToFront() {
		SwapList<String> list = new SwapList<>();
		assertEquals(0, list.size());
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
		
		// ensure list is not changed
		list.moveToFront(0);
		assertEquals("Hello", list.get(0));
		
		// move middle to front
		list.moveToFront(2);
		assertEquals("!", list.get(0));
		assertEquals("Hello", list.get(1));
		// make sure back of list is not changed
		assertEquals("filler1", list.get(3));
		
		
	}

	/**
	 * Test method for moveToBack().
	 */
	@Test
	public void testMoveToBack() {
		SwapList<String> list = new SwapList<>();
		assertEquals(0, list.size());
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
		
		// ensure list is not changed
		list.moveToBack(4);
		assertEquals("filler2", list.get(4));
		
		// move middle to back
		list.moveToBack(2);
		assertEquals("!", list.get(4));
		assertEquals("filler2", list.get(3));
		// make sure front of list is not changed
		assertEquals("Hello", list.get(0));
				
	}

	/**
	 * Test method for get().
	 */
	@Test
	void testGet() {
		SwapList<String> list = new SwapList<>();
		assertEquals(0, list.size());
	}

}
