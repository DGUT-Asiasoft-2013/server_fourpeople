package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.User;

public interface IJobsService {
	Jobs save(Jobs jobs);
	List<Jobs>findAllByAuthor(User uer);
	List<Jobs>findAllByAuthorAccount(String studentId);
	Page<Jobs>getJobs(int page);
	Jobs findOne(int id);
	Page<Jobs>findJobsByAuthorAccount(int page ,String account);
	Jobs findOneByAccount(String account);

}
