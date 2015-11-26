package com.bighi.algos.week6.twosum;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TwoSumFinderThread implements Runnable {
	private static final String FILENAME1 = "week6/2sum/2sum_small5.txt";
	//private static final String FILENAME1 = "week6/2sum_big_week6.txt";
	
	private static HashSet<Long> set = new HashSet<>();
	private static ConcurrentHashMap<Long, Long> sumConcMap = new ConcurrentHashMap<>();
	
	
	private static int numThreads = 10;
	private static AtomicInteger finalCounter = new AtomicInteger();
	
	private int startVal;
	private int endVal;
	
	public TwoSumFinderThread(int startVal, int endVal) {
	    this.startVal = startVal;
	    this.endVal = endVal;
	}
	
	@Override
	public void run() {
	    System.out.println("Thread: "+ Thread.currentThread().getName() + " Start time: " + new GregorianCalendar().getTime());
	    System.out.println("Thread: "+ Thread.currentThread().getName() + " processing from " + startVal + " to " + endVal);
        int count = find2SumCount(startVal, endVal);
        System.out.println("Thread: "+ Thread.currentThread().getName() + " processed " + count + " values");
        finalCounter.addAndGet(count);
        System.out.println("Thread: "+ Thread.currentThread().getName() + " End time: " + new GregorianCalendar().getTime());
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
	    System.out.println("Main thread: "+ Thread.currentThread().getName() + " Start time: " + new GregorianCalendar().getTime());
        readInputAndCalculate2Sums(FILENAME1);		
		int startT = -10000;
        int endT = 10000;
        
        int incr = (endT - startT + 1) / numThreads;
        
        ArrayList<Thread> thList = new ArrayList<>();
        
        int s = startT;
        int e = startT + incr;
        
        while(e <= endT) {
            TwoSumFinderThread thObj = new TwoSumFinderThread(s, e);
            Thread t = new Thread(thObj);
            thList.add(t);
            t.start();
            System.out.println("Thread: "+ t.getName() + " started");            
            s = e + 1;
            e = s + incr;
        }
        TwoSumFinderThread thObj = new TwoSumFinderThread(s, endT);
        Thread t = new Thread(thObj);
        thList.add(t);
        t.start();
        System.out.println("Thread: "+ t.getName() + " started");
        
        
        for(Thread t1 : thList) {
            System.out.println("Thread: "+ t1.getName() + " joining");
            t1.join();
            System.out.println("Thread: "+ t1.getName() + " joined");
        }
        
        System.out.println("Main thread: "+ Thread.currentThread().getName() + " Start time: " + new GregorianCalendar().getTime());
        
        System.out.println("Final count: "+finalCounter.get()); 
        
	}

	public static void readInputAndCalculate2Sums(String fileName) throws IOException {
		
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
