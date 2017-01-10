package com.cloudage.membercenter.service;

import java.util.List;

import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;

public interface IMallLikeService {

	boolean checkLiked(Integer id, int mallId);

	void addLike(User me, Mall mall);

	void removeLike(User me, Mall mall);

	List<Mall> getFavorite(Integer id);

}
