package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Bid;

public interface IBidRepository extends PagingAndSortingRepository<Bid, Integer> {
	@Query("select count(*) from Bid bid where bid.auction.id=?1")
	Integer countBidNumber(int auction_id);
	
	@Query("from Bid bid where bid.auction.id=?1 and bid.methodIsPrice=?2")
	Bid findByAuctionId(int auction_id,Boolean isPrice);
	
	@Query("from Bid bid where bid.auction.id=?1 and bid.methodIsPrice=?2")
	List<Bid> findBidByAuctionId(int auctionnerId,Boolean isPrice);
}
