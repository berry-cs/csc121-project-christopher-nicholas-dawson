import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/** Represents a still obstacle*/

public class Obstacle {
 
	private Posn posn;
	private int width;
	private int height;
	private String color;
	private static final int SIZE = 400;

	public Obstacle(Posn posn, int width, int height, String color) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	
	public Obstacle(int ypos) {
		Random rand = new Random();
		int randomX = rand.nextInt(SIZE - 200);
		this.posn = (new Posn(randomX, ypos));
		this.width = 35;
		this.height = 35;
		this.color = "red";
	}

	// draws the obstacle
	public PApplet draw(PApplet c) {
//	    c.imageMode(PApplet.CENTER);
//	    PImage monsterImg = c.loadImage("monster.png");
//	    float scaledWidth = monsterImg.width * 0.2f; 
//	    float scaledHeight = monsterImg.height * 0.2f; 
//	    c.image(monsterImg, (float)this.posn.getX(), (float)this.posn.getY(), scaledWidth, scaledHeight);
		c.fill(255, 0, 0);
		c.rect((int)this.getPosn().getX(), (int)this.getPosn().getY(), this.getWidth(), this.getHeight(), 28);
		return c;
	}
	
	// returns a posn
	public Posn getPosn() {
		return posn;
	}
	// returns a width
	public int getWidth() {
		return width;
	}
	// returns a height
	public int getHeight() {
		return height;
	}
 
	@Override
	public String toString() {
		return "Obstacle [posn=" + getPosn() + ", width=" + getWidth() + ", height=" + getHeight() + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, getHeight(), getPosn(), getWidth());
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
		return Objects.equals(color, other.color) && getHeight() == other.getHeight() && Objects.equals(getPosn(), other.getPosn())
				&& getWidth() == other.getWidth();
	}

}
