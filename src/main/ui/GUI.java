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
import java.util.Iterator;

// Represents the graphical user interface for Flashcard Application
public class GUI extends JFrame implements ListSelectionListener {
    private static final String JSON_STORE = "./data/deck.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Deck userDeck;
    private int numCorrectAnswers;
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
            new SetQuizMe();
        }

        // MODIFIES: GUI, userDeck
        // EFFECTS: adds a set to the userDeck
        public void addSetsAction() {
            new SetAdder();
        }

        // MODIFIES: GUI, userDeck
        // EFFECTS: adds a flashcard to a set in the userDeck
        public void addFlashcardsAction() {
            new SetFlashcardAdder();
        }

        // REQUIRES: More than one set in the userDeck (there must always be at least one set)
        // MODIFIES: GUI, userDeck
        // EFFECTS: removes a set from the userDeck
        public void removeSetsAction() {
            new SetRemover();
        }

        // MODIFIES: GUI, userDeck
        // EFFECTS: removes a flashcard from a set in the userDeck
        public void removeFlashcardsAction() {
            new SetFlashcardRemover();
        }

        // MODIFIES: GUI
        // EFFECTS: displays the title and number of flashcards of each
        //          created flashcard set
        public void viewSetsAction() {
            new SetViewer();
        }

        // MODIFIES: GUI
        // EFFECTS: displays the questions and answer of each flashcard in each
        //          created flashcard set
        public void viewFlashcardsAction() {
            new SetFlashcardViewer();
        }

        // MODIFIES: deck.json
        // EFFECTS: saves the userDeck to file
        public void saveAction() {
            try {
                jsonWriter.open();
                jsonWriter.write(userDeck);
                jsonWriter.close();
                new SuccessfulSave();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }

        // EFFECTS: loads the userDeck from file
        public void loadAction() {
            try {
                userDeck = jsonReader.read();
                new SuccessfulLoad();
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // Represents a pop-up menu displaying a message that the data has been saved
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SuccessfulSave extends JFrame {
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

    // Represents a pop-up menu displaying a message that the data has been loaded
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SuccessfulLoad extends JFrame {
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

    // Represents the pop-up menu for adding sets
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetAdder extends JFrame implements ActionListener {
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

    // Represents a pop-up menu displaying a message that the given set has been added
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SuccessfulSetAdd extends JFrame {
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

    // Represents the pop-up menu for removing sets
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetRemover extends JFrame implements ActionListener {
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
                        userDeck.removeSet(name);
                        new SuccessfulSetRemoval(name);
                    }
                }
            }
        }
    }

    // Represents a pop-up menu displaying a message that the given set has been removed
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SuccessfulSetRemoval extends JFrame {
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

    // Represents the pop-up for choosing a set to add a flashcard to
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetFlashcardAdder extends JFrame implements ActionListener {
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

    // Represents the pop-up menu for adding a flashcard
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class FlashcardAdder extends JFrame implements ActionListener {
        private JTextField questionField;
        private JTextField answerField;
        private JLabel questionLabel;
        private JLabel answerLabel;
        private String setTitle;


        // MODIFIES: GUI
        // EFFECTS: constructs a pop-up menu for adding a flashcard
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

        // MODIFIES: userDeck
        // EFFECTS: Adds a flashcard with the given question and answer to the chosen set after the
        //          "Add Flashcard" button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myAddFlashcardButton")) {
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(setTitle)) {
                        String question = questionField.getText();
                        String answer = answerField.getText();
                        set.addFlashcard(question, answer);
                        new SuccessfulFlashcardAdd(setTitle);
                    }
                }
            }
        }
    }

    // Represents a pop-up menu displaying a message that a flashcard has been added to the given set
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SuccessfulFlashcardAdd extends JFrame {
        private JLabel flashcardAddMessage;

        // MODIFIES: GUI
        // EFFECTS: Constructs a pop-up menu displaying a message that a flashcard has been added to the given set
        public SuccessfulFlashcardAdd(String setTitle) {
            super("Add Flashcards");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            flashcardAddMessage = new JLabel("Your new flashcard has been added to the set '" + setTitle + "'!");
            add(flashcardAddMessage);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }
    }

    // Represents the pop-up menu for choosing a set to remove a flashcard from
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetFlashcardRemover extends JFrame implements ActionListener {
        private JTextField setToRemoveFlashcard;

        // MODIFIES: GUI
        // EFFECTS: constructs the pop-up menu for choosing a set to remove a flashcard from
        public SetFlashcardRemover() {
            super("Choose a Set to Remove a Flashcard from");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            JButton btn = new JButton("Remove a Flashcard From This Set");
            btn.setActionCommand("mySetFlashcardRemoverButton");
            btn.addActionListener(this);
            setToRemoveFlashcard = new JTextField(5);
            add(setToRemoveFlashcard);
            add(btn);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }

        // EFFECTS: opens the pop-up menu for removing a flashcard after the
        //          "Remove a Flashcard From This Set" button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("mySetFlashcardRemoverButton")) {
                String name = setToRemoveFlashcard.getText();
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(name)) {
                        new FlashcardRemover(set.getTitle());
                    }
                }
            }
        }
    }

    // Represents the pop-up menu for removing a flashcard
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class FlashcardRemover extends JFrame implements ActionListener {
        private JTextField questionField;
        private JLabel questionLabel;
        private String setTitle;

        // MODIFIES: GUI
        // EFFECTS: constructs the pop-up menu for removing a flashcard
        public FlashcardRemover(String setTitle) {
            super("Remove Flashcards");
            this.setTitle = setTitle;
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 120));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            JButton btn = new JButton("Remove Flashcard");
            btn.setActionCommand("myRemoveFlashcardButton");
            btn.addActionListener(this);
            questionField = new JTextField(5);
            questionLabel = new JLabel("Enter the Question of the Flashcard you Wish to Remove:");
            add(questionLabel);
            add(questionField);
            add(btn);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }

        // MODIFIES: userDeck
        // EFFECTS: Removes a flashcard of the given question from the chosen set after the
        //          "Remove Flashcard" button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myRemoveFlashcardButton")) {
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(setTitle)) {
                        String question = questionField.getText();
                        for (Iterator<Flashcard> iterator = set.getFlashcardList().iterator(); iterator.hasNext(); ) {
                            Flashcard flashcard = iterator.next();
                            if (flashcard.getQuestion().equals(question)) {
                                iterator.remove();
                                new SuccessfulFlashcardRemoval(setTitle);

                            }
                        }
                    }
                }
            }
        }
    }

    // Represents a pop-up menu displaying a message that a flashcard has been removed from the given set
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SuccessfulFlashcardRemoval extends JFrame {
        private JLabel flashcardRemovalMessage;

        // MODIFIES: GUI
        // EFFECTS: Constructs a pop-up menu displaying a message that a flashcard has been removed from the given set
        public SuccessfulFlashcardRemoval(String setTitle) {
            super("Remove Flashcards");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            flashcardRemovalMessage = new JLabel("The flashcard has been removed from the set '"
                    + setTitle + "'!");
            add(flashcardRemovalMessage);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }
    }

    // Represents the pop-up menu that displays the title and number of flashcards of each created flashcard set
    // DISCLAIMER: class structure based on JListExample:
    // https://www.codejava.net/java-se/swing/jlist-basic-tutorial-and-examples
    class SetViewer extends JFrame {
        private JList<String> setJList;

        // MODIFIES: GUI
        // EFFECTS: constructs a JList displaying the title and number of flashcards of each created flashcard set
        public SetViewer() {
            DefaultListModel<String> setListModel = new DefaultListModel<>();
            for (Set set : userDeck.getSetList()) {
                setListModel.addElement(set.getTitle() + " - Number of Flashcards: " + set.getFlashcardList().size());
            }

            setJList = new JList<>(setListModel);
            add(new JScrollPane(setJList));
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setTitle("Set Viewer");
            this.setSize(400,400);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }

    // Represents the pop-up menu for choosing a set to view its flashcards
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetFlashcardViewer extends JFrame implements ActionListener {
        private JTextField setToView;

        // MODIFIES: GUI
        // EFFECTS: constructs the pop-up menu for choosing a set to view its flashcards
        public SetFlashcardViewer() {
            super("Choose a Set to View Flashcards");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            JButton btn = new JButton("View Flashcards Of This Set");
            btn.setActionCommand("mySetFlashcardViewerButton");
            btn.addActionListener(this);
            setToView = new JTextField(5);
            add(setToView);
            add(btn);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }

        // EFFECTS: Displays a list of flashcards of the given set
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("mySetFlashcardViewerButton")) {
                String name = setToView.getText();
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(name)) {
                        new FlashcardViewer(set.getTitle());
                    }
                }
            }
        }
    }

    // Represents the pop-up menu that displays the question and answer of each flashcard in a given set
    // DISCLAIMER: class structure based on JListExample:
    // https://www.codejava.net/java-se/swing/jlist-basic-tutorial-and-examples
    class FlashcardViewer extends JFrame {
        private JList<String> flashcardJList;
        private String setTitle;

        // MODIFIES: GUI
        // EFFECTS: constructs a JList displaying the question and answer of each flashcard in a given set
        public FlashcardViewer(String setTitle) {
            this.setTitle = setTitle;
            DefaultListModel<String> flashcardListModel = new DefaultListModel<>();
            for (Set set : userDeck.getSetList()) {
                if (set.getTitle().equals(setTitle)) {
                    for (Flashcard flashcard : set.getFlashcardList()) {
                        flashcardListModel.addElement("Question: " + flashcard.getQuestion() + " -- Answer: "
                                + flashcard.getAnswer());
                    }
                }
            }
            flashcardJList = new JList<>(flashcardListModel);
            add(new JScrollPane(flashcardJList));
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setTitle("Flashcard Viewer");
            this.setSize(400,400);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }

    // Represents the pop-up menu for choosing a set to be quizzed on
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class SetQuizMe extends JFrame implements ActionListener {
        private JTextField setToQuiz;

        // MODIFIES: GUI
        // EFFECTS: constructs a pop-up menu for choosing a set to be quizzed on
        public SetQuizMe() {
            super("Choose a Set to be Quizzed On");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            JButton btn = new JButton("QuizMe on This Set!");
            btn.setActionCommand("mySetQuizMeButton");
            btn.addActionListener(this);
            setToQuiz = new JTextField(5);
            add(setToQuiz);
            add(btn);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }

        // EFFECTS: starts a quiz if the user inputs a set with at least one flashcard
        @Override
        public void actionPerformed(ActionEvent e) {
            numCorrectAnswers = 0;
            if (e.getActionCommand().equals("mySetQuizMeButton")) {
                String name = setToQuiz.getText();
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(name) && set.getFlashcardList().size() >= 1) {
                        for (Flashcard flashcard : set.getFlashcardList()) {
                            new QuizMe(flashcard.getQuestion(), flashcard.getAnswer(),
                                    set.getFlashcardList().get(0).getQuestion(), set.getFlashcardList().size());
                        }
                    }
                }
            }
        }
    }

    // Represents the pop-up menu for each flashcard in a set during a quiz
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class QuizMe extends JFrame implements ActionListener {
        private JTextField userAnswer;
        private JLabel questionLabel;
        private String question;
        private String answer;
        private String firstFlashcardQuestion;
        private int flashcardListSize;

        // MODIFIES: GUI
        // EFFECTS: constructs the pop-up menu for each flashcard in a set during a quiz
        public QuizMe(String question, String answer, String firstFlashcardQuestion, int flashcardListSize) {
            super("QuizMe");
            this.question = question;
            this.answer = answer;
            this.firstFlashcardQuestion = firstFlashcardQuestion;
            this.flashcardListSize = flashcardListSize;
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 140));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            JButton btn = new JButton("Submit Answer");
            btn.setActionCommand("myQuizMeButton");
            btn.addActionListener(this);
            userAnswer = new JTextField(5);
            questionLabel = new JLabel(question);
            add(questionLabel);
            add(userAnswer);
            add(btn);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }

        // MODIFIES: GUI, numCorrectAnswers
        // EFFECTS: increases numCorrectAnswers by 1 if the user inputs the correct answer,
        //          disposes the current pop-up menu regardless of answer to prepare for next question,
        //          if current question is the last question, then displays the final results of the quiz
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("myQuizMeButton")) {
                String userAnswerString = userAnswer.getText();
                if (userAnswerString.equals(answer)) {
                    numCorrectAnswers++;
                    if (question.equals(firstFlashcardQuestion)) {
                        this.dispose();
                        new Results(flashcardListSize);
                    }
                    this.dispose();
                } else if (question.equals(firstFlashcardQuestion)) {
                    this.dispose();
                    new Results(flashcardListSize);
                } else {
                    this.dispose();
                }
            }
        }
    }

    // Represents a pop-up menu displaying the final score of the quiz
    // DISCLAIMER: class structure based on Test:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    class Results extends JFrame {
        private JLabel results;

        // MODIFIES: GUI
        // EFFECTS: constructs a pop-up menu displaying the final score of the quiz
        public Results(int flashcardListSize) {
            super("Results");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setPreferredSize(new Dimension(400, 90));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
            setLayout(new FlowLayout());
            results = new JLabel("You got " + numCorrectAnswers + " out of "
                    + flashcardListSize + " questions correct!");
            add(results);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }
    }
}


