import java.util.Objects;

import processing.core.PApplet;

public class Score {
//	int score = 0;
	private int points;
	Posn Score_Block;
	
	public Score() {
		this.points = 0;
	}
	
	public void increment(int amount) {
		this.points += amount;
	}
	
	public void draw(PApplet c) {
		c.fill(0);
		c.textSize(18);
		String scoreText = "Score: " + points;
		c.textAlign(PApplet.RIGHT, PApplet.TOP);
		c.text(scoreText, c.width - 10, 10);
	}
	
	public int getPoints() {
		return points;
	}
	
	
	
	/*
	Score(int score, Posn score_Block) {
		super();
		this.score = score;
		Score_Block = score_Block;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		return score == other.score;
	}
	
	*/
}

