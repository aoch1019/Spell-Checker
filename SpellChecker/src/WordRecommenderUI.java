import java.util.ArrayList;
import java.util.Scanner;

public class WordRecommenderUI {
	
	/*
	 * returns word to return based on user input (r, t, or a)
	 */
	public String getUserOption(String option, String word, ArrayList<String> suggestions) {
		Scanner scnr = new Scanner (System.in);
		//user types a-> return word
		if (option.equals("a")) {
			return word;
		//user types t-> allow user to input replacement
		} else if (option.equals("t")) {
			System.out.println("Please type the word that will be used as the replacement in the output file.");
			String replaceWord = scnr.nextLine();
			return replaceWord;
		//user types r->replace with a suggestion
		} else {
			System.out.println("Your word will now be replaced with one of the suggestions.");
			System.out.println("Enter the number corresponding to the word that you want to use for replacement.");
			int userInput;
			do {
				userInput = scnr.nextInt();
				//number is not a suggestion available
				if (userInput <= 0 || userInput > suggestions.size()) {
					System.out.println("Please enter a valid number. Try again.");
				//valid input
				} else {
					break;
				}
			} while(true);
			return suggestions.get(userInput - 1);
		}
	}
}
