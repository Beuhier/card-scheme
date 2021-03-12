package com.interview.nine.cardscheme.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.interview.nine.cardscheme.model.CardInfo;
import com.interview.nine.cardscheme.pojo.ExtractPojo;

@Repository
public interface CardInfoRepository extends PagingAndSortingRepository<CardInfo, Long>{
	
	@Query(value = "SELECT * FROM card_info",nativeQuery = true)
	Page<CardInfo> allCards(Pageable page);
	
	CardInfo findByCardNumber(String cardNumber);

}
