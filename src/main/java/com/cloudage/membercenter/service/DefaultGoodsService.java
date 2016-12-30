package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.repository.IGoodsRepository;

@Component
@Service
@Transactional
public class DefaultGoodsService implements IGoodsService {

	@Autowired
	IGoodsRepository goodsRepo;

	@Override
	public Goods save(Goods goods) {
		// TODO Auto-generated method stub
		return goodsRepo.save(goods);
	}

	@Override
	public Page<Goods> getGoods(Integer userid,int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 5, sort);
		return goodsRepo.findAllByUserId(userid,pageRequest);
	}

}