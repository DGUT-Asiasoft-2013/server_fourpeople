package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.BookParttime;
import com.cloudage.membercenter.entity.BookParttime.Book;
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IBookPtRepository;


@Component
@Service
@Transactional
public class DefaultBookService implements IBookPartTime{
	@Autowired
	IBookPtRepository bookRepo;

	@Override
	public void addBook(User user, Resume resume) {
		// TODO Auto-generated method stub
		BookParttime.Book book=new Book();
		book.setUser(user);
		book.setResume(resume);
		BookParttime pt=new BookParttime();
		pt.setId(book);
		bookRepo.save(pt);
		
	}

	@Override
	public void removeBook(User user, Resume resume) {
		// TODO Auto-generated method stub
		BookParttime.Book book=new Book();
		book.setUser(user);
		book.setResume(resume);
		bookRepo.delete(book);
		
	}

	@Override
	public int countBooks(int resume_id) {
		
		return bookRepo.releaseCountsOfBooks(resume_id);
	}

	@Override
	public boolean checkBooked(String userId, int resumeId) {
		return bookRepo.checkBookExsists(userId, resumeId)>0;
	}

}
