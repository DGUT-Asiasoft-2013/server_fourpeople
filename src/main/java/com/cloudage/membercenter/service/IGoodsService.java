package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Mall;

public interface IGoodsService {
	Goods save(Goods goods);
	Page<Goods> getGoods(Integer userid,int page);
}
