import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

	//private List<Score> topScores;
	private ArrayList<Score> scores;
	
	public DoodleWorld() {
		this(new Score[] {});
	}
	
	DoodleWorld(Score[] scores) {
		this.scores = new ArrayList<Score>(Arrays.asList(scores));
	}
//	public DoodleWorld() {
//		this.topScores = new ArrayList<>();
//	}

	private Score score;
	private int scrollAmount;

	private static final int ScreenHgt = 600;

	public DoodleWorld(Jumper jumper, LoX<Platform> platforms, LoX<Obstacle> obstacles,LoX<Star> stars, int scrollAmount, Score score) {
		this.jumper = jumper;
		this.platforms = platforms;
		this.obstacles = obstacles;
		this.stars = stars;
		this.scrollAmount = scrollAmount;
		this.score = score;
		this.scores = new ArrayList<>();
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
		c.background(255);  
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

	/** decide whether a new obstacle should be generated */
	public boolean readyForNewObstacle() {
		return Math.random() < 0.005; // 0.5 percent chance an obstacle generates
	}

	/** decide whether a new star should be generated */
	public boolean readyForNewStar() {
		return Math.random() < 0.005;
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
		return ScreenHgt + actualScrollAmount();
	}


	// updates the state of our doodleworld
	public IWorld update() { 
		Jumper newJumper = this.jumper.move();

		if (this.jumper.isCollisionLop(platforms)) {
			score.increment(100); 
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
		} else if (this.jumper.isCollisionLoS(stars)) {
			newStars = this.jumper.removeStar(stars);
			score.increment(200);
		} else {
			newStars = this.stars;
		}


		return new DoodleWorld(newJumper, newPlatforms, newObstacles, newStars, this.scrollAmount + 80, this.score); 
	}

	// jumper boosts if spacebar is pressed, jumper moves to the left with press of left arrow, right with press of right arrow
	public DoodleWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == ' ') {  // space
			return new DoodleWorld(this.jumper.boost(), this.platforms, this.obstacles, this.stars, this.scrollAmount, this.score);
		} else if (kev.getKeyCode() == PApplet.LEFT) {
			// Move jumper to the left by translating its position by -10 units in x
			return new DoodleWorld(this.jumper.translate(new Posn(-10, 0)), this.platforms, this.obstacles,this.stars, this.scrollAmount, this.score);
		} else if (kev.getKeyCode() == PApplet.RIGHT) {
			// Move jumper to the right by translating its position by 10 units in x
			return new DoodleWorld(this.jumper.translate(new Posn(10, 0)), this.platforms, this.obstacles,this.stars, this.scrollAmount, this.score);
		} else {
			switch (kev.getKey()) {  // Use getKeyChar() to handle 's', 'o' etc.
			case 's':
				System.out.println("Saving score...");
				saveTopScores();
				System.out.println("Score Saved");
				break;
			case 'o': 
				System.out.println("Loading top scores...");
				loadTopScores(); 
				
				break;
			default:
				break;
			}

			return new DoodleWorld(this.jumper.translateVel(new Posn(0, 0)), this.platforms,this.obstacles, this.stars, this.scrollAmount, this.score);
		}
	}

	// saves the current score in the window to a text file
	public void saveTopScores() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("scores.txt", true));

			for (Score s : this.scores) {
				s.writeToFile(pw);
			}

			pw.close();
		} catch (IOException exp) {
			System.out.println("Problem saving score: " + exp.getMessage());
		}
	}

	// outputs the top scores to a output text file
	public void loadTopScores() {
		try {
			Scanner sc = new Scanner(new File("top-scores.txt"));
			this.scores.clear();
			
			while(sc.hasNextInt()) {
				
			}
			
			sc.close();
		} catch (IOException exp) {
			System.out.println("Problem loading top scores: " + exp.getMessage());
		}
	}


	//	/**
	//	 * Writes a single score to the given PrintWriter.
	//	 */
	//	private void writeScoreToFile(PrintWriter pw, int score) {
	//		pw.println(score);
	//	}
	//
	//	/**
	//	 * Saves the top scores to a text file specified by the user.
	//	 */
	//	public void saveTopScores() {
	//		try {
	//			// Prompt the user for a file name
	//			String filename = javax.swing.JOptionPane.showInputDialog("Please enter file name:");
	//			filename = filename.trim();
	//			if (filename.equals("")) {
	//				javax.swing.JOptionPane.showMessageDialog(null, "Cannot save to a blank name");
	//				return;
	//			}
	//			if (!filename.endsWith(".txt")) {
	//				filename = filename + ".txt"; // Ensure the file ends with .txt
	//			}
	//
	//			// Open the file for writing
	//			PrintWriter pw = new PrintWriter(new File(filename));
	//
	//			// Use a helper method to write each score
	//			for (int score : this.topScores) {
	//				writeScoreToFile(pw, score);
	//			}
	//
	//			pw.close(); // Close the writer
	//			System.out.println("Scores saved successfully to: " + filename);
	//		} catch (IOException exp) {
	//			System.out.println("Problem saving scores: " + exp.getMessage());
	//		}
	//	}
	//
	//	/**
	//	 * Loads the top scores from a text file specified by the user.
	//	 */
	//	public void loadTopScores() {
	//		try {
	//			// Prompt the user for a file name
	//			String filename = javax.swing.JOptionPane.showInputDialog("Please enter file name:");
	//			filename = filename.trim();
	//
	//			if (filename.equals("")) {
	//				javax.swing.JOptionPane.showMessageDialog(null, "Cannot load from a blank name");
	//				return;
	//			}
	//
	//			// Ensure the filename ends with .txt
	//			if (!filename.endsWith(".txt")) {
	//				filename = filename + ".txt";
	//			}
	//
	//			// Open the file for reading
	//			Scanner sc = new Scanner(new File(filename));
	//
	//			// Clear the current top scores
	//			List<Integer> topScores = new ArrayList<>();
	//
	//			// Read the scores from the file
	//			while (sc.hasNextInt()) {
	//				topScores.add(sc.nextInt());
	//			}
	//
	//			// Optionally: Sort the scores (if needed)
	//			// topScores.sort(Collections.reverseOrder());
	//
	//			// Close the scanner
	//			sc.close();
	//
	//			// Optionally, you can display the loaded scores or assign them to a field
	//			System.out.println("Top scores loaded successfully:");
	//			for (int score : topScores) {
	//				System.out.println(score);
	//			}
	//
	//		} catch (IOException exp) {
	//			System.out.println("Problem loading scores: " + exp.getMessage());
	//		}
	//	}



}
