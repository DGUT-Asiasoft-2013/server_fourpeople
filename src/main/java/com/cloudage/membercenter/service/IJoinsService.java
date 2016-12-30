package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;

public interface IJoinsService {
	void addRelease(User user,Jobs jobs);
	void removeRelease(User user,Jobs jobs);
	int countReleases(int jobs_id);
	 boolean checkLiked(String userId, int jobsId);
	 Page<Resume> getResume(int userId, int page);
	 Page<Resume>getResumeByJobsId(int jobsId,int page);

	

}
