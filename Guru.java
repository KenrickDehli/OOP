
public class Guru implements Runnable{
	int id;
	static final int MAGENKAPPA = 200;
	int magenMomentan = 0;
	EssStaebchen links;
	EssStaebchen rechts;

	@Override public void run(){
		int i = 0;
		while(true){
						System.out.println("Guru " + this.id + " wartet auf Staebchen " + links.id
						+ ", " + rechts.id);
			synchronized(links){
				synchronized(rechts){
				try {
							System.out.println("Guru " + this.id + " isst mit Staebchen " + links.id
							+ ", " + rechts.id);
						while(magenMomentan <= MAGENKAPPA){
							magenMomentan += 10;
						}
						System.out.println("Guru " + this.id + " schlaeft");
						Thread.sleep(1000);
					} catch (InterruptedException ex){}
				}
				magenMomentan = 0;
				}
			//i++;
		}
	}


	public Guru(int id, EssStaebchen links, EssStaebchen rechts ){
		this.id = id;
		this.links = links;
		this.rechts = rechts;
	}

	
}
