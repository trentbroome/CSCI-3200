import java.util.PriorityQueue;
import java.util.Random;

public class A6Work {
	private static Random randGen = new Random();
	/*
	 * Add operation counts to all methods in A6BH - 4pts
	 */
	
	/*
	 * Grading: 
	 * Create a heap from the values in the array in the most efficient method: 1pt
	 * Remove the k smallest values from the heap: 0.5pt
	 * Return the kth smallest value from the heap: 0.5pt
	 * f(N): 0.5pt
	 * O(N): 0.5pt
	 */


	public static String[] kthSmallest(int k, Integer[] arr)
	{
		/*
		 * Create a heap from the array, ensure this is done in the most efficient way possible
		 * Perform k deleteMin() operations
		 * Return the final value returned from deleteMin() as the kth smallest
		 * When calculating f(N)/O(N), ensure you mention the BigO notation for each heap method used
		 * - You can use the BigO reduction from the heap methods in your f(N)
		 */
		//randGen.nextInt(N);//count as 1 unit of time
		A6BH<Integer> heap = new A6BH<>(arr);
		for (int i = 0; i < k - 1; i++)
		{
			heap.deleteMin();
		}
		String fn = "f(N) = [2NLogN + 3N + 5]";
		String on = "O(N) = [NLogN]";
		String kth = heap.deleteMin().toString();
		return new String[] {kth,fn,on};//1+logN
		
	}
	/*
	 * Grading: 
	 * Create a heap of first k values: 1.25pt
	 * Correctly maintain heap size while adding/removing values: 1.25pt
	 * Return the kth largest value from the heap: 0.5pt
	 * f(N): 0.5pt
	 * O(N): 0.5pt
	 */
	public static String[] kthLargest(int k, Integer[] arr)
	{
		/*
		 * Create a heap from the first k values in the array
		 * For all other values in the array
		 * - if the value is larger than the smallest in the heap, remove the smallest from the heap and add the larger value
		 * - if the value is smaller than the smallest in the heap, continue to the next value
		 * Return the smallest value in the heap as the kth largest
		 * When calculating f(N)/O(N), ensure you mention the BigO notation for each heap method used
		 * - You can use the BigO reduction from the heap methods in your f(N)
		 */
		//randGen.nextInt(N);//count as 1 unit of time
		A6BH<Integer> heap = new A6BH<>();
		
		for (int i = 0; i < k; i++)
		{
			heap.insert(arr[i]);
		}
		for(int i = k; i < arr.length; i++) // 7NlogN
		{
			
			if(heap.findMin().compareTo(arr[i]) < 0)
			{
				heap.deleteMin(); // NlogN
				heap.insert(arr[i]); //NlogN
			}
			
		}
		String fn = "f(N) = [10NlogN + 3N + 4]";
		String on = "O(N) = [NlogN]";
		String kth = heap.deleteMin().toString();
		return new String[] {kth,fn,on};//1+logN
	}
	
}
