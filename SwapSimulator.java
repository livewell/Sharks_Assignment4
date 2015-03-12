import java.util.*;

public class SwapSimulator {
	
	LinkedList<Process> memory = new LinkedList<Process>();
	
	public SwapSimulator()
	{
		memory.add(new Process(0, 100, 0, 0, false, true));
	}
	
	public void firstFit(ArrayList<Process> processQueue)
	{
		printMemoryMap();
		long startTime = System.currentTimeMillis();
		
		// Loop for 1 minute
	    while((System.currentTimeMillis()-startTime)<60000)
	    {
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
				if(proc.getDuration() != 0)
				{
					proc.setDuration(proc.getDuration() - 1);
				}
				else
				{
					proc.setHole(true);
					printMemoryMap();
				}
			}
		}
	}
	
	public void printMemoryMap()
	{
		ListIterator<Process> listIterator = memory.listIterator();
		while (listIterator.hasNext()) {
        	Process proc = listIterator.next();
        	if(proc.isHole())
        		System.out.print(" [Hole | " + proc.getSize() + "MB], ");
        	else
        		System.out.print(" [P" + proc.id + " | " + proc.getSize() + "MB | " + proc.getDuration() + "sec ]");

		}
		System.out.println("\n");
	}
}
