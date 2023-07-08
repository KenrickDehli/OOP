

public class Deadlocks {
	public static void main (String args []) {
		var drucker = new Object();
		var scanner = new Object();
		
		var threadA = new Thread("A") {
			@Override public void run() {
				synchronized(drucker) {
					System.out.println(getName() + " reserviert Drucker");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {}
					synchronized (scanner) {
						System.out.println(getName() + " reserviert Scanner");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {}
					}
					System.out.println(getName() + " gibt Scanner frei");
				}
				System.out.println(getName() + " gibt Drucker frei");
			}
		};
		var threadB = new Thread("B") {
			@Override public void run() {
				synchronized(drucker) {
					System.out.println(getName() + " reserviert Drucker");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {}
					synchronized (scanner) {
						System.out.println(getName() + " reserviert Scanner");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {}
					}
					System.out.println(getName() + " gibt Scanner frei");
				}
				System.out.println(getName() + " gibt Drucker frei");
			}
		};
		threadA.start();
		threadB.start();
	}
}

