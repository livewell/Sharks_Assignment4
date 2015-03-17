import java.util.*;

public class PageSimulator {
	
	static int page = 0;
	static ArrayList<Page> pageReferences = new ArrayList<Page>();
	static ArrayList<Page> lruPages, lfuPages, mfuPages;


	ArrayList<Page> pages = new ArrayList<Page>();

	public PageSimulator()
	{
		for(int i=0; i<10; i++)
			pages.add(new Page(i));
		// Use the same 100 page references for each algorithm
		// in order to accurately and fairly compare each algorithm.
		for(int i=0; i<100; i++)
			pageReferences.add(pages.get(nextPage()));
		
		lruPages = (ArrayList<Page>)pageReferences.clone();
		lfuPages = (ArrayList<Page>)pageReferences.clone();
		mfuPages = (ArrayList<Page>)pageReferences.clone();
	}
	
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
		ListIterator<Page> p = physicalMemory.listIterator();
		String memoryMap = "";
		while (p.hasNext())
        			memoryMap = memoryMap + "[" + p.next().getAddress() + "]";
		memoryMap = memoryMap + "";
		return memoryMap;
	}
	
	public double fifo()
	{
		LinkedList<Page> physicalMemory = new LinkedList<Page>();
		int hits = 0;
		int pagedIn = 0;
		int evictedPage = 0;
		// Go through 100 page references
		for(int i=0; i<100; i++)
		{
			Page pageBlock = pageReferences.get(i);
			if(physicalMemory.contains(pageBlock))
			{
				hits++;
				String memoryMap = printMemoryMap(physicalMemory);
				System.out.println("Physical Memory = " + memoryMap + "\tHits = " + hits + "\tPaged In = *" + "\tEvicted Page = *" + "\tReferenced Page = " + pageBlock.getAddress());

			}
			else
			{
				if(physicalMemory.size() < 4)
				{
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = *");
				}
				else
				{
					evictedPage = physicalMemory.remove().getAddress();
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = " + evictedPage);
				}
			}
		}
		double hitRatio = (double)(hits/100.0);
		return hitRatio;
	}
	
	
	public double lru()
	{
		LinkedList<Page> physicalMemory = new LinkedList<Page>();
		int hits = 0;
		int pagedIn = 0;
		int evictedPage = 0;
		// Go through 100 page references
		for(int i=0; i<100; i++)
		{
			Page pageBlock = lruPages.get(i);
			if(physicalMemory.contains(pageBlock))
			{
				hits++;
				pageBlock.setCounter(i);
				String memoryMap = printMemoryMap(physicalMemory);
				System.out.println("Physical Memory = " + memoryMap + "\tHits = " + hits + "\tPaged In = *" + "\tEvicted Page = *" + "\tReferenced Page = " + pageBlock.getAddress());
			}
			else
			{
				if(physicalMemory.size() < 4)
				{
					pageBlock.setCounter(i);
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = *");
				}
				else
				{
					// LRU Evict the right page
					int index=0;
					int removeIndex = 0;
					evictedPage = 0;
					ListIterator<Page> p = physicalMemory.listIterator();
					while (p.hasNext())
					{
						index = p.nextIndex();
						Page currentPage = p.next();
						int lastReference = i - currentPage.getCounter();
						if(lastReference > evictedPage)
						{
							evictedPage = lastReference;
							removeIndex = index;
						}
					}
					evictedPage = physicalMemory.remove(removeIndex).getAddress();
					pageBlock.setCounter(i);
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = " + evictedPage);
				}
			}
		}
		double hitRatio = (double)(hits/100.0);
		return hitRatio;
	}
	
	public double lfu()
	{
		LinkedList<Page> physicalMemory = new LinkedList<Page>();
		int hits = 0;
		int pagedIn = 0;
		int evictedPage = 0;
		// Go through 100 page references
		for(int i=0; i<100; i++)
		{
			Page pageBlock = lfuPages.get(i);
			if(physicalMemory.contains(pageBlock))
			{
				hits++;
				pageBlock.updateCounter();
				String memoryMap = printMemoryMap(physicalMemory);
				System.out.println("Physical Memory = " + memoryMap + "\tHits = " + hits + "\tPaged In = *" + "\tEvicted Page = *" + "\tReferenced Page = " + pageBlock.getAddress());
			}
			else
			{
				if(physicalMemory.size() < 4)
				{
					pageBlock.updateCounter();
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = *");
				}
				else
				{
					// LFU Eviction
					int index=0;
					int removeIndex = 0;
					evictedPage = 100;
					ListIterator<Page> p = physicalMemory.listIterator();
					while (p.hasNext())
					{
						index = p.nextIndex();
						Page currentPage = p.next();
						int leastFrequent = currentPage.getCounter();
						if(evictedPage > leastFrequent)
						{
							evictedPage = leastFrequent;
							removeIndex = index;
						}
					}
					Page e = physicalMemory.remove(removeIndex);
					evictedPage = e.getAddress();
					e.setCounter(0);
					pageBlock.updateCounter();
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = " + evictedPage);
				}
			}
		}
		double hitRatio = (double)(hits/100.0);
		return hitRatio;
	}
	
	public Double mfu()
	{
		LinkedList<Page> physicalMemory = new LinkedList<Page>();
		int hits = 0;
		int pagedIn = 0;
		int evictedPage = 0;
		// Go through 100 page references
		for(int i=0; i<100; i++)
		{
			Page pageBlock = mfuPages.get(i);
			if(physicalMemory.contains(pageBlock))
			{
				hits++;
				pageBlock.updateCounter();
				String memoryMap = printMemoryMap(physicalMemory);
				System.out.println("Physical Memory = " + memoryMap + "\tHits = " + hits + "\tPaged In = *" + "\tEvicted Page = *" + "\tReferenced Page = " + pageBlock.getAddress());
			}
			else
			{
				if(physicalMemory.size() < 4)
				{
					pageBlock.updateCounter();
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = *");
				}
				else
				{
					// MFU Eviction
					int index=0;
					int removeIndex = 0;
					evictedPage = -1;
					ListIterator<Page> p = physicalMemory.listIterator();
					while (p.hasNext())
					{
						index = p.nextIndex();
						Page currentPage = p.next();
						int mostFrequent = currentPage.getCounter();
						if(evictedPage < mostFrequent)
						{
							evictedPage = mostFrequent;
							removeIndex = index;
						}
					}
					Page e = physicalMemory.remove(removeIndex);
					evictedPage = e.getAddress();
					e.setCounter(0);
					pageBlock.updateCounter();
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = " + evictedPage);
				}
			}
		}
		double hitRatio = (double)(hits/100.0);
		return hitRatio;
	}
	
	public double randomPick()
	{
		LinkedList<Page> physicalMemory = new LinkedList<Page>();
		int hits = 0;
		int pagedIn = 0;
		int evictedPage = 0;
		// Go through 100 page references
		for(int i=0; i<100; i++)
		{
			Page pageBlock = pageReferences.get(i);
			if(physicalMemory.contains(pageBlock))
			{
				hits++;
				String memoryMap = printMemoryMap(physicalMemory);
				System.out.println("Physical Memory = " + memoryMap + "\tHits = " + hits + "\tPaged In = *" + "\tEvicted Page = *" + "\tReferenced Page = " + pageBlock.getAddress());
			}
			else
			{
				if(physicalMemory.size() < 4)
				{
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = *");
				}
				else
				{
					evictedPage = physicalMemory.remove((int)(Math.random()*4)).getAddress();
					physicalMemory.add(pageBlock);
					pagedIn = pageBlock.getAddress();
					String memoryMap = printMemoryMap(physicalMemory);
					System.out.println("Physical Memory = " + memoryMap + "\tHits = *" + "\tPaged In = " + pagedIn + "\tEvicted Page = " + evictedPage);
				}
			}
		}
		double hitRatio = (double)(hits/100.0);
		return hitRatio;
	}
}
