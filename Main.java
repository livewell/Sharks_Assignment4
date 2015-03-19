
public class Main {

	static int []frame ={-1,-1,-1,-1}; // initial frame to -1 mean contain null
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][]page = new int[7][100];
		int [][]page1 = new int[7][100];
		int [][]page2 = new int[7][100];
		int [][]page3 = new int[7][100];
		int [][]page4 = new int[7][100];
		int [][]page5 = new int[7][100];
		int rand =  (int)(Math.random()*10);
			
				for(int r=0; r<100; r++)
				{
					for(int c=0; c<7;c++)
					{
						if(c==0){
							page1[c][r]=rand;
							page2[c][r]=rand;
							page3[c][r]=rand;
							page4[c][r]=rand;
							page5[c][r]=rand;
							rand = nextPage(rand);
						
						}else{
							page1[c][r]=-1;
							page2[c][r]=-1;
							page3[c][r]=-1;
							page4[c][r]=-1;
							page5[c][r]=-1;
						
						}
						//String count= String.format("% 4d",(t+1));		
						//System.out.print(page1[c][r]);							
					}
				//System.out.println("");
			}
		//	/*
			
			System.out.println("First In First Out \n");
            Simulator(page1,1);
            System.out.println("\n------------------------------------------------");
            System.out.println("Least Recently Used \n");
            Simulator(page2,2);
            System.out.println("\n------------------------------------------------");
            System.out.println("Least Fre Used \n");
            Simulator(page3,3);
            System.out.println("\n------------------------------------------------");
            System.out.println("Most Recently Used \n");
            Simulator(page4,4);
            System.out.println("\n------------------------------------------------");
            System.out.println("Random Pick \n");
            Simulator(page5,5);
            System.out.println("\n------------------------------------------------");
			
		
	//	*/


	}
	
	
	
	public static int nextPage(int page)
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
//	/*
	
	
	
	
	//1
		public static int addFIFO(int [][]page,int p )
		{
			int temp=(int)(Math.random()*3)+1;
			int compare=p+1;
			for (int c=1; c <5; c++)
			{
				for(int i=p-1; i>0; i--)
				{
					if(page[c][p]==page[c][i])
					{
						if(compare>i)
							{
								compare=i;
								temp=c;
							}
					}else{
						i=-1;// break
					}
				}
			}	 
			return temp;
		}
	// end 1 add FIFO
		
		//2
		public static int addLRU(int [][]page,int p )
		{
			int temp=(int)(Math.random()*3)+1;
			int compare=p+1;
			for (int c=1; c <5; c++)
			{
				for(int i=p-1; i>0; i--)
				{
					if(page[c][p]==page[0][i])
					{
						if(compare>i)
							{
								compare=i;
								temp=c;
							}
						i=-1;// break
					}
				}
			}	 
			return temp;
		}
	// end 2 least recent used
	
	//3
		public static int addLFU(int [][]page,int p )
		{
			int temp=(int)(Math.random()*3)+1;
			int compare=p+1;
			int count=0;
			for (int c=1; c <5; c++)
			{
				count =0;// reinitialize
				for(int i=p-1; i>0; i--)
				{
					if(page[c][p]==page[0][i])
					{
						count++;
					}
				}
				if(compare>count)
				{
					compare=count;
					temp=c;
				}
			}	 
			return temp;
		}
	// end 3 least F used	
		
		
		//3
		public static int addMFU(int [][]page,int p )
		{
			int temp=(int)(Math.random()*3)+1;
			int compare=0;
			int count=0;
			for (int c=1; c <5; c++)
			{
				count =0;// reinitialize
				for(int i=p-1; i>0; i--)
				{
					if(page[c][p]==page[0][i])
					{
						count++;
					}
				}
				if(compare>count)
				{
					compare=count;
					temp=c;
				}
			}	 
			return temp;
		}
	// end 3 least F used
		
		//5
		public static int addRP(int [][]page,int p )
		{
			int temp=(int)(Math.random()*3)+1;
			return temp;
		}
	// end 5 random pick
		
		
		
	public static void Simulator(int [][]page,int type)
	{
		int ref =-1;
		boolean check =false;
	//	/*
		for(int r=0; r<100; r++)
		{
			 ref =-1;
			 check = false;
			 for (int c=1; c<5;c++)
			 {
			
				 if(page[c][r]== page[0][r])
				 {
					 page[6][r] = 1;// 1 mean repeat occurs
					 page[5][r] = page[c][r];// copy out
					 check = true;
				 }
				 if(page[c][r]==-1) 
				 {
					 page[5][r] = page[c][r];// copy out
					 page[c][r] = page[0][r];// set to the page
					 check=true;
					// if(r<99)page[c][r+1] = page[0][r];// copy to the next page			
				 }			
				 if(check) c=6;
			  }
			 
			  if(!check)// do the the algorithms 
					{
						 	if(type==1){ref = addFIFO(page,r);}
							if(type==2){ref = addLRU(page,r);}
							if(type==3){ref = addLFU(page,r);}
							if(type==4){ref = addMFU(page,r);}
							if(type==5){ref = addRP(page,r);}
						 	page[5][r] = page[ref][r];// copy out
							page[ref][r] = page[0][r];// set to the page
							// if(r<99)page[ref][r+1] = page[0][r];// copy to the next page
					 }
			// copy next page
				for(int n=1; n<5; n++)
				{
					if(r<99)page[n][r+1] = page[n][r];// copy to the next page
				}	 
	    }
	//    */
		display(page);

	}
		 
			
	public static void display(int [][]page)
	{
		int countH=0;
		String print="";
		for(int r=0; r<100;r++)
		{
			
			 print="";
			if(page[6][r]!=1)
			{
				print = "(*) Paged In =" +page[0][r] + " Evicted Page =";
				countH++;
			}
			 if(page[6][r]==1)  print = "( ) Paged In =" +page[0][r] + " Evicted Page =";
			 
			 if( page[5][r]!=-1) print +=  page[5][r];
			 if( page[5][r]==-1) print +=   "*";
			 
			 if(page[6][r]==1)print +=  "  Referenced Page :" + page[0][r]; // or evicted
			 
			print = String.format("%30s", print);

			String count= String.format("% 4d",(r+1));
			String Sframe="";
			for (int f=1; f<5; f++)
			{
				if(page[f][r]==-1) Sframe += "[ ]";
				if(page[f][r]!=-1) Sframe += "["+page[f][r]+"]";
			}
			System.out.println(count+": Physical Memory: " + page[0][r] + " = " + Sframe + print);
		}
		System.out.println("Average Hit is: " + countH +"%");
	}
	
	
	
}
