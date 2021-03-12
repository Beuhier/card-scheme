package com.interview.nine.cardscheme.responses;


import com.interview.nine.cardscheme.model.Payload;
import lombok.Data;

@Data
public class InfoResponse {

	private Boolean success;
	private Payload payload;

}
