import java.*;
import java.util.Stack;
/* 1. String einlesen
 * 2. Über den String iterieren
 * 3. Momentanes Zeichen == öffnende Klammer? → Auf Stack legen
 * 4. Momentanes Zeichen == schließende Klammer? → Liegt auf Stack die zugehörige offene?
 * 5. Wenns passt dann das Element vom Stack nehmen
 * 6. Andernfalls abbrechen
 * 7. Quelltext ist korrekt geklammert wenn Stack am Ende leer ist. 
 */
public class Klammerpruefung {

	private static string test;

		public static void main(String [] args){
				test = "das ist ( ein Text )";
				Stack<Character> stack = new Stack();
				for (char chara : test) {
						switch (chara){
								case '(':
								stack.push(chara);
								break;
								case '[':
								stack.push(chara);
								break;
								case '{':
								stack.push(chara);
								break;
								case ')':
								stack.peek(chara);
								break;
								case ']':
								stack.peek(chara);
								break;
								case '}':
								stack.peek(chara);
								break;
								default: 
								break;
						}
				}
		}
		private bool isCorrectClosingElement(char c){
				return false;
		}
}
