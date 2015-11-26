package com.bighi.algos.week6.twosum;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

public class TwoSumFinder {
	private static final String FILENAME1 = "week6/2sum/2sum_small5.txt";
	
	public static void main(String[] args) throws IOException {
		System.out.println("Start time: " + new GregorianCalendar().getTime());
		new TwoSumFinder().readInputAndCalculate2Sums(FILENAME1);
		System.out.println("End time: " + new GregorianCalendar().getTime());
	}

	public void readInputAndCalculate2Sums(String fileName) throws IOException {
		
		HashSet<Long> set = new HashSet<>();
		HashSet<Long> sumSet = new HashSet<>();
		
        File file = new File(fileName);
        Path path = file.toPath();

        List<String> allInput = Files.readAllLines(path,
                Charset.defaultCharset());

        for (String ip : allInput) {
            Long ipNum = Long.valueOf(ip);
            set.add(ipNum);
        }
        
        int count = find2SumCount(set, sumSet, -10000, 10000);
        System.out.println(count);        
	}

	private int find2SumCount(HashSet<Long> set, HashSet<Long> sumSet, int startT, int endT) {
		int count = 0;		
		
		for(long i = startT; i <= endT; i++) {
			int subCount = find2SumCountForT(set, i, sumSet);
			count += subCount;
		}
		
		return count;
	}

	private int find2SumCountForT(HashSet<Long> set, long t, HashSet<Long> sumSet) {
		int count = 0;
		for(Long i: set) {
			long toFind = t - i;
			if(toFind != i && set.contains(toFind) && !sumSet.contains(t)) {
				count ++;
				sumSet.add(t);
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
