import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.io.*;

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
		return "DoodleWorld [jumper=" + jumper + ", platforms=" + platforms + ", star=" + stars + ", obstacles="
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
		return Math.random() < 0.02; // 2 percent chance a platform generates
	}

	// decide whether a new obstacle should be generated
	public boolean readyForNewObstacle() {
<<<<<<< HEAD
		return Math.random() < 0.005; // 0.5 percent chance an obstacle generates
=======
		return Math.random() < 0.005;
	}

	public boolean readyForNewStar() {
		return Math.random() < 0.005;
>>>>>>> aad9ddda4d2e1cb62e3d3eb51aca034323a3df62
	}

	public boolean readyForNewStar() {
		return Math.random() < 0.005; // 0.5 percent chance an obstacle generates
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

		if (this.jumper.isCollisionLoS(stars)) {
			score.increment(200);
		}

		LoX<Platform> newPlatforms;
		if (readyForNewPlatform()) {
			newPlatforms = new Cons<Platform>(new Platform(topY()), this.platforms);
		} else {
			newPlatforms = this.platforms;
		}

<<<<<<< HEAD
		LoX<Obstacle> newObstacles;
		if (readyForNewObstacle()) {
			newObstacles = new Cons<Obstacle>(new Obstacle(topY()), this.obstacles);
		} else {
			newObstacles = this.obstacles;
		}
		
=======
>>>>>>> aad9ddda4d2e1cb62e3d3eb51aca034323a3df62
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
<<<<<<< HEAD
			return new DoodleWorld(this.jumper.translateVel(new Posn(-5, 0)), this.platforms, this.obstacles,this.stars, this.scrollAmount, this.score);
		} else if (kev.getKeyCode() == PApplet.RIGHT) {
			// Move jumper to the right by translating its position by 10 units in x
			return new DoodleWorld(this.jumper.translateVel(new Posn(5, 0)), this.platforms, this.obstacles,this.stars, this.scrollAmount, this.score);
=======
 
			return new DoodleWorld(this.jumper.translateVel(new Posn(-2, 0)), this.platforms, this.stars,this.obstacles, this.scrollAmount, this.score);
		} else if (kev.getKeyCode() == PApplet.RIGHT) {
			// Move jumper to the right by translating its position by 10 units in x
			return new DoodleWorld(this.jumper.translateVel(new Posn(2, 0)), this.platforms, this.stars,this.obstacles, this.scrollAmount, this.score);
		}   else if (kev.getKeyCode() == PApplet.RIGHT) {
			// Move jumper to the right by translating its position by 10 units in x
			return new DoodleWorld(this.jumper.translateVel(new Posn(2, 0)), this.platforms, this.stars, this.obstacles, this.scrollAmount, this.score);
>>>>>>> aad9ddda4d2e1cb62e3d3eb51aca034323a3df62
		} else {
			switch (kev.getKey()) {  // Use getKeyChar() to handle 's', 'o' etc.
			case 's':
				System.out.println("Saving top scores...");
				saveTopScores(null);  // Call method to save the top scores
				break;
			case 'o':
				System.out.println("Loading top scores...");
				loadTopScores();  // Call method to load the top scores
				break;
			default:
				break;
			}

			return new DoodleWorld(this.jumper.translateVel(new Posn(-2, 0)), this.platforms,this.stars, this.obstacles, this.scrollAmount, this.score);
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
		return SIZE + actualScrollAmount();
	}
	
	/**
	 * Writes a single score to the given PrintWriter.
	 */
	private void writeScoreToFile(PrintWriter pw, int score) {
		pw.println(score);
	}

	/**
	 * Saves the top scores to a text file specified by the user.
	 */
	public void saveTopScores(List<Integer> topScores) {
		try {
			// Prompt the user for a file name
			String filename = javax.swing.JOptionPane.showInputDialog("Please enter file name:");
			filename = filename.trim();
			if (filename.equals("")) {
				javax.swing.JOptionPane.showMessageDialog(null, "Cannot save to a blank name");
				return;
			}
			if (!filename.endsWith(".txt")) {
				filename = filename + ".txt"; // Ensure the file ends with .txt
			}

			// Open the file for writing
			PrintWriter pw = new PrintWriter(new File(filename));

			// Use a helper method to write each score
			for (int score : topScores) {
				writeScoreToFile(pw, score);
			}

			pw.close(); // Close the writer
			System.out.println("Scores saved successfully to: " + filename);
		} catch (IOException exp) {
			System.out.println("Problem saving scores: " + exp.getMessage());
		}
	}

	/**
	 * Loads the top scores from a text file specified by the user.
	 */
	public void loadTopScores() {
		try {
			// Prompt the user for a file name
			String filename = javax.swing.JOptionPane.showInputDialog("Please enter file name:");
			filename = filename.trim();

			if (filename.equals("")) {
				javax.swing.JOptionPane.showMessageDialog(null, "Cannot load from a blank name");
				return;
			}

			// Ensure the filename ends with .txt
			if (!filename.endsWith(".txt")) {
				filename = filename + ".txt";
			}

			// Open the file for reading
			Scanner sc = new Scanner(new File(filename));

			// Clear the current top scores
			List<Integer> topScores = new ArrayList<>();

			// Read the scores from the file
			while (sc.hasNextInt()) {
				topScores.add(sc.nextInt());
			}

			// Optionally: Sort the scores (if needed)
			// topScores.sort(Collections.reverseOrder());

			// Close the scanner
			sc.close();

			// Optionally, you can display the loaded scores or assign them to a field
			System.out.println("Top scores loaded successfully:");
			for (int score : topScores) {
				System.out.println(score);
			}

		} catch (IOException exp) {
			System.out.println("Problem loading scores: " + exp.getMessage());
		}
	}

	
	
}
