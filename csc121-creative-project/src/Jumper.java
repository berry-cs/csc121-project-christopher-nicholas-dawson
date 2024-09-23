import java.util.Objects;

import processing.core.PApplet;

public class Jumper {
	Posn loc;

	Jumper(Posn jumper) {
		super();
		loc = jumper;
	}

	@Override
	public int hashCode() {
		return Objects.hash(loc);
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
		return Objects.equals(loc, other.loc);
	}
	
	 public PApplet draw(PApplet c) {
	        c.imageMode(PApplet.CENTER);
	        c.pushMatrix();
	        c.scale(.25f);
	        c.image(c.loadImage("Jumper.png"), this.loc.getX(), this.loc.getY());
	        c.popMatrix();
	        return c;
	    }
	
}
