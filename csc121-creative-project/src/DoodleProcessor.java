import java.util.*;
import java.io.*;

public class DoodleProcessor {

	public static void main(String[] args) {

		// Print to the console
		System.out.println("Processing High Scores");

		// Prompt for the input file name
		System.out.println("Enter a file name:");
		Scanner kb = new Scanner(System.in);
		String filename = kb.nextLine();
 
		System.out.println("Reading from: " + filename + "...");

		try {
			// Open the data file for reading
			Scanner sc = new Scanner(new File(filename));

			// Open an output file for writing
			PrintWriter pw = new PrintWriter(new File("top_scores.txt"));

			// List to hold all scores
			List<Integer> scores = new ArrayList<>();

			// Read scores from the file
			while (sc.hasNextInt()) {
				scores.add(sc.nextInt());
			}

			// Sort scores in descending order
			scores.sort(Collections.reverseOrder());

			// Get the top 3 scores
			List<Integer> topScores = scores.stream().limit(3).toList();

			// Write the top scores to the output file
			pw.println("Top 3 High Scores:");
			for (int i = 0; i < topScores.size(); i++) {
				pw.println(topScores.get(i));
			}

			// Close the files
			kb.close();
			pw.close();
			sc.close();

			System.out.println("Top scores written to: top_scores.txt");

		} catch (IOException e) {
			System.out.println("Something went wrong: " + e);
			e.printStackTrace();
		}

		System.out.println("All done.");
	}
}

