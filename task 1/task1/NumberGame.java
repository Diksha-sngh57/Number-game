import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGame extends JFrame {
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel scoreLabel;
    private JButton guessButton;
    private int number;
    private int attempts;
    private final int maxAttempts = 10;
    private int totalScore = 0;
    private int round = 1;
    private boolean gameOver;

    public NumberGame() {
        setTitle("Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JLabel instruction = new JLabel("Round " + round + ": Guess a number between 1 and 100");
        add(instruction);

        guessField = new JTextField();
        add(guessField);

        guessButton = new JButton("Guess");
        add(guessButton);

        feedbackLabel = new JLabel("");
        add(feedbackLabel);

        scoreLabel = new JLabel("Total Score: 0");
        add(scoreLabel);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameOver) {
                    startNewRound();
                    return;
                }

                int guess;
                try {
                    guess = Integer.parseInt(guessField.getText());
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Invalid input. Please enter a number.");
                    return;
                }

                attempts++;

                if (guess == number) {
                    feedbackLabel.setText("Correct! You took " + attempts + " attempts.");
                    int roundScore = maxAttempts - attempts + 1;
                    totalScore += roundScore;
                    scoreLabel.setText("Total Score: " + totalScore);
                    guessButton.setText("Play Next Round");
                    gameOver = true;
                } else if (guess > number) {
                    feedbackLabel.setText("Too high! Attempts left: " + (maxAttempts - attempts));
                } else {
                    feedbackLabel.setText("Too low! Attempts left: " + (maxAttempts - attempts));
                }

                if (attempts >= maxAttempts && !gameOver) {
                    feedbackLabel.setText("Out of attempts! The number was " + number + ".");
                    guessButton.setText("Play Next Round");
                    gameOver = true;
                }

                guessField.setText("");
            }
        });

        startNewRound();
    }

    private void startNewRound() {
        number = (int) (Math.random() * 100) + 1;
        attempts = 0;
        gameOver = false;
        round++;
        feedbackLabel.setText("Round " + round + ": New round started.");
        guessButton.setText("Guess");
        guessField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGame().setVisible(true));
    }
}
