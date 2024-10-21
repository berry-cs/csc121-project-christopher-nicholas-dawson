import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;

/** represent the state of our doodle jump animation */
public class DoodleWorld implements IWorld {

	Jumper jumper;
	ILoP platforms;
	Star star;
	Obstacle obstacles;
	Score score;
	int scrollAmount;


	//public DoodleWorld(Jumper jumper, ILoP platform) {
	//	this(jumper, platform, 0);
	//}

		
	public DoodleWorld(Jumper jumper, ILoP platforms, int scrollAmount) {
		super();
		this.jumper = jumper;
		this.platforms = platforms;
		this.scrollAmount = scrollAmount;
	}

	//	this.platform3 = new Platform(new Posn(300, 120), 80, 20, "blue");

//		this.platform2 = new Platform(new Posn(100, 200), 75, 10, "blue");
	//	this.platform3 = new Platform(new Posn(300, 120), 75, 10, "blue");

		
		/*
		this.star = star;
		this.obstacles = obstacle;
		this.score = score;*/


	@Override
	public String toString() {
		return "DoodleWorld [jumper=" + jumper + ", platforms=" + platforms + ", star=" + star + ", obsticals="
				+ obstacles + ", score=" + score + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(jumper, obstacles, platforms, score, scrollAmount, star);
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
				&& Objects.equals(platforms, other.platforms) && Objects.equals(score, other.score)
				&& scrollAmount == other.scrollAmount && Objects.equals(star, other.star);
	}

	public PApplet draw(PApplet c) {
		c.background(255);  // clear the screen each time (color white)
		c.translate(0, actualScrollAmount());
		this.jumper.draw(c);
		this.platforms.draws(c);
		return c;
	}

	
	/** decide whether a new platform should be generated */
	public boolean readyForNewPlatform() {
		
		
		return Math.random() < 0.01;
	}

	
    public IWorld update() {
        // Handle collisions and updates
    	
    	Jumper newJumper = this.jumper.boost();
    					//   this.jumper.boost(this)
    	ILoP newPlatforms;
    	if (readyForNewPlatform()) {
    		newPlatforms = new ConsLoP(new Platform( topY() ), this.platforms);
    	} else {
    		newPlatforms = this.platforms;
    	}
    	
    	
        return new DoodleWorld(newJumper, newPlatforms, this.scrollAmount + 90); // just using this as a placeholder 
        											// so i could make sure the platforms were drawn correctly,
        											// still figuring out collision with lists
    }
/*	public IWorld update() {
		if (this.jumper.isCollision(this.platform)) {
			return new DoodleWorld(this.jumper.boost(), this.platform, platform2, platform3);
		} else if (this.jumper.isCollision(this.platform2)) {
			return new DoodleWorld(this.jumper.boost(), this.platform, platform2, platform3);

		
		} else if (this.jumper.isCollision(this.platform3)) {
			return new DoodleWorld(this.jumper.boost(), this.platform, platform2, platform3);

		
		
		
		} else {
			return new DoodleWorld(this.jumper.move(this.platform), this.platform, platform2, platform3);
		}
	} */
	
	public DoodleWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == ' ') {  // space
			return new DoodleWorld(this.jumper.boost(), this.platforms, this.scrollAmount);

		}else if (kev.getKeyCode() == PApplet.LEFT) {
			// Move jumper to the left by translating its position by -10 units in x
			return new DoodleWorld(this.jumper.translate(new Posn(-10, 0)), this.platforms, this.scrollAmount);
		} else if (kev.getKeyCode() == PApplet.RIGHT) {
			// Move jumper to the right by translating its position by 10 units in x
			return new DoodleWorld(this.jumper.translate(new Posn(10, 0)), this.platforms, this.scrollAmount);
		} else {
			return this;
		}
	}
	
	
	/** produces the actual scroll amount in window pixels */
	public int actualScrollAmount() {
		return (int) (scrollAmount / 100.0f);
	}
	
	/** produce the y coordinate of the top visible area in the window */
	public int topY() {
		return - actualScrollAmount();
	}
	
	
	/** produce the y coordinate of the bottom of the window */
	public int bottomY() {
		return 400 + actualScrollAmount();
	}
	
} 



