package com.interview.nine.cardscheme.pojo;

import lombok.Data;

@Data
public class ExtractPojo {

	private String scheme;
	private String type;
	private String brand;
	private Boolean prepaid;
	private Bank bank;
	private Country country;
	private Number number;

}
