import java.util.*;

public class PageSimulator {
	
	static int i = 0;
	public int nextPage()
	{
		int[] lor = {-1, 0, 1};
		int r =  (int)(Math.random()*10);
		if(r >= 0 && r < 7)
			i = (i + lor[(int)(Math.random()*3)]) % 9;
		if(r >= 7 && r<= 9)
			i = (int)(2 + Math.random()*6);
		return i;
	}
}
