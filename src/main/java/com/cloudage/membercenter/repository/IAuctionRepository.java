package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Auction;
import com.cloudage.membercenter.entity.User;

public interface IAuctionRepository extends PagingAndSortingRepository<Auction, Integer> {
	
	@Query("from Auction auction where auction.isAuctioning = 1")
	Page<Auction> findAllVaildAuction(Pageable page);
	
	@Query("from Auction auction where auction.id = ?1")
	Auction findAuction(int id);
	
	@Query("from Auction auction where auction.auctinner.id = ?1 and auction.stateInfo !=?2")
	Page<Auction> findMyAuctionById(int auctionnerId,String stateInfo,Pageable page);


}
