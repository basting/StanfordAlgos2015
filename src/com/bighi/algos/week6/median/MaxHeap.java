package com.bighi.algos.week6.median;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class MaxHeap implements Iterable<Integer>{ //extends Heap<Integer> {

	private PriorityQueue<Integer> maxPriorityHeap = new PriorityQueue<>(5000, new MaxHeapComparator());
	
	/*public MaxHeap() {
		this(new MaxHeapComparator());
	}

	public MaxHeap(Comparator<Integer> comparator) {
		super(comparator);
	}*/

	public Integer pop() {
		return maxPriorityHeap.remove();
	}
	
	public void insert(int e) {
		maxPriorityHeap.add(e);
	}
	
	public Integer top() {
		return maxPriorityHeap.peek();
	}
	
	public int size() {
		return maxPriorityHeap.size();
	}
	
	@Override
	public Iterator<Integer> iterator() { 
		return maxPriorityHeap.iterator();
	}
	
	public boolean contains(int elem) {
		return maxPriorityHeap.contains(elem);
	}
	
	static class MaxHeapComparator implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	}

}
