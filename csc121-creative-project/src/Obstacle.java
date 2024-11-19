import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;

/** Represents a still obstacle*/

public class Obstacle {

	Posn posn;
	int width;
	int height;
	String color;
	
	public Obstacle(Posn posn, int width, int height, String color) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public Obstacle(int ypos) {
		Random rand = new Random();
		int randomX = rand.nextInt(400 - 200);
		this.posn = new Posn(randomX, ypos);
		this.width = 35;
		this.height = 35;
		this.color = "red";
	}

	
	public PApplet draw(PApplet c) {
		c.fill(255, 0, 0);
		c.rect((int)this.posn.x, (int)this.posn.y, this.width, this.height, 28);
		return c;

	}

	@Override
	public String toString() {
		return "Obstacle [posn=" + posn + ", width=" + width + ", height=" + height + ", color=" + color + "]";
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
		Obstacle other = (Obstacle) obj;
		return Objects.equals(color, other.color) && height == other.height && Objects.equals(posn, other.posn)
				&& width == other.width;
	}
	
	

}
