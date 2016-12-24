package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.User;

@Repository
public interface IJobsRepository extends PagingAndSortingRepository<Jobs,String>
{
	@Query("From User u where u=?1")
	List<Jobs>findAllByAuthor(User user);
	@Query("From User u where u.studentId=?1")
	List<Jobs>findAllByAuthorAccount(String studentId);
	
}
