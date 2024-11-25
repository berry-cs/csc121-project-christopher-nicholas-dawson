import java.util.Objects;
import processing.core.PApplet;
import processing.event.KeyEvent;

/** represent the state of our doodle jump animation */
public class DoodleWorld implements IWorld {


	private Jumper jumper;
	private LoX<Platform> platforms;
	private Star star;
	private LoX<Obstacle> obstacles;
	private LoX<Star> stars;
	private Score score;
	private int scrollAmount;
	
	private boolean moveLeft = false;
	private boolean moveRight = false;
	private static final int SIZE = 400;
	
	


	public DoodleWorld(Jumper jumper, LoX<Platform> platforms, LoX<Obstacle> obstacles,LoX<Star> stars, int scrollAmount, Score score) {
		this.jumper = jumper;
		this.platforms = platforms;
		this.obstacles = obstacles;
		this.stars = stars;
		this.scrollAmount = scrollAmount;
		this.score = score; // Use the score parameter directly
	} 

	@Override
	public String toString() {
		return "DoodleWorld [jumper=" + jumper + ", platforms=" + platforms + ", star=" + star + ", obstacles="
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
	// draws our doodleworld
	public PApplet draw(PApplet c) {
		c.background(255);  // clear the screen each time (color white)
		c.translate(0, actualScrollAmount());
		this.jumper.draw(c);
		this.platforms.draws(c);
		this.obstacles.draws(c);

		c.translate(0, - actualScrollAmount());
		score.draw(c);

		c.translate(0, actualScrollAmount());

		return c;
	}

	/** decide whether a new platform should be generated */
	public boolean readyForNewPlatform() {
		return Math.random() < 0.02; // 2 percent chance a platform generates
	}

	// decide whether a new obstacle should be generated
	public boolean readyForNewObstacle() {
		return Math.random() < 0.005; // 0.5 percent chance an obstacle generates
	}

	public boolean readyForNewStar() {
		return Math.random() < 0.005; // 0.5 percent chance an obstacle generates
	}
	// updates the state of our doodleworld
	public IWorld update() { 
		Jumper newJumper = this.jumper.move();
		
//		if (moveLeft) {
//			newJumper = newJumper.translate(new Posn(-5, 0));
//		}
//		
//		if (moveRight) {
//			newJumper = newJumper.translate(new Posn(5, 0));
//		}

		if (this.jumper.isCollisionLop(platforms)) {
			score.increment(100); // increase score by 100 points
			newJumper = this.jumper.boost();
		}

		if (this.jumper.isCollisionLoB(obstacles)) {
			throw new RuntimeException("Game Over");
		}

		if (this.jumper.atBottom(actualScrollAmount())) {
			throw new RuntimeException("Game Over");
		}

		LoX<Platform> newPlatforms;
		if (readyForNewPlatform()) {
			newPlatforms = new Cons<Platform>(new Platform(topY()), this.platforms);
		} else {
			newPlatforms = this.platforms;
		}

		LoX<Obstacle> newObstacles;
		if (readyForNewObstacle()) {
			newObstacles = new Cons<Obstacle>(new Obstacle(topY()), this.obstacles);
		} else {
			newObstacles = this.obstacles;
		}
		
		LoX<Star> newStars;
		if (readyForNewStar()) {
			newStars = new Cons<Star>(new Star(topY()), this.stars);
		} else {
			newStars = this.stars;
		}

		return new DoodleWorld(newJumper, newPlatforms, newObstacles, newStars,this.scrollAmount + 80, this.score); 
	}

	// jumper boosts if spacebar is pressed, jumper moves to the left with press of left arrow, right with press of right arrow
	public DoodleWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == ' ') {  // space
			return new DoodleWorld(this.jumper.boost(), this.platforms, this.obstacles, this.stars, this.scrollAmount, this.score);
		} else if (kev.getKeyCode() == PApplet.LEFT) {
			// Move jumper to the left by translating its position by -10 units in x
			return new DoodleWorld(this.jumper.translateVel(new Posn(-5, 0)), this.platforms, this.obstacles,this.stars, this.scrollAmount, this.score);
		} else if (kev.getKeyCode() == PApplet.RIGHT) {
			// Move jumper to the right by translating its position by 10 units in x
			return new DoodleWorld(this.jumper.translateVel(new Posn(5, 0)), this.platforms, this.obstacles,this.stars, this.scrollAmount, this.score);
		} else {
			return this;
		}
	}
	
//	public DoodleWorld keyPressed(KeyEvent kev) {
//		if (kev.getKey() == ' ') {  // space
//			return new DoodleWorld(this.jumper.boost(), this.platforms, this.obstacles, this.scrollAmount, this.score);
//		} else if (kev.getKeyCode() == PApplet.LEFT) {
//			moveLeft = true;
//			return this;
//		//	return new DoodleWorld(this.jumper.translate(new Posn(-10, 0)), this.platforms, this.obstacles, this.scrollAmount, this.score);
//		} else if (kev.getKeyCode() == PApplet.RIGHT) {
//			moveRight = true;
//			return this;
//		//	return new DoodleWorld(this.jumper.translate(new Posn(10, 0)), this.platforms, this.obstacles, this.scrollAmount, this.score);
//		} else {
//			return this;
//		}
//	}
//	
//	public DoodleWorld keyReleased(KeyEvent kev) {
//		if (kev.getKeyCode() == PApplet.LEFT) {
//			moveLeft = false;
//		} else if (kev.getKeyCode() == PApplet.RIGHT) {
//			moveRight = false;
//		} 
//			return this;
//		}
	

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
		return SIZE + actualScrollAmount();
	}
}
