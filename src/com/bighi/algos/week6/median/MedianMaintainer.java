package com.bighi.algos.week6.median;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.PriorityQueue;

public class MedianMaintainer {
	private static final String FILENAME1 = "week6/median/Median_big_week6.txt";

	private PriorityQueue<Integer> highHeap = new PriorityQueue<>(5000);
	private PriorityQueue<Integer> lowHeap = new PriorityQueue<>(5000, Collections.reverseOrder());
	
	public static void main(String[] args) throws IOException {
		System.out.println("Start time: " + new GregorianCalendar().getTime());
		new MedianMaintainer().readInputAndCalculateMedians(FILENAME1);
		System.out.println("End time: " + new GregorianCalendar().getTime());
	}

	public void readInputAndCalculateMedians(String fileName) throws IOException {

		File file = new File(fileName);
		Path path = file.toPath();

		List<String> allInput = Files.readAllLines(path, Charset.defaultCharset());

		int size = allInput.size();

		long sum = 0;
		
		for (int i = 0; i < size; i++) {
			String ip = allInput.get(i);
			int ipNum = Integer.valueOf(ip);
			int newMedian = insertToHeapAndGetMedian(ipNum);
			sum = sum + newMedian;
		}
		
		long tempSum = 0;
		for(int i : lowHeap) {
			tempSum = tempSum + i;
		}
		
		for(int i : highHeap) {
			tempSum = tempSum + i;
		}
		
		long expectedSum = (10000 * 10001)/2;
		
		System.out.println(expectedSum - tempSum);
		
		System.out.println(sum);
		System.out.println(sum % 10000);
		
	}

	private int insertToHeapAndGetMedian(int num) {

		if(lowHeap.size() > 0) {
			if(num > lowHeap.peek()) {
				highHeap.offer(num);
			} else {
				lowHeap.offer(num);
			}
		} else {
			lowHeap.offer(num);
		}

		return getMedianOfHeaps();
	}

	private int getMedianOfHeaps() {
		normalizeHeaps();
		if (lowHeap.size() < highHeap.size()) {
			return highHeap.peek();
		} else if (lowHeap.size() > highHeap.size()) {
			return lowHeap.peek();
		}
		return lowHeap.peek();
	}
	
	private void normalizeHeaps() {
		if(lowHeap.size() > highHeap.size() + 1) {
			highHeap.offer(lowHeap.remove());
		} else if(highHeap.size() > lowHeap.size() + 1) {
			lowHeap.offer(highHeap.remove());
		}
	}
}

/*
 * The goal of this problem is to implement the "Median Maintenance" algorithm
 * (covered in the Week 5 lecture on heap applications). The text file contains
 * a list of the integers from 1 to 10000 in unsorted order; you should treat
 * this as a stream of numbers, arriving one by one. Letting xi denote the ith
 * number of the file, the kth median mk is defined as the median of the numbers
 * x1,...,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number among
 * x1,...,xk; if k is even, then mk is the (k/2)th smallest number among
 * x1,...,xk.)
 * 
 * In the box below you should type the sum of these 10000 medians, modulo 10000
 * (i.e., only the last 4 digits). That is, you should compute
 * (m1+m2+m3+...+m10000)mod10000.
 * 
 * OPTIONAL EXERCISE: Compare the performance achieved by heap-based and
 * search-tree-based implementations of the algorithm.
 */

/*
 * New median = 6331, low heap size = 1, high heap size = 0, medians sum = 6331
 * New median = 2793, low heap size = 1, high heap size = 1, medians sum = 9124
 * New median = 2793, low heap size = 2, high heap size = 1, medians sum = 11917
 * New median = 2793, low heap size = 2, high heap size = 2, medians sum = 14710
 * New median = 2793, low heap size = 3, high heap size = 2, medians sum = 17503
 * New median = 1640, low heap size = 3, high heap size = 3, medians sum = 19143
 * New median = 2793, low heap size = 3, high heap size = 4, medians sum = 21936
 * New median = 2303, low heap size = 4, high heap size = 4, medians sum = 24239
 * New median = 2793, low heap size = 4, high heap size = 5, medians sum = 27032
 * New median = 2303, low heap size = 5, high heap size = 5, medians sum = 29335
 */