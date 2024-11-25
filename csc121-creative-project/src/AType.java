import processing.core.PApplet;

public abstract class AType implements LoX {
	Posn posn;
	int width;
	int height; 
	String color;
	 
	AType(Posn posn, int width, int height, String color) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
		this.color = color;
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
	
	public abstract PApplet draw(PApplet c);

}
