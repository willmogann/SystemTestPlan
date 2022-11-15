package edu.ncsu.csc216.stp.model.util;

/**
 * This class implements the log interface. It is the implementation
 * of a list that can only add elements to the back of the list. It is
 * an Array-Based list which has functionality to get an element at an index
 * to get the current size of the list. 
 * The fields of this class include the underlying Array of this implementation
 * of Array List, the size of the array, and a static field for the initial capacity.
 * This array will hold objects of type TestResult which is the testResult, so it interacts with the
 * TestCase class.
 * 
 * @author William Morgan
 * @param <E> the type for the log
 */
public class Log<E> implements ILog<E> {
	
	/**
	 * The underlying array of the log. Holds objects of the generic type E
	 */
	private E[] log;
	
	/**
	 * The field to hold the current size of the list. 
	 */
	private int size;
	
	/**
	 * Final field to hold the initial size of the list.
	 */
	private static final int INIT_CAPACITY = 10;
	
	/**
	 * Constructs a new Log Array List. It creates a new
	 * underlying array with the initial size and sets the size 
	 * field to zero. 
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		size = 0;
		log = (E[]) new Object[INIT_CAPACITY];
	}

	/**
	 * Adds an element to the end of the list. This method allows
	 * duplicate elements to be added. 
	 * 
	 * @param element the element to be added
	 * @throws NullPointerException if the element to be added is null
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (size() == log.length) {
			growArray();
		}
		log[size] = element;
		size++;
	}
	
	/**
	 * Doubles the size of the list in the event the list has reached maximum
	 * capacity
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] temp = log;
		log = (E[]) new Object[size() * 2];

		for (int i = 0; i < temp.length; i++) {
			log[i] = temp[i];
		}
	}

	/**
	 * Searches the list for the element at the given index and returns that element
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the index given is out of bounds of the list
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return log[idx];
	}

	/**
	 * Returns the size of the list
	 * @return size the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
}
