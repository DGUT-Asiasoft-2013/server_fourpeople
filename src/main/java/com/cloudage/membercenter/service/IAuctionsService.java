package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cloudage.membercenter.entity.Auction;

public interface IAuctionsService {

	Page<Auction> getAuctions(int page);

	Auction save(Auction auction);

	Page<Auction> getAllAuctions(int page);

	Auction findAuction(int id);
	
	Page<Auction> getMyAction(int auctionnerId,int page);

}
