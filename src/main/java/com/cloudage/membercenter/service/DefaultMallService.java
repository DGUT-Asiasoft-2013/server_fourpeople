package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IMallRepository;
import com.cloudage.membercenter.repository.IUserRepository;

@Component
@Service
@Transactional
public class DefaultMallService implements IMallService {

	@Autowired
	IMallRepository mallRepo;
	@Override
	public Mall save(Mall mall) {
		// TODO Auto-generated method stub
		return mallRepo.save(mall);
	}
	@Override
	public Mall ishavashop(User user) {
		// TODO Auto-generated method stub
		return mallRepo.ishaveshop(user);
	}
	@Override
	public Mall getMall(Integer userid) {
		// TODO Auto-generated method stub
		return mallRepo.getMall(userid);
	}

}
