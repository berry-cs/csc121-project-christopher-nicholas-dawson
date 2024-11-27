import java.util.Objects;

import processing.core.PApplet;

public class Score {
	private int points;

	// initializes the score to 0
	Score() {
		this.points = 0;
	}

	// increments the score by a given amount
	public void increment(int amount) {
		this.points += amount;
	}

	// draws the current score on the given canvas at the top-right corner
	public void draw(PApplet c) {
		c.fill(0);
		c.textSize(18);
		String scoreText = "Score: " + points;
		c.textAlign(PApplet.RIGHT, PApplet.TOP);
		c.text(scoreText, c.width - 10, 10);
	}

	// returns the current score points
	public int getPoints() {
		return points;
	}


	// initializes the score to the given points value
	public Score(int points) {
		super();
		this.points = points;
	}

	@Override
	public int hashCode() {
		return Objects.hash(points);
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
		return points == other.points;
	}


}

