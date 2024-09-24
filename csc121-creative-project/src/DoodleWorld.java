import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;

/** represent the state of our doodle jump animation */
public class DoodleWorld implements IWorld {

	Jumper jumper;
	Platform platform;
	Star star;
	Obstacle obstacles;
	Score score;

	public DoodleWorld(Jumper jumper, Platform platform)   {   //, Platform platform, Star star, IObstacle obsticals, Score score) {
		super();
		this.jumper = jumper;
		this.platform = platform;
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
		return c;
	}


	public IWorld update() {

		return new DoodleWorld( this.jumper.move(),  this.platform );

	}
	// Method to handle key presses for moving the jumper
	public DoodleWorld keyPressed(KeyEvent kev) {
        if (kev.getKeyCode() == PApplet.LEFT) {
            // Move jumper to the left by translating its position by -10 units in x
            return new DoodleWorld(this.jumper.translate(new Posn(-10, 0)), this.platform);
        } else if (kev.getKeyCode() == PApplet.RIGHT) {
            // Move jumper to the right by translating its position by 10 units in x
            return new DoodleWorld(this.jumper.translate(new Posn(10, 0)), this.platform);
        } else {
            return this;  // No change if other keys are pressed
        }
    }
}

