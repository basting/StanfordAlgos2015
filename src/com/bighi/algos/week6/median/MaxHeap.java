package com.bighi.algos.week6.median;

import java.util.Comparator;

public class MaxHeap extends Heap<Integer> {

	public MaxHeap() {
		this(new MaxHeapComparator());
	}

	public MaxHeap(Comparator<Integer> comparator) {
		super(comparator);
	}

	static class MaxHeapComparator implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	}

}
