package com.bighi.algos.week6.median;

import java.util.Comparator;


public class MinHeap extends Heap<Integer> {
	public MinHeap() {
		this(new MinHeapComparator());
	}

	public MinHeap(Comparator<Integer> comparator) {
		super(comparator);
	}

	static class MinHeapComparator implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	}
}
