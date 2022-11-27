package ui;

import model.Deck;
import model.Flashcard;
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
    JButton quizMeButton = new JButton("QuizMe!");
    JButton addSetsButton = new JButton("Add Sets");
    JButton removeSetsButton = new JButton("Remove Sets");
    JButton addFlashcardsButton = new JButton("Add Flashcards");
    JButton removeFlashcardsButton = new JButton("Remove Flashcards");
    JButton viewSetsButton = new JButton("View Sets");
    JButton viewFlashcardsButton = new JButton("View Flashcards");
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
        quizMeButton.setBounds(175, 95, 150, 75);
        quizMeButton.setFont(new Font("Impact", Font.BOLD, 25));
        quizMeButton.setOpaque(true);
        quizMeButton.setBorderPainted(true);
        quizMeButton.setForeground(lemonColor);
        quizMeButton.setBackground(aquaMarineColor);
        quizMeButton.setBorder(BorderFactory.createEtchedBorder());
        quizMeButton.setActionCommand("QuizMe");
        quizMeButton.addActionListener(new ButtonListener());

        addSetsButton.setBounds(15, 15, 150, 75);
        addSetsButton.setFont(new Font("Impact", Font.BOLD, 17));
        addSetsButton.setOpaque(true);
        addSetsButton.setBorderPainted(true);
        addSetsButton.setForeground(lemonColor);
        addSetsButton.setBackground(aquaMarineColor);
        addSetsButton.setBorder(BorderFactory.createEtchedBorder());
        addSetsButton.setActionCommand("Add Sets");
        addSetsButton.addActionListener(new ButtonListener());

        removeSetsButton.setBounds(15, 95, 150, 75);
        removeSetsButton.setFont(new Font("Impact", Font.BOLD, 17));
        removeSetsButton.setOpaque(true);
        removeSetsButton.setBorderPainted(true);
        removeSetsButton.setForeground(lemonColor);
        removeSetsButton.setBackground(aquaMarineColor);
        removeSetsButton.setBorder(BorderFactory.createEtchedBorder());
        removeSetsButton.setActionCommand("Remove Sets");
        removeSetsButton.addActionListener(new ButtonListener());

        saveButton.setBounds(175, 15, 150, 75);
        saveButton.setFont(new Font("Impact", Font.BOLD, 17));
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(true);
        saveButton.setForeground(lemonColor);
        saveButton.setBackground(aquaMarineColor);
        saveButton.setBorder(BorderFactory.createEtchedBorder());
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(new ButtonListener());

        loadButton.setBounds(175, 175, 150, 75);
        loadButton.setFont(new Font("Impact", Font.BOLD, 17));
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(true);
        loadButton.setForeground(lemonColor);
        loadButton.setBackground(aquaMarineColor);
        loadButton.setBorder(BorderFactory.createEtchedBorder());
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(new ButtonListener());

        addFlashcardsButton.setBounds(335, 15, 150, 75);
        addFlashcardsButton.setFont(new Font("Impact", Font.BOLD, 17));
        addFlashcardsButton.setOpaque(true);
        addFlashcardsButton.setBorderPainted(true);
        addFlashcardsButton.setForeground(lemonColor);
        addFlashcardsButton.setBackground(aquaMarineColor);
        addFlashcardsButton.setBorder(BorderFactory.createEtchedBorder());
        addFlashcardsButton.setActionCommand("Add Flashcards");
        addFlashcardsButton.addActionListener(new ButtonListener());

        removeFlashcardsButton.setBounds(335, 95, 150, 75);
        removeFlashcardsButton.setFont(new Font("Impact", Font.BOLD, 17));
        removeFlashcardsButton.setOpaque(true);
        removeFlashcardsButton.setBorderPainted(true);
        removeFlashcardsButton.setForeground(lemonColor);
        removeFlashcardsButton.setBackground(aquaMarineColor);
        removeFlashcardsButton.setBorder(BorderFactory.createEtchedBorder());
        removeFlashcardsButton.setActionCommand("Remove Flashcards");
        removeFlashcardsButton.addActionListener(new ButtonListener());

        viewSetsButton.setBounds(15, 175, 150, 75);
        viewSetsButton.setFont(new Font("Impact", Font.BOLD, 17));
        viewSetsButton.setOpaque(true);
        viewSetsButton.setBorderPainted(true);
        viewSetsButton.setForeground(lemonColor);
        viewSetsButton.setBackground(aquaMarineColor);
        viewSetsButton.setBorder(BorderFactory.createEtchedBorder());
        viewSetsButton.setActionCommand("View Sets");
        viewSetsButton.addActionListener(new ButtonListener());

        viewFlashcardsButton.setBounds(335, 175, 150, 75);
        viewFlashcardsButton.setFont(new Font("Impact", Font.BOLD, 17));
        viewFlashcardsButton.setOpaque(true);
        viewFlashcardsButton.setBorderPainted(true);
        viewFlashcardsButton.setForeground(lemonColor);
        viewFlashcardsButton.setBackground(aquaMarineColor);
        viewFlashcardsButton.setBorder(BorderFactory.createEtchedBorder());
        viewFlashcardsButton.setActionCommand("View Flashcards");
        viewFlashcardsButton.addActionListener(new ButtonListener());
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
        toolPanel.setBounds(0, 500, 500, 300);
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
        frame.setSize(500, 800);
        toolPanel.setLayout(null);
        logoPanel.add(logo);
        toolPanel.add(quizMeButton);
        toolPanel.add(addSetsButton);
        toolPanel.add(addFlashcardsButton);
        toolPanel.add(removeSetsButton);
        toolPanel.add(saveButton);
        toolPanel.add(loadButton);
        toolPanel.add(removeFlashcardsButton);
        toolPanel.add(viewSetsButton);
        toolPanel.add(viewFlashcardsButton);
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
            } else if (e.getActionCommand().equals("Add Flashcards")) {
                addFlashcardsAction();
            } else if (e.getActionCommand().equals("Remove Sets")) {
                removeSetsAction();
            } else if (e.getActionCommand().equals("Save")) {
                saveAction();
            } else if (e.getActionCommand().equals("Load")) {
                loadAction();
            } else if (e.getActionCommand().equals("Remove Flashcards")) {
                removeFlashcardsAction();
            } else if (e.getActionCommand().equals("View Sets")) {
                viewSetsAction();
            } else if (e.getActionCommand().equals("View Flashcards")) {
                viewFlashcardsAction();
            } else {
                System.out.println("Sorry! There is no such selection!");
            }
        }

        // MODIFIES: this
        // EFFECTS: prompts the user to choose a set to be quizzed on
        public void quizMeAction() {
            //System.out.println("QuizMe button has yet to be implemented!"); // stub
            if (userDeck.getSetList().isEmpty()) {
                System.out.println("\nYou don't have any sets yet!");
            } else {
                for (Set set : userDeck.getSetList()) {
                    if (set.getFlashcardList().isEmpty()) {
                        System.out.println("\nThe set '" + set.getTitle() + "' has no flashcards!");
                    } else {
                        System.out.println("\nThe set '" + set.getTitle() + "' has the following flashcards:");
                        for (Flashcard flashcard : set.getFlashcardList()) {
                            System.out.print("Question: " + flashcard.getQuestion() + " --"
                                    + " Answer: " + flashcard.getAnswer() + "\n");
                        }
                    }
                }
            }
        }

        // MODIFIES: this, userDeck
        // EFFECTS: adds a set to the userDeck
        public void addSetsAction() {
            new SetAdder();
        }

        public void addFlashcardsAction() {
            new SetChooser();
        }

        // REQUIRES: More than one set in the userDeck (there must always be at least one set)
        // MODIFIES: this, userDeck
        // EFFECTS: removes a set from the userDeck
        public void removeSetsAction() {
            new SetRemover();
        }

        // MODIFIES: this, userDeck
        // EFFECTS: removes a flashcard from a set in the userDeck
        public void removeFlashcardsAction() {
            System.out.println("Remove Flashcards has yet to be implemented!"); // stub
        }

        // MODIFIES: this
        // EFFECTS: displays the title and number of flashcards of each
        //          created flashcard set
        public void viewSetsAction() {
            System.out.println("View Sets has yet to be implemented!"); // stub
        }

        // MODIFIES: this
        // EFFECTS: displays the questions and answer of each flashcard in each
        //          created flashcard set
        public void viewFlashcardsAction() {
            System.out.println("View Flashcards has yet to be implemented!"); // stub
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

    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetChooser extends JFrame implements ActionListener {
        private JTextField setTitleToChoose;

        public SetChooser() {
            super("Choose a Set to Add a Flashcard to");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            JButton btn = new JButton("Add a Flashcard to This Set");
            btn.setActionCommand("mySetChooserButton");
            btn.addActionListener(this);
            setTitleToChoose = new JTextField(5);
            add(setTitleToChoose);
            add(btn);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("mySetChooserButton")) {
                String name = setTitleToChoose.getText();
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(name)) {
                        new FlashcardAdder(set.getTitle());
                    }
                }
            }
        }
    }

    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class FlashcardAdder extends JFrame implements ActionListener {
        private JTextField questionField;
        private JTextField answerField;
        private JLabel questionLabel;
        private JLabel answerLabel;
        private String setTitle;


        public FlashcardAdder(String setTitle) {
            super("Add Flashcards");
            this.setTitle = setTitle;
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 120));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            JButton btn = new JButton("Add Flashcard");
            btn.setActionCommand("myAddFlashcardButton");
            btn.addActionListener(this);
            questionField = new JTextField(5);
            answerField = new JTextField(5);
            questionLabel = new JLabel("Question:");
            answerLabel = new JLabel("Answer:");
            add(questionLabel);
            add(questionField);
            add(answerLabel);
            add(answerField);
            add(btn);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myAddFlashcardButton")) {
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(setTitle)) {
                        String question = questionField.getText();
                        String answer = answerField.getText();
                        set.addFlashcard(question, answer);
                    }
                }
            }
        }
    }
}


