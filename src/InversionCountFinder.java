import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class InversionCountFinder {
	private static final String FILENAME1 = "week1/IntegerArray.txt";
	//private static final String FILENAME2 = "week1/sample_input.txt";

	public static void main(String[] args) throws IOException {
		InversionCountFinder finder = new InversionCountFinder();
		finder.doInversionCount();
	}
	
	private void doInversionCount() throws IOException {
		File file = new File(FILENAME1);
		Path path = file.toPath();
		
		List<String> allInput = Files.readAllLines(path, Charset.defaultCharset());
		
		Integer[] allNumbers = new Integer[allInput.size()];
		
		int i=0;
		for (String s: allInput) {
			allNumbers[i++] = Integer.parseInt(s);
		}
		
		//System.out.println(Arrays.toString(allNumbers));
		
		long count = findInversionCount(allNumbers);
		
		//System.out.println(Arrays.toString(allNumbers));
		
		System.out.println(count);
	}
	
	public long findInversionCount(Comparable<Integer>[] a)
	{
		Comparable<Integer>[] tmp = new Comparable[a.length];
		return mergeSortAndCount(a, tmp,  0,  a.length - 1);
	}


	private long mergeSortAndCount(Comparable [] a, Comparable [] tmp, int left, int right)
	{
		if( left < right )
		{
			int center = (left + right) / 2;
			long x = mergeSortAndCount(a, tmp, left, center);
			long y = mergeSortAndCount(a, tmp, center + 1, right);
			long z = mergeAndCountInversions(a, tmp, left, center + 1, right);		
			return x + y + z;
		}
		return 0;
	}


    private long mergeAndCountInversions(Comparable[] a, Comparable[] tmp, int left, int right, int rightEnd )
    {
        long inversions = 0;
    	
    	int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd) {
            if(a[left].compareTo(a[right]) <= 0) {
                tmp[k++] = a[left++];
            } else {
                tmp[k++] = a[right++];
                inversions += leftEnd - left + 1; 
            }
        }

        while(left <= leftEnd) {    // Copy rest of first half
            tmp[k++] = a[left++];
        }

        while(right <= rightEnd) {  // Copy rest of right half
            tmp[k++] = a[right++];
        }

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
        
        return inversions;
    }
}
