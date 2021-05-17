//package com._3line.gravity.freedom.zohodesk.model;
package com.interview.nine.cardscheme.zohodesk.model;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class Source {
    private String appName = null;
    private String extId = null;
    private String type;
    private String permalink = null;
    private String appPhotoURL = null;

    public JSONObject SourceToJson(Source source){
        JSONObject Json = new JSONObject();
        Json.put("appName", source.appName);
        Json.put("extId", source.extId);
        Json.put("type", source.type);
        Json.put("permalink", source.permalink);
        Json.put("appPhotoURL", source.appPhotoURL);

        return Json;
    }
}
