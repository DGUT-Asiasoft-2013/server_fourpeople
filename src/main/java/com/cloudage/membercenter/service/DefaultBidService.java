package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	IBidRepository bidRepository;

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

	@Override
	public Bid findByAuctionId(int auctionId) {
		// TODO Auto-generated method stub
		return bidRepository.findByAuctionId(auctionId, true);
	}

	@Override
	public List<Bid> findBidByAuctionId(int auctionId) {
		// TODO Auto-generated method stub
		return bidRepository.findBidByAuctionId(auctionId,false);
	}

	@Override
	public Bid findById(Integer id) {
		// TODO Auto-generated method stub
		return bidRepository.findOne(id);
	}


}
