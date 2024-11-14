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

    public DoodleWorld(Jumper jumper, ILoP platforms, int scrollAmount, Score score) {
        this.jumper = jumper;
        this.platforms = platforms;
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

    public PApplet draw(PApplet c) {
        c.background(255);  // clear the screen each time (color white)
        c.translate(0, actualScrollAmount());
        this.jumper.draw(c);
        this.platforms.draws(c);

        c.translate(0, -actualScrollAmount());
        score.draw(c);
        
        c.translate(0, actualScrollAmount());
        
        return c;
    }

    /** decide whether a new platform should be generated */
    public boolean readyForNewPlatform() {
        return Math.random() < 0.0125;
    }

    public IWorld update() { 
        Jumper newJumper = this.jumper.move();

        if (this.jumper.isCollisionlop(platforms)) {
            score.increment(100); // increase score by 100 points
            newJumper = this.jumper.boost();
        }

        if (this.jumper.atBottom(actualScrollAmount())) {
            throw new RuntimeException("Game Over");
        }

        ILoP newPlatforms;
        if (readyForNewPlatform()) {
            newPlatforms = new ConsLoP(new Platform(topY()), this.platforms);
        } else {
            newPlatforms = this.platforms;
        }

        return new DoodleWorld(newJumper, newPlatforms, this.scrollAmount + 80, this.score); 
    }

    public DoodleWorld keyPressed(KeyEvent kev) {
        if (kev.getKey() == ' ') {  // space
            return new DoodleWorld(this.jumper.boost(), this.platforms, this.scrollAmount, this.score);
        } else if (kev.getKeyCode() == PApplet.LEFT) {
            // Move jumper to the left by translating its position by -10 units in x
            return new DoodleWorld(this.jumper.translate(new Posn(-10, 0)), this.platforms, this.scrollAmount, this.score);
        } else if (kev.getKeyCode() == PApplet.RIGHT) {
            // Move jumper to the right by translating its position by 10 units in x
            return new DoodleWorld(this.jumper.translate(new Posn(10, 0)), this.platforms, this.scrollAmount, this.score);
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
