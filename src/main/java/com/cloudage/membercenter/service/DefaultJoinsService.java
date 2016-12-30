package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Joins;
import com.cloudage.membercenter.repository.IJoinsRepository;
@Component
@Service
@Transactional
public class DefaultJoinsService implements IJoinsService{
	@Autowired
	IJoinsRepository joinRepo;

	@Override
	public Joins save(Joins join) {
		// TODO Auto-generated method stub
		return joinRepo.save(join);
	}

}
