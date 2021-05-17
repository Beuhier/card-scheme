//package com._3line.gravity.freedom.zohodesk.model;
package com.interview.nine.cardscheme.zohodesk.model;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class Department {
    private String name;
    private long id;
    private String type;

    public JSONObject DepartmentToJson(Department department){
        JSONObject Json = new JSONObject();
        Json.put("name", department.name);
        Json.put("id", String.valueOf(department.id));
        Json.put("type", department.type);

        return Json;
    }
}
