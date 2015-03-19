import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ArrayList<Process> processList = new ArrayList<>();
		int Npro=0;
	  	for (int a =65; a<85; a++)// loop range from 65 to 91
		{
			for (int b =0; b<10; b++)
			{
				String S = new StringBuilder().append((char)a).append(b).toString();
				processList.add(new Process(S, generateSize(), generateDuration(), Npro, false, false));
				System.out.println(processList.get(Npro).toString());
				Npro++;
			}
		}
	  	System.out.println(Npro++);
	  	//FirstFistWrapping FF = new FirstFistWrapping();
       // FF.Simulate(processList,Npro++);
        
	  //	NextFitWrapping NF = new NextFitWrapping();
      //  NF.Simulate(processList,Npro++);
        
      //  BestFitWrapping BF = new BestFitWrapping();
      //  BF.Simulate(processList,Npro++);
        
        WorstFitWrapping WF = new WorstFitWrapping();
        WF.Simulate(processList,Npro++);
	}
	
	
	 public static int generateSize()
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
	   
	   public static int generateDuration()
	   {
	      return 1 + (int)(Math.random()*5);
	   }   

	

}
