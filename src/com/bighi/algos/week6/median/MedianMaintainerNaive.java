package com.bighi.algos.week6.median;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class MedianMaintainerNaive {

	private static final String FILENAME1 = "week6/median/Median_big_week6.txt";

	public static void main(String[] args) throws IOException {

		FileWriter fw = new FileWriter("median_big_output_naive.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		File file = new File(FILENAME1);
		Path path = file.toPath();
		
		int[] list = new int[10000];
        Scanner sc = new Scanner(path);
        long sum = 0;
        int item = 0;

        for (int i = 0; i < list.length; i++) {

            list[i] = sc.nextInt();
            Arrays.sort(list, 0, i+1);
            if(i % 2 == 0) {
            	item = list[i/2]; 
            } else {
            	item = list[(i-1)/2];
            }
            sum += item;
            bw.write(String.valueOf(item));
            bw.newLine();
        }
        System.out.println(sum);
        System.out.println(sum%10000);
        bw.close();
        sc.close();
	}
}
