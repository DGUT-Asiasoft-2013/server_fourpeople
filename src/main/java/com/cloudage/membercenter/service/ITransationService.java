package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.Transaction;

public interface ITransationService {
	Transaction save(Transaction transaction);
	List<Transaction> findAllTransactionById(Integer id);
	Transaction findTransactionById(Integer id);
	List<Transaction> findAllFinishTransactionById(Integer id);
	Transaction findTransactionByAuctionId(Integer auctionId);
	List<Transaction> findAllFinishActionById(Integer id);
}
