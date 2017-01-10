package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.MallLike;
import com.cloudage.membercenter.entity.MallLike.Key;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IMallLikeRepository;

@Component
@Service
@Transactional
public class DefaultMallLikeService implements IMallLikeService{
	@Autowired
	IMallLikeRepository iMallLikeRepo;

	@Override
	public boolean checkLiked(Integer id, int mallId) {
		// TODO Auto-generated method stub
		return iMallLikeRepo.checkLikesExsists(id, mallId)>0;
	}

	@Override
	public void addLike(User me, Mall mall) {
		// TODO Auto-generated method stub
		MallLike.Key key = new Key();
		key.setUser(me);
		key.setMall(mall);

		MallLike lk = new MallLike();
		lk.setId(key);
		iMallLikeRepo.save(lk);
	}

	@Override
	public void removeLike(User me, Mall mall) {
		// TODO Auto-generated method stub
		MallLike.Key key = new Key();
		key.setUser(me);
		key.setMall(mall);
		iMallLikeRepo.delete(key);
	}

	@Override
	public List<Mall> getFavorite(Integer userId) {
		// TODO Auto-generated method stub
		return iMallLikeRepo.getFavorite(userId);
	}
}
