package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a pop-up menu displaying a message that a flashcard has been removed from the given set
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SuccessfulFlashcardRemoval extends JFrame {
    private JLabel flashcardRemovalMessage;

    // MODIFIES: GUI
    // EFFECTS: Constructs a pop-up menu displaying a message that a flashcard has been removed from the given set
    public SuccessfulFlashcardRemoval(String setTitle) {
        super("Remove Flashcards");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        flashcardRemovalMessage = new JLabel("The flashcard has been removed from the set '"
                + setTitle + "'!");
        add(flashcardRemovalMessage);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
