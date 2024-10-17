import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;

/** represent the state of our doodle jump animation */
public class DoodleWorld implements IWorld {

	Jumper jumper;
	Platform platform;
	Platform platform2;
	Platform platform3;
	Star star;
	Obstacle obstacles;
	Score score;

	public DoodleWorld(Jumper jumper, Platform platform, Platform platform2, Platform platform3)   {   //, Platform platform, Star star, IObstacle obsticals, Score score) {
		super();
		this.jumper = jumper;
		this.platform = platform;

		
		this.platform3 = new Platform(new Posn(300, 120), 80, 20, "blue");

		this.platform2 = new Platform(new Posn(100, 200), 75, 10, "blue");
		this.platform3 = new Platform(new Posn(300, 120), 75, 10, "blue");

		
		/*
		this.star = star;
		this.obstacles = obstacle;
		this.score = score;*/
	}

	@Override
	public String toString() {
		return "DoodleWorld [jumper=" + jumper + ", platforms=" + platform + ", star=" + star + ", obsticals="
				+ obstacles + ", score=" + score + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(jumper, obstacles, platform, score, star);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoodleWorld other = (DoodleWorld) obj;
		return Objects.equals(jumper, other.jumper) && Objects.equals(obstacles, other.obstacles)
				&& Objects.equals(platform, other.platform) && Objects.equals(score, other.score)
				&& Objects.equals(star, other.star);
	}

	public PApplet draw(PApplet c) {
		c.background(255);  // clear the screen each time (color white)
		this.jumper.draw(c);
		this.platform.draw(c);
		this.platform2.draw(c);
		this.platform3.draw(c);
		
		return c;
	}


	public IWorld update() {
		if (this.jumper.isCollision(this.platform)) {
			return new DoodleWorld(this.jumper.boost(), this.platform, platform2, platform3);
		} else if (this.jumper.isCollision(this.platform2)) {
			return new DoodleWorld(this.jumper.boost(), this.platform, platform2, platform3);

		
		} else if (this.jumper.isCollision(this.platform3)) {
			return new DoodleWorld(this.jumper.boost(), this.platform, platform2, platform3);

		
		
		
		} else {
			return new DoodleWorld(this.jumper.move(this.platform), this.platform, platform2, platform3);
		}
	}
	
	public DoodleWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == ' ') {  // space
			return new DoodleWorld(this.jumper.boost(), this.platform, platform2, platform3);

		}else if (kev.getKeyCode() == PApplet.LEFT) {
			// Move jumper to the left by translating its position by -10 units in x
			return new DoodleWorld(this.jumper.translate(new Posn(-10, 0)), this.platform, platform2, platform3);
		} else if (kev.getKeyCode() == PApplet.RIGHT) {
			// Move jumper to the right by translating its position by 10 units in x
			return new DoodleWorld(this.jumper.translate(new Posn(10, 0)), this.platform, platform2, platform3);
		} else {
			return this;
		}
	}
	
} 



