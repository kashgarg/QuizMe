package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as a JSON object
    // DISCLAIMER: Interface structure based on JsonSerializationDemo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    JSONObject toJson();
}
