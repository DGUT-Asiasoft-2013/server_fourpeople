package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;

public interface IResumeService {
	Resume save(Resume resume);
	List<Resume>findAllByAuthor(User user);
	List<Resume>findAllByAuthorAccount(String studentId);
	Page<Resume>getResume(int page);
	Resume findOne(int id);
	Resume findResumeByAuthorAccount(String account);
	Page<Resume>searchTextWithKeyword(String key,int page);

}
