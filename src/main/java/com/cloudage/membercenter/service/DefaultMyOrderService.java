package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.MyOrder;
import com.cloudage.membercenter.repository.IMyOrderRepository;

@Component
@Service
@Transactional
public class DefaultMyOrderService implements IMyOrderService {
	@Autowired
	IMyOrderRepository orderRepo;

	@Override
	public MyOrder save(MyOrder order) {
		// TODO Auto-generated method stub
		return orderRepo.save(order);
	}

	@Override
	public List<MyOrder> findAllCustomerOrderById(Integer currentUserId) {
		// TODO Auto-generated method stub
		return orderRepo.findAllCustomerOrderById(currentUserId);
	}

	@Override
	public List<MyOrder> findAllShopOrderById(Integer currentUserId) {
		// TODO Auto-generated method stub
		return orderRepo.findAllShopOrderById(currentUserId);
	}
}
