import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/*
 * Answers are:
size   first      last      median
10     25        29        21
100   615      587      518
1000 10297 10184  8921
 */

public class QuickSortMedianOf3 {

	private static final String FILENAME1 = "week2/QuickSort.txt";
	//private static final String FILENAME1 = "week2/1000.txt";
	static long masterCount = 0;
	
	public static void main(String[] args) {

		// Get a random generated array

		int[] a = getArray();

		System.out.println(new GregorianCalendar().getTime());
		
		// sort the array
		sort(a);

		System.out.println(new GregorianCalendar().getTime());

		// save the sorted array in file
		saveArray(a);
		
		System.out.println(masterCount);
	}

	// This method sorts an array and internally calls quickSort
	public static void sort(int[] a) {

		int left = 0;
		int right = a.length - 1;
		quickSort(a, left, right);

	}

	// This method is used to sort the array using quicksort algorithm.
	// It takes the left and the right end of the array as the two cursors.
	private static void quickSort(int[] a, int left, int right) {

		// If both cursor scanned the complete array quicksort exits
		if (left >= right)
			return;
		
		// For the simplicity, we took the right most item of the array as a
		// pivot
		int medianOf3Idx = getMedianOfThreeIdx(a, left, right);		
		swap(a, left, medianOf3Idx);
		int pivot = a[left];

		int partition = partition(a, left, right, pivot);

		// Recursively, calls the quicksort with the different left and right
		// parameters of the sub-array
		quickSort(a, left, partition - 1);
		quickSort(a, partition + 1, right);

	}

	private static int getMedianOfThreeIdx(int[] a, int left, int right) {
		int middle = (right - left)/2; 
		
		int leftElem = a[left];
		int middleElem = a[left + middle];
		int rightElem = a[right];
		
		int[] elems = new int[]{leftElem, middleElem, rightElem};
		Arrays.sort(elems);
		
		if(elems[1] == leftElem) {
			return left;
		}
		if(elems[1] == rightElem) {
			return right;
		}
		
		return left + middle;
	}

	// This method is used to partition the given array and returns the integer
	// which points to the sorted pivot index
	private static int partition(int[] a, int left, int right, int pivot) {
		masterCount = masterCount + right - left;
		int i = left + 1;
		for (int j=left+1; j <=right; j++) {
			if(a[j] < pivot) {
				swap(a, j, i);
				i++;
			}
		}
		swap(a, left, i-1);
		return i-1;
	}

	// This method is used to swap the values between the two given index
	public static void swap(int[] a, int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	public static void saveArray(int[] a) {
		File fOut = new File("week2/output3.txt");
		FileWriter fWriter;
		try {
			fWriter = new FileWriter(fOut);
			BufferedWriter writer = new BufferedWriter(fWriter);
			for(int num: a) {
				writer.write(String.valueOf(num));
				writer.newLine();
			}
			writer.flush();
			writer.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static int[] getArray() {

		File file = new File(FILENAME1);
		Path path = file.toPath();
		
		List<String> allInput = null;
		try {
			allInput = Files.readAllLines(path, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] allNumbers = new int[allInput.size()];
		
		int i=0;
		for (String s: allInput) {
			allNumbers[i++] = Integer.parseInt(s);
		}
		return allNumbers;
	}

}
