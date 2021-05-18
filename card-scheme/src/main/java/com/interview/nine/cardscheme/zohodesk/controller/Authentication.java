//package com._3line.gravity.freedom.zohodesk.controller;
package com.interview.nine.cardscheme.zohodesk.controller;

//import com._3line.gravity.freedom.zohodesk.authenticator.AuthenticateService;
import com.interview.nine.cardscheme.zohodesk.authenticator.AuthenticateService;
import com.interview.nine.cardscheme.zohodesk.model.Ticket;
import com.interview.nine.cardscheme.zohodesk.service.WebHookService;
import com.interview.nine.cardscheme.zohodesk.service.ZohoTicketService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zoho/")
public class Authentication {

    @Autowired
    AuthenticateService authService;

    @Autowired
    ZohoTicketService zohoService;

    @Autowired
    WebHookService webHookService;

    @PostMapping(path = "auth", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity authenticateUser(){
        System.out.println(authService.getToken());
        return new ResponseEntity<>(authService.getToken(), HttpStatus.OK);
    }

    @GetMapping(path = "ticket", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity getTickets(){
        return new ResponseEntity<>(zohoService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping(path = "ticketId", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity getTicket(){
        return new ResponseEntity<>(zohoService.testTicket("607424000000167073"), HttpStatus.OK);
    }

    @PostMapping(path = "addticket", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTicket(@RequestBody Ticket ticket){
        return new ResponseEntity<>(zohoService.createTicket(ticket.TicketToJsonForCreation(ticket)), HttpStatus.OK);
    }

    @PostMapping(path = "deleteticket", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteTicket(@RequestBody JSONObject ticketIds){
        return new ResponseEntity<>(zohoService.deleteTicket(ticketIds.get("ticketIds")), HttpStatus.OK);
    }

    @PostMapping(path = "createwebhook", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createWebhook(@RequestBody JSONObject webhookObj){
        return new ResponseEntity<>(webHookService.createWebHook(webhookObj), HttpStatus.OK);
    }

    @GetMapping(path = "ticketwebhook")
    public ResponseEntity ticketWebhook(@RequestBody JSONArray webhookRespObj){
        return new ResponseEntity<>(webHookService.handleEvents(webhookRespObj),HttpStatus.OK);
    }
}
