package ui;

import model.Deck;
import model.Set;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the graphical user interface for Flashcard Application
public class GUI extends JFrame implements ListSelectionListener {
    private static final String JSON_STORE = "./data/deck.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Deck userDeck;
    Color lemonColor = new Color(202, 224, 86);
    Color aquaMarineColor = new Color(20, 180, 118);
    JPanel toolPanel = new JPanel();
    JPanel logoPanel = new JPanel();
    JButton quizMeButton = new JButton("QuizMe");
    JButton addSetsButton = new JButton("Add Sets");
    JButton removeSetsButton = new JButton("Remove Sets");
    JButton editFlashcardsButton = new JButton("Edit Flashcards");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");
    JLabel logo = new JLabel();
    JFrame frame = new JFrame();


    // EFFECTS: initializes main menu and data
    public GUI() {
        initializeButtons();
        initializeLogo();
        initializePanels();
        initializeFrame();
        initializeData();
    }

    // MODIFIES: this
    // EFFECTS: initializes JButtons used in the main menu
    @SuppressWarnings("methodlength")
    public void initializeButtons() {
        quizMeButton.setBounds(175, 10, 150, 75);
        quizMeButton.setFont(new Font("Impact", Font.BOLD, 10));
        quizMeButton.setOpaque(true);
        quizMeButton.setBorderPainted(true);
        quizMeButton.setForeground(lemonColor);
        quizMeButton.setBackground(aquaMarineColor);
        quizMeButton.setBorder(BorderFactory.createEtchedBorder());
        quizMeButton.setActionCommand("QuizMe");
        quizMeButton.addActionListener(new ButtonListener());

        addSetsButton.setBounds(15, 10, 150, 75);
        addSetsButton.setFont(new Font("Impact", Font.BOLD, 10));
        addSetsButton.setOpaque(true);
        addSetsButton.setBorderPainted(true);
        addSetsButton.setForeground(lemonColor);
        addSetsButton.setBackground(aquaMarineColor);
        addSetsButton.setBorder(BorderFactory.createEtchedBorder());
        addSetsButton.setActionCommand("Add Sets");
        addSetsButton.addActionListener(new ButtonListener());

        removeSetsButton.setBounds(15, 90, 150, 75);
        removeSetsButton.setFont(new Font("Impact", Font.BOLD, 10));
        removeSetsButton.setOpaque(true);
        removeSetsButton.setBorderPainted(true);
        removeSetsButton.setForeground(lemonColor);
        removeSetsButton.setBackground(aquaMarineColor);
        removeSetsButton.setBorder(BorderFactory.createEtchedBorder());
        removeSetsButton.setActionCommand("Remove Sets");
        removeSetsButton.addActionListener(new ButtonListener());

        saveButton.setBounds(175, 90, 150, 75);
        saveButton.setFont(new Font("Impact", Font.BOLD, 10));
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(true);
        saveButton.setForeground(lemonColor);
        saveButton.setBackground(aquaMarineColor);
        saveButton.setBorder(BorderFactory.createEtchedBorder());
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(new ButtonListener());

        loadButton.setBounds(335, 90, 150, 75);
        loadButton.setFont(new Font("Impact", Font.BOLD, 10));
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(true);
        loadButton.setForeground(lemonColor);
        loadButton.setBackground(aquaMarineColor);
        loadButton.setBorder(BorderFactory.createEtchedBorder());
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(new ButtonListener());

        editFlashcardsButton.setBounds(335, 10, 150, 75);
        editFlashcardsButton.setFont(new Font("Impact", Font.BOLD, 10));
        editFlashcardsButton.setOpaque(true);
        editFlashcardsButton.setBorderPainted(true);
        editFlashcardsButton.setForeground(lemonColor);
        editFlashcardsButton.setBackground(aquaMarineColor);
        editFlashcardsButton.setBorder(BorderFactory.createEtchedBorder());
        editFlashcardsButton.setActionCommand("Edit Flashcards");
        editFlashcardsButton.addActionListener(new ButtonListener());
    }

