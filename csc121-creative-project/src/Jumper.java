import java.util.Objects;

import processing.core.PApplet;

public class Jumper {
	Posn loc;
	Posn vel; //velocity

	 Jumper(Posn loc, Posn vel) {
		super();
		this.loc = loc;
		this.vel = vel;
	}

	 public Jumper Jump(){
		 Posn newloc = this.loc.translate(this.vel); //how to alter the velocity is the question
		 return new Jumper(newloc, this.vel);
	 }
	
	 
	public PApplet draw(PApplet c) {
	        c.imageMode(PApplet.CENTER);
	        c.pushMatrix();
	        //c.scale(.25f);
	       // c.image(c.loadImage("Jumper.png"), this.loc.getX(), this.loc.getY());
	        c.fill(0, 0,255);
	        c.circle(this.loc.getX(), this.loc.getY(), 10);
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
	
}
