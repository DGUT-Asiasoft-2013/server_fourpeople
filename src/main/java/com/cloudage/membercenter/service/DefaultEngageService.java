package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Engage;
import com.cloudage.membercenter.entity.Engage.Key;
import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IEngageRepository;

@Component
@Service
@Transactional
public class DefaultEngageService implements IEngageService{
	@Autowired
	IEngageRepository engageRepo;

	@Override
	public Engage save(Engage engage) {
		// TODO Auto-generated method stub
		return engageRepo.save(engage);
	}

	@Override
	public void addEngage(User user, Jobs jobs) {
		// TODO Auto-generated method stub
		Engage.Key key=new Key();
		key.setUser(user);
		key.setJobs(jobs);
		Engage lk=new Engage();
		lk.setId(key);
		engageRepo.save(lk);
		
	}

	@Override
	public void removeLike(User user, Jobs jobs) {
		// TODO Auto-generated method stub
		Engage.Key key=new Key();
		key.setUser(user);
		key.setJobs(jobs);
		engageRepo.delete(key);
		
	}

	@Override
	public int countLikes(String articleId) {
		// TODO Auto-generated method stub
		return engageRepo.likeCountsOfArticle(articleId);
	}

	@Override
	public boolean checkLiked(String useraccount, int jobsId) {
		// TODO Auto-generated method stub
		return engageRepo.checkLikesExsists(useraccount, jobsId)>0;
	}

	
	

	

}
