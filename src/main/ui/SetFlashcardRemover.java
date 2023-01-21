package ui;

import model.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.userDeck;

// Represents the pop-up menu for choosing a set to remove a flashcard from
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SetFlashcardRemover extends JFrame implements ActionListener {
    private JTextField setToRemoveFlashcard;

    // MODIFIES: GUI
    // EFFECTS: constructs the pop-up menu for choosing a set to remove a flashcard from
    public SetFlashcardRemover() {
        super("Choose a Set to Remove a Flashcard from");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Remove a Flashcard From This Set");
        btn.setActionCommand("mySetFlashcardRemoverButton");
        btn.addActionListener(this);
        setToRemoveFlashcard = new JTextField(5);
        add(setToRemoveFlashcard);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: opens the pop-up menu for removing a flashcard after the
    //          "Remove a Flashcard From This Set" button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("mySetFlashcardRemoverButton")) {
            String name = setToRemoveFlashcard.getText();
            for (Set set : userDeck.getSetList()) {
                if (set.getTitle().equals(name)) {
                    new FlashcardRemover(set.getTitle());
                }
            }
        }
    }
}
