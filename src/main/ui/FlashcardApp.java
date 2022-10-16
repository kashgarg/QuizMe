package ui;

import model.Deck;
import model.Set;

import java.util.Scanner;
import java.util.List;

// Flashcard application
public class FlashcardApp {
    private Scanner input;
    private Deck userDeck;

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
        String command;
        userDeck = new Deck();

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
    // DISCLAIMER: method structure based on TellerApp
    private void showMenu() {
        System.out.println("\nWelcome to QuizMe! What would you like to do?");
        System.out.println("\tPress q to start QuizMe!");
        System.out.println("\tPress c to create a new set");
        System.out.println("\tPress r to remove a set");
        System.out.println("\tPress e to edit a set");
        System.out.println("\tPress v to view sets");
        System.out.println("\tPress s to take a study break");
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

    // REQUIRES: Duplicate set titles not allowed.
    // MODIFIES: this
    // EFFECTS: creates a new empty flashcard set with a title
    public void doCreateSet() {

        boolean keepRunningDoCreateSet = true;
        while (keepRunningDoCreateSet) {
            System.out.print("Enter the title of the new flashcard set you wish to create: ");

            String setTitleSelection = input.nextLine();

            userDeck.addSet(setTitleSelection);

            System.out.println("\nThe set '" + setTitleSelection + "' has been created!");

            keepRunningDoCreateSet = false;
        }
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


}
