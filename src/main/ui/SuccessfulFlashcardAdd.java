package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a pop-up menu displaying a message that a flashcard has been added to the given set
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SuccessfulFlashcardAdd extends JFrame {
    private JLabel flashcardAddMessage;

    // MODIFIES: GUI
    // EFFECTS: Constructs a pop-up menu displaying a message that a flashcard has been added to the given set
    public SuccessfulFlashcardAdd(String setTitle) {
        super("Add Flashcards");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        flashcardAddMessage = new JLabel("Your new flashcard has been added to the set '" + setTitle + "'!");
        add(flashcardAddMessage);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
