import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;


import processing.core.PImage;


public class Star {
	private Posn posn;
	private int width;
	private int height;
<<<<<<< HEAD
	private String color;



=======
>>>>>>> aad9ddda4d2e1cb62e3d3eb51aca034323a3df62
	


	static PImage starImg = null;
	private static final int SIZE = 400;

	Star(Posn posn, int width, int height) {
		super();
		this.posn = posn;
		this.width = width;
<<<<<<< HEAD
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
=======
		this.height = height; 
	}

public Star(int ypos) {
	this(new Posn(new Random().nextInt(400 - 200), ypos), 35, 35);
}

public PApplet draw(PApplet c) {
	if (starImg == null) {
		starImg = c.loadImage("star.png");
	}
	
//	c.fill(255, 0, 0);
//	c.rect((int)this.getPosn().getX(), (int)this.getPosn().getY(), this.getWidth(), this.getHeight(), 28);
	c.imageMode(PApplet.CENTER);
	
	float scaleWidth = starImg.width * 0.15f;
	float scaleHeight = starImg.height * 0.15f;
	c.image(starImg, (float)this.posn.getX(), (float)this.posn.getY(), scaleWidth, scaleHeight);
	return c;
}
>>>>>>> aad9ddda4d2e1cb62e3d3eb51aca034323a3df62




<<<<<<< HEAD









=======
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
	Star other = (Star) obj;
	return height == other.height && Objects.equals(posn, other.posn) && width == other.width;
}
>>>>>>> aad9ddda4d2e1cb62e3d3eb51aca034323a3df62

@Override
public String toString() {
	return "Star [posn=" + posn + ", width=" + width + ", height=" + height + "]";
}

}