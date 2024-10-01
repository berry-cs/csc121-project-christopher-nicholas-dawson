import java.util.Objects;

import processing.core.PApplet;

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
/*
	 // Check if the jumper has landed on the platform
    public boolean checkCollision(Jumper jumper) {
        // collision detection if the jumper's Y position is on the platform
        return jumper.getY() >= y && jumper.getY() <= y + 10 && jumper.getX() >= x && jumper.getX() <= x + 50;
    }
*/

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
