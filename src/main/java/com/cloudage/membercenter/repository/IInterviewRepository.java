package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.InterviewRequest;
@Repository
public interface IInterviewRepository extends PagingAndSortingRepository<InterviewRequest,Integer>{
	@Query("From InterviewRequest interview where interview.interviewer=?1")
	Page<InterviewRequest>findInterviewByAuthorAccount(String account,Pageable page);

}
