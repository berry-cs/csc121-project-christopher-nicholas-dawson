import java.util.Objects;

public class Jumper {
	Posn Jumper;

	Jumper(Posn jumper) {
		super();
		Jumper = jumper;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Jumper);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jumper other = (Jumper) obj;
		return Objects.equals(Jumper, other.Jumper);
	}
	

	
}
