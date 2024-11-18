import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;

interface ILoP {

	public PApplet draws(PApplet c);

	public Platform getFirst();

	public ILoP getRest();

	public boolean isEmpty(); 

}

class MTLoP implements ILoP {
	MTLoP() {}

	public PApplet draws(PApplet c) {
		return c;
	}

	public boolean isEmpty() {
		return true;
	}

	// produces a "non-existant platform, best way i could think to handle this
	public Platform getFirst() {
		return new Platform(new Posn(0, 0), 0, 0, "blue");
	}

	public ILoP getRest() {
		return this;
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
	private Platform first;
	private ILoP rest;

	ConsLoP(Platform first, ILoP rest) {
		this.first = first;
		this.rest = rest;
	}

	public PApplet draws(PApplet c) {
		first.draw(c);
		rest.draws(c);
		return c;
	}

	public boolean isEmpty() {
		return false;
	}

	public Platform getFirst() {
		return first;
	}

	public ILoP getRest() {
		return rest;
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

	/** construct a randomly placed platform at the given y in a random color */
	public Platform(int ypos) {
		Random rand = new Random();
		int randomX = rand.nextInt(400 - 75);
		this.posn = new Posn(randomX, ypos);
		this.width =75;
		this.height = 10;
		this.color = "blue";
	}

	/** produce a visual platform */ 
	public PApplet draw(PApplet c) {

		c.fill(0, 0, 255);   // solid blue
		c.rect((int)this.posn.getX(), (int)this.posn.getY(), this.width, this.height, 28);
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
