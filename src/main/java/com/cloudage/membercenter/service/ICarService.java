package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.Car;

public interface ICarService {
	Car save(Car car);

	boolean check(Integer currentId, Integer currentGoodsId);

	List<Car> findCarByUserId(Integer id);

	Boolean deleteCarById(Integer cartId);

}
