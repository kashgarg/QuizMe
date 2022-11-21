package ui;

import model.Set;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ListSelectionListener {
    private static final String JSON_STORE = "./data/deck.json";
    Color lemonColor = new Color(202, 224, 86);
    Color aquaMarineColor = new Color(20, 180, 118);
    JPanel toolPanel = new JPanel();
    JPanel logoPanel = new JPanel();
    JButton quizMeButton = new JButton("QuizMe");
    JButton editSetsButton = new JButton("Edit Sets");
    JButton editFlashcardsButton = new JButton("Edit Flashcards");
    JLabel logo = new JLabel();
    JFrame frame = new JFrame();


    // EFFECTS: initializes main menu
    public GUI() {
        initializeButtons();
        initializeLogo();
        initializePanels();
        initializeFrame();
    }

    // MODIFIES: this
    // EFFECTS: initializes JButtons
    @SuppressWarnings("methodlength")
    public void initializeButtons() {
        quizMeButton.setBounds(175, 10, 150, 150);
        quizMeButton.setFont(new Font("Impact", Font.BOLD, 36));
        quizMeButton.setOpaque(true);
        quizMeButton.setBorderPainted(true);
        quizMeButton.setForeground(lemonColor);
        quizMeButton.setBackground(aquaMarineColor);
        quizMeButton.setBorder(BorderFactory.createEtchedBorder());
        quizMeButton.setActionCommand("QuizMe");
        quizMeButton.addActionListener(new ButtonListener());

        editSetsButton.setBounds(15, 10, 150, 150);
        editSetsButton.setFont(new Font("Impact", Font.BOLD, 15));
        editSetsButton.setOpaque(true);
        editSetsButton.setBorderPainted(true);
        editSetsButton.setForeground(lemonColor);
        editSetsButton.setBackground(aquaMarineColor);
        editSetsButton.setBorder(BorderFactory.createEtchedBorder());
        editSetsButton.setActionCommand("Edit Sets");
        editSetsButton.addActionListener(new ButtonListener());

        editFlashcardsButton.setBounds(335, 10, 150, 150);
        editFlashcardsButton.setFont(new Font("Impact", Font.BOLD, 15));
        editFlashcardsButton.setOpaque(true);
        editFlashcardsButton.setBorderPainted(true);
        editFlashcardsButton.setForeground(lemonColor);
        editFlashcardsButton.setBackground(aquaMarineColor);
        editFlashcardsButton.setBorder(BorderFactory.createEtchedBorder());
        editFlashcardsButton.setActionCommand("Edit Flashcards");
        editFlashcardsButton.addActionListener(new ButtonListener());
    }

    // MODIFIES: this
    // EFFECTS: initializes logo image
    public void initializeLogo() {
        ImageIcon logoPhoto = new ImageIcon("QuizMe.png");
        Image resizedLogo = logoPhoto.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon logoPhotoResized = new ImageIcon(resizedLogo);
        logo.setIcon(logoPhotoResized);
    }

    // MODIFIES: this
    // EFFECTS: initializes JPanels
    public void initializePanels() {
        toolPanel.setBackground(lemonColor);
        toolPanel.setBounds(0, 500, 500, 200);
        logoPanel.setBounds(0, 0, 500, 500);
    }

    // MODIFIES: this
    // EFFECTS: initializes JFrame
    public void initializeFrame() {
        frame.setTitle("QuizMe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 700);
        toolPanel.setLayout(null);
        logoPanel.add(logo);
        toolPanel.add(quizMeButton);
        toolPanel.add(editSetsButton);
        toolPanel.add(editFlashcardsButton);
        frame.add(logoPanel);
        frame.add(toolPanel);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }


    class ButtonListener implements ActionListener {

        public ButtonListener() {}

        // EFFECTS: performs appropriate actions after buttons are clicked
        // DISCLAIMER: method structure based on TellerApp:
        // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("QuizMe")) {
                quizMeAction();
            } else if (e.getActionCommand().equals("Edit Sets")) {
                editSetsAction();
            } else if (e.getActionCommand().equals("Edit Flashcards")) {
                editFlashcardsAction();
            } else {
                System.out.println("Sorry! There is no such selection!");
            }
        }

        public void quizMeAction() {
            System.out.println("QuizMe button has yet to be implemented!");
        }

        public void editSetsAction() {
            //System.out.println("Edit Sets button has yet to be implemented!");
            JFrame setEditor = new JFrame("Your Sets");
            setEditor.setVisible(true);
            setEditor.setSize(300, 300);
            setEditor.setResizable(false);
            frame.setEnabled(false);
        }

        public void editFlashcardsAction() {
            System.out.println("Edit Flashcards button has yet to be implemented!");
        }

    }

}


