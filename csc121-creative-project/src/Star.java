import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Star {
	private Posn posn;
	private int width;
	private int height;
	private String color;
	
	static PImage starImg = null;

	Star(Posn posn, int width, int height, String color) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
		this.color = color;
	}

public Star(int ypos) {
	this(new Posn(new Random().nextInt(400 - 200), ypos), 35, 35, "yellow");
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

@Override
public String toString() {
	return "Star [posn=" + posn + ", width=" + width + ", height=" + height + ", color=" + color + "]";
}

}