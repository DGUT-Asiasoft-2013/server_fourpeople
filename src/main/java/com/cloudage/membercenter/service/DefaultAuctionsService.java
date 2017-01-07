package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Auction;
import com.cloudage.membercenter.repository.IAuctionRepository;

@Component
@Service
@Transactional
public class DefaultAuctionsService implements IAuctionsService {

	@Autowired
	IAuctionRepository auctionRepository;

	@Override
	public Page<Auction> getAuctions(int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(0, 50, sort);
		return auctionRepository.findAllVaildAuction(request);
		// return auctionRepository.findAll(request);
	}

	@Override
	public Page<Auction> getAllAuctions(int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(0, 50, sort);
		// return auctionRepository.findAllVaildAuction(request);
		return auctionRepository.findAll(request);
	}

	@Override
	public Auction save(Auction auction) {
		// TODO Auto-generated method stub
		return auctionRepository.save(auction);
	}

	@Override
	public Auction findAuction(int id) {
		// TODO Auto-generated method stub
		return auctionRepository.findAuction(id);
	}

	@Override
	public Page<Auction> getMyAction(int auctionnerId, int page) {

		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(0, 50, sort);
		// return auctionRepository.findAllVaildAuction(request);
		return auctionRepository.findMyAuctionById(auctionnerId,"完成交易",request);
	}

}
