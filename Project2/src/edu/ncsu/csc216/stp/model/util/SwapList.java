package edu.ncsu.csc216.stp.model.util;

/**
 * The SwapList class is a custom Array-Based list to be used with the AbstractTestPlan class.
 * This class implements the ISwapList interface which provides the custom functionality of the list.
 * The fields of this class include the underlying array that the list is based around, and the
 * current size of the list. There is a static final field to hold the initial capacity of the list.
 * The behavior of this class includes adding an element to the list, removing an element, move an element up,
 * move an element down, move element to front, move element to back, get an element at an index,
 * and get the size of the list. This class is used to hold the list of test cases for each
 * test plan, and it gives functionality to move test cases around. 
 * 
 * @author William Morgan
 * @param <E> the generic type that the SwapList can hold
 */
public class SwapList<E> implements ISwapList<E>{

	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param element the element to be added
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException if the element cannot be added
	 */
	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Searches the list and removes the element at the given index
	 * 
	 * @param idx the index of the element to remove
	 * @return the element that is removed
	 * @throws IndexOutOfBoundsException if the index is out of bounds of the list
	 */
	@Override
	public E remove(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Moves the element at the given index up one (index - 1). If the element
	 * is at the front, the list is not changed.
	 * 
	 * @param idx the index of the element to move up
	 * @throws IndexOutOfBoundsException if the index is out of bounds of the list
	 */
	@Override
	public void moveUp(int idx) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Moves the element at the given index down one (index + 1). If the element
	 * is at the end of the list, the list is not changed.
	 * 
	 * @param idx the index of the element to move down
	 * @throws IndexOutOfBoundsException if the index is out of bounds of the list
	 */
	@Override
	public void moveDown(int idx) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Moves the element at the given index to the front of the list. If
	 * the element is at the front, the list is not changed.
	 * 
	 * @param idx the index of the element to move to the front
	 * @throws IndexOutOfBoundsException if the index is out of bounds of the list
	 */
	@Override
	public void moveToFront(int idx) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Moves the element at the given index to the back of the list. If the
	 * element is at the back, the list is not changed.
	 * 
	 * @param idx the index of the element to move to the back
	 * @throws IndexOutOfBoundsException if the index is out of bounds of the list
	 */
	@Override
	public void moveToBack(int idx) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns the element at the given index
	 * 
	 * @param idx the index of the element to return
	 * @return the element at the given index
	 */
	@Override
	public E get(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the number of elements in the list
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
