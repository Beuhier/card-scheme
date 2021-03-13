package com.interview.nine.cardscheme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interview.nine.cardscheme.service.CardDetailsInfoService;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/card-scheme/")
public class CardDetailsController {
	@Autowired
	private CardDetailsInfoService service;
	

	@GetMapping(path = "verify/{cardNumber}", produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity getInfo(@PathVariable("cardNumber") String cardNumber) {
		return new ResponseEntity<>(service.getCardInfo(cardNumber),HttpStatus.OK);
	}

	@GetMapping(path = "stats", produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity getStatistics(@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		return new ResponseEntity<>(service.getCardStats(start, limit), HttpStatus.OK);
	}
	
}
