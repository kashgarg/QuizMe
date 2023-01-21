package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.userDeck;

// Represents the pop-up menu for removing sets
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SetRemover extends JFrame implements ActionListener {
    private JTextField setTitleToRemove;

    // MODIFIES: GUI
    // EFFECTS: constructs the pop-up for removing sets
    public SetRemover() {
        super("Remove Sets");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Remove Set");
        btn.setActionCommand("myRemoveButton");
        btn.addActionListener(this);
        setTitleToRemove = new JTextField(5);
        add(setTitleToRemove);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // REQUIRES: More than one set in the userDeck
    // MODIFIES: userDeck
    // EFFECTS: Removes a set of the given title from the userDeck after the "Remove Set" button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myRemoveButton")) {
            if (userDeck.getSetList().size() > 1) {
                String name = setTitleToRemove.getText();
                if (userDeck.removeSetBoolean(name)) {
                    //userDeck.removeSet(name);
                    new SuccessfulSetRemoval(name);
                }
            }
        }
    }
}
