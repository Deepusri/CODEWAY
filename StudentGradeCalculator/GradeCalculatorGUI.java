import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GradeCalculatorGUI {
    private JFrame frame;
    private JTextField[] subjectFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public GradeCalculatorGUI() {
        frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 2));

        // Create input fields for subjects
        int numSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of subjects:"));
        subjectFields = new JTextField[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            subjectFields[i] = new JTextField();
            frame.add(new JLabel("Subject " + (i + 1) + " Marks:"));
            frame.add(subjectFields[i]);
        }

        // Calculate button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });
        frame.add(calculateButton);

        // Reset button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        frame.add(resetButton);

        // Labels for results
        totalMarksLabel = new JLabel();
        averagePercentageLabel = new JLabel();
        gradeLabel = new JLabel();
        frame.add(totalMarksLabel);
        frame.add(averagePercentageLabel);
        frame.add(gradeLabel);

        frame.setVisible(true);
    }

    private void calculateResults() {
        int totalMarks = 0;
        for (JTextField field : subjectFields) {
            totalMarks += Integer.parseInt(field.getText());
            int marks = Integer.parseInt(field.getText());
            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(frame, "Please enter valid marks (0-100).");
                return;
            }

        }

        double averagePercentage = (double) totalMarks / subjectFields.length;

        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        gradeLabel.setText("Grade: " + grade);
    }

    private void resetFields() {
        for (JTextField field : subjectFields) {
            field.setText("");
        }
        totalMarksLabel.setText("");
        averagePercentageLabel.setText("");
        gradeLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeCalculatorGUI());
    }
}
