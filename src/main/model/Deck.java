package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Represents a flashcard deck having a list of Sets
public class Deck {

    private final List<Set> setList;

    // REQUIRES: setList should be an empty list initially
    // EFFECTS:  Constructs a flashcard deck object with an
    //           empty list of flashcard sets.
    //           An empty list of flashcard sets is set to setList.
    public Deck() {
        setList = new ArrayList<>();
    }

    // REQUIRES: title must be a non-empty string.
    // MODIFIES: this
    // EFFECTS:  creates a new flashcard set with the given title
    //           and adds it to this flashcard deck
    public void addSet(String title) {
        Set newSet = new Set(title);
        setList.add(newSet);
    }

    // MODIFIES: this
    // EFFECTS:  Removes the flashcard set of the given title from
    //           the flashcard deck and returns true. If no set of
    //           the given title was found in the flashcard deck
    //           then returns false.
    public boolean removeSet(String title) {

        for (Set set : setList) {
            if (set.getTitle().equals(title)) {
                setList.remove(set);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns true if a flashcard set has the same title
    //          as the given title, false otherwise
    public boolean containsSet(String title) {

        for (Set set: setList) {
            if (set.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public List<Set> getSetList() {
        return setList;
    }


}
