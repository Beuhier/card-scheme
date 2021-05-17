//package com._3line.gravity.freedom.zohodesk.model;
package com.interview.nine.cardscheme.zohodesk.model;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class Assignee {
    private String firstName;
    private String lastName;
    private String photoURL;
    private long id;
    private String email;

    public JSONObject AssigneeToJson(Assignee assignee){
        JSONObject Json = new JSONObject();
        Json.put("firstName", assignee.firstName);
        Json.put("lastName", assignee.lastName);
        Json.put("photoURL", assignee.photoURL);
        Json.put("id", String.valueOf(assignee.id));
        Json.put("email", assignee.email);
        return Json;
    }
}
