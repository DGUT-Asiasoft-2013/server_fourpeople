package com.cloudage.membercenter.service;

import java.util.List;


import com.cloudage.membercenter.entity.Bid;

public interface IBidService {

	Bid findById(Integer id);
	Bid save(Bid bid);
	Integer countBidNumber(int auctionId);
	Bid findByAuctionId(int auctionId);
	List<Bid> findBidByAuctionId(int auctionId);


}
