//package com._3line.gravity.freedom.zohodesk.model;

package com.interview.nine.cardscheme.zohodesk.model;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class Cf {
    private String cf_permanentaddress ;
    private String cf_dateofpurchase;
    private String cf_phone;
    private String cf_numberofitems;
    private String cf_url;
    private String cf_secondaryemail;
    private String cf_severitypercentage;
    private String cf_modelname;

    public JSONObject CfToJson(Cf cf){
        JSONObject cfJson = new JSONObject();
        cfJson.put("cf_permanentaddress", cf.cf_permanentaddress);
        cfJson.put("cf_dateofpurchase", cf.cf_dateofpurchase);
        cfJson.put("cf_phone", cf.cf_phone);
        cfJson.put("cf_numberofitems", cf.cf_numberofitems);
        cfJson.put("cf_url", cf.cf_url);
        cfJson.put("cf_secondaryemail", cf.cf_secondaryemail);
        cfJson.put("cf_severitypercentage", cf.cf_severitypercentage);
        cfJson.put("cf_modelname", cf.cf_modelname);
        return cfJson;
    }
}
