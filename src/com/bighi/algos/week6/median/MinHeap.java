package com.bighi.algos.week6.median;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


public class MinHeap implements Iterable<Integer>{//extends Heap<Integer> {
	private PriorityQueue<Integer> minPriorityHeap = new PriorityQueue<>(5000, new MinHeapComparator());
	/*public MinHeap() {
		this(new MinHeapComparator());
	}

	public MinHeap(Comparator<Integer> comparator) {
		super(comparator);
	}*/
	
	public Integer pop() {
		return minPriorityHeap.remove();
	}
	
	public void insert(int e) {
		minPriorityHeap.add(e);
	}
	
	public Integer top() {
		return minPriorityHeap.peek();
	}
	
	public int size() {
		return minPriorityHeap.size();
	}

	@Override
	public Iterator<Integer> iterator() { 
		return minPriorityHeap.iterator();
	}
	
	public boolean contains(int elem) {
		return minPriorityHeap.contains(elem);
	}
	
	static class MinHeapComparator implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	}
}
