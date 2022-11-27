package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a flashcard set having a title and list of Flashcards
public class Set implements Writable {

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
    // EFFECTS:  creates a new flashcard and adds it to this set
    public void addFlashcard(String question, String answer) {
        Flashcard newFlashcard = new Flashcard(question, answer);
        flashcardList.add(newFlashcard);
    }

    // MODIFIES: this
    // EFFECTS: creates a new flashcard and adds it to this set
    public void addFlashcard(Flashcard flashcardTOadd) {
        flashcardList.add(flashcardTOadd);
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

    // EFFECTS:  Returns true if a flashcard in the set has the
    //           same question as the given question, false otherwise
    public boolean containsFlashcard(String question) {
        for (Flashcard flashcard : flashcardList) {
            if (flashcard.getQuestion().equals(question)) {
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

    @Override
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("flashcardList", flashcardListToJson());
        return json;
    }

    // EFFECTS: returns flashcards in this set as a JSON array
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private JSONArray flashcardListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flashcard flashcard : flashcardList) {
            jsonArray.put(flashcard.toJson());
        }

        return jsonArray;
    }

}