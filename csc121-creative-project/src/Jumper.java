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
		 if (this.atBottom() ) {
		   return new Jumper( this.loc, new Posn(0, 0),  this.width, this.height);
		 }else {
			return new Jumper(this.loc.translate(this.vel),   this.vel.translate(new Posn(0, GRAVITY)).bound(12), this.width, this.height);
	}
	}
	


	public Jumper boost() {
		return new Jumper(this.loc.translate(this.vel), this.vel.translate(new Posn(0, (GRAVITY - 9))).bound(12), this.width, this.height);
	} 
	
	
	public Jumper Collider(Platform platform) {
		if(this.isCollision(platform)) {
			return new Jumper( this.loc, new Posn(0, 0),  this.width, this.height);
		}else {
			return new Jumper(this.loc.translate(this.vel),   this.vel.translate(new Posn(0, GRAVITY)).bound(12), this.width, this.height);
		}
	}
	

	public boolean isCollision(Platform platform) {
	    if (withinXRange(platform) && withinYRange(platform)) {
	        return true;
	    } else {
	        return false;
	    }
	}

	boolean withinXRange(Platform platform) {
	    return this.loc.x + this.width >= platform.posn.x 
	           && this.loc.x <= platform.posn.x + platform.width;
	}

	boolean withinYRange(Platform platform) {
	    return this.loc.y + this.height >= platform.posn.y 
	           && this.loc.y <= platform.posn.y + platform.height;
	}



	// Method to translate (move) the jumper's position by a certain offset
	public Jumper translate(Posn offset) {
		return new Jumper(new Posn(this.loc.x + offset.x, this.loc.y + offset.y), this.vel, this.width, this.height);
	}
	 

	// Getter for position
	public Posn getPosition() {
		return this.loc;
	}

	public Boolean atBottom(DoodleWorld w) {
						// w.bottomY()
		if (this.loc.y >= 400 && this.vel.y >= 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Jumper [loc=" + loc + ", vel=" + vel + ", width=" + width + ", height=" + height + "]";
	}


}
