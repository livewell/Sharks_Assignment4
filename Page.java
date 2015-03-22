
public class Page {
	
	int address;
	int counter;
	
	public Page(int address)
	{
		this.address = address;
		this.counter = 0;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}
	
	public void updateCounter() {
		counter++;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
