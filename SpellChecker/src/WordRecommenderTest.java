import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordRecommenderTest {

	@Test
	void testGetSimilarity() {
		WordRecommender w1 = new WordRecommender("engDictionary.txt");
		double sim1 = w1.getSimilarity("oblige", "oblivion");
		assertEquals(sim1, 2.5);
		double sim2 = w1.getSimilarity("aghast", "gross");
		assertEquals(sim2, 1.5);
		double sim3 = w1.getSimilarity("aghast", "hello");
		assertEquals(sim3, 0.0);
		double sim4 = w1.getSimilarity("", "hello");
		assertEquals(sim4, 0.0);
		double blank = w1.getSimilarity("", "");
		assertEquals(blank, 0.0);
	}
	
	@Test
	void testGetWordSuggestions() {
		WordRecommender w1 = new WordRecommender("partialDictionary.txt");
		ArrayList<String> list1 = w1.getWordSuggestions("aardko", 2, 0.5, 2);
		assertTrue(list1.contains("aardvark"));
		assertTrue(list1.contains("aardwolf"));
		
		ArrayList<String> list2 = w1.getWordSuggestions("aardva", 2, 0.5, 1);
		assertTrue(list2.contains("aardvark"));
		assertFalse(list2.contains("aardwolf"));
		
		ArrayList<String> list3 = w1.getWordSuggestions("aardva", 1, 0.5, 2);
		assertFalse(list3.contains("aardvark"));
		assertFalse(list3.contains("aardwolf"));
		
		ArrayList<String> list4 = w1.getWordSuggestions("aardva", 2, 0.7, 1);
		assertTrue(list4.contains("aardvark"));
		assertFalse(list4.contains("aardwolf"));
		
		ArrayList<String> list5 = w1.getWordSuggestions("aardva", 0, 1.0, 1);
		assertFalse(list5.contains("aardvark"));
		assertFalse(list5.contains("aardwolf"));
		assertTrue(list5.size() == 0);
	}
	
	@Test
	void testInCommon() {
		WordRecommender w1 = new WordRecommender("partialDictionary.txt");
		
		double comm1 = w1.inCommon("hello", "halo");
		assertEquals(comm1, 0.6);
		
		double comm2 = w1.inCommon("hello", "part");
		assertEquals(comm2, 0.0);
	}
	
	@Test
	void testIsWord() {
		WordRecommender w1 = new WordRecommender("partialDictionary.txt");
		
		boolean hello = w1.isWord("hello");
		assertTrue(hello);
		
		boolean notAWord = w1.isWord("notaword");
		assertFalse(notAWord);
		
		assertFalse(w1.isWord(""));
	}
}
