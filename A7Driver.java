import java.util.Arrays;

public class A7Driver {

	public static void main(String[] args) {
		String[] answer;
		int N = 10;
		Integer[][] matrix = new Integer[N][N];
		int c = 1;
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[0].length; j++, c+=2)
			{
				matrix[i][j] = c;
			}
		}
		System.out.println("Matrix");
		System.out.println(Arrays.deepToString(matrix).replaceAll("], ", "],\n"));
		answer = A7Work.contains(5, matrix);
		System.out.println(answer[0].equals("true"));
		answer = A7Work.contains(6, matrix);
		System.out.println(answer[0].equals("false"));
		
		String[] words = new String[]{"abc", "z", "abzf", "12", " a b c"};
		System.out.println("Before Sort");
		System.out.println(Arrays.toString(words));
		A7Work.radixSortStrings(words);
		System.out.println("After Sort");
		System.out.println(Arrays.toString(words));
		System.out.println("Sorted Correctly: "+(Arrays.toString(words).equals("[ a b c, 12, abc, abzf, z]")));
	}

}
