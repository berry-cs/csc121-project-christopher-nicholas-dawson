import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;

public class Star {
	private Posn posn;
	private int width;
	private int height;
	private String color;
	
	private static final int SIZE = 400;

	Star(Posn posn, int width, int height, String color) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public Star (int ypos) {
		this( new Posn(new Random().nextInt(SIZE - 200), ypos), 35, 35, "yellow" );
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
		Star other = (Star) obj;
		return Objects.equals(color, other.color) && height == other.height && Objects.equals(posn, other.posn)
				&& width == other.width;
	}




}
