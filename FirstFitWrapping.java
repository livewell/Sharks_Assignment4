import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


public class FirstFitWrapping {
	LinkedList<Process> memory = new LinkedList<Process>();
	int timeEslape =0;
	int count=0;
	int Npr=0;
	int PID=0;// highest ID
	
	public FirstFitWrapping()
	{
		memory.add(new Process("@", 100, 0, 0, false, true));
		//printMemoryMap();
	}
	public void Simulate(ArrayList<Process> processQueue, int n) throws InterruptedException
	{
		Npr =n;
		for(int i=0; i<60; i++)// 60 is for 60 seconds
		{
			boolean addA= false;// for add print funtion
			int first=0;// for add print funtion

	    	for(int a=0; a< processQueue.size(); a++)	    		
	    	{
	    		if(!processQueue.get(a).isStarted())
	    		{
			    		if(add(processQueue.get(a)))
						{
			    			if(first==0)
			    				System.out.print("Swap in: ");
			    			processQueue.get(a).setStarted(true);
			    			System.out.print("["+processQueue.get(a).id + ", " + processQueue.get(a).getSize() + "MB, " + processQueue.get(a).getDuration() + "sec]");
			    			count++; 
			    			first++;
			    			addA= true;
						}
	    		}
	    	}
	    	if(addA)
	    	{
		    	System.out.println("");
		    	printMemoryMap();
	    	}
	    	
	    	timeEslape++;
	    	
	    	if(removeFinishedProcess())
	    	printMemoryMap();
	    	
	    	/*if(i==30)// do compasion
	    	{    		
	    		
	    		if(memoryCompaction()==true)
	    		{
	    			printMemoryMap();	
	    			//System.out.println("COM");
	    		}
	    	}*/
	    	
	    }
		System.out.println("The number of Process Wrapped in and out is: " + count);
		// for testing purpose
		for (int t=0; t<Npr-1; t++)
		{
			//System.out.println(" the Process ID : "+processQueue.get(t));
			if(processQueue.get(t).isStarted())
			{
				PID = t;
			}
		}
		
		 System.out.println("Highest Position :"+ count+ " the Process ID : "+processQueue.get(PID));
	
	
	}
	
	public void merge(int a, int b)
	{		
			//System.out.println("heloe");
			memory.get(b).setSize(memory.get(a).getSize()+memory.get(b).getSize());
			memory.remove(a);			
	}
	public void swap(int a, int b)
	{
		Process temp = memory.get(a);
		memory.set(a, memory.get(b));
		memory.set(b, temp);
	}
	
	public boolean memoryCompaction()
	{
		boolean check =false;
		for (int r=1; r < memory.size(); r++)
		{
			if(memory.get(r-1).isHole())
			{
				if(memory.get(r).isHole())
				{
					merge(r-1,r);
					r--;
				}
				else{
					swap(r-1,r);
					check=true;
				}
			}
			
		}
		return check;
	}
	
	
	public boolean removeFinishedProcess()
	{
		int cprint=0;
		boolean check = false;
		for (int r=0; r < memory.size(); r++)
		{
			
			if(!memory.get(r).isHole())
			{
				if(memory.get(r).getDuration() > 0)
				{
					memory.get(r).setDuration(memory.get(r).getDuration() - 1);
				}
				if(memory.get(r).getDuration() == 0)
				{				
					memory.get(r).setHole(true);
					if(cprint==0)
					System.out.print("Swap Out: ");
					System.out.print("[ID " + memory.get(r).id + ", " + memory.get(r).getSize() + "MB, " + memory.get(r).getDuration() + "sec]");
					cprint++;
					check = true;
				}
			}
			if(r>0)						
			{
				if(memory.get(r).isHole() && memory.get(r-1).isHole())
				{
					merge(r,r-1);
					r--;
				}					
			}
		}
		if(cprint!=0)
		{
			System.out.println("");
		}
		return check;
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
	
	
	
	
	public void printMemoryMap()
	{
		ListIterator<Process> listIterator = memory.listIterator();
		System.out.print("Time :" + timeEslape + "  ");
       // System.out.flush();

		while (listIterator.hasNext()) {
        	Process proc = listIterator.next();
        	if(proc.isHole())
        	{
        		System.out.print("[");
        		for(int i=0; i<proc.getSize(); i++)
            		System.out.print(".");
        		System.out.print("]");
        		//System.out.print(i);
        	}
        	else
        	{
        		System.out.print("[");
        		for(int i=0; i<proc.getSize(); i++){
        			
        		//	System.out.print(proc.id);
        		//	System.out.print(proc.id+proc.duration);
        			System.out.print(proc.duration);
        			// System.out.print(i);
        			}
        		System.out.print("]");
        	}

		}
		System.out.println("\n");
	}
	
}
