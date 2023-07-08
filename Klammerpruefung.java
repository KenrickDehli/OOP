import java.util.Stack;
import java.io.FileReader;
import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.IOException; 

/* 1. Dateien einlesen
 * 2. Über den String iterieren
 * 3. Momentanes Zeichen == öffnende Klammer? → Auf Stack legen
 * 4. Momentanes Zeichen == schließende Klammer? → Liegt auf Stack die zugehörige offene?
 * 5. Wenns passt dann das Element vom Stack nehmen
 * 6. Andernfalls abbrechen
 * 7. Quelltext ist korrekt geklammert wenn Stack am Ende leer ist. 
 */
public class Klammerpruefung {

	public static void main(String [] args){

		for(String filename : args) {
			StringBuilder filetext = new StringBuilder();
			try {
			var bufferedReader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = bufferedReader.readLine()) != null){
				filetext.append(line);
			}
			bufferedReader.close();
			if (correctBracking(filetext.toString())){
					System.out.println("Der Quelltext in der Datei" + filename + " ist korrekt geklammert");
				}
			else {
					System.out.println("Der Quelltext in der Datei " + filename + " ist nicht korrekt geklammert");
				}
			} catch (FileNotFoundException ex	) {
					System.out.println(filename + "not found");
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}
		

	private static boolean isCorrectClosingElement(char c, Bracket bracketType){
			switch (bracketType){
			case ROUND:
				if (c == '('){
				return true;
			}
			case SQUARE:
				if (c == '[') {
				 return true; 
			}
			case CURLY:
				if (c == '{'){
				return true;
			}
			default:
			return false;
		}
	}

	private static boolean correctBracking(String input){
		Stack<Character> stack = new Stack<Character>();
		char topChar;
		for (char currentChar : input.toCharArray()) {
			switch (currentChar) {
				case '(', '[', '{' -> stack.push(currentChar);
				case ')' -> {
					topChar = stack.peek();
					boolean correctClosingelement = isCorrectClosingElement(topChar, Bracket.ROUND); 
					if (correctClosingelement) {
						stack.pop();
					}
					else if (!correctClosingelement) {
						System.out.println(""+ topChar + currentChar);
						return false;
					}
				}
				case ']' -> {
					topChar = stack.peek();
					boolean correctClosingelement = isCorrectClosingElement(topChar, Bracket.SQUARE); 
					if (correctClosingelement) {
						stack.pop();
					}
					else if (!correctClosingelement){
						System.out.println("" + topChar + currentChar);
						return false;
					} 

				}
				case '}' -> {
					topChar = stack.peek();
					boolean correctClosingelement = isCorrectClosingElement(topChar, Bracket.CURLY); 
					if (correctClosingelement) {
						stack.pop();
					}
					else if (!correctClosingelement){
						System.out.println(""+topChar + currentChar);
						return false;
					} 
				}
				default -> {
				}
			}
		}
		return stack.isEmpty();
	}

	private enum Bracket {
		ROUND, SQUARE, CURLY
	}
}
