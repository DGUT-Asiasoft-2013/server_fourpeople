package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Car;

@Repository
public interface ICarRepository extends PagingAndSortingRepository<Car, Integer>{
	@Query("from Car car where car.customerId=?1 and car.goods.id=?2")
	Car check(Integer currentId, Integer currentGoodsId);
	@Query("from Car car where car.customerId=?1")
	List<Car> findCarByUserId(Integer id);

}
