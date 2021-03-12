package com.interview.nine.cardscheme.service;

import org.springframework.stereotype.Service;

import com.interview.nine.cardscheme.responses.InfoResponse;
import com.interview.nine.cardscheme.responses.StatsResponse;


@Service
public interface CardDetailsInfoService {
	public InfoResponse getCardInfo(String cardNumber);
	public StatsResponse getCardStats(int start, int limit);	
}
