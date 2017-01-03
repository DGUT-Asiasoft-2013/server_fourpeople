package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Car;

public interface ICarService {
	Car save(Car car);

	boolean check(Integer currentId, Integer currentGoodsId);

}
