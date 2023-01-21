package ui;

import model.Flashcard;
import model.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import static ui.GUI.userDeck;

// Represents the pop-up menu for removing a flashcard
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class FlashcardRemover extends JFrame implements ActionListener {
    private JTextField questionField;
    private JLabel questionLabel;
    private String setTitle;

    // MODIFIES: GUI
    // EFFECTS: constructs the pop-up menu for removing a flashcard
    public FlashcardRemover(String setTitle) {
        super("Remove Flashcards");
        this.setTitle = setTitle;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 120));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Remove Flashcard");
        btn.setActionCommand("myRemoveFlashcardButton");
        btn.addActionListener(this);
        questionField = new JTextField(5);
        questionLabel = new JLabel("Enter the Question of the Flashcard you Wish to Remove:");
        add(questionLabel);
        add(questionField);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: userDeck
    // EFFECTS: Removes a flashcard of the given question from the chosen set after the
    //          "Remove Flashcard" button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myRemoveFlashcardButton")) {
            for (Set set : userDeck.getSetList()) {
                if (set.getTitle().equals(setTitle)) {
                    String question = questionField.getText();
                    if (set.containsFlashcard(question)) {
                        for (Iterator<Flashcard> iterator = set.getFlashcardList().iterator(); iterator.hasNext();) {
                            Flashcard flashcard = iterator.next();
                            if (flashcard.getQuestion().equals(question)) {
                                iterator.remove();
                                new SuccessfulFlashcardRemoval(setTitle);
                            }
                        }
                    }
                }
            }
        }
    }
}
