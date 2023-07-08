
public class Lager {
	static final int CAPACITY = 100;
	static int bestand = 0;

	public static void main(String [] args) {
		var mutex = new Object();
		var random = new java.util.Random();
		var producer = new Runnable() {
			@Override public void run()	{
				while(true) {
					synchronized(mutex) {
						if (bestand < CAPACITY) {
							int howmany = random.nextInt(CAPACITY - bestand) +1;
							bestand += howmany;
							System.out.println("Produziere " + howmany + " , Bestand: " + bestand);
						}
					}
				}
			}
		};
		var consumer = new Runnable() {
			@Override public void run()	{
				while(true) {
					synchronized(mutex) {
						if (bestand > 0) {
							int howmany = random.nextInt(bestand) + 1;
							bestand -= howmany;
							System.out.println("Konsumiere " + howmany + ", Bestand: " + bestand);
						}
					}
				}
			}
		};
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}
