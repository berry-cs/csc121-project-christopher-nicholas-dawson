import java.util.Objects;

import processing.core.PApplet;

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
		c.pushMatrix();
		//c.scale(.25f);
		// c.image(c.loadImage("Jumper.png"), this.loc.getX(), this.loc.getY());
		c.fill(0, 0,255);
		c.ellipse((int)this.loc.getX(), (int)this.loc.getY(), this.width, this.height);
		c.popMatrix();
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

	public Jumper translateVel(Posn offset) {
		return new Jumper(this.loc, new Posn(this.vel.getX() + offset.getX(), this.vel.getY() + offset.getY()), this.width, this.height);
	}


	public Jumper move() {
		return new Jumper(this.loc.translate(this.vel),   this.vel.translate(new Posn(0, GRAVITY)).bound(12), this.width, this.height);
	}


	public Jumper boost() {
		return new Jumper(this.loc.translate(this.vel), this.vel.translate(new Posn(0, (GRAVITY - 9))).bound(12), this.width, this.height); 
	} 



	public Jumper Collider(Platform platform) {
		if(this.isCollisionPla(platform)) {

			return new Jumper( this.loc, new Posn(0, 0),  this.width, this.height);
		}else {
			return new Jumper(this.loc.translate(this.vel),   this.vel.translate(new Posn(0, GRAVITY)).bound(12), this.width, this.height);
		}
	}


	public boolean isCollisionlop(LoX<Platform> platforms) {

		if (platforms.isEmpty()) {
			return false;
		} else if (this.isCollisionPla(platforms.getFirst())) {
			return true;
		} else if (this.isCollisionlop(platforms.getRest())){
			return true;
		} else {
			return false;
		}
	}

	public boolean isCollisionPla(Platform platform) {
		if (withinXRangePla(platform) && withinYRangePla(platform)) {
			return true;
		} else {
			return false;
		}
	}


	boolean withinXRangePla(Platform platform) {
		return this.loc.getX() + this.width >= platform.getPosn().getX() 
				&& this.loc.x <= platform.posn.x + platform.width;
	}

	boolean withinYRangePla(Platform platform) {
		return this.loc.y + this.height >= platform.posn.y 
				&& this.loc.y <= platform.posn.y + platform.height;

	}
	
	public boolean isCollisionOb(Obstacle obstacle) {
		if (withinXRangeOb(obstacle) && withinYRangeOb(obstacle)) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean withinXRangeOb(Obstacle obstacle) {
		return this.loc.x + this.width >= obstacle.posn.x 
				&& this.loc.x <= obstacle.posn.x + obstacle.width;
	}

	boolean withinYRangeOb(Obstacle obstacle) {
		return this.loc.y + this.height > obstacle.posn.y 
				&& this.loc.y < obstacle.posn.y + obstacle.height;
	}
	
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
