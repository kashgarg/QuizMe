package ui;

import model.Flashcard;
import model.Set;

import javax.swing.*;

import static ui.GUI.userDeck;

// Represents the pop-up menu that displays the question and answer of each flashcard in a given set
// DISCLAIMER: class structure based on JListExample:
// https://www.codejava.net/java-se/swing/jlist-basic-tutorial-and-examples
public class FlashcardViewer extends JFrame {
    private JList<String> flashcardJList;
    private String setTitle;

    // MODIFIES: GUI
    // EFFECTS: constructs a JList displaying the question and answer of each flashcard in a given set
    public FlashcardViewer(String setTitle) {
        this.setTitle = setTitle;
        DefaultListModel<String> flashcardListModel = new DefaultListModel<>();
        for (Set set : userDeck.getSetList()) {
            if (set.getTitle().equals(setTitle)) {
                for (Flashcard flashcard : set.getFlashcardList()) {
                    flashcardListModel.addElement("Question: " + flashcard.getQuestion() + " -- Answer: "
                            + flashcard.getAnswer());
                }
            }
        }
        flashcardJList = new JList<>(flashcardListModel);
        add(new JScrollPane(flashcardJList));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Flashcard Viewer");
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
