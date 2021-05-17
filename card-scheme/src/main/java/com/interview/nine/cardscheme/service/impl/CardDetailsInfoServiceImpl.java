package com.interview.nine.cardscheme.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.interview.nine.cardscheme.exceptions.CardInfoServiceException;
import com.interview.nine.cardscheme.exceptions.InvalidInputException;
import com.interview.nine.cardscheme.messages.ErrorMessages;
import com.interview.nine.cardscheme.model.CardInfo;
import com.interview.nine.cardscheme.model.Payload;
import com.interview.nine.cardscheme.pojo.ExtractPojo;
import com.interview.nine.cardscheme.repository.CardInfoRepository;
import com.interview.nine.cardscheme.responses.InfoResponse;
import com.interview.nine.cardscheme.responses.StatsResponse;
import com.interview.nine.cardscheme.service.CardDetailsInfoService;


@Service
public class CardDetailsInfoServiceImpl implements CardDetailsInfoService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CardInfoRepository repo;
	
	@Value("${binlist.url}")
    String binUrl;
	
	private Logger log = LoggerFactory.getLogger(CardDetailsInfoServiceImpl.class);

	@Override
	public InfoResponse getCardInfo(String cardNumber) {
		return cardInfo(cardNumber);
	}

	@Override
	public StatsResponse getCardStats(int start, int limit) {
		return getAllCardStats(start, limit);
	}

	//checks if number is complete and valid, also remove spaces
	private String validateInput(String number) {
		String nospace = number.replaceAll("\\s+", "");
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(nospace);
		boolean b = m.find();
		if(nospace.chars().anyMatch(Character::isLetter) || b || number.length() < 8) {
			log.info("invalid card number");
			throw new InvalidInputException(ErrorMessages.WRONG_INPUT.getErrorMessages());
		}
		String num = nospace.substring(0, 8);
		log.info("card verified");
		return num;
	}

	/*takes in the validated card number and checks if its already in the database
	 * if not it makes an external api call
	 * 
	 */
	@Async
	InfoResponse cardInfo(String number) {
		int nums = 0;
		String num = validateInput(number);
		CardInfo info = repo.findByCardNumber(num);
		
		if (info == null) {
			ExtractPojo pojo = restTemplate.getForObject(binUrl + num, ExtractPojo.class);
			if (pojo == null) {
				throw new CardInfoServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
			}
			Payload payload = new Payload(pojo.getScheme() == null ? "" : pojo.getScheme(), 
					pojo.getType() == null ? "" : pojo.getType(), 
					pojo.getBank().getName() == null ? "" : pojo.getBank().getName());
			CardInfo cardInfo = new CardInfo(payload, nums + 1, true, num);
			cardInfo.setLatestRequest(new Date());
			try {
				CardInfo returnValue = repo.save(cardInfo);
				if (returnValue != null)
					return cardResponse(returnValue);
			} catch (Exception e) {
				log.info("unable to save object");
			}
		}
		
		info.setSearchAmount(info.getSearchAmount() + 1);
		return cardResponse(repo.save(info));
	}

	//map card details to the response payload
	private InfoResponse cardResponse(CardInfo info) {
		Payload payload = info.getPayload();
		InfoResponse response = new InfoResponse();
		response.setSuccess(info.getSuccess() == null ? false : info.getSuccess());
		response.setPayload(payload);
		return response;
	}

	@Async
	StatsResponse getAllCardStats(int start, int limit) {
		Map<String, Integer> payload = new HashMap<>();
		StatsResponse response = new StatsResponse();
		if (start > 0)
			start -= 1;
		Pageable request = PageRequest.of(start, limit);
		Page<CardInfo> cardInfoPages = repo.allCards(request);
		List<CardInfo> cards = cardInfoPages.getContent();
		if (cards.isEmpty()) {
			log.info("list is empty");
			throw new CardInfoServiceException(ErrorMessages.EMPTY_LIST.getErrorMessages());
		}
		cards.forEach(card -> {
			payload.put(card.getCardNumber(), card.getSearchAmount());
		});
		response.setLimit(limit);
		response.setSize(cards.size());
		response.setStart(start);
		response.setPayload(payload);
		response.setSuccess(true);
		return response;
	}
}
