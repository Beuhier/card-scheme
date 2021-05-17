//package com._3line.gravity.freedom.zohodesk.service;

//import com._3line.gravity.freedom.zohodesk.model.Ticket;
//import com._3line.gravity.freedom.zohodesk.model.TicketList;

package com.interview.nine.cardscheme.zohodesk.service;

import com.interview.nine.cardscheme.zohodesk.model.Ticket;
import com.interview.nine.cardscheme.zohodesk.model.TicketList;
import org.json.simple.JSONObject;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ZohoTicketService {

    Ticket getTicket(String ticketId);

    Ticket createTicket(JSONObject ticket);

    Ticket updateTicket(JSONObject ticket, String ticketId);

    String deleteTicket(Object ticketIds);

    TicketList getAllTickets();

    Ticket testTicket(String ticketId);
}
