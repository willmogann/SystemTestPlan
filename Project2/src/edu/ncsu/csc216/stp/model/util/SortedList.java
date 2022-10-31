package edu.ncsu.csc216.stp.model.util;

/**
 * This class is an implementation of a LinkedList which keeps elements added
 * in a sorted order. This class implements the ISortedList interface and the
 * generic type input must extend the comparable class in order to be used. 
 * There is a field that holds the current size of the list.
 * The behavior of this class includes the ability to add the element in sorted order,
 * remove an element at a given index, see if the list contains an element,
 * get the element at an index, and get the size of the list. 
 * This class contains an inner class called ListNode which represents each
 * data point in the list. Each data point will have its data field and a pointer to the 
 * next node in the list. 
 * This class interacts with the TestPlanManager class to hold the list of TestPlans.
 * 
 * @author William Morgan
 * @param <E> the generic object that the sorted list holds
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	
	/**
	 * Field to hold the current size of the list
	 */
	private int size;
	
	/**
	 * Field to hold the first pointer in the list, which points to
	 * the first listNode.
	 */
	private ListNode front;
	
	/**
	 * Constructs a new SortedList. Sets the size to zero and the front ListNode to null.
	 */
	public SortedList() {
		
	}

	/**
	 * Adds an element in sorted order to the list using the comparable.compareTo() method.
	 * This method does not allow duplicate elements to be added, or null elements to be added.
	 * 
	 * @param element the element to be added to the list
	 * @throws NullPointerException if the element parameter is null
	 * @throws IllegalArgumentException if the element is a duplicate element
	 */
	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Searches the list for the element at the given index and removes it.
	 * 
	 * @param idx the index of the element to remove
	 * @return the element that is removed
	 * @throws IndexOutOfBoundsException if the given idx is not in the current list
	 */
	@Override
	public E remove(int idx) {
		// TODO Auto-generated method stub
		return null;
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
	 * Searches the list and attempts to find the given element. If the element 
	 * is found, then the list contains the element, it does not otherwise.
	 * 
	 * @param element the element to look for
	 * @return true if the list contains the element, false otherwise
	 */
	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Searches the list and returns the element at the given index.
	 * 
	 * @param idx the index of the element to search for
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException of the given index is out of the list bounds.
	 */
	@Override
	public E get(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the current size of the list
	 * @return the size of the list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Inner class to hold each node in the LinkedList. Holds a field for the data of the given node
	 * and another field which points to the next node in the list.
	 */
	private class ListNode {
		
		/**
		 * Field that holds the data for the given node
		 */
		public E data;
		
		/**
		 * Field that holds the pointer to the next node
		 */
		public ListNode next;
		
		/**
		 * Constructs a new ListNode. Sets the data field to the generic type E 
		 * and also sets the pointer to the next ListNode through the parameter
		 * 
		 * @param data the data to set
		 * @param next the next ListNode in the list
		 */
		public ListNode(E data, ListNode next) {
			
		}
	}

}
