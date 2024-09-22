// represents the different obstacles 
public interface IObstacle {

}


/** Represents a still obstacle*/
class StillObstacle implements IObstacle {
	Posn posn;
	boolean collision;
	String color;
	public StillObstacle(Posn posn, boolean collision, String color) {
		super();
		this.posn = posn;
		this.collision = collision;
		this.color = color;
	}

	
	
	
	
}

/** Represents a moving obstacle*/
class MovingObstacle implements IObstacle {
	Posn posn1;
	Posn posn2;
	boolean collision;
	String color;
	public MovingObstacle(Posn posn1, Posn posn2, boolean collision, String color) {
		super();
		this.posn1 = posn1;
		this.posn2 = posn2;
		this.collision = collision;
		this.color = color;
	}
	
	
}