import java.util.Objects;

import processing.core.PApplet;

public class Jumper {
	Posn loc;
	Posn vel;
	int width;
	int height;
	//velocity

	static int GRAVITY = 1;

	Jumper(Posn loc, Posn vel, int width, int height) {
		super();
		this.loc = loc;
		this.vel = vel;
		this.width = width;
		this.height = height;
	}

	/** 
	 * 
	 */
	/*public Jumper Jump(){
		 Posn newvel = this.vel.translate(new Posn(0,10)); // we need a gravity function that decreases the velocity except for when collisions occur
		 Posn newloc = this.loc.translate(newvel); //how to alter the velocity is the question
		 return new Jumper(newloc, this.vel);
	 }
	 */

	public PApplet draw(PApplet c) {
		c.imageMode(PApplet.CENTER);
		c.pushMatrix();
		//c.scale(.25f);
		// c.image(c.loadImage("Jumper.png"), this.loc.getX(), this.loc.getY());
		c.fill(0, 0,255);
		c.ellipse(this.loc.getX(), this.loc.getY(), this.width, this.height);
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
		return new Jumper(this.loc, new Posn(this.vel.x + offset.x, this.vel.y + offset.y), this.width, this.height);
	}

	public Jumper move(Platform platform) {
			return new Jumper(this.loc.translate(this.vel),   this.vel.translate(new Posn(0, GRAVITY)), this.width, this.height);
	}

	public Jumper boost() {
		return new Jumper(this.loc.translate(this.vel), this.vel.translate(new Posn(0, (GRAVITY - 15))), this.width, this.height);
	} 

	public boolean isCollision(Platform platform) {
	return (this.loc.y == platform.posn.y);
	} 

	// Method to translate (move) the jumper's position by a certain offset
	public Jumper translate(Posn offset) {
		return new Jumper(new Posn(this.loc.x + offset.x, this.loc.y + offset.y), this.vel, this.width, this.height);
	}
	 

	// Getter for position
	public Posn getPosition() {
		return this.loc;
	}





}
