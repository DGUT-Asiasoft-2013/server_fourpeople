package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.GoodsLike;
import com.cloudage.membercenter.entity.GoodsLike.Key;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IGoodsLikeRepository;
import com.cloudage.membercenter.repository.IMallLikeRepository;

@Component
@Service
@Transactional
public class DefaultGoodsLikeService implements IGoodsLikeService{
	@Autowired
	IGoodsLikeRepository iGoodsLikeRepo;

	@Override
	public boolean checkLiked(Integer id, int goodsId) {
		// TODO Auto-generated method stub
		return iGoodsLikeRepo.checkLikesExsists(id, goodsId)>0;
	}

	@Override
	public void addLike(User me, Goods goods) {
		// TODO Auto-generated method stub
		GoodsLike.Key key = new Key();
		key.setUser(me);
		key.setGoods(goods);

		GoodsLike lk = new GoodsLike();
		lk.setId(key);
		iGoodsLikeRepo.save(lk);
	}

	@Override
	public void removeLike(User me, Goods goods) {
		// TODO Auto-generated method stub
		GoodsLike.Key key = new Key();
		key.setUser(me);
		key.setGoods(goods);
		iGoodsLikeRepo.delete(key);
	}

	@Override
	public List<Goods> getFavorite(Integer userId) {
		// TODO Auto-generated method stub
		return iGoodsLikeRepo.getFavorite(userId);
	}
}
