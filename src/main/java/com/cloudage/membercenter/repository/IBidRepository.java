package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Bid;

public interface IBidRepository extends PagingAndSortingRepository<Bid, Integer> {
	@Query("select count(*) from Bid bid where bid.auction.id=?1")
	Integer countBidNumber(int auction_id);

}
