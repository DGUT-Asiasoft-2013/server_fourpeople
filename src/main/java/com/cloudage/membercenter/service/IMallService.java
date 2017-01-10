package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;

public interface IMallService {
	Mall save(Mall mall);
	Mall ishavashop(User user);
	Mall findMallByUserId(Integer userId);
	Page<Mall> getDefaultMall(int page);
    Mall findOne(int id);
	Page<Mall> getCreditMall(int page);
	List<Mall> getSearchshop(String edit);

}
