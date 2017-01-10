package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;

public interface IGoodsLikeService {

	boolean checkLiked(Integer id, int goodsId);

	void addLike(User me, Goods goods);

	void removeLike(User me, Goods goods);

	List<Goods> getFavorite(Integer userId);

}
