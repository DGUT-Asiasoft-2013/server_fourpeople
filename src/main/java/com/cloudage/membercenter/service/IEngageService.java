package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Engage;
import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.User;

public interface IEngageService {
	Engage save(Engage engage);
	void addEngage(User user,Jobs jobs);
	 void removeLike(User user,Jobs jobs);
	 int countLikes(String studentId);
	 boolean checkLiked(String useraccount, int jobsId);



}
