package persistence;

import model.Deck;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of deck to file
// DISCLAIMER: class structure based on JsonSerializationDemo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer and throws FileNotFoundException if
    // destination file cannot be opened for writing
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of deck to file
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void write(Deck deck) {
        JSONObject jsonObject = deck.toJson();
        saveToFile(jsonObject.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void saveToFile(String json) {
        writer.print(json);
    }
}
