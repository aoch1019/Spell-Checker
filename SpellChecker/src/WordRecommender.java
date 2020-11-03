
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
	
	public ArrayList<String> getWordSuggestions(String word, int tolerance, double commonPercent, int topN){
		ArrayList<String> possibleWords = new ArrayList<String>();
		
		File f = new File(this.filename);
		try {
			Scanner fileScanner = new Scanner(f);
			while (fileScanner.hasNextLine()) {
				String currWord = fileScanner.nextLine();
				if(currWord.length() <= word.length() + tolerance &&  
				   currWord.length() >= word.length() - tolerance &&
				   inCommon(currWord, word) >= commonPercent) {
				   possibleWords.add(currWord);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("maybe file name is incorrect");
			e.printStackTrace();
		}
		
		ArrayList<String> answer = new ArrayList<String>();
		while(topN > 0 && possibleWords.size() > 0) {
			double highSimilarity = 0.0;
			String highWord = "";
			for(String pos : possibleWords) {
				double currSimilarity = getSimilarity(pos, word);
				if(currSimilarity > highSimilarity) {
					highWord = pos;
					highSimilarity = currSimilarity;
				}
			}
			answer.add(highWord);
			possibleWords.remove(highWord);
			topN--;
		}

		return answer;
	}
	
	public double inCommon(String word1, String word2) {
		Set<Character> s1 = new HashSet<Character>();
		Set<Character> s2 = new HashSet<Character>();
		
		for(int i=0; i < word1.length(); i++) {
			Character currChar = word1.charAt(i);
			s1.add(currChar);
		}
		
		for(int i=0; i < word2.length(); i++) {
			Character currChar = word2.charAt(i);
			s2.add(currChar);
		}

		int total = s1.size() + s2.size();
		
		s1.addAll(s2);
		int denominator = s1.size();
		int numerator = total - denominator;
		
		return (double) numerator / (double) denominator;
	}
	
	public boolean isWord(String word) {
		File f = new File("engDictionary.txt");
		try {
			Scanner fileScanner = new Scanner(f);
			while (fileScanner.hasNextLine()) {
				String currWord = fileScanner.nextLine();
				if(word.equals(currWord)) {
					return true;
				}
				if(word.compareToIgnoreCase(currWord) < 0) {
					return false;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("maybe file name is incorrect");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
