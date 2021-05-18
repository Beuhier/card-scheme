//package com._3line.gravity.freedom.zohodesk.model;
package com.interview.nine.cardscheme.zohodesk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TicketList {
    @JsonProperty(value = "data")
    private List<Ticket> listTicket;
}
