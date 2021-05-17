package com.interview.nine.cardscheme.zohodesk.service.impl;

import com.interview.nine.cardscheme.zohodesk.model.Ticket;
import com.interview.nine.cardscheme.zohodesk.model.TicketList;
import com.interview.nine.cardscheme.zohodesk.service.WebHookService;
import com.interview.nine.cardscheme.zohodesk.utils.APIUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WebHookServiceImpl implements WebHookService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${zoho.webhook.url}")
    String url;

    @Autowired
    APIUtils utils;

    @Override
    public JSONObject createWebHook(JSONObject webhookObj) {
        System.out.println(webhookObj);

        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(webhookObj, utils.prepareHeaders());

        ResponseEntity<JSONObject> resp = restTemplate.exchange(url, HttpMethod.POST, requestEntity , JSONObject.class);

        return resp.getBody();
    }

    @Override
    public JSONObject handleEvents(JSONArray webhookRespObj) {
        System.out.println("Event Called  " + webhookRespObj.get(0));
        return (JSONObject) webhookRespObj.get(0);
    }
}
