import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGameGUI extends JFrame {
    private int targetNumber;
    private int attempts;
    private int roundsWon;

    private JTextField guessTextField;
    private JTextArea feedbackTextArea;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initGame();

        // UI components
        JLabel guessLabel = new JLabel("Enter your guess:");
        guessTextField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        feedbackTextArea = new JTextArea(5, 20);
        feedbackTextArea.setEditable(false);
        feedbackTextArea.setLineWrap(true);

        // Layout
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.add(guessLabel);
        inputPanel.add(guessTextField);
        inputPanel.add(guessButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(feedbackTextArea), BorderLayout.CENTER);

        // Event listeners
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void initGame() {
        targetNumber = generateRandomNumber(1, 100);
        attempts = 0;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());
            attempts++;

            if (userGuess == targetNumber) {
                roundsWon++;
                feedbackTextArea.append("Congratulations! You guessed the correct number in " + attempts + " attempts.\n");
                feedbackTextArea.append("Rounds won: " + roundsWon + "\n");

                int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    initGame();
                    guessTextField.setText("");
                    feedbackTextArea.setText("");
                } else {
                    System.exit(0);
                }
            } else if (userGuess < targetNumber) {
                feedbackTextArea.append("Too low. Try again.\n");
            } else {
                feedbackTextArea.append("Too high. Try again.\n");
            }

            guessTextField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}
