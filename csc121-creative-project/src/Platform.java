import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;

/** Represents a still platform*/

public class Platform {

	private Posn posn;
	private int width;
	private int height;
	private String color;


	public Platform(Posn posn, int width, int height, String color) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	/** construct a randomly placed platform at the given y in a random color */
	public Platform(int ypos) {
		Random rand = new Random();
		int randomX = rand.nextInt(400 - 50);
		this.posn = new Posn(randomX, ypos);
		this.width = 75;
		this.height = 10;
		this.color = "blue";
	}
	
	/** produce a visual platform */ 
	public PApplet draw(PApplet c) {
		c.fill(0, 0, 255);   // solid blue
		c.rect((int)this.posn.getX(), (int)this.posn.getY(), this.width, this.height, 28);
		return c; 
	}

	// returns a platforms posn
	public Posn getPosn() {
		return posn;
	}

	// returns a platforms width
	public int getWidth() {
		return width;
	}

	// returns a platforms height
	public int getHeight() {
		return height;
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
