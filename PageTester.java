
public class PageTester {

	public static void main(String[] args) {
		 PageSimulator p = new PageSimulator();
		 
		 // Testing the nextPage method
		 System.out.println("First In First Out");
		 System.out.println("---------------------------------------------------------------------------------");
		 p.fifo();
		 System.out.println("\n\n");
		 System.out.println("Least Recently Used");
		 System.out.println("---------------------------------------------------------------------------------");
		 p.lru();
		 System.out.println("\n\n");
		 System.out.println("Pick Random");
		 System.out.println("---------------------------------------------------------------------------------");
		 p.randomPick();

	}
}
