package persistence;

import model.Deck;
import model.Flashcard;
import model.Set;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads deck from JSON data stored in file
// DISCLAIMER: class structure based on JsonSerializationDemo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // DISCLAIMER: constructor structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads deck from file and returns it.
    // throws IOException if an error occurs reading data from file
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public Deck read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDeck(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses deck from JSON object and returns it
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private Deck parseDeck(JSONObject jsonObject) {
        Deck deck = new Deck();
        addSets(deck, jsonObject);
        return deck;
    }

    // MODIFIES: deck
    // EFFECTS: parses sets from JSON object and adds them to deck
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void addSets(Deck deck, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("setList");
        for (Object json : jsonArray) {
            JSONObject nextSet = (JSONObject) json;
            addSet(deck, nextSet);
        }
    }

    // MODIFIES: deck
    // EFFECTS: parses Set from JSON object and adds it to deck
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void addSet(Deck deck, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        Set set = new Set(title);
        JSONArray jsonArray = jsonObject.getJSONArray("flashcardList");
        for (Object json : jsonArray) {
            JSONObject nextFlashcard = (JSONObject) json;
            Flashcard card = makeFlashcard(nextFlashcard);
            set.addFlashcard(card);
        }
        deck.addSet(set);
    }

    // EFFECTS: parses Flashcard from JSON object and returns it
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private Flashcard makeFlashcard(JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        Flashcard flashcard = new Flashcard(question, answer);
        return flashcard;
    }
}
