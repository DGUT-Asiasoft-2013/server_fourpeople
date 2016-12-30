package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Bid;

public interface IBidService {

	Bid save(Bid bid);
	Integer countBidNumber(int auctionId);

}
