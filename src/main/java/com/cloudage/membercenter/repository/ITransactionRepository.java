package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Transaction;

public interface ITransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {
	@Query("from Transaction transaction where transaction.bid.bider.id=?1 and transaction.isFinish=?2")
	List<Transaction> findAllTransactionById(Integer id,Boolean isFinish);

	@Query("from Transaction transaction where transaction.bid.auction.id=?1")
	Transaction findTransactionByAuctionId(Integer auctionId);

	@Query("from Transaction transaction where transaction.auctionner.id=?1 and transaction.isFinish=?2")
	List<Transaction> findAllFinishActionByAuctionId(Integer id,Boolean isFinish);

}
