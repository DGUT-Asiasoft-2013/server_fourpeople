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

import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IJobsRepository;
@Component
@Service
@Transactional
public class DefaultIJobsService implements IJobsService{
	@Autowired
    IJobsRepository jobsRepo;
	@Override
	public Jobs save(Jobs jobs) {
		// TODO Auto-generated method stub
		return jobsRepo.save(jobs);
	}
	@Override
	public List<Jobs> findAllByAuthor(User user) {
		// TODO Auto-generated method stub
		return (List<Jobs>) jobsRepo.findAllByAuthor(user);
	}
	@Override
	public List<Jobs> findAllByAuthorAccount(String studentId) {
		// TODO Auto-generated method stub
		return jobsRepo.findAllByAuthorAccount(studentId);
	}
	@Override
	public Page<Jobs> getJobs(int page) {
		// TODO Auto-generated method stub
		Sort sort=new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest=new PageRequest(page, 10,sort);
		return jobsRepo.findAll(pageRequest);
	}
	@Override
	public Jobs findOne(String job_account) {
		// TODO Auto-generated method stub
		return jobsRepo.findOne(job_account);
	}

}
