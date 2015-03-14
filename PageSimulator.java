import java.util.*;

public class PageSimulator {
	
	static int page = 0;
	public int nextPage()
	{
		int[] lor = {-1, 0, 1};
		int r =  (int)(Math.random()*10);
		if(r >= 0 && r < 7)
		{
			// Generate a random value r from 0 to 2
			// Place r in the locality of reference array to get value l: -1 , 0, or 1
			// Add l onto the last page reference i and modulo 9 the result to get a value within 0 - 9
			page = (page + lor[(int)(Math.random()*3)]) % 10;
			if(page<0)
				page = 9;
		}
		if(r >= 7 && r<= 9)
		{
			//Generate values 2-8 inclusive
			page = (int)(2 + Math.random()*7); 
		}
		return page;
	}
	
	public String printMemoryMap(LinkedList physicalMemory)
	{
		ListIterator<Integer> p = physicalMemory.listIterator();
		String memoryMap = "";
		while (p.hasNext())
        			memoryMap = memoryMap + "[" + p.next() + "]";
		memoryMap = memoryMap + "";
		return memoryMap;
	}
	
	public void fifo()
	{
		LinkedList<Integer> physicalMemory = new LinkedList<Integer>();
		int hits = 0;
		int pagedIn = 0;
		int evictedPage = 0;
		// Go through 100 page references
		for(int i=0; i<100; i++)
		{
			int pageBlock = nextPage();
			if(physicalMemory.contains(pageBlock))
			{
				hits++;
			}
			else
			{
				if(physicalMemory.size() < 4)
				{
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock;
				}
				else
				{
					evictedPage = physicalMemory.remove();
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock;
				}
			}
			String memoryMap = printMemoryMap(physicalMemory);
			System.out.println("Physical Memory = " + memoryMap + "\tHits = " + hits + "\tPaged In = " + pagedIn + "\tEvicted Page = " + evictedPage);
		}
	}
	
	
	public void lru()
	{
	}
	
	public void lfu()
	{
	}
	
	public void mfu()
	{
	}
	
	public void randomPick()
	{
	}
}
