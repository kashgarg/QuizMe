package ui;

import model.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.userDeck;

// Represents the pop-up menu for choosing a set to view its flashcards
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SetFlashcardViewer extends JFrame implements ActionListener {
    private JTextField setToView;

    // MODIFIES: GUI
    // EFFECTS: constructs the pop-up menu for choosing a set to view its flashcards
    public SetFlashcardViewer() {
        super("Choose a Set to View Flashcards");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("View Flashcards Of This Set");
        btn.setActionCommand("mySetFlashcardViewerButton");
        btn.addActionListener(this);
        setToView = new JTextField(5);
        add(setToView);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: Displays a list of flashcards of the given set
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("mySetFlashcardViewerButton")) {
            String name = setToView.getText();
            for (Set set : userDeck.getSetList()) {
                if (set.getTitle().equals(name)) {
                    new FlashcardViewer(set.getTitle());
                }
            }
        }
    }
}
