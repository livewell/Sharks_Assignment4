import java.util.ArrayList;
import java.util.Random;

/*
 * 
 * 
 */

public class AlgorithmTester
{

   static Random rand = new Random();
   
   public static void main(String[] args)
   {
      ArrayList<Process> processList = new ArrayList<>();
      for (int i = 0; i < 10; i++)
      {
         //processList.add(new Process(i, generateSize(), generateDuration(),i, false));
         System.out.println(processList.get(i).toString());
      }
   }

   // Needs ENUM not random numbers.
//   public static int generateSize()
//   {
//      return (int) (rand.nextInt(1000) / 10.0);
//   }
//   
//   public static int generateDuration()
//   {
//      return (int) (rand.nextInt(600) / 10.0);
//   }
   
}
