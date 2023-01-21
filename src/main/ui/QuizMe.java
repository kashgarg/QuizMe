package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.numCorrectAnswers;

// Represents the pop-up menu for each flashcard in a set during a quiz
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class QuizMe extends JFrame implements ActionListener {
    private JTextField userAnswer;
    private JLabel questionLabel;
    private String question;
    private String answer;
    private String firstFlashcardQuestion;
    private int flashcardListSize;

    // MODIFIES: GUI
    // EFFECTS: constructs the pop-up menu for each flashcard in a set during a quiz
    public QuizMe(String question, String answer, String firstFlashcardQuestion, int flashcardListSize) {
        super("QuizMe");
        this.question = question;
        this.answer = answer;
        this.firstFlashcardQuestion = firstFlashcardQuestion;
        this.flashcardListSize = flashcardListSize;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 140));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Submit Answer");
        btn.setActionCommand("myQuizMeButton");
        btn.addActionListener(this);
        userAnswer = new JTextField(5);
        questionLabel = new JLabel(question);
        add(questionLabel);
        add(userAnswer);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: GUI, numCorrectAnswers
    // EFFECTS: increases numCorrectAnswers by 1 if the user inputs the correct answer,
    //          disposes the current pop-up menu regardless of answer to prepare for next question,
    //          if current question is the last question, then displays the final results of the quiz
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myQuizMeButton")) {
            String userAnswerString = userAnswer.getText();
            if (userAnswerString.equals(answer)) {
                numCorrectAnswers++;
                if (question.equals(firstFlashcardQuestion)) {
                    this.dispose();
                    new Results(flashcardListSize);
                }
                this.dispose();
            } else if (question.equals(firstFlashcardQuestion)) {
                this.dispose();
                new Results(flashcardListSize);
            } else {
                this.dispose();
            }
        }
    }
}
