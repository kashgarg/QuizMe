package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static ui.GUI.numCorrectAnswers;

// Represents a pop-up menu displaying the final score of the quiz
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class Results extends JFrame {
    private JLabel results;

    // MODIFIES: GUI
    // EFFECTS: constructs a pop-up menu displaying the final score of the quiz
    public Results(int flashcardListSize) {
        super("Results");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        results = new JLabel("You got " + numCorrectAnswers + " out of "
                + flashcardListSize + " questions correct!");
        add(results);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
