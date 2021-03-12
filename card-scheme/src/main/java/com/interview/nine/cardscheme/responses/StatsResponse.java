package com.interview.nine.cardscheme.responses;

import java.util.Map;

import lombok.Data;

@Data
public class StatsResponse {
	private Boolean success;
	private int start;
	private int limit;
	private long size;
	private Map<String, Integer> payload;

}
