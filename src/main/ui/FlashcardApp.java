package ui;

import model.Deck;
import model.Flashcard;
import model.Set;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

// Flashcard application
public class FlashcardApp {
    private static final String JSON_STORE = "./data/deck.json";
    private Scanner input;
    private Deck userDeck;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // EFFECTS: runs the flashcard application
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public FlashcardApp() {
        input = new Scanner(System.in);
        userDeck = new Deck();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFlashcard();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // DISCLAIMER: method structure based on TellerApp:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void runFlashcard() {
        boolean keepRunning = true;
        String command;
        input = new Scanner(System.in);

        while (keepRunning) {
            showMenu();
            command = input.nextLine();

            if (command.equals("s")) {
                keepRunning = false;
            } else {
                runCommand(command);
            }
        }
        System.out.println("\n Have a good study break!");
    }

    // EFFECTS: shows the main menu to the user
    // DISCLAIMER: method structure based on TellerApp:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void showMenu() {
        System.out.println("\nWelcome to QuizMe! What would you like to do?");
        System.out.println("\tPress q to start QuizMe!");
        System.out.println("\tPress c to create a new set");
        System.out.println("\tPress r to remove a set");
        System.out.println("\tPress a to add a flashcard to a set");
        System.out.println("\tPress rf to remove a flashcard from a set");
        System.out.println("\tPress v to view sets");
        System.out.println("\tPress vf to view flashcards");
        System.out.println("\tPress sd to save deck to file");
        System.out.println("\tPress l to load deck from file");
        System.out.println("\tPress s to take a study break");
    }

    // MODIFIES: this
    // EFFECTS: runs the user command
    // DISCLAIMER: method structure based on TellerApp:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void runCommand(String command) {
        if (command.equals("q")) {
            doQuizMe();
        } else if (command.equals("c")) {
            doCreateSet();
        } else if (command.equals("r")) {
            doRemoveSet();
        } else if (command.equals("a")) {
            doAddFlashcard();
        } else if (command.equals("rf")) {
            doRemoveFlashcard();
        } else if (command.equals("v")) {
            doViewSets();
        } else if (command.equals("vf")) {
            doViewFlashcards();
        } else if (command.equals("sd")) {
            doSaveDeck();
        } else if (command.equals("l")) {
            doLoadDeck();
        } else {
            System.out.println("There is no such selection! Try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: quizzes the user on a set of flashcards
    @SuppressWarnings("methodlength")
    protected void doQuizMe() {
        boolean keepRunningDoQuizMe = true;
        while (keepRunningDoQuizMe) {
            System.out.print("Enter the title of the flashcard set you wish to be quizzed on: ");
            String setSelection = input.nextLine();
            if (!(userDeck.containsSet(setSelection))) {
                System.out.println("\nSorry! No set with title '" + setSelection + "' was found!");
            } else {
                int correctAnswers = 0;
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(setSelection)) {
                        for (Flashcard flashcard : set.getFlashcardList()) {
                            System.out.print(flashcard.getQuestion() + ": ");
                            String userAnswer = input.nextLine();
                            if (flashcard.getAnswer().equals(userAnswer)) {
                                System.out.println("Correct!\n");
                                correctAnswers++;
                            } else {
                                System.out.println("Incorrect...\n");
                            }
                        }
                        System.out.println("You got " + correctAnswers
                                + " out of " + set.getFlashcardList().size() + " questions correct!");
                    }
                }
            }
            keepRunningDoQuizMe = false;
        }
    }

    // REQUIRES: Duplicate set titles not allowed.
    // MODIFIES: this
    // EFFECTS: creates a new empty flashcard set with a title
    public void doCreateSet() {

        boolean keepRunningDoCreateSet = true;
        while (keepRunningDoCreateSet) {
            System.out.print("Enter the title of the new flashcard set you wish to create: ");

            String addSetTitleSelection = input.nextLine();

            userDeck.addSet(addSetTitleSelection);

            System.out.println("\nThe set '" + addSetTitleSelection + "' has been created!");

            keepRunningDoCreateSet = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a flashcard set from the flashcard deck
    private void doRemoveSet() {

        boolean keepRunningDoRemoveSet = true;
        while (keepRunningDoRemoveSet) {
            System.out.print("Enter the title of the flashcard set you wish to remove: ");
            String removeSetTitleSelection = input.nextLine();
            if (userDeck.removeSet(removeSetTitleSelection)) {
                System.out.println("\nThe set '" + removeSetTitleSelection + "' has been removed!");
            } else {
                System.out.println("\nSorry! No set with title '" + removeSetTitleSelection + "' was found!");
            }
            keepRunningDoRemoveSet = false;
        }
    }

    // REQUIRES: Duplicate questions not allowed.
    // MODIFIES: this
    // EFFECTS: adds a flashcard to a flashcard set
    public void doAddFlashcard() {
        boolean keepRunningDoAddFlashcard = true;
        while (keepRunningDoAddFlashcard) {
            System.out.print("Enter the title of the set you wish to add a flashcard to: ");
            String setTitleSelection = input.nextLine();
            if (userDeck.containsSet(setTitleSelection)) {
                System.out.print("Enter the question of your new flashcard: ");
                String questionSelection = input.nextLine();
                System.out.print("Enter the answer of your new flashcard: ");
                String answerSelection = input.nextLine();
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(setTitleSelection)) {
                        set.addFlashcard(questionSelection, answerSelection);
                    }
                }
                System.out.println("\nYour new flashcard has been added to the set '" + setTitleSelection + "'!");
            } else {
                System.out.println("\nSorry! No set with title '" + setTitleSelection + "' was found!");
            }
            keepRunningDoAddFlashcard = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a flashcard from a flashcard set
    @SuppressWarnings("methodlength")
    public void doRemoveFlashcard() {
        boolean keepRunningDoRemoveFlashcard = true;
        while (keepRunningDoRemoveFlashcard) {
            System.out.print("Enter the title of the set you wish to remove a flashcard from: ");
            String setTitleSelection = input.nextLine();
            if (!(userDeck.containsSet(setTitleSelection))) {
                System.out.println("\nSorry! No set with title '" + setTitleSelection + "' was found!");
            } else {
                for (Set set : userDeck.getSetList()) {
                    if (set.getTitle().equals(setTitleSelection)) {
                        if (set.getFlashcardList().isEmpty()) {
                            System.out.println("\nSorry! The set '" + setTitleSelection
                                    + "' doesn't contain any flashcards to remove!");
                        } else {
                            System.out.print("Enter the question of the flashcard you wish to remove: ");
                            String questionSelection = input.nextLine();
                            if (!(set.containsFlashcard(questionSelection))) {
                                System.out.println("\nSorry! The set '" + setTitleSelection
                                        + "' doesn't contain a flashcard with that question!");
                            } else {
                                for (Iterator<Flashcard> iterator
                                        = set.getFlashcardList().iterator(); iterator.hasNext(); ) {
                                    Flashcard flashcard = iterator.next();
                                    if (flashcard.getQuestion().equals(questionSelection)) {
                                        iterator.remove();
                                        System.out.println("\nThe selected flashcard has been removed from the set '"
                                                + setTitleSelection + "'!");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            keepRunningDoRemoveFlashcard = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the title and number of flashcards of each
    //          created flashcard set
    public void doViewSets() {
        if (userDeck.getSetList().isEmpty()) {
            System.out.println("\nYou don't have any sets yet!");
        } else {
            for (Set set : userDeck.getSetList()) {
                System.out.print("\nTitle: "
                        + set.getTitle()
                        + " --"
                        + " Number of flashcards: "
                        + set.getFlashcardList().size()
                        + "\n");
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: displays the questions and answer of each flashcard in each
    //          created flashcard set
    public void doViewFlashcards() {
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

    // EFFECTS: saves the deck to file
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void doSaveDeck() {
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
    // EFFECTS: loads deck from file
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void doLoadDeck() {
        try {
            userDeck = jsonReader.read();
            System.out.println("Loaded deck from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
