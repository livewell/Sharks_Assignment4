
public class PageTester {

	public static void main(String[] args) {
		 PageSimulator p = new PageSimulator();
		 
		 // Testing the nextPage method
		 for(int i=0; i< 20; i++)
		 {
		   System.out.println("i = " + p.nextPage());
		 }
	}
}
