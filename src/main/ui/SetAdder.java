package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.userDeck;

// Represents the pop-up menu for adding sets
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SetAdder extends JFrame implements ActionListener {
    private JTextField setTitleToAdd;

    // MODIFIES: GUI
    // EFFECTS: constructs a pop-up menu for adding sets
    public SetAdder() {
        super("Add Sets");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Add Set");
        btn.setActionCommand("myAddButton");
        btn.addActionListener(this);
        setTitleToAdd = new JTextField(5);
        add(setTitleToAdd);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: userDeck
    // EFFECTS: Adds a set of the given title to the userDeck after the "Add Set" button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myAddButton")) {
            String name = setTitleToAdd.getText();
            userDeck.addSet(name);
            new SuccessfulSetAdd(name);
        }
    }
}
