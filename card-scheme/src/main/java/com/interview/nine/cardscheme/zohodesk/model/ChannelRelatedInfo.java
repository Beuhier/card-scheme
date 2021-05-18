//package com._3line.gravity.freedom.zohodesk.model;
package com.interview.nine.cardscheme.zohodesk.model;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class ChannelRelatedInfo {
    private long topicId;
    private boolean isTopicDeleted;
    private String forumStatus;
    private String sourceLink;
    private String topicType;

    public JSONObject ChannelRelatedInfoToJson(ChannelRelatedInfo channelRelatedInfo){
        JSONObject Json = new JSONObject();
        Json.put("topicId", String.valueOf(channelRelatedInfo.topicId));
        Json.put("isTopicDeleted", channelRelatedInfo.isTopicDeleted);
        Json.put("forumStatus", channelRelatedInfo.forumStatus);
        Json.put("sourceLink", channelRelatedInfo.sourceLink);
        Json.put("topicType", channelRelatedInfo.topicType);

        return Json;
    }
}
