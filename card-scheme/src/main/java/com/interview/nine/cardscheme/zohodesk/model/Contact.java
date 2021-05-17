//package com._3line.gravity.freedom.zohodesk.model;
package com.interview.nine.cardscheme.zohodesk.model;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class Contact {
    private String lastName;
    private String firstName;
    private String phone;
    private String mobile;
    private long id;
    private boolean isSpam;
    private String type;
    private String email;
    Account account;

    public JSONObject ContactToJson(Contact contact){
        JSONObject Json = new JSONObject();
        Json.put("lastName", contact.lastName);
        Json.put("firstName", contact.firstName);
        Json.put("phone", contact.phone);
        Json.put("mobile", contact.mobile);
        Json.put("id", String.valueOf(contact.id));
        Json.put("isSpam", contact.isSpam);
        Json.put("type", contact.type);
        Json.put("email", contact.email);
        Json.put("account", contact.account);

        return Json;
    }
}
