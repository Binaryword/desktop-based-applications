package WordNet;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		Wordnet.loadWordNet("C:\\Program Files (x86)\\WordNet\\2.1\\Dict");
		
		Scanner input = new Scanner(System.in);
		String text;

		do {

			System.out.println("Enter word to search");
			text = input.nextLine();
			Wordnet.searchWordNet(text.toLowerCase());

		} while (text != "1");
		
		
		
		
		

	}

}
