import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;

/** Represents a still obstacle*/

public class Obstacle {

	private Posn posn;
	private int width;
	private int height;
	private String color;
	
	public Obstacle(Posn posn, int width, int height, String color) {
		super();
		this.setPosn(posn);
		this.setWidth(width);
		this.setHeight(height);
		this.color = color;
	}
	
	public Obstacle(int ypos) {
		Random rand = new Random();
		int randomX = rand.nextInt(400 - 200);
		this.setPosn(new Posn(randomX, ypos));
		this.setWidth(35);
		this.setHeight(35);
		this.color = "red";
	}

	
	public PApplet draw(PApplet c) {
		c.fill(255, 0, 0);
		c.rect((int)this.getPosn().getX(), (int)this.getPosn().getY(), this.getWidth(), this.getHeight(), 28);
		return c;

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

	public Posn getPosn() {
		return posn;
	}

	public void setPosn(Posn posn) {
		this.posn = posn;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

}
