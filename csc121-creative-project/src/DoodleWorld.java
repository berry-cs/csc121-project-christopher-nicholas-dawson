import java.util.Objects;

/** represent the state of our doodle jump animation */
public class DoodleWorld implements IWorld {
	
	Jumper jumper;
    IPlatform platforms;
    Star star;
    IObstical obsticals;
    Score score;
    
	public DoodleWorld(Jumper jumper, IPlatform platforms, Star star, IObstical obsticals, Score score) {
		super();
		this.jumper = jumper;
		this.platforms = platforms;
		this.star = star;
		this.obsticals = obsticals;
		this.score = score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(jumper, obsticals, platforms, score, star);
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
		return Objects.equals(jumper, other.jumper) && Objects.equals(obsticals, other.obsticals)
				&& Objects.equals(platforms, other.platforms) && Objects.equals(score, other.score)
				&& Objects.equals(star, other.star);
	}
    
		
		


}

