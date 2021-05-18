//package com._3line.gravity.freedom.zohodesk.model;

package com.interview.nine.cardscheme.zohodesk.model;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class Account {
    private String website;
    private String accountName;
    private long id;

    public JSONObject AccountToJson(Account account){
        JSONObject Json = new JSONObject();
        Json.put("website", account.website);
        Json.put("accountName", account.accountName);
        Json.put("id", String.valueOf(account.id));

        return Json;
    }
}

