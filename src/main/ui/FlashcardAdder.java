package ui;

import model.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.userDeck;

// Represents the pop-up menu for adding a flashcard
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class FlashcardAdder extends JFrame implements ActionListener {
    private JTextField questionField;
    private JTextField answerField;
    private JLabel questionLabel;
    private JLabel answerLabel;
    private String setTitle;


    // MODIFIES: GUI
    // EFFECTS: constructs a pop-up menu for adding a flashcard
    public FlashcardAdder(String setTitle) {
        super("Add Flashcards");
        this.setTitle = setTitle;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 120));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Add Flashcard");
        btn.setActionCommand("myAddFlashcardButton");
        btn.addActionListener(this);
        questionField = new JTextField(5);
        answerField = new JTextField(5);
        questionLabel = new JLabel("Question:");
        answerLabel = new JLabel("Answer:");
        add(questionLabel);
        add(questionField);
        add(answerLabel);
        add(answerField);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: userDeck
    // EFFECTS: Adds a flashcard with the given question and answer to the chosen set after the
    //          "Add Flashcard" button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myAddFlashcardButton")) {
            for (Set set : userDeck.getSetList()) {
                if (set.getTitle().equals(setTitle)) {
                    String question = questionField.getText();
                    String answer = answerField.getText();
                    set.addFlashcard(question, answer);
                    new SuccessfulFlashcardAdd(setTitle);
                }
            }
        }
    }
}
