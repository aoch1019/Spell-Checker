
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordRecommender {

	String filename;
	
	public WordRecommender(String fileName) {
		this.filename = fileName;
	}
	
	public double getSimilarity(String word1, String word2) {
		int leftSim = leftSimilarity(word1, word2);
		StringBuilder sb1 = new StringBuilder(word1);
		StringBuilder sb2 = new StringBuilder(word2);
		String str1 = sb1.reverse().toString();
		String str2 = sb2.reverse().toString();
		int rightSim = leftSimilarity(str1, str2);
		
		return ((double)leftSim + (double)rightSim)/2.0;
	}
	
	private int leftSimilarity(String word1, String word2) {
		int length = 0;
		if(word1.length() >= word2.length()) {
			length = word2.length();
		}
		else {
			length = word1.length();
		}
		
		int similar = 0;
		
		for(int i = 0; i < length; i++) {
			if(word1.charAt(i) == word2.charAt(i)) {
				similar++;
			}
		}
		
		return similar;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		WordRecommender w1 = new WordRecommender("partialDictionary.txt");		
//		
//		ArrayList<String> list4 = w1.getWordSuggestions("aardva", 2, 0.7, 1);
//		System.out.println(list4.get(0));

		
//		ArrayList<String> testing = w.getWordSuggestions("testere", 3, 0.5, 8);
//		
//		for(String currWord : testing) {
//			System.out.println(currWord);
//		}
		
//		System.out.println(w.prettyPrint(testing));
		
	}

}
