package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.GoodsComment;
import com.cloudage.membercenter.repository.IGoodsCommentRepository;

@Component
@Service
@Transactional
public class DefaultGoodsCommentService implements IGoodsCommentService {
	@Autowired
	IGoodsCommentRepository goodsCommentRepo;

	@Override
	public GoodsComment save(GoodsComment comment) {
		// TODO Auto-generated method stub
		return goodsCommentRepo.save(comment);
	}

	@Override
	public List<GoodsComment> findGoodsCommentByGoodsId(Integer currentGoodsId) {
		// TODO Auto-generated method stub
		return goodsCommentRepo.findGoodsCommentByGoodsId(currentGoodsId);
	}
}
