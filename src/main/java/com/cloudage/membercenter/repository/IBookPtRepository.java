package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.BookParttime;


@Repository
public interface IBookPtRepository extends PagingAndSortingRepository<BookParttime,BookParttime.Book>{
	@Query("select count(*)from BookParttime book where book.id.resume.id=?1")
	int releaseCountsOfBooks(int resumeId);
	@Query("select count(*)from BookParttime book where book.id.user.studentId=?1 and book.id.resume.id=?2")
	int checkBookExsists(String account,int jobsId);

}
