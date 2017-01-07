package com.cloudage.membercenter.service;


import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;

public interface IBookPartTime {
	void addBook(User user,Resume resume);
	void removeBook(User user,Resume resume);
	int countBooks(int resume_id);
	 boolean checkBooked(String userId, int resumeId);

}
