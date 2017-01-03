package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Car;
import com.cloudage.membercenter.repository.ICarRepository;

@Component
@Service
@Transactional
public class DefaultCarService implements ICarService{
	@Autowired
	ICarRepository carRepo;

	@Override
	public Car save(Car car) {
		// TODO Auto-generated method stub
		return carRepo.save(car);
	}

	@Override
	public boolean check(Integer currentId, Integer currentGoodsId) {
		// TODO Auto-generated method stub
		return carRepo.check(currentId,currentGoodsId)!=null;
	}
}
