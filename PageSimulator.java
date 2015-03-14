import java.util.*;

public class PageSimulator {
	
	static int i = 0;
	public int getNextPage()
	{
		int r =  (int)(Math.random()*9);
		i = r;
		return i;
	}

}
