import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {

	@Test
	void test() {
		Score score = new Score();
        assertEquals(0, score.getPoints());

        score.increment(10);
        assertEquals(10, score.getPoints());

        Score score2 = new Score(50);
        assertEquals(50, score2.getPoints());
        

        score2.increment(50);
        assertEquals(100, score2.getPoints());
    }
	

}
