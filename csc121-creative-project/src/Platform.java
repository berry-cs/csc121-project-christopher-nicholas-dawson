import java.util.Objects;

import processing.core.PApplet;

interface ILoP {
	
 public PApplet draws(PApplet c);

}

class MTLoP implements ILoP {
	MTLoP() {}
	
	public PApplet draws(PApplet c) {
		return c;
	}
	
	
	@Override
	public boolean equals(Object other) {
		return other instanceof MTLoP;
	}

	@Override
	public int hashCode() {
		return MTLoP.class.hashCode();
	}

	@Override
	public String toString() {
		return "MTLoP []";
	}
}



class ConsLoP implements ILoP {
	Platform first;
	ILoP rest;
	
	ConsLoP(Platform first, ILoP rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public PApplet draws(PApplet c) {
		first.draw(c);
		rest.draws(c);
		return c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, rest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsLoP other = (ConsLoP) obj;
		return Objects.equals(first, other.first) && Objects.equals(rest, other.rest);
	}

	@Override
	public String toString() {
		return "ConsLoP [first=" + first + ", rest=" + rest + "]";
	}
	
	
}

/** Represents a still platform*/
public class Platform {

	Posn posn;
	int width;
	int height;
	String color;
	

	public Platform(Posn posn, int width, int height, String color) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
		this.color = color;
		
	}

	/** produce a visual platform */ 
	public PApplet draw(PApplet c) {

		c.fill(0, 0, 255);   // solid blue
		c.rect(this.posn.x, this.posn.y, this.width, this.height, 28);

		return c; 
	}

	

	@Override
	public String toString() {
		return "Platform [posn=" + posn + ", width=" + width + ", height=" + height + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, height, posn, width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Platform other = (Platform) obj;
		return Objects.equals(color, other.color) && height == other.height && Objects.equals(posn, other.posn)
				&& width == other.width;
	}


}
