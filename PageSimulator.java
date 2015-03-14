import java.util.*;

public class PageSimulator {
	
	static int i = 0;
	public int nextPage()
	{
		int[] lor = {-1, 0, 1};
		int r =  (int)(Math.random()*10);
		if(r >= 0 && r < 7)
		{
			// Generate a random value r from 0 to 2
			// Place r in the locality of reference array to get value l: -1 , 0, or 1
			// Add l onto the last page reference i and modulo 9 the result to get a value within 0 - 9
			i = (i + lor[(int)(Math.random()*3)]) % 10;
			if(i<0)
				i = 9;
		}
		if(r >= 7 && r<= 9)
		{
			//Generate values 2-8 inclusive
			i = (int)(2 + Math.random()*7); 
		}
		return i;
	}
}
