package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.Joins;
import com.cloudage.membercenter.entity.Joins.Key;
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IJoinsRepository;
@Component
@Service
@Transactional
public class DefaultJoinsService implements IJoinsService{
	@Autowired
	IJoinsRepository joinRepo;

	@Override
	public void addRelease(User user, Jobs jobs) {
		// TODO Auto-generated method stub
		Joins.Key key=new Key();
		key.setUser(user);
		key.setJobs(jobs);
		Joins ji=new Joins();
		ji.setId(key);
		joinRepo.save(ji);
		
		
	}

	@Override
	public void removeRelease(User user, Jobs jobs) {
		// TODO Auto-generated method stub
		Joins.Key key=new Key();
		key.setUser(user);
		key.setJobs(jobs);
		joinRepo.delete(key);
		
		
	}

	@Override
	public int countReleases(int jobs_id) {
		// TODO Auto-generated method stub
		return joinRepo.releaseCountsOfJobs(jobs_id);
	}

	@Override
	public boolean checkLiked(String userId, int jobsId) {
		// TODO Auto-generated method stub
		return joinRepo.checkReleaseExsists(userId, jobsId)>0;
	}

	@Override
	public Page<Resume> getResume(int userId, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Resume> getResumeByJobsId(int jobsId, int page) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(page, 10);	
		return joinRepo.findAllOfResume(jobsId, pageable);
	}



	
	

	
	

}
