import java.util.Random;


public class PageTester {

	public static void main(String[] args) throws InterruptedException {
		int limit = 5;
		double fifo, lru, lfu, mfu, randomPick;
		double avFifo=0, avLru=0, avLfu=0, avMfu=0, avRandomPick=0;
		StringBuilder results = new StringBuilder();
		results.append("\nH I T\tR A T I O\n");
		results.append("\n\tFIFO\tLRU\tLFU\tMFU\tRandom Pick\n");

		
		for(int i=0; i<limit; i++)
		{
			 PageSimulator p = new PageSimulator();
			 // Testing the nextPage method
			 System.out.println("First In First Out");
			 System.out.println("---------------------------------------------------------------------------------");
			 fifo = p.fifo();
			 System.out.println("\n\n");
			 System.out.println("Least Recently Used");
			 System.out.println("---------------------------------------------------------------------------------");
			 lru = p.lru();
			 System.out.println("\n\n");
			 System.out.println("Least Frequently Used");
			 System.out.println("---------------------------------------------------------------------------------");
			 lfu = p.lfu();
			 System.out.println("\n\n");
			 System.out.println("Most Frequently Used");
			 System.out.println("---------------------------------------------------------------------------------");
			 mfu = p.mfu();
			 System.out.println("\n\n");
			 System.out.println("Pick Random");
			 System.out.println("---------------------------------------------------------------------------------");
			 randomPick = p.randomPick();
			 
			 results.append(String.format("\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n", fifo, lru, lfu, mfu, randomPick));
			 avFifo += fifo; 
			 avLru += lru; 
			 avLfu += lfu; 
			 avMfu += mfu; 
			 avRandomPick += randomPick;
			 Thread.sleep(1000);
		}
		results.append("\t--------------------------------------\n");
		results.append("Average\t");
		results.append(String.format("%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n", avFifo/limit, avLru/limit, avLfu/limit, avMfu/limit, avRandomPick/limit));
		System.out.println(results);
	}
}
