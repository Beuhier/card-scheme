//package com._3line.gravity.freedom.zohodesk.service;

//import com._3line.gravity.freedom.zohodesk.authenticator.AuthenticateService;
//import com._3line.gravity.freedom.zohodesk.model.Access;
//import com._3line.gravity.freedom.zohodesk.model.Ticket;
//import com._3line.gravity.freedom.zohodesk.model.TicketList;
package com.interview.nine.cardscheme.zohodesk.service.impl;

import com.interview.nine.cardscheme.zohodesk.authenticator.AuthenticateService;
import com.interview.nine.cardscheme.zohodesk.model.Ticket;
import com.interview.nine.cardscheme.zohodesk.model.TicketList;
import com.interview.nine.cardscheme.zohodesk.service.ZohoTicketService;
import com.interview.nine.cardscheme.zohodesk.utils.APIUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ZohoTicketServiceImpl implements ZohoTicketService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${zoho.ticket.url}")
    String url;

    @Autowired
    APIUtils utils;

    @Override
    public Ticket getTicket(String ticketId) {
        try{
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("include", "contacts,products,assignee,departments,team");

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, utils.prepareHeaders());
            ResponseEntity<Ticket> resp = restTemplate.exchange(url+"/"+ticketId, HttpMethod.GET, requestEntity , Ticket.class);

            return resp.getBody();
        }catch(ResourceAccessException e){
            System.out.println(e);
        }
        return new Ticket();
    }

    @Override
    public Ticket createTicket(JSONObject ticket) {
        HttpEntity<JSONObject> entity = new HttpEntity<>(ticket, utils.prepareHeaders());
        return restTemplate.postForObject(url, entity, Ticket.class);
    }

    @Override
    public Ticket updateTicket(JSONObject ticket, String ticketId) {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        HttpEntity<String> entity = new HttpEntity<>(ticket.toJSONString(), utils.prepareHeaders());
        return restTemplate.patchForObject(url+"/"+ticketId, entity, Ticket.class);
    }

    @Override
    public String deleteTicket(Object ticketIds) {
        JSONObject params = new JSONObject();
        params.put("ticketIds",ticketIds);

        HttpEntity<JSONObject> entity = new HttpEntity<>(params,utils.prepareHeaders());
        System.out.println(entity.getBody());
        return restTemplate.postForObject(url+"/moveToTrash", entity, String.class);
    }

    @Override
    public TicketList getAllTickets() {
        try{
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("include", "contacts,assignee,departments,team,isRead");

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params,utils.prepareHeaders());
            ResponseEntity<TicketList> resp = restTemplate.exchange(url, HttpMethod.GET, requestEntity , TicketList.class);

            return resp.getBody();
        }catch(ResourceAccessException e){
            System.out.println(e);
        }
        return new TicketList();
    }

    public Ticket testTicket(String ticketId){
        JSONObject json = new JSONObject();
        Ticket ticket = getTicket(ticketId);
        //System.out.println("Get "+ticket);
        //ticket.setResponseDueDate(LocalDateTime.now().plusDays(2).toString());
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"); //.toString()

        // json.put("responseDueDate",String.valueOf(LocalDateTime.now().plusDays(2).format(dtf)));
        json.put("isOverDue",false);
        json.put("subject", "Here your updated ticket.");
        json.put("status", "Closed");

        return updateTicket(json, ticketId);
    }
}
