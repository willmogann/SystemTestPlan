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
public class SwapList<E> implements ISwapList<E> {
	
	/**
	 * Field to hold the initial capacity of the list
	 */
	private static final int INITIAL_CAPACITY = 10;
	
	/**
	 * The underlying array that the list is based around. Holds objects of the generic type.
	 */
	private E[] list;
	
	/**
	 * Field to hold the current number of elements in the list
	 */
	private int size;
	
	/**
	 * Constructs a new SwapList. Sets the size to zero and initializes a new empty
	 * underlying array.
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		this.size = 0;
		this.list = (E[]) new Object[INITIAL_CAPACITY];
	}

	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param element the element to be added
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException if the element cannot be added
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		try {
			checkCapacity(size);
			list[size()] = element;
			size++;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Cannot add element.");
		}
	}
	
	/**
	 * Private method that makes sure the array can fit one more element,
	 * if the array cannot hold an additional element, the array is grown
	 * to twice its size.
	 * @param size the size of the array to check
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int size) {
		if (size == list.length) {
			E[] temp = list;
			list = (E[]) new Object[size() * 2];

			for (int i = 0; i < temp.length; i++) {
				list[i] = temp[i];
			}
		}
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
		checkIndex(idx);
		E item = list[idx];
		for (int i = idx; i < size() - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size() - 1] = null;
		size--;
		return item;
	}
	
	/**
	 * Private method to check the index
	 * @param idx the index to check
	 * @throws IndexOutOfBoundsException of the given index is out of the list bounds.
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
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
		checkIndex(idx);
		if (idx > 0) {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
		}
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
		checkIndex(idx);
		if (idx < size() - 1) {
			E temp = list[idx + 1];
			list[idx + 1] = list[idx];
			list[idx] = temp;
		}
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
		checkIndex(idx);
		if (idx > 0) {
			E temp = list[idx];
			for (int i = idx; i > 0; i--) {
				list[i] = list[i - 1];
			}
			list[0] = temp;
		}
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
		checkIndex(idx);
		if (idx < size() - 1) {
			E temp = list[idx];
			for (int i = idx; i < size() - 1; i++) {
				list[i] = list[i + 1];
			}
			list[size() - 1] = temp;
		}
	}

	/**
	 * Returns the element at the given index
	 * 
	 * @param idx the index of the element to return
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds of the list
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}

	/**
	 * Returns the number of elements in the list
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}
