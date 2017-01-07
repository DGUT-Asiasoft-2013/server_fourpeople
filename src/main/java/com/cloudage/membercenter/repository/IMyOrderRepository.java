package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.MyOrder;

@Repository
public interface IMyOrderRepository extends PagingAndSortingRepository<MyOrder, Integer>{
	@Query("from MyOrder myOrder where myOrder.user.id=?1")
	List<MyOrder> findAllCustomerOrderById(Integer currentUserId);
	@Query("from MyOrder myOrder where myOrder.goods.mall.user.id=?1")
	List<MyOrder> findAllShopOrderById(Integer currentUserId);

}
