import java.util.Random;
import javax.swing.JOptionPane;

public class GuessTheNumber {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 5;

    public static void main(String[] args) {
        int totalScore = 0;
        boolean playAgain = true;

        while (playAgain) {
            int score = playRound();
            totalScore += score;

            String message = "Score: " + score + "\nTotal Score: " + totalScore;
            message += "\n\nDo you want to play again?";
            int option = JOptionPane.showConfirmDialog(null, message, "Guess the Number", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                playAgain = false;
            }
        }

        JOptionPane.showMessageDialog(null, "Thank you for playing. Final Score: " + totalScore);
    }

    private static int playRound() {
        Random random = new Random();
        int targetNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            String guessString = JOptionPane.showInputDialog("Enter your guess (between " + MIN_RANGE + " and " + MAX_RANGE + "):");

            if (guessString == null) {
                return 0;  // User clicked Cancel, exit the round without scoring
            }

            int guess = Integer.parseInt(guessString);
            attempts++;

            if (guess == targetNumber) {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number in " + attempts + " attempt(s)!");
                return MAX_ATTEMPTS - attempts + 1;  // Assign score based on attempts remaining
            } else if (guess < targetNumber) {
                JOptionPane.showMessageDialog(null, "Too low! Try again.");
            } else {
                JOptionPane.showMessageDialog(null, "Too high! Try again.");
            }
        }

        JOptionPane.showMessageDialog(null, "Sorry, you have used all your attempts. The number was: " + targetNumber);
        return 0;  // No score if all attempts are used
    }
}
