package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.InterviewRequest;

public interface IIterviewRequestService {
	InterviewRequest save(InterviewRequest interviewRequest);
	Page<InterviewRequest>findInterviewByAuthorAccount(String interviewer,int page);

}
