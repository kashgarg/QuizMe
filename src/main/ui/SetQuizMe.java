package ui;

import model.Flashcard;
import model.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.userDeck;
import static ui.GUI.numCorrectAnswers;

// Represents the pop-up menu for choosing a set to be quizzed on
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SetQuizMe extends JFrame implements ActionListener {
    private JTextField setToQuiz;

    // MODIFIES: GUI
    // EFFECTS: constructs a pop-up menu for choosing a set to be quizzed on
    public SetQuizMe() {
        super("Choose a Set to be Quizzed On");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("QuizMe on This Set!");
        btn.setActionCommand("mySetQuizMeButton");
        btn.addActionListener(this);
        setToQuiz = new JTextField(5);
        add(setToQuiz);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: starts a quiz if the user inputs a set with at least one flashcard
    @Override
    public void actionPerformed(ActionEvent e) {
        numCorrectAnswers = 0;
        if (e.getActionCommand().equals("mySetQuizMeButton")) {
            String name = setToQuiz.getText();
            for (Set set : userDeck.getSetList()) {
                if (set.getTitle().equals(name) && set.getFlashcardList().size() >= 1) {
                    for (Flashcard flashcard : set.getFlashcardList()) {
                        new QuizMe(flashcard.getQuestion(), flashcard.getAnswer(),
                                set.getFlashcardList().get(0).getQuestion(), set.getFlashcardList().size());
                    }
                }
            }
        }
    }
}
