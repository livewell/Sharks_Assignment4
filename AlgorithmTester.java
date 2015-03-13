import java.util.ArrayList;
import java.util.Random;

/*
 * 
 * 
 */

public class AlgorithmTester
{

   static Random rand = new Random();
   
   public static void main(String[] args) throws InterruptedException
   {
      ArrayList<Process> processList = new ArrayList<>();
      for (int i = 0; i < 120; i++)
      {
         processList.add(new Process(i, generateSize(), generateDuration(), 0, false, false));
         System.out.println(processList.get(i).toString());
      }
      
      SwapSimulator s = new SwapSimulator();
      s.firstFit(processList);
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
