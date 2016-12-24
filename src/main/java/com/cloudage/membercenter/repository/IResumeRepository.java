package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;

@Repository
public interface IResumeRepository extends PagingAndSortingRepository<Resume,String>{
	@Query("From User u where u=?1")
	List<Resume>findAllByAuthor(User user);
	@Query("From User u where u.account=?1")
	List<Resume>findAllByAuthorAccount(String account);

}
