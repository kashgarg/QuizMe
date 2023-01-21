package ui;

import model.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.userDeck;

// Represents the pop-up for choosing a set to add a flashcard to
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SetFlashcardAdder extends JFrame implements ActionListener {
    private JTextField setTitleToChoose;

    // MODIFIES: GUI
    // EFFECTS: constructs the pop-up menu for choosing a set to add a flashcard to
    public SetFlashcardAdder() {
        super("Choose a Set to Add a Flashcard to");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Add a Flashcard to This Set");
        btn.setActionCommand("mySetFlashcardAdderButton");
        btn.addActionListener(this);
        setTitleToChoose = new JTextField(5);
        add(setTitleToChoose);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    // EFFECTS: opens the pop-up for adding a flashcard to the given set after the
    //          "Add a Flashcard to This Set" button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("mySetFlashcardAdderButton")) {
            String name = setTitleToChoose.getText();
            for (Set set : userDeck.getSetList()) {
                if (set.getTitle().equals(name)) {
                    new FlashcardAdder(set.getTitle());
                }
            }
        }
    }
}
