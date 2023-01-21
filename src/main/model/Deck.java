package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Represents a flashcard deck having a list of Sets
public class Deck implements Writable {

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
    // EFFECTS:  creates a new set with the given title
    //           and adds it to this deck
    public void addSet(String title) {
        Set newSet = new Set(title);
        EventLog.getInstance().logEvent(new Event("Added Set: " + title));
        setList.add(newSet);
    }

    // MODIFIES: this
    // EFFECTS: creates a new set and adds it to this deck
    public void addSet(Set setToAdd) {
        setList.add(setToAdd);
    }

    // MODIFIES: this
    // EFFECTS:  Removes the flashcard set of the given title from
    //           the flashcard deck and returns true. If no set of
    //           the given title was found in the flashcard deck
    //           then returns false.
    public boolean removeSetBoolean(String title) {

        for (Set set : setList) {
            if (set.getTitle().equals(title)) {
                EventLog.getInstance().logEvent(new Event("Removed Set: " + title));
                setList.remove(set);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: Removes the set of the given title from the deck
    public void removeSet(String title) {
        this.getSetList().removeIf(set -> set.getTitle().equals(title));
    }

    // EFFECTS: Returns true if a flashcard set in the deck has
    //          the same title as the given title, false otherwise
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

    @Override
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("setList", setListToJson());
        return json;
    }

    // EFFECTS: returns sets in this deck as a JSON array
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private JSONArray setListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Set set : setList) {
            jsonArray.put(set.toJson());
        }

        return jsonArray;
    }




}
