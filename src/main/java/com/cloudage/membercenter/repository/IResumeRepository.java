package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;

@Repository
public interface IResumeRepository extends PagingAndSortingRepository<Resume,Integer>{
	@Query("From User u where u=?1")
	List<Resume>findAllByAuthor(User user);
	@Query("From User u where u.studentId=?1")
	List<Resume>findAllByAuthorAccount(String studentId);
	@Query("From Resume resume where resume.account=?1")
	Resume findResumeByAuthorAccount(String studentId);
	@Query("From Resume resume where resume.details like %?1%")
	Page<Resume> searchTextWithKeyword(String keyword,Pageable page);
	

}
