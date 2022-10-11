package model;

// Represents a flashcard having a question and an answer
public class Flashcard {

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

}
