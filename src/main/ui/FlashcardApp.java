package ui;

import model.Set;

import java.util.Scanner;
import java.util.List;

// Flashcard application
public class FlashcardApp {
    private Scanner input;
    private Set setOne;
    private Set setTwo;
    private Set setThree;
    private Set setFour;
    private Set setFive;
    private List<Set> flashcardSets; 

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
        System.out.println("\tPress q to start QuizMe!");
        System.out.println("\t----------------------------");
        System.out.println("\tPress c to create a new set");
        System.out.println("\t----------------------------");
        System.out.println("\tPress r to remove a set");
        System.out.println("\t----------------------------");
        System.out.println("\tPress e to edit a set");
        System.out.println("\t----------------------------");
        System.out.println("\tPress v to view sets");
        System.out.println("\t----------------------------");
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

    // MODIFIES: this
    // EFFECTS: creates a new empty flashcard set with a title
    // DISCLAIMER: method structure based on TellerApp
    private void doCreateSet() {

        //System.out.println("CreateSet is not ready yet!"); // stub

        //System.out.println("Sorry! You've reached the maximum number of sets!");

        String nameSelection = "";

        if (flashcardSets.size() < 5) {
            while (nameSelection.equals("")) {
                System.out.println("Enter the title of the new flashcard set: ");
                nameSelection = input.nextLine();
                if (flashcardSets.isEmpty()) {
                    setOne = new Set(nameSelection);
                } else if (flashcardSets.size() == 1) {
                    setTwo = new Set(nameSelection);
                } else if (flashcardSets.size() == 2) {
                    setThree = new Set(nameSelection);
                } else if (flashcardSets.size() == 3) {
                    setFour = new Set(nameSelection);
                } else {
                    setFive = new Set(nameSelection);
                }
            }

            System.out.println("The set '" + nameSelection + "' has been created!");

        } else {
            System.out.println("Sorry! You've reached the maximum number of sets!");
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
    private void doViewSets() {
        System.out.println("ViewSets is not ready yet!"); // stub
    }


}
