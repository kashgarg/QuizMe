package ui;

import model.Set;

import javax.swing.*;

import static ui.GUI.userDeck;

// Represents the pop-up menu that displays the title and number of flashcards of each created flashcard set
// DISCLAIMER: class structure based on JListExample:
// https://www.codejava.net/java-se/swing/jlist-basic-tutorial-and-examples
public class SetViewer extends JFrame {
    private JList<String> setJList;

    // MODIFIES: GUI
    // EFFECTS: constructs a JList displaying the title and number of flashcards of each created flashcard set
    public SetViewer() {
        DefaultListModel<String> setListModel = new DefaultListModel<>();
        for (Set set : userDeck.getSetList()) {
            setListModel.addElement(set.getTitle() + " - Number of Flashcards: " + set.getFlashcardList().size());
        }

        setJList = new JList<>(setListModel);
        add(new JScrollPane(setJList));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Set Viewer");
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
