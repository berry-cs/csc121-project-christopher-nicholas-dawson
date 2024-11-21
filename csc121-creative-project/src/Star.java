import java.util.Objects;

public class Star {
	private Posn star;
	private int star_num;

	Star(Posn star, int star_num) {
		super();
		this.star = star;
		this.star_num = star_num;
	}

	@Override
	public int hashCode() {
		return Objects.hash(star, star_num);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Star other = (Star) obj;
		return Objects.equals(star, other.star) && star_num == other.star_num;
	}



}
