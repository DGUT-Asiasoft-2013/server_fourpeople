package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Auction;

public interface IAuctionsService {

	Page<Auction> getAuctions(int page);

	Auction save(Auction auction);

}
