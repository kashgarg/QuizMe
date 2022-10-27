package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a flashcard having a question and an answer
public class Flashcard implements Writable {

    private final String question;
    private final String answer;

    // REQUIRES: question and answer must be non-empty strings
    // EFFECTS:  Constructs a flashcard object with a question
    //           and an answer. Question on flashcard is set to
    //           question. Answer on flashcard is set to answer;
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    // DISCLAIMER: method structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answer", answer);
        return json;
    }

}
