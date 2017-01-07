package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.MyOrder;

public interface  IMyOrderService {

	MyOrder save(MyOrder order);

	List<MyOrder> findAllCustomerOrderById(Integer currentUserId);

	List<MyOrder> findAllShopOrderById(Integer id);

}
