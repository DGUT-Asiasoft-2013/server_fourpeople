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
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IResumeRepository;

@Component
@Service
@Transactional
public class DefaultResumeService implements IResumeService{
	@Autowired
	IResumeRepository resumeRepo;

	@Override
	public Resume save(Resume resume) {
		
		return resumeRepo.save(resume);
	}

	@Override
	public List<Resume> findAllByAuthor(User user) {
		
		return (List<Resume>) resumeRepo.findAllByAuthor(user);
	}

	@Override
	public List<Resume> findAllByAuthorAccount(String studentId) {

		return resumeRepo.findAllByAuthorAccount(studentId);
	}

	@Override
	public Page<Resume> getResume(int page) {
		// 
		Sort sort=new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest=new PageRequest(page, 10,sort);
		return resumeRepo.findAll(pageRequest);
	
	}

	

	@Override
	public Resume findOne(int id) {
		// TODO Auto-generated method stub
		return resumeRepo.findOne(id);
	}






}
