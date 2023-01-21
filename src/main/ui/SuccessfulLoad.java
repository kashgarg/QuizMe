package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a pop-up menu displaying a message that the data has been loaded
// DISCLAIMER: class structure based on Test:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class SuccessfulLoad extends JFrame {
    private JLabel loadMessage;

    // MODIFIES: GUI
    // EFFECTS: Constructs a pop-up menu displaying a message that the data has been loaded
    public SuccessfulLoad() {
        super("Load Data");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        loadMessage = new JLabel("The data has been loaded!");
        add(loadMessage);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

}
