//package com._3line.gravity.freedom.zohodesk.authenticator;
package com.interview.nine.cardscheme.zohodesk.authenticator;

//import com._3line.gravity.freedom.zohodesk.model.Access;
import com.google.gson.Gson;
import com.interview.nine.cardscheme.zohodesk.model.Access;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthenticateService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${zoho.code}")
    String code;

    @Value("${zoho.client.id}")
    String clientId;

    @Value("${zoho.client.secret}")
    String clientSecret;

    @Value("${zoho.auth.url}")
    String url;

    @Value("${zoho.client.organisationId}")
    String orgId;

    private Access access = new Access("","","","",0.0,null);

    public void createToken() { System.out.println("Create Token");
        try{
            if(!this.access.getAccess_token().equalsIgnoreCase("")){
                if (this.access.getExpiry_date().isBefore(LocalDateTime.now())) {
                    refreshToken();
                }
            }else {
                MultiValueMap<String, String> params = prepareAuthCall();
                params.add("redirect_uri", "https://accounts.zoho.com/oauthredirect");
                params.add("grant_type", "authorization_code");
                params.add("code", code);

                HttpHeaders headers = new HttpHeaders();
                headers.set("orgId", orgId);

                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

                ResponseEntity<String> access_resp = restTemplate.postForEntity(url, requestEntity, String.class);

                if (!Objects.requireNonNull(access_resp.getBody()).contains("error")) {
                    System.out.println("Success");
                    Gson gson = new Gson();
                    Access access = gson.fromJson(access_resp.getBody(), Access.class);

                    access.setExpiry_date(LocalDateTime.now().plusMinutes(1));
                    this.access = access;
                } else {
                    System.out.println(access_resp.getBody());
                }
            }
        }catch(ResourceAccessException e){
            System.out.println(e);
        }
    }

    public void refreshToken() { System.out.println("Refresh Token");
        try{
            MultiValueMap<String, String> params = prepareAuthCall();
            HttpHeaders headers = new HttpHeaders();

            params.add("grant_type", "refresh_token");
            params.add("refresh_token", this.access.getRefresh_token());
            headers.set("orgId",orgId);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params,headers);

            ResponseEntity<String> access_resp = restTemplate.postForEntity(url, requestEntity, String.class);

            if(!Objects.requireNonNull(access_resp.getBody()).contains("error")){
                Gson gson = new Gson();
                Access access = gson.fromJson(access_resp.getBody(),Access.class);

                access.setExpiry_date(LocalDateTime.now().plusMinutes(1));
                this.access =  access;
            }else {
                System.out.println(access_resp.getBody());
            }
        }catch(ResourceAccessException e){
            System.out.println(e);
        }
    }

    private MultiValueMap<String, String> prepareAuthCall() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);

        return params;
    }

    public String getToken() {
        createToken();
        return this.access.getAccess_token();
    }
}
