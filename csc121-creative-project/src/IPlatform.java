
public interface IPlatform {

}

/** Represents a still platform*/
class StillPlat implements IPlatform {
	Posn posn;
	int width;
	int height;
	String color;
	
	public StillPlat(Posn posn, int width, int height, String color) {
		super();
		this.posn = posn;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	
}

/** Represents a still platform*/
class MovingPlat implements IPlatform {
	Posn posn1;
	Posn posn2;
	int width;
	int height;
	String color;
	
	public MovingPlat(Posn posn1, Posn posn2, int width, int height, String color) {
		super();
		this.posn1 = posn1;
		this.posn2 = posn2;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	
}
	