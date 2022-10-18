package model;

import java.util.ArrayList;
import java.util.List;

// Represents a flashcard set having a title and list of Flashcards
public class Set {

    private final String title;
    private final List<Flashcard> flashcardList;

    // REQUIRES: title must be a non-empty string.
    //           flashcardList should be an empty list initially.
    // EFFECTS:  Constructs a flashcard set object with a title
    //           and an empty list of flashcards.
    //           Title of flashcard set is set to title.
    //           An empty list of flashcards is set to flashcardList.
    public Set(String title) {
        this.title = title;
        flashcardList = new ArrayList<>();
    }

    // REQUIRES: question and answer must be non-empty strings
    // MODIFIES: this
    // EFFECTS:  creates a new Flashcard and adds it to this flashcard set
    public void addFlashcard(String question, String answer) {
        Flashcard newFlashcard = new Flashcard(question, answer);
        flashcardList.add(newFlashcard);
    }

    // MODIFIES: this
    // EFFECTS:  Removes the flashcard of the given question from
    //           the set and returns true. If no flashcard of the
    //           given question was found in the set then return false
    public boolean removeFlashcard(String question) {

        for (Flashcard flashcard : flashcardList) {
            if (flashcard.getQuestion().equals(question)) {
                flashcardList.remove(flashcard);
                return true;
            }
        }
        return false;
    }

    public String getTitle() {
        return title;
    }

    public List<Flashcard> getFlashcardList() {
        return flashcardList;
    }

}