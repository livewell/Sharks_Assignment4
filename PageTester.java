public class PageTester {

	public static void main(String[] args) {
		int limit = 5;
		double fifo, lru, lfu, lfur, mfu, mfur, randomPick;
		double avFifo=0, avLru=0, avLfu=0, avLfuc=0, avMfu=0, avMfur=0, avRandomPick=0;
		StringBuilder results = new StringBuilder();
		results.append("\nP A G I N G\tH I T\tR A T I O\n");
		results.append("\nFIFO\t\tLRU\t\tLFU\t\tLFU*\t\tMFU\t\tMFU*\t\tRP\n");

		
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
			 lfu = p.lfu(false);
			 System.out.println("\n\n");
			 System.out.println("Least Frequently Used w/ evicted page's counter reset to zero");
			 System.out.println("---------------------------------------------------------------------------------");
			 lfur = p.lfu(true); // Reset page counter to zero when evicted
			 System.out.println("\n\n");
			 System.out.println("Most Frequently Used");
			 System.out.println("---------------------------------------------------------------------------------");
			 mfu = p.mfu(false);
			 System.out.println("\n\n");
			 System.out.println("Most Frequently Used w/ evicted page's counter reset to zero");
			 System.out.println("---------------------------------------------------------------------------------");
			 mfur = p.mfu(true); // Reset page counter to zero when evicted
			 System.out.println("\n\n");
			 System.out.println("Pick Random");
			 System.out.println("---------------------------------------------------------------------------------");
			 randomPick = p.randomPick();
			 
			 results.append(String.format("%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\n", fifo, lru, lfu, lfur, mfu, mfur, randomPick));
			 avFifo += fifo; 
			 avLru += lru; 
			 avLfu += lfu; 
			 avLfuc += lfur; 
			 avMfu += mfu; 
			 avMfur += mfur; 
			 avRandomPick += randomPick;
		}
		results.append("-----------------------------------------------------------------------------------------------------\n");
		results.append(String.format("%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\n", avFifo/limit, avLru/limit, avLfu/limit, avLfuc/limit, avMfu/limit, avMfur/limit, avRandomPick/limit));
		System.out.println(results);
	}
}
