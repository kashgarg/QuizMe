package ui;

import java.util.Scanner;

// Flashcard application
public class FlashcardApp {
    private Scanner input;

    // EFFECTS: runs the flashcard application
    // DISCLAIMER: constructor structure based on TellerApp
    public FlashcardApp() {
        runFlashcard();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // DISCLAIMER: method structure based on TellerApp
    private void runFlashcard() {
        boolean keepRunning = true;
        String command = null;

        input = new Scanner(System.in);

        while (keepRunning) {
            showMenu();
            command = input.next();

            if (command.equals("s")) {
                keepRunning = false;
            } else {
                runCommand(command);
            }
        }
        System.out.println("\n Have a good study break!");
    }

    // EFFECTS: shows the main menu to the user
    // DISCLAIMER: method structure based on TellerApp
    private void showMenu() {
        System.out.println("\nWelcome to QuizMe!");
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tq -> QuizMe!");
        System.out.println("\tc -> Create a new set");
        System.out.println("\tr -> Remove set");
        System.out.println("\te -> Edit sets");
        System.out.println("\tv -> View sets");
        System.out.println("\ts -> Study break");
    }

    // MODIFIES: this
    // EFFECTS: runs the user command
    // DISCLAIMER: method structure based on TellerApp
    private void runCommand(String command) {
        if (command.equals("q")) {
            doQuizMe();
        } else if (command.equals("c")) {
            doCreateSet();
        } else if (command.equals("r")) {
            doRemoveSet();
        } else if (command.equals("e")) {
            doEditSets();
        } else if (command.equals("v")) {
            doViewSets();
        } else {
            System.out.println("There is no such selection! Try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: quizzes the user on a set of flashcards
    private void doQuizMe() {
        System.out.println("QuizMe is not ready yet!"); // stub
    }

    // MODIFIES: this
    // EFFECTS: creates a new empty flashcard set with a title
    private void doCreateSet() {
        System.out.println("CreateSet is not ready yet!"); // stub
    }

    // MODIFIES: this
    // EFFECTS: removes a flashcard set
    private void doRemoveSet() {
        System.out.println("RemoveSet is not ready yet!"); // stub
    }

    // MODIFIES: this
    // EFFECTS: edits a flashcard set by adding or removing a flashcard
    private void doEditSets() {
        System.out.println("EditSets is not ready yet!"); // stub
    }

    // MODIFIES: this
    // EFFECTS: displays the title and number of flashcards of each
    //          created flashcard set
    private void doViewSets() {
        System.out.println("ViewSets is not ready yet!"); // stub
    }


}
