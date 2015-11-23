package com.bighi.algos.week6.twosum;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TwoSumFinderThread implements Runnable {
	//private static final String FILENAME1 = "week6/2sum_small1.txt";
	private static final String FILENAME1 = "week6/2sum_big_week6.txt";
	
	private HashSet<Long> set = new HashSet<>();
	private ConcurrentHashMap<Long, Long> sumConcMap = new ConcurrentHashMap();
	
	
	private static int numThreads = 2;
	private static AtomicInteger count = new AtomicInteger();
	
	@Override
	public void run() {
	    System.out.println("Start time: " + new GregorianCalendar().getTime()+ " Thread: "+ Thread.currentThread().getName());
        int startVal = -10000;
        int endVal = 10000;
        
        int ptr = (endVal - startVal) / 2;
        
        for(int i=0; i< numThreads; i++) {
            count.set(count.get() + find2SumCount(startVal, startVal + ptr));
            startVal = startVal + ptr + 1;
        }
        System.out.println(count);        
        System.out.println("End time: " + new GregorianCalendar().getTime());
	}
	
	public static void main(String[] args) throws IOException {
		new TwoSumFinderThread().readInputAndCalculate2Sums(FILENAME1);
		
		
	}

	public void readInputAndCalculate2Sums(String fileName) throws IOException {
		
        File file = new File(fileName);
        Path path = file.toPath();

        List<String> allInput = Files.readAllLines(path,
                Charset.defaultCharset());

        for (String ip : allInput) {
            Long ipNum = Long.valueOf(ip);
            set.add(ipNum);
        }       
	}

	private int find2SumCount(int startT, int endT) {
		int count = 0;		
		
		for(long i = startT; i <= endT; i++) {
			int subCount = find2SumCountForT(set, i);
			count += subCount;
		}
		
		return count;
	}

	private int find2SumCountForT(HashSet<Long> set, long t) {
		int count = 0;
		for(Long i: set) {
			long toFind = t - i;
			if(toFind != i && set.contains(toFind) && !sumConcMap.contains(t)) {
				count ++;
				sumConcMap.put(t, t);
			}
		}
		return count;
	}

}

/*
 * The goal of this problem is to implement a variant of the 2-SUM algorithm
 * (covered in the Week 6 lecture on hash table applications). The file contains
 * 1 million integers, both positive and negative (there might be some
 * repetitions!).This is your array of integers, with the ith row of the file
 * specifying the ith entry of the array.
 * 
 * Your task is to compute the number of target values t in the interval
 * [-10000,10000] (inclusive) such that there are distinct numbers x,y in the
 * input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a
 * one-line addition to the algorithm from lecture.)
 * 
 * Write your numeric answer (an integer between 0 and 20001) in the space
 * provided.
 * 
 * 
 * OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing
 * your own hash table for it. For example, you could compare performance under
 * the chaining and open addressing approaches to resolving collisions.
 */
