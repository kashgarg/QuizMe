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
    // EFFECTS:  creates a new flashcard set and adds it to this flashcard deck
    public void addSet(String title) {
        Set newSet = new Set(title);
        setList.add(newSet);
    }

    public void removeSet(int index) {
        setList.remove(index);
    }

    public List<Set> getSetList() {
        return setList;
    }


}
