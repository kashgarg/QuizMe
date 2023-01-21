package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a pop-up menu displaying a message that the given set has been added
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SuccessfulSetAdd extends JFrame {
    private JLabel setAddMessage;

    // MODIFIES: GUI
    // EFFECTS: Constructs a pop-up menu displaying a message that the given set has been added
    public SuccessfulSetAdd(String name) {
        super("Add Sets");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        setAddMessage = new JLabel("The set '" + name + "' has been added!");
        add(setAddMessage);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
