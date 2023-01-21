package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a pop-up menu displaying a message that the data has been saved
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SuccessfulSave extends JFrame {
    private JLabel saveMessage;

    // MODIFIES: GUI
    // EFFECTS: Constructs a pop-up menu displaying a message that the data has been saved
    public SuccessfulSave() {
        super("Save Data");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        saveMessage = new JLabel("The data has been saved!");
        add(saveMessage);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
