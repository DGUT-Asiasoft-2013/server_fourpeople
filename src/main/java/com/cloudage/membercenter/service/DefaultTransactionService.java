package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Transaction;
import com.cloudage.membercenter.repository.ITransactionRepository;

@Component
@Service
@Transactional
public class DefaultTransactionService implements ITransationService {
	@Autowired
	ITransactionRepository iTrasactionRepository;

	@Override
	public Transaction save(Transaction transaction) {
		// TODO Auto-generated method stub
		return iTrasactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> findAllTransactionById(Integer id) {
		// TODO Auto-generated method stub
		return iTrasactionRepository.findAllTransactionById(id,false);
	}
	@Override
	public List<Transaction> findAllFinishTransactionById(Integer id) {
		// TODO Auto-generated method stub
		return iTrasactionRepository.findAllTransactionById(id,true);
	}

	@Override
	public Transaction findTransactionById(Integer id) {
		// TODO Auto-generated method stub
		return iTrasactionRepository.findOne(id);
	}

	@Override
	public Transaction findTransactionByAuctionId(Integer auctionId) {
		// TODO Auto-generated method stub
		return iTrasactionRepository.findTransactionByAuctionId(auctionId);
	}

	@Override
	public List<Transaction> findAllFinishActionById(Integer id) {
		// TODO Auto-generated method stub
		return iTrasactionRepository.findAllFinishActionByAuctionId(id,true);
	}

}
