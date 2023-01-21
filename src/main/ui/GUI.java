package ui;

import model.Deck;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the graphical user interface for Flashcard Application
public class GUI extends JFrame implements ListSelectionListener {
    private static final String JSON_STORE = "./data/deck.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    protected static Deck userDeck;
    protected static int numCorrectAnswers;
    Color lemonColor = new Color(244, 224, 86);
    Color aquaMarineColor = new Color(20, 170, 118);
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
        EventLog.getInstance().clear();
    }

    private void initializeButton(JButton button, String text, int x, int y,
                                  Font font, Color foreground, Color background) {
        button.setText(text);
        button.setBounds(x, y, 150, 75);
        button.setFont(font);
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setActionCommand(text);
        button.addActionListener(new ButtonListener());
    }

    // MODIFIES: this
    // EFFECTS: initializes JButtons used in the main menu
    @SuppressWarnings("methodlength")
    public void initializeButtons() {
        initializeButton(quizMeButton, "QuizMe", 175, 95,
                new Font("Helvetica", Font.BOLD, 25), lemonColor, aquaMarineColor);
        initializeButton(addSetsButton, "Add Sets", 15, 15,
                new Font("Helvetica", Font.BOLD, 17), lemonColor, aquaMarineColor);
        initializeButton(removeSetsButton, "Remove Sets", 15, 95,
                new Font("Helvetica", Font.BOLD, 17), lemonColor, aquaMarineColor);
        initializeButton(saveButton, "Save", 175, 15,
                new Font("Helvetica", Font.BOLD, 17), lemonColor, aquaMarineColor);
        initializeButton(loadButton, "Load", 175, 175,
                new Font("Helvetica", Font.BOLD, 17), lemonColor, aquaMarineColor);
        initializeButton(addFlashcardsButton, "Add Flashcards", 335, 15,
                new Font("Helvetica", Font.BOLD, 17), lemonColor, aquaMarineColor);
        initializeButton(removeFlashcardsButton, "Remove Flashcards", 335, 95,
                new Font("Helvetica", Font.BOLD, 14), lemonColor, aquaMarineColor);
        initializeButton(viewSetsButton, "View Sets", 15, 175,
                new Font("Helvetica", Font.BOLD, 17), lemonColor, aquaMarineColor);
        initializeButton(viewFlashcardsButton, "View Flashcards", 335, 175,
                new Font("Helvetica", Font.BOLD, 17), lemonColor, aquaMarineColor);
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
    @SuppressWarnings("methodlength")
    public void initializeFrame() {
        frame.setTitle("QuizMe");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString() + "\n");
                }
                System.exit(0);
            }
        });
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
}