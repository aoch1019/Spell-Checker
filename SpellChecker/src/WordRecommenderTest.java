import static org.junit.jupiter.api.Assertions.*;

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
}
