import java.util.Objects;

public class Score {
	int score = 0;
	Posn Score_Block;
	
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
	
	
}
