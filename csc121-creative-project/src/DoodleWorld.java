import java.util.Objects;
import processing.core.PApplet;
import processing.event.KeyEvent;

/** represent the state of our doodle jump animation */
public class DoodleWorld implements IWorld {


	private Jumper jumper;
	private LoX<Platform> platforms;
	private LoX<Star> stars;
	private LoX<Obstacle> obstacles;
	private Score score;
	private int scrollAmount;

	public DoodleWorld(Jumper jumper, LoX<Platform> platforms, LoX<Star> stars, LoX<Obstacle> obstacles, int scrollAmount, Score score) {
		this.jumper = jumper;
		this.platforms = platforms;
		this.stars = stars;
		this.obstacles = obstacles;
		this.scrollAmount = scrollAmount;
		this.score = score; // Use the score parameter directly
	} 

	@Override
	public String toString() {
		return "DoodleWorld [jumper=" + jumper + ", platforms=" + platforms + ", stars=" + stars + ", obstacles="
				+ obstacles + ", score=" + score + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(jumper, obstacles, platforms, score, scrollAmount, stars);
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
				&& scrollAmount == other.scrollAmount && Objects.equals(stars, other.stars);
	}
	// draws our doodleworld
	public PApplet draw(PApplet c) {
		c.background(255);  // clear the screen each time (color white)
		c.translate(0, actualScrollAmount());
		this.jumper.draw(c);
		this.platforms.draws(c);
		this.stars.draws(c);
		this.obstacles.draws(c);

		c.translate(0, - actualScrollAmount());
		score.draw(c);

		c.translate(0, actualScrollAmount());

		return c;
	}

	/** decide whether a new platform should be generated */
	public boolean readyForNewPlatform() {
		return Math.random() < 0.02;
	}

	// decide whether a new obstacle should be generated
	public boolean readyForNewObstacle() {
		return Math.random() < 0.005;
	}
	
	public boolean readyForNewStar() {
		return Math.random() < 0.025;
	}

	// updates the state of our doodleworld
	public IWorld update() { 
		Jumper newJumper = this.jumper.move();

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
		
		LoX<Star> newStars;
		if (readyForNewStar()) {
			newStars = new Cons<Star>(new Star(topY()), this.stars);
		} else {
			newStars = this.stars;
		}

		LoX<Obstacle> newObstacles;
		if (readyForNewObstacle()) {
			newObstacles = new Cons<Obstacle>(new Obstacle(topY()), this.obstacles);
		} else {
			newObstacles = this.obstacles;
		}

		return new DoodleWorld(newJumper, newPlatforms, newStars, newObstacles, this.scrollAmount + 80, this.score); 
	}

	// jumper boosts if spacebar is pressed, jumper moves to the left with press of left arrow, right with press of right arrow
	public DoodleWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == ' ') {  // space
			return new DoodleWorld(this.jumper.boost(), this.platforms, this.stars, this.obstacles, this.scrollAmount, this.score);
		} else if (kev.getKeyCode() == PApplet.LEFT) {
			// Move jumper to the left by translating its position by -10 units in x
			return new DoodleWorld(this.jumper.translateVel(new Posn(-2, 0)), this.platforms,this.stars, this.obstacles, this.scrollAmount, this.score);
		} else if (kev.getKeyCode() == PApplet.RIGHT) {
			// Move jumper to the right by translating its position by 10 units in x
			return new DoodleWorld(this.jumper.translateVel(new Posn(2, 0)), this.platforms, this.stars, this.obstacles, this.scrollAmount, this.score);
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
