package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;

public interface IMallService {
	Mall save(Mall mall);
	Mall ishavashop(User user);
	Mall findMallByUserId(Integer userId);
	Page<Mall> getMall(int page);


}
