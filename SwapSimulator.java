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
		// Create some processes
		for(int i=0; i<processQueue.size(); i++)
		{
			if(add(processQueue.get(i)))
			{
				printMemoryMap();
			}
			// Add processes
			// Decrement processes
			// Check if processes are 0
			// remove them from memory
		}
	}
	
	public boolean add(Process process)
	{
		ListIterator<Process> listIterator = memory.listIterator();
        while (listIterator.hasNext()) {
        	Process proc = listIterator.next();
        	
            if(proc.isHole() && (proc.size >= process.size))
            {
            	// Split the empty hole in half
            	proc.size = proc.size - process.size;
            	// Replace the hole with the current process
            	listIterator.set(process);
            	// Add the left over hole after this process
            	listIterator.add(proc);
            	return true;
            }        	
        }     
        return false;
	}
	
	public void decrementProcess(Process proc)
	{
		if(!proc.isHole())
		{
			if(proc.getDuration() != 0)
			{
				proc.setDuration(proc.getDuration() - 1);
			}
			else
			{
				proc.setHole(true);
			}
		}
	}
	
	public void printMemoryMap()
	{
		ListIterator<Process> listIterator = memory.listIterator();
		while (listIterator.hasNext()) {
        	Process proc = listIterator.next();
        	if(proc.isHole())
        		System.out.print("[Hole => " + proc.getSize() + "MB], ");
        	else
        		System.out.print("[P" + proc.id + " => " + proc.getSize() + "MB], ");

		}
		System.out.println("\n");
	}
}
