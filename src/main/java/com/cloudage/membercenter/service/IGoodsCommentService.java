package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.GoodsComment;

public interface IGoodsCommentService {
	GoodsComment save(GoodsComment comment);

	List<GoodsComment> findGoodsCommentByGoodsId(Integer currentGoodsId);
}
