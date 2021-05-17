package com.interview.nine.cardscheme.zohodesk.utils;

import com.interview.nine.cardscheme.zohodesk.authenticator.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class APIUtils {

    @Autowired
    AuthenticateService authenticateService;

    @Value("${zoho.client.organisationId}")
    String orgId;

    public HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+authenticateService.getToken());
        headers.set("orgId",orgId);

        return headers;
    }

}
