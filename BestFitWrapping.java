import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class BestFitWrapping
{
			LinkedList<Process> memory = new LinkedList<Process>();
			int timeEslape =0;
			int count=0;
			int Npr=0;
			int PID=0;// highest ID
			
			
			
			public BestFitWrapping()
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
			    
			    	//for(int a=0; a< 6; a++)	
			    	for(int a=0; a< processQueue.size(); a++)	    		
			    	{
			    		if(!processQueue.get(a).isStarted())
			    		{
					    		if(add(processQueue.get(a)))
								{
					    			//System.out.println("inADD");
					    			if(first==0)
					    				System.out.print("Swapp in: ");
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
			    	
			    	if(i==29)// do compasion
			    	{    		
			    		
			    		if(memoryCompaction()==true)
			    		{
			    			printMemoryMap();	
			    			//System.out.println("COM");
			    		}
			    	}
			    	
			    }
				System.out.println("The number of Process Wrapped in and out is: " + count);
				// for testing purpose
				
				for (int t=0; t<Npr-1; t++)
				{
					if(processQueue.get(t).isStarted())
					{
						PID = t;
					}
				}
				
				 System.out.println("Highest Position :"+ count + " the Process ID : "+processQueue.get(PID));
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
			//	System.out.println("is"+process.isHole());
				int BFP=0;
				int hTight=101;
				int cprint=0;
				boolean Isfound = false;
				for (int r=0; r < memory.size(); r++)
				{
					//System.out.println("inADD2");
					if(memory.get(r).isHole())
					{
						//System.out.println("inADD3");
						if(memory.get(r).getSize()<hTight && memory.get(r).getSize() >=process.size )
						{
							//System.out.println(BFP);
							hTight = memory.get(r).getSize();
							BFP = r;
							Isfound = true;
						}
						
					}
					
				}
				if(Isfound)
				{
					// Replace the hole with the current process
					memory.add(BFP, process);
	            
	            	// Split the empty hole in half
	            
	            		int sizeL =memory.get(BFP+1).getSize() - process.size;	
	            	//	System.out.println("SIze left"+ sizeL);
	            		// Add the left over hole after this process
	            		memory.get(BFP+1).setSize(sizeL);
	            		if(sizeL==0) memory.remove(BFP+1);
	            		//printMemoryMap();
				}
				return Isfound;
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
