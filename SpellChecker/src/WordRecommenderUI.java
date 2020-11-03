import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	
	/*
	 * returns spell checked filename
	 */
	public String getOutputName(String name) {
		return name.replaceAll(".txt", "_chk.txt");
	}
	
	/*
	 * controls user interface
	 */
	public void ui() {
		Scanner scnr = new Scanner(System.in);
		String filename;
		System.out.println("What is the name of the file you want to spell check? ");
		filename = scnr.nextLine();
		String outputFile = getOutputName(filename);
		WordRecommender wr = new WordRecommender("engDictionary.txt");
		File spellCheckFile = new File(filename);
		try {
			Scanner reader = new Scanner(spellCheckFile);
			FileWriter fw = new FileWriter(outputFile);
			while(reader.hasNext()) {
				String word = reader.next();
				//word found in dictionary
				if (wr.isWord(word)){
					fw.write(word + " ");
			    //word not found in dictionary
				} else {
					//tolerance = 3, commonPercent = 0.75, number of suggestions = 5
					ArrayList<String> wordSuggestions = wr.getWordSuggestions(word, 3, 0.75, 5);
					//no suggestions available
					if (wordSuggestions.size() == 0) {
						System.out.println("The word '" + word + "' is mispelled.");
						System.out.println("There are 0 suggestions in our dictionary for this word.");
						System.out.println("Press ‘a’ for accept as is, ‘t’ for type in manually.");
						String input;
						do {
							input = scnr.next();
							if (input.equals("a") || input.equals("t")) {
								break;
							} else {
								System.out.println("Invalid input. Please try again.");
							}
						} while(true);
						String replaceWord = getUserOption(input, word, wordSuggestions);
						fw.write(replaceWord + " ");
					//suggestions available
					} else {
						System.out.println("The word '" + word + "' is mispelled. The following suggestions are available: ");
						System.out.println(wr.prettyPrint(wordSuggestions));
						System.out.println("Press ‘r’ for replace, ‘a’ for accept as is, ‘t’ for type in manually.");
						String input;
						do {
							input = scnr.next();
							System.out.println(input);
							if (input.equals("a") || input.equals("t") || input.equals("r")) {
								break;
							} else {
								System.out.println("Invalid input. Please try again.");
							}
						} while(true);
						String replaceWord = getUserOption(input, word, wordSuggestions);
						fw.write(replaceWord + " ");
					}
				}
			}
			System.out.println("Your file has been edited under: " + outputFile);
			fw.close();
			reader.close();
			scnr.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WordRecommenderUI test = new WordRecommenderUI();
		test.ui();
		
	}
	
}
