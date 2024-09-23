import java.util.Objects;

public class Jumper {
	Posn Jumper;
	
	int Jump_factor = 10;
	
	Jumper(Posn jumper, int jump_factor) {
		super();
		Jumper = jumper;
		Jump_factor = jump_factor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Jump_factor, Jumper);
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
		return Jump_factor == other.Jump_factor && Objects.equals(Jumper, other.Jumper);
	}

	
	
	
	
}
