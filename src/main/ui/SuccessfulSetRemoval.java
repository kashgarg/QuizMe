package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a pop-up menu displaying a message that the given set has been removed
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SuccessfulSetRemoval extends JFrame {
    private JLabel setRemovalMessage;

    // MODIFIES: GUI
    // EFFECTS: Constructs a pop-up menu displaying a message that the given set has been added
    public SuccessfulSetRemoval(String name) {
        super("Remove Sets");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        setRemovalMessage = new JLabel("The set '" + name + "' has been removed!");
        add(setRemovalMessage);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
