import java.util.Objects;

import processing.core.PApplet;
import processing.core.PImage;

// represents the jumper in the doodleworld
public class Jumper {
	private Posn loc;
	private Posn vel;
	private int width;
	private int height;
	//velocity

	static double GRAVITY = .45;

	Jumper(Posn loc, Posn vel, int width, int height) {
		super();
		this.loc = loc;
		this.vel = vel;
		this.width = width;
		this.height = height;
	} 
	
	public PApplet draw(PApplet c) {
	    c.imageMode(PApplet.CENTER);
	    PImage jumperImg = c.loadImage("Jumper2.png");
	    float scaledWidth = jumperImg.width * 0.2f; 
	    float scaledHeight = jumperImg.height * 0.2f; 
	    c.image(jumperImg, (float)this.loc.getX(), (float)this.loc.getY(), scaledWidth, scaledHeight);
	    return c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(loc, vel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jumper other = (Jumper) obj;
		return Objects.equals(loc, other.loc) && Objects.equals(vel, other.vel);
	}

	// returns a new jumper with its velocity adjusted by the given offset
	public Jumper translateVel(Posn offset) {
		return new Jumper(this.loc, new Posn(this.vel.getX() + offset.getX(), this.vel.getY() + offset.getY()), this.width, this.height);
	}

	// returns a new jumper with its position updated based on its velocity and simulates gravity
	public Jumper move() {
		return new Jumper(this.loc.translate(this.vel),   this.vel.translate(new Posn(0, GRAVITY)).bound(12), this.width, this.height);
	}

	// returns a new jumper with an upward velocity boost, simulating a jump
	public Jumper boost() {
		return new Jumper(this.loc.translate(this.vel), this.vel.translate(new Posn(0, (GRAVITY - 9))).bound(12), this.width, this.height); 
	} 


	// handles collision with a platform. If a collision occurs, resets velocity to zero
	public Jumper collider(Platform platform) {
		if(this.isCollisionPla(platform)) {

			return new Jumper( this.loc, new Posn(0, 0),  this.width, this.height);
		} else {
			return new Jumper(this.loc.translate(this.vel),   this.vel.translate(new Posn(0, GRAVITY)).bound(12), this.width, this.height);
		}
	}

	// checks if the jumper is colliding with an platform in the given list
	public boolean isCollisionLop(LoX<Platform> platforms) {

		if (platforms.isEmpty()) {
			return false;
		} else if (this.isCollisionPla(platforms.getFirst())) {
			return true;
		} else if (this.isCollisionLop(platforms.getRest())){
			return true;
		} else {
			return false;
		}
	}

	// checks if the jumper is colliding with a specific platform
	public boolean isCollisionPla(Platform platform) {
		if (withinXRangePla(platform) && withinYRangePla(platform)) {
			return true;
		} else {
			return false;
		}
	}
	
	// checks if the jumper is colliding with a specific obstacle
	public boolean isCollisionOb(Obstacle obstacle) {
		if (withinXRangeOb(obstacle) && withinYRangeOb(obstacle)) {
			return true;
		} else {
			return false;
		}
	}
	
	// checks if the jumper is colliding with a specific obstacle
	public boolean isCollisionSta(Star star) {
		if (withinXRangeSta(star) && withinYRangeSta(star)) {
			return true;
		} else {
			return false;
		}
	}

	// checks if the jumper's x-coordinate overlaps with a given platform
	boolean withinXRangePla(Platform platform) {
		return this.loc.getX() + this.width >= platform.getPosn().getX() 
			&& this.loc.getX() <= platform.getPosn().getX() + platform.getWidth();
	}

	// checks if the jumper's y-coordinate overlaps with a given platform
	boolean withinYRangePla(Platform platform) {
		return this.loc.getY() + this.height >= platform.getPosn().getY() 
				&& this.loc.getY() <= platform.getPosn().getY() + platform.getHeight();
 
	}

	// checks if the jumper's x-coordinate overlaps with a given obstacle
	boolean withinXRangeOb(Obstacle obstacle) {
		return this.loc.getX() + this.width >= obstacle.getPosn().getX() 
				&& this.loc.getX() <= obstacle.getPosn().getX() + obstacle.getWidth();
	}

	// checks if the jumper's y-coordinate overlaps with a given obstacle
	boolean withinYRangeOb(Obstacle obstacle) {
		return this.loc.getY() + this.height >= obstacle.getPosn().getY() 
				&& this.loc.getY() <= obstacle.getPosn().getY() + obstacle.getHeight();
	}
	
	// checks if the jumper's x-coordinate overlaps with a given obstacle
	boolean withinXRangeSta(Star star) {
		return this.loc.getX() + this.width >= star.getPosn().getX() 
				&& this.loc.getX() <= star.getPosn().getX() + star.getWidth();
	}

	// checks if the jumper's y-coordinate overlaps with a given obstacle
	boolean withinYRangeSta(Star star) {
		return this.loc.getY() + this.height >= star.getPosn().getY() 
				&& this.loc.getY() <= star.getPosn().getY() + star.getHeight();
	}
	
	// checks if the jumper is colliding with any obstacle sin the given list
	public boolean isCollisionLoS(LoX<Star> stars) {
		if (stars.isEmpty()) {
			return false;
		} else if (this.isCollisionSta(stars.getFirst())) {
			return true;
		} else if (this.isCollisionLoS(stars.getRest())){
			return true;
		} else {
			return false;
		}
	}

	
	// checks if the jumper is colliding with any obstacle sin the given list
	public boolean isCollisionLoB(LoX<Obstacle> obstacles) {
		if (obstacles.isEmpty()) {
			return false;
		} else if (this.isCollisionOb(obstacles.getFirst())) {
			return true;
		} else if (this.isCollisionLoB(obstacles.getRest())){
			return true;
		} else {
			return false;
		}
	}



	// Method to translate (move) the jumper's position by a certain offset
	public Jumper translate(Posn offset) {
		return new Jumper(new Posn(this.loc.getX() + offset.getX(), this.loc.getY() + offset.getY()), this.vel, this.width, this.height);
	}


	// Getter for position
	public Posn getPosition() {
		return this.loc;
	}

	// determines if this jumper is at the bottom of the screen or not
	public Boolean atBottom(int scrollamt) {
		return this.loc.getY() >= 400 - scrollamt;
	}

	@Override
	public String toString() {
		return "Jumper [loc=" + loc + ", vel=" + vel + ", width=" + width + ", height=" + height + "]";
	}


}
