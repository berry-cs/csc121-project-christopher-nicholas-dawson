import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/** Represents a still obstacle*/
public class Obstacle {
 
	private Posn posn;
	private int width;
	private int height;

	private static final int SIZE = 400;
    private static PImage monsterImg = null;

	public Obstacle(Posn posn, int width, int height) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
	}
	
	 
	public Obstacle(int ypos) {
		this(new Posn(new Random().nextInt(SIZE - 200), ypos), 35, 35);
	}

	// draws the obstacle
	public PApplet draw(PApplet c) {
		if (monsterImg == null) {
			monsterImg = c.loadImage("monster.png");
		}
		
	    c.imageMode(PApplet.CENTER);
	   
		float scaledWidth = monsterImg.width * 0.2f; 
		float scaledHeight = monsterImg.height * 0.2f; 
	    c.image(monsterImg, (float)this.posn.getX(), (float)this.posn.getY(), scaledWidth, scaledHeight);
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
	public int hashCode() {
		return Objects.hash(height, posn, width);
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
		return height == other.height && Objects.equals(posn, other.posn) && width == other.width;
	}

	@Override
	public String toString() {
		return "Obstacle [posn=" + posn + ", width=" + width + ", height=" + height + "]";
	}
 


}
