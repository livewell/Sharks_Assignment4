import java.util.*;

public class SwapSimulator {
	
	LinkedList<Process> memory = new LinkedList<Process>();
	int swapCounter = 0;
	
	public SwapSimulator()
	{
		memory.add(new Process(0, 100, 0, 0, false, true));
	}
	
	public void firstFit(ArrayList<Process> processQueue) throws InterruptedException
	{
		printMemoryMap();		
		for(int i=0; i<60; i++)
		{
	    	//Below the code should be changed to a loop so that each as many process as possible get pushed into memory////////
			/// CODE CHANGE/////////
			// Add processes
	    	Iterator<Process> queueIter = processQueue.iterator();
	    	if(queueIter.hasNext())
	    	{
	    		Process p = queueIter.next();
	    		if(add(p))
				{
	    			// Remove process from queue
	    			queueIter.remove();
	    			// Print memory map
	        		System.out.println("Swap In   [P" + p.id + ", " + p.getSize() + "MB, " + p.getDuration() + "sec]");
					printMemoryMap();
				}
	    		else
	    		{
	    			// Remove process from queue
	    			queueIter.remove();
	    			// Add unallocated process to end of queue
	    			processQueue.add(p);
	    		}
	    	}
	    	////// MUST ADD AS MANY PROCESSES AS POSSIBLE////////
	    	// Here for debugging purpose
	    	Thread.sleep(1000);
				
			// Decrement processes
	    	decrementProcess();
	    }	
	}
	
	public boolean add(Process process)
	{
		ListIterator<Process> listIterator = memory.listIterator();
        while (listIterator.hasNext()) {
        	Process proc = listIterator.next();
        	
            if(proc.isHole() && (proc.size >= process.size))
            {
            	// Replace the hole with the current process
            	listIterator.set(process);
            	swapCounter++;
            	// Split the empty hole in half
            	if(proc.size != process.size)
            	{
            		// Add the left over hole after this process
            		proc.size = proc.size - process.size;
            		listIterator.add(proc);
            	}
            	return true;
            }        	
        }     
        return false;
	}
	
	public void decrementProcess()
	{
		ListIterator<Process> listIterator = memory.listIterator();
		while (listIterator.hasNext()) 
		{
        	Process proc = listIterator.next();
			if(!proc.isHole())
			{
				if(proc.getDuration() > 0)
				{
					proc.setDuration(proc.getDuration() - 1);
				}
				else
				{
					proc.setHole(true);
	        		System.out.println("Swap Out   [P" + proc.id + ", " + proc.getSize() + "MB, " + proc.getDuration() + "sec]");
					printMemoryMap();
				}
			}
		}
	}
	
	public void printMemoryMap()
	{
		ListIterator<Process> listIterator = memory.listIterator();
		System.out.print("Swaps[" + swapCounter + "]  ");
        System.out.flush();

		while (listIterator.hasNext()) {
        	Process proc = listIterator.next();
        	if(proc.isHole())
        	{
        		for(int i=0; i<proc.getSize(); i++)
            		System.out.print(".");
        	}
        	else
        	{
        		for(int i=0; i<proc.getSize(); i++)
        			System.out.print("["+proc.id+"]");
        	}

		}
		System.out.println("\n");
	}
}
