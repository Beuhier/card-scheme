//package com._3line.gravity.freedom.zohodesk.model;
package com.interview.nine.cardscheme.zohodesk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class Ticket {
        @JsonProperty(value = "modifiedTime")
        private String modifiedTime= null;
        @JsonProperty(value = "subCategory")
        private String subCategory= null;
        @JsonProperty(value = "statusType")
        private String statusType= null;
        @JsonProperty(value = "subject")
        private String subject= null;
        @JsonProperty(value = "dueDate")
        private String dueDate;
        @JsonProperty(value = "responseDueDate")
        private String responseDueDate= null;
        @JsonProperty(value = "departmentId")
        private long departmentId;
        @JsonProperty(value = "channel")
        private String channel= null;
        @JsonProperty(value = "onholdTime")
       private String onholdTime = null;
        @JsonProperty(value = "language")
        private String language = null;
        @JsonProperty(value = "source")
        Source source;
        @JsonProperty(value = "resolution")
        private String resolution= null;
        @JsonProperty(value = "sharedDepartments")
        List< Department > sharedDepartments = new ArrayList<>();
        @JsonProperty(value = "closedTime")
        private String closedTime= null;
        @JsonProperty(value = "sharedCount")
        private String sharedCount;
        @JsonProperty(value = "approvalCount")
        private String approvalCount;
        @JsonProperty(value = "isOverDue")
        private boolean isOverDue = false;
        @JsonProperty(value = "isTrashed")
        private boolean isTrashed;
        @JsonProperty(value = "contact")
        Contact contact;
        @JsonProperty(value = "createdTime")
        private String createdTime= null;
        @JsonProperty(value = "id")
        private long id;
        @JsonProperty(value = "customerResponseTime")
        private String customerResponseTime= null;
        @JsonProperty(value = "productId")
        private long productId;
        @JsonProperty(value = "contactId")
        private long contactId;
        @JsonProperty(value = "threadCount")
        private String threadCount;
        @JsonProperty(value = "secondaryContacts")
        List < String > secondaryContacts = new ArrayList <> ();
        @JsonProperty(value = "priority")
        private String priority= null;
        @JsonProperty(value = "classification")
        private String classification= null;
        @JsonProperty(value = "commentCount")
        private String commentCount;
        @JsonProperty(value = "taskCount")
        private String taskCount;
        @JsonProperty(value = "phone")
        private String phone= null;
        @JsonProperty(value = "webUrl")
        private String webUrl= null;
        @JsonProperty(value = "isSpam")
        private boolean isSpam;
        @JsonProperty(value = "assignee")
        Assignee assignee;
        @JsonProperty(value = "status")
        private String status= null;
        @JsonProperty(value = "entitySkills")
        List < String > entitySkills = new ArrayList <> ();
        @JsonProperty(value = "ticketNumber")
        private String ticketNumber;
        @JsonProperty(value = "isRead")
        private boolean isRead;
        @JsonProperty(value = "description")
        private String description= null;
        @JsonProperty(value = "timeEntryCount")
        private String timeEntryCount;
        @JsonProperty(value = "channelRelatedInfo")
        ChannelRelatedInfo channelRelatedInfo;
        @JsonProperty(value = "isDeleted")
        private String isDeleted= null;
        @JsonProperty(value = "department")
        Department department;
        @JsonProperty(value = "followerCount")
        private String followerCount;
        @JsonProperty(value = "email")
        private String email= null;
        @JsonProperty(value = "channelCode")
        private String channelCode= null;
        @JsonProperty(value = "product")
        private String product= null;
        @JsonProperty(value = "cf")
        Cf cf;
        @JsonProperty(value = "isFollowing")
        private String isFollowing= null;
        @JsonProperty(value = "team")
        Team team;
        @JsonProperty(value = "assigneeId")
        private long assigneeId;
        @JsonProperty(value = "teamId")
        private long teamId;
        @JsonProperty(value = "contractId")
        private long contractId;
        @JsonProperty(value = "tagCount")
        private String tagCount;
        @JsonProperty(value = "attachmentCount")
        private String attachmentCount;
        @JsonProperty(value = "isEscalated")
        private boolean isEscalated;
        @JsonProperty(value = "category")
        private String category= null;

     public JSONObject TicketToJsonForCreation(Ticket ticket) {
        JSONObject ticketJson = new JSONObject();
        ticketJson.put("subCategory",ticket.subCategory);
        ticketJson.put("subject",ticket.subject);
        ticketJson.put("dueDate",ticket.dueDate);
        ticketJson.put("departmentId",String.valueOf(ticket.departmentId));
        ticketJson.put("channel",ticket.channel);
        ticketJson.put("language",ticket.language);
        ticketJson.put("contactId",String.valueOf(ticket.contactId));
        ticketJson.put("priority",ticket.priority);
        ticketJson.put("classification",ticket.classification);
        ticketJson.put("phone",ticket.phone);
        ticketJson.put("status",ticket.status);
        ticketJson.put("entitySkills",ticket.entitySkills);
        ticketJson.put("description",ticket.description);
        ticketJson.put("timeEntryCount",ticket.timeEntryCount);
        ticketJson.put("email",ticket.email);
        ticketJson.put("cf",ticket.cf.CfToJson(ticket.cf));
        ticketJson.put("assigneeId",String.valueOf(ticket.assigneeId));
        ticketJson.put("category",ticket.category);

        return ticketJson;
     }

     public JSONObject TicketToJson(Ticket ticket) {
        JSONObject ticketJson = new JSONObject();
        ticketJson.put("modifiedTime",ticket.modifiedTime);
        ticketJson.put("subCategory",ticket.subCategory);
        ticketJson.put("statusType",ticket.statusType);
        ticketJson.put("subject",ticket.subject);
        ticketJson.put("dueDate",ticket.dueDate);
        ticketJson.put("responseDueDate",ticket.responseDueDate);
        ticketJson.put("departmentId",String.valueOf(ticket.departmentId));
        ticketJson.put("channel",ticket.channel);
        ticketJson.put("onholdTime",ticket.onholdTime);
        ticketJson.put("language",ticket.language);
        ticketJson.put("source",ticket.source);
        ticketJson.put("resolution",ticket.resolution);
        ticketJson.put("sharedDepartments",ticket.sharedDepartments);
        ticketJson.put("closedTime",ticket.closedTime);
        ticketJson.put("sharedCount",ticket.sharedCount);
        ticketJson.put("approvalCount",ticket.approvalCount);
        ticketJson.put("isOverDue",ticket.isOverDue);
        ticketJson.put("isTrashed",ticket.isTrashed);
        ticketJson.put("contact",ticket.contact);
        ticketJson.put("createdTime",ticket.createdTime);
        ticketJson.put("id",ticket.id);
        ticketJson.put("customerResponseTime",ticket.customerResponseTime);
        ticketJson.put("contactId",String.valueOf(ticket.contactId));
        ticketJson.put("threadCount",ticket.threadCount);
        ticketJson.put("secondaryContacts",ticket.secondaryContacts);
        ticketJson.put("priority",ticket.priority);
        ticketJson.put("classification",ticket.classification);
        ticketJson.put("commentCount",ticket.commentCount);
        ticketJson.put("taskCount",ticket.taskCount);
        ticketJson.put("phone",ticket.phone);
        ticketJson.put("webUrl",ticket.webUrl);
        ticketJson.put("isSpam",ticket.isSpam);
        ticketJson.put("assignee",ticket.assignee);
        ticketJson.put("status",ticket.status);
        ticketJson.put("entitySkills",ticket.entitySkills);
        ticketJson.put("ticketNumber",ticket.ticketNumber);
        ticketJson.put("isRead",ticket.isRead);
        ticketJson.put("description",ticket.description);
        ticketJson.put("timeEntryCount",ticket.timeEntryCount);
        ticketJson.put("channelRelatedInfo",ticket.channelRelatedInfo);
        ticketJson.put("isDeleted",ticket.isDeleted);
        ticketJson.put("followerCount",ticket.followerCount);
        ticketJson.put("email",ticket.email);
        ticketJson.put("channelCode",ticket.channelCode);
        ticketJson.put("product",ticket.product);
        ticketJson.put("cf",ticket.cf.CfToJson(ticket.cf));
        ticketJson.put("isFollowing",ticket.isFollowing);
        ticketJson.put("team",ticket.team);
        ticketJson.put("assigneeId",String.valueOf(ticket.assigneeId));
        ticketJson.put("teamId",ticket.teamId);
        ticketJson.put("contractId",ticket.contractId);
        ticketJson.put("tagCount",ticket.tagCount);
        ticketJson.put("attachmentCount",ticket.attachmentCount);
        ticketJson.put("isEscalated",ticket.isEscalated);
        ticketJson.put("category",ticket.category);

        return ticketJson;
     }
}
