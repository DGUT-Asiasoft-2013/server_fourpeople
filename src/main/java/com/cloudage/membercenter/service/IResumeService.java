package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;

public interface IResumeService {
	Resume save(Resume resume);
	List<Resume>findAllByAuthor(User user);
	List<Resume>findAllByAuthorAccount(String account);
	Resume findOne(String job_account);
	Page<Resume>getResume(int page);

}
