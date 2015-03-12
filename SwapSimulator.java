import java.util.*;

public class SwapSimulator {
	
	LinkedList<Process> memory = new LinkedList<Process>();
	
	public void firstFit()
	{
		ArrayList<Process> processQueue = new ArrayList<Process>();
		// Create some processes
		for(int i=0; i<10; i++)
		{
			processQueue.add(new Process(i, ))
		}
		
	}
	
	public void add(Process p)
	{
		memory.add(p);
	}
	
	public int size()
	{
	        int size =  (int)(Math.random()*4);
	        switch(size)
	        {
	        	case 0: return 5;
	        	case 1: return 11;
	        	case 2: return 17;
	        	default: return 31;
	        }
	}

}
