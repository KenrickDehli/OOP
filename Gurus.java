
public class Gurus {

	public static void main(String args[])	{
		if(args.length <= 0){
			System.out.println("Bitte geben Sie ein Argument ein.");
			return;
		}

		try {
		  int anzahlGurus = Integer.parseInt(args[0]);
			Guru [] gurus = new Guru[anzahlGurus];
			EssStaebchen [] staebchen = new EssStaebchen[anzahlGurus];

			for (int i = 0; i < anzahlGurus; i++){
				staebchen[i] = new EssStaebchen(i);
			}

			for (int i = 0; i < anzahlGurus; i++){
				var links = staebchen[i];
				var rechts = staebchen[(i+1)%anzahlGurus];
				gurus[i] = new Guru(i, links, rechts);
				new Thread(gurus[i]).start();
			}
		} catch (Exception ex){
			return;
		}
	}
}
