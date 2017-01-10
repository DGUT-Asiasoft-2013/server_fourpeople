package com.cloudage.membercenter.service;

import java.util.List;

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
	public Page<Goods> getGoods(Integer userid, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 5, sort);
		return goodsRepo.findAllByUserId(userid, pageRequest);
	}

	@Override
	public boolean delete(Integer goodsId) {
		// TODO Auto-generated method stub
		goodsRepo.delete(goodsId);
		return true;
	}

	@Override
	public Goods findGoodsById(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsRepo.findOne(goodsId);
	}

	@Override
	public Page<Goods> getGoodsByMall(Integer mallId, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 1000, sort);
		return goodsRepo.getGoodsByMall(mallId, pageRequest);
	}

	@Override
	public List<Goods> getSearchGoods(String edit) {
		// TODO Auto-generated method stub
		return goodsRepo.getSearchGoods(edit);
	}

}
