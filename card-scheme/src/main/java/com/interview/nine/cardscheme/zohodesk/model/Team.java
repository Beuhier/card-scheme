//package com._3line.gravity.freedom.zohodesk.model;
package com.interview.nine.cardscheme.zohodesk.model;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class Team {
    private String name;
    private long id;

    public JSONObject TeamToJson(Team team){
        JSONObject Json = new JSONObject();
        Json.put("name", team.name);
        Json.put("id", String.valueOf(team.id));

        return Json;
    }
}
