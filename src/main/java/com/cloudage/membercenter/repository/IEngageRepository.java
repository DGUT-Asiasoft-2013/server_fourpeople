package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Engage;

@Repository
public interface IEngageRepository extends PagingAndSortingRepository<Engage,Engage.Key>{
	 @Query("select count(*) from Engage engage where engage.id.user.studentId=?1 and engage.id.jobs.id=?2")
	 int checkLikesExsists(String authoraccount,int jobsId);
	 @Query("select count(*) from Engage engage where engage.id.user.studentId=?1")
	 int likeCountsOfArticle(String account);

}
