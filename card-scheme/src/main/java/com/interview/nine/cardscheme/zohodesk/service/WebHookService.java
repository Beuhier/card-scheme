package com.interview.nine.cardscheme.zohodesk.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface WebHookService {

    JSONObject createWebHook(JSONObject webhookObj);

    JSONObject handleEvents(JSONArray webhookRespObj);
}
