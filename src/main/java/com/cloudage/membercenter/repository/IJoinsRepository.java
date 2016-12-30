package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Joins;
import com.cloudage.membercenter.entity.Resume;

@Repository
public interface IJoinsRepository extends PagingAndSortingRepository<Joins,Joins.Key>{
	@Query("select count(*)from Joins joins where joins.id.jobs.id=?1")
	int releaseCountsOfJobs(int jobsId);
	@Query("select count(*)from Joins joins where joins.id.user.studentId=?1 and joins.id.jobs.id=?2")
	int checkReleaseExsists(String account,int jobsId);
	@Query("select resume from Joins joins,Resume resume where joins.id.jobs.id=?1 and resume.account = joins.id.user.studentId")
	Page<Resume>findAllOfResume(int jobsId,Pageable page);
	//@Query("From joins joins where joins.id.jobs.id=?1")

}