    // MODIFIES: this
    // EFFECTS: initializes logo image in the main menu
    public void initializeLogo() {
        ImageIcon logoPhoto = new ImageIcon("QuizMe.png");
        Image resizedLogo = logoPhoto.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon logoPhotoResized = new ImageIcon(resizedLogo);
        logo.setIcon(logoPhotoResized);
    }

    // MODIFIES: this
    // EFFECTS: initializes JPanels used in the main menu
    public void initializePanels() {
        toolPanel.setBackground(lemonColor);
        toolPanel.setBounds(0, 500, 500, 200);
        logoPanel.setBounds(0, 0, 500, 500);
    }

    // MODIFIES: this
    // EFFECTS: initializes JFrame used in the main menu
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
        toolPanel.add(addSetsButton);
        toolPanel.add(editFlashcardsButton);
        toolPanel.add(removeSetsButton);
        toolPanel.add(saveButton);
        toolPanel.add(loadButton);
        frame.add(logoPanel);
        frame.add(toolPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes data
    public void initializeData() {
        userDeck = new Deck();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // Required by ListSelectionListener
    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    // Represents the listener of the buttons in the main menu of the GUI
    class ButtonListener implements ActionListener {

        // EFFECTS: performs appropriate actions after buttons are clicked
        // DISCLAIMER: method structure based on TellerApp:
        // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("QuizMe")) {
                quizMeAction();
            } else if (e.getActionCommand().equals("Add Sets")) {
                addSetsAction();
            } else if (e.getActionCommand().equals("Edit Flashcards")) {
                editFlashcardsAction();
            } else if (e.getActionCommand().equals("Remove Sets")) {
                removeSetsAction();
            } else if (e.getActionCommand().equals("Save")) {
                saveAction();
            } else if (e.getActionCommand().equals("Load")) {
                loadAction();
            } else {
                System.out.println("Sorry! There is no such selection!");
            }
        }

        // MODIFIES: this
        // EFFECTS: prompts the user to choose a set to be quizzed on
        public void quizMeAction() {
            //System.out.println("QuizMe button has yet to be implemented!"); // stub
            for (Set set : userDeck.getSetList()) {
                System.out.println(set.getTitle());
            }
        }

        // MODIFIES: this, userDeck
        // EFFECTS: adds a set to the userDeck
        public void addSetsAction() {
            new SetAdder();
        }


        public void editFlashcardsAction() {
            System.out.println("Edit Flashcards button has yet to be implemented!"); // stub
        }

        // REQUIRES: More than one set in the userDeck (there must always be at least one set)
        // MODIFIES: this, userDeck
        // EFFECTS: removes a set from the userDeck
        public void removeSetsAction() {
            new SetRemover();
        }

        // EFFECTS: saves the userDeck to file
        public void saveAction() {
            try {
                jsonWriter.open();
                jsonWriter.write(userDeck);
                jsonWriter.close();
                System.out.println("Saved the deck to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }

        // MODIFIES: this
        // EFFECTS: loads the userDeck from file
        public void loadAction() {
            try {
                userDeck = jsonReader.read();
                System.out.println("Loaded deck from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetAdder extends JFrame implements ActionListener {
        private JTextField setTitleToAdd;

        public SetAdder() {
            super("Add Sets");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
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

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myAddButton")) {
                String name = setTitleToAdd.getText();
                userDeck.addSet(name);
            }
        }
    }

    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetRemover extends JFrame implements ActionListener {
        private JTextField setTitleToRemove;

        public SetRemover() {
            super("Remove Sets");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
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

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myRemoveButton")) {
                if (userDeck.getSetList().size() > 1) {
                    String name = setTitleToRemove.getText();
                    userDeck.removeSetName(name);
                }
            }
        }
    }
}


