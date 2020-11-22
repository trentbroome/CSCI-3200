import java.util.Arrays;
import java.util.Random;

public class A6Driver {

	public static void main(String[] args) {
		Integer[] arr1 = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		Integer[] arr2 = new Integer[] {10,9,8,7,6,5,4,3,2,1};
		Integer[] arr3 = new Integer[] {1,4,7,10,2,5,8,3,6,9};
		System.out.println("kth Smallest:");
		System.out.println(Arrays.toString(A6Work.kthSmallest(5, arr1)));
		System.out.println(Arrays.toString(A6Work.kthSmallest(3, arr2)));
		System.out.println(Arrays.toString(A6Work.kthSmallest(7, arr3)));
		System.out.println();
		System.out.println("kth Largest:");
		System.out.println(Arrays.toString(A6Work.kthLargest(5, arr1)));
		System.out.println(Arrays.toString(A6Work.kthLargest(3, arr2)));
		System.out.println(Arrays.toString(A6Work.kthLargest(7, arr3)));
		System.out.println();
		
		Random randGen = new Random(123456789);
		randGen.nextInt(10);//better timing values
		long time = System.nanoTime();//better timing values
		A6BH<Integer> heap = new A6BH<>();
		heap.insert(5);//better timing values


		int N = 100000;
		Integer[] randoms = new Integer[N];
		time = System.nanoTime();
		for(int j = 0; j < randoms.length; j++)
		{
			randoms[j] = randGen.nextInt(N)+1;
		}
		time = System.nanoTime() - time;
		System.out.println("Generate "+N+" Randoms: "+(time/1000000000.0)+ " seconds");
		time = System.nanoTime();
		heap = new A6BH<>(randoms);
		time = System.nanoTime() - time;
		System.out.println("Bulk insert: " + (time/1000000000.0)+" seconds");
		System.out.println("Bulk insert: " + heap.getOpCount()+" operations (correct is > 2,000,000)");
		time = System.nanoTime();
		heap = new A6BH<>();
		for(int j = 0; j < randoms.length; j++)
		{
			heap.insert(randoms[j]);
		}
		time = System.nanoTime() - time;
		System.out.println("Individual insert: " + (time/1000000000.0)+" seconds");
		System.out.println("Individual insert: " + heap.getOpCount()+" operations (correct is > 2,000,000)");
		System.out.println();

	}

}
