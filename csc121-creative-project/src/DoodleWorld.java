import java.util.Objects;

/** represent the state of our doodle jump animation */
public class DoodleWorld implements IWorld {
	
	Jumper jumper;
    IPlatform platforms;
    Star star;
    IObstacle obstacles;
    Score score;
    
	public DoodleWorld(Jumper jumper, IPlatform platforms, Star star, IObstacle obsticals, Score score) {
		super();
		this.jumper = jumper;
		this.platforms = platforms;
		this.star = star;
		this.obstacles = obsticals;
		this.score = score;
	}

	@Override
	public String toString() {
		return "DoodleWorld [jumper=" + jumper + ", platforms=" + platforms + ", star=" + star + ", obsticals="
				+ obstacles + ", score=" + score + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(jumper, obstacles, platforms, score, star);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoodleWorld other = (DoodleWorld) obj;
		return Objects.equals(jumper, other.jumper) && Objects.equals(obstacles, other.obstacles)
				&& Objects.equals(platforms, other.platforms) && Objects.equals(score, other.score)
				&& Objects.equals(star, other.star);
	}
    


}

