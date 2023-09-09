import java.util.Stack;
import java.io.FileReader;
import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.IOException; 

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
					System.out.println("Der Quelltext in der Datei " + filename + " ist korrekt geklammert");
				}
			else {
					System.out.println("Der Quelltext in der Datei " + filename + " ist nicht korrekt geklammert");
				}
			} catch (FileNotFoundException ex	) {
					System.out.println("Die Datei " + filename + " konnte nicht gefunden werden.");
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
		int quotesCount = 0;
		boolean quotes = false;
		for (char currentChar : input.toCharArray()) {
			if (currentChar == '\'' || currentChar == '\"' || quotesCount < 2){
				if (quotesCount == 2){
					quotes = true; 
					quotesCount = 0;
				}
				else {
					quotesCount++;
					quotes = true;
				}
			continue;
			}
			if (!quotes){
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
		}
		return stack.isEmpty();
	}
	
	private enum Bracket {
		ROUND, SQUARE, CURLY
	}
}
