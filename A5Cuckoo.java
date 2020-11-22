import java.math.BigInteger;
import java.util.LinkedList;
/*
 * Complete the A5Cuckoo() method
 * Complete the A5Cuckoo(int size) method
 * Complete the insert(E val) method
 * Complete the contains(E val) method
 * Complete the get(E val) method
 * Complete the remove(E val) method
 * Complete the makeEmpty() method
 * Complete the hashForTable1(E val) method
 * Complete the hashForTable2(E val) method
 * Complete the rehash() method
 */
public class A5Cuckoo<E> {
	private E[] table1, table2;
	private int count;
	private int defaultSize = 7;
	/*
	 * Grading:
	 * Correctly initializes all values - 0.5pt
	 */
	public A5Cuckoo()
	{
		//create tables using defaultSize
		table1 = (E[]) new Object[nextPrime(defaultSize)];
		table2 = (E[]) new Object[nextPrime(defaultSize)];
		count = 0;
	}
	/*
	 * Grading:
	 * Correctly initializes all values - 0.5pt
	 */
	public A5Cuckoo(int size)
	{
		//create tables using prime larger than size
		table1= (E[]) new Object[nextPrime(size)];
		table2 = (E[]) new Object[nextPrime(size)];
		count = 0;
	}
	/*
	 * Grading:
	 * Correctly inserts value into available position - 1pt
	 * Correctly moves values 1 step - 1pt
	 * Correctly moves values multiple steps - 2pt
	 * Correctly determines when to rehash - 2pt
	 */
	public void insert(E val)
	{
		//add item to table1 or table 2
		//move other items between table1/table2 if needed
		//rehash if more than half full or can't complete insert
		E cur = val;
		if (count >= table1.length/2)
		{
			rehash();
		}
		
		while (true) {

			int t1Index = hashForTable1(cur);

			if (table1[t1Index] == null) 
			{
				table1[t1Index] = val;
				count++;
				break;
				
			} 
			
			else 
			{
				E store = table1[t1Index];
				table1[t1Index] = cur;
				cur = store;
				store = null;

				int t2Index = hashForTable2(cur);
				if (table2[t2Index] == null) 
				{
					table2[t2Index] = cur;
					count++;
					break;
				}
			}
		}
	}
	
	/*
	 * Grading:
	 * Correctly finds if value exists in table or not - 1pt
	 */
	public boolean contains(E val)
	{
		int i = hashForTable1(val);
		
		if(table1[i] != null && table1[i].equals(val))
		{
			return true;
		}
		i = hashForTable2(val);
		if (table2[i] != null && table2[i].equals(val))
		{
			return true;
		}
		return false;
		//return true/false if the value exists in the structure

	}
	/*
	 * Grading:
	 * Correctly returns value from table - 1pt
	 */
	public E get(E val)
	{
		if (this.contains(val))
			{
				int i = hashForTable1(val);
				if (table1[i].equals(val))
				{
					return val;
				}
				
				i = hashForTable2(val);
				if (table2[i].equals(val)) {
					return val;
				}
				return null;
			}
		//return the object if it exists in the structure
		return val;

	}
	/*
	 * Grading:
	 * Correctly removes value from table - 1pt
	 */
	public boolean remove(E val)
	{
		if (this.contains(val))
		{
			int i = hashForTable1(val);
			if (table1[i].equals(val))
			{
				table1[i] = null;
				return true;
			}
			i = hashForTable2(val);
	           if(table2[i].equals(val))
	           {
	               table2[i] = null;
	               return true;
	           }
		}
		return false;
	}

	/*
	 * Grading:
	 * Correctly empties both tables - 1pt
	 */
	public void makeEmpty()
	{
		//clear both table1 and table2 of values, maintain current size
		count = 0;
		table1 = (E[]) new Object[table1.length];
		table2 = (E[]) new Object[table2.length];
	}
	/*
	 * Grading:
	 * Correctly finds position for value - 1pt
	 * Uses full universal hashing formula - 1pt
	 */
	private int hashForTable1(E val)
	{
		//return a valid position in table1 using a form of Universal Hashing (slide 24 from part 3)
		//ensure the formula for finding a position is different for both
		return((((15421515 * val.hashCode() ) + 5415731) % nextPrime(table1.length)) % table1.length);
	}
	/*
	 * Grading:
	 * Correctly finds position for value - 1pt
	 * Uses full universal hashing formula - 1pt
	 * Ensure position is different than table1 for the majority of values - 1pt
	 */
	private int hashForTable2(E val)
	{
		//return a valid position in table2 using a form of Universal Hashing (slide 24 from part 3)
		//ensure the formula for finding a position is different for both
		return((((54125421 * val.hashCode() ) + 7512645) % nextPrime(table1.length)) % table1.length);

	}
	/*
	 * Grading:
	 * Correctly creates larger tables of correct sizes - 1pt
	 * Correctly rehashes values to new tables - 1pt
	 */
	private void rehash()
	{
		//make the tables the prime above double the current size and move values into the new tables
		E[] temp = table1;
		table1 = (E[]) new Object[nextPrime(table1.length*2)];
		E[] temp1 = table2;
		table2 = (E[]) new Object[nextPrime(table2.length*2)];
		makeEmpty();
		for (int i = 0; i < temp.length; i++)
		{
			if (temp[i] != null)
			{
				insert(temp[i]);
			}
		}
		for (int i = 0; i < temp.length; i++)
		{
			if (temp1[i] != null)
			{
				insert(temp1[i]);
			}
		}
		
	}
	
	/*
	 * COMPLETED METHODS
	 */
	public void testPositions(int n)
	{
		System.out.println("Value:Table1:Table2");
		for(Integer i = 0; i <= n; i++)
		{
			System.out.println(i+":"+hashForTable1((E)i)+":"+hashForTable2((E)i));
		}
	}
	private int nextPrime(int n)
	{
		return new BigInteger(""+n).nextProbablePrime().intValue();
	}
	public int getUsedSpace()
	{
		return count;
	}
	public int getAvailableSpace()
	{
		//total - used = available
		return table1.length+table2.length-count;
	}
	public String toString()
	{
		String output = "Table1\n";
		if(table1 != null)
		{
			for(E val : table1)
			{
				if(val != null)
					output += val+",";
			}
		}
		output += "Table2\n";
		if(table1 != null)
		{
			for(E val : table2)
			{
				if(val != null)
					output += val+",";
			}
		}
		return output;
	}
}
