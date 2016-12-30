package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Bid;
import com.cloudage.membercenter.repository.IBidRepository;

@Component
@Service
@Transactional
public class DefaultBidService implements IBidService {

	@Autowired
	IBidRepository  bidRepository;
	@Override
	public Bid save(Bid bid) {
		// TODO Auto-generated method stub
		return bidRepository.save(bid);
	}
	@Override
	public Integer countBidNumber(int auctionId) {
		// TODO Auto-generated method stub
		return bidRepository.countBidNumber(auctionId);
	}
	
	
}
