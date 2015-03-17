
public class PageTester {

	public static void main(String[] args) {
		 PageSimulator p = new PageSimulator();
		 
		 // Testing the nextPage method
		 System.out.println("First In First Out");
		 System.out.println("---------------------------------------------------------------------------------");
		 double fifo = p.fifo();
		 System.out.println("\n\n");
		 System.out.println("Least Recently Used");
		 System.out.println("---------------------------------------------------------------------------------");
		 double lru = p.lru();
		 System.out.println("\n\n");
		 System.out.println("Least Frequently Used");
		 System.out.println("---------------------------------------------------------------------------------");
		 double lfu = p.lfu();
		 System.out.println("\n\n");
		 System.out.println("Most Frequently Used");
		 System.out.println("---------------------------------------------------------------------------------");
		 double mfu = p.mfu();
		 System.out.println("\n\n");
		 System.out.println("Pick Random");
		 System.out.println("---------------------------------------------------------------------------------");
		 double randomPick = p.randomPick();
		 System.out.println("\nFIFO\tLRU\tLFU\tMFU\tRandom Pick");
		 System.out.printf("%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n", fifo, lru, lfu, mfu, randomPick);
	}
}
