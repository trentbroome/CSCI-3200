import java.util.LinkedList;
import java.util.List;

public class A7Work {
	/*
	 * Grading: 
	 * Search the two-dimensional array for the value and return if it exists in the matrix or not - 2pt
	 * The matrix is guaranteed to have each row/column sorted, and the number of rows/columns are equal (NxN) - see driver for example
	 * f(N) - 0.5pts
	 * O(N) - 0.5pts
	 * opCounts - 0.5pts
	 * compareCounts - 0.5pts
	 * No more than 2N-1 compares - 2pt
	 * No more than Ceiling(log(N^2)) compares - Bonus 1pt
	 */
	public static <E extends Comparable<? super E>> String[] contains(E val, E[][] matrix)
	{
		long opCount = 0;
		long compareCount = 0;
		int i = 0; 
		opCount++;
		int j = matrix[0].length - 1;
		opCount++;
		boolean ans = false;
		
		while (i < matrix.length && j >= 0)
		{
			opCount+=3;
			if (val.compareTo(matrix[i][j]) == 0)
			{
				opCount+=2;
				compareCount++;
				ans = true;
				opCount++;
				break;
			}
			else if (val.compareTo(matrix[i][j]) == 0)
			{
				opCount+=2;
				compareCount++;
				i++;
			}
			else
			{
				opCount++;
				compareCount++;
				j--;
			}
		}
		//Determine if the val exists in the matrix as efficiently as possible
		//increment compareCounts every time you compare val to an position in the matrix
		//for notation, consider the number of rows/columns to be N, so the matrix contains N^2 values (rows/columns always equal)
		String ans_str = ans? "true":"false";
		String fn = "f(N) = 6N + 5";
		String on = "O(N) = N";
		return new String[] {ans_str, fn, on, ""+opCount, ""+compareCount};

	}

	/*
	 * Grading: 
	 * Modify the String radix sort for same length strings to work for multiple length strings - 2pts
	 * Do not add any additional data structures to the sort
	 * It is ok to determine the max length of strings in the array, but no other variables should be added
	 */
	public static void radixSortStrings(String[] arr)
	{
		//Configure this method to perform a radix sort on an array of Strings of various lengths
		//use the version below that requires all the strings to be the same length as a starting point
		//very little should need to be modified for this to work
		int maxLength = 0;
		for (String s: arr)
		{
			if(maxLength < s.length())
			{
				maxLength = s.length();
			}
			int bucketCount = 256;
			List<String>[] buckets = new LinkedList[bucketCount];
			for(int i = 0; i < buckets.length; i++)
			{
				buckets[i] = new LinkedList<>();
			}

			for(int strpos = maxLength-1; strpos >= 0; strpos--)
			{
				for(int i = 0; i < arr.length; i++)

				{


				if(arr[i].length()-1>=strpos)

				buckets[arr[i].charAt(strpos)].add(arr[i]);


				else//if length of string is less than currChar value then add that string to bucket 0 as 0 ASCII number equals to null char

				buckets[0].add(arr[i]);

				}
				int pos = 0;
				for(int i = 0; i < buckets.length; i++)
				{
					for(String item : buckets[i])
					{
						arr[pos] = item;
						pos++;
					}
					buckets[i].clear();//O(1)
				}
			}
		}
	}
		
		
	
	
	/*DO NOT MODIFY*/
	public static void radixSortStrings(String[] arr, int stringLen)
	{
		//number of buckets = 256 (characters in the character set)
		int bucketCount = 256;
		//if you were doing a case insensitive sort, and you knew everything was single words, you could use 26 as your size

		//Buckets need to be lists instead of counters
		List<String>[] buckets = new LinkedList[bucketCount];
		//create array of lists and initialize each object
		for(int i = 0; i < buckets.length; i++)
		{
			buckets[i] = new LinkedList<>();
		}

		//loop from end of string to beginning
		for(int strpos = stringLen-1; strpos >= 0; strpos--)
		{
			//loop through each string
			for(String item : arr)
			{
				//add to appropriate bucket
				buckets[item.charAt(strpos)].add(item);
				//item.charAt(strpos) converts to int automatically
				//A = 65, a = 97, 0 = 48, space = 32
			}
			//pointer for position in original list
			int pos = 0;
			//loop through buckets
			for(int i = 0; i < buckets.length; i++)
			{
				//loop through items in each buck
				for(String item : buckets[i])
				{
					//add each string back to original array in new order
					arr[pos] = item;
					pos++;
				}
				//clear the bucket
				buckets[i].clear();//O(1)
			}
			//System.out.println("Sorted on "+strpos+" : "+Arrays.toString(arr));
		}
	}
}
