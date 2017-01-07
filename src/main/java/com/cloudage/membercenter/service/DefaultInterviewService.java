package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.InterviewRequest;
import com.cloudage.membercenter.repository.IInterviewRepository;

@Component
@Service
@Transactional

public class DefaultInterviewService implements IIterviewRequestService{
	@Autowired
	IInterviewRepository interviewRepo;

	@Override
	public InterviewRequest save(InterviewRequest interviewRequest) {
		// TODO Auto-generated method stub
		return interviewRepo.save(interviewRequest);
	}

	@Override
	public Page<InterviewRequest> findInterviewByAuthorAccount(String interviewer, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 10, sort);
		return interviewRepo.findInterviewByAuthorAccount(interviewer, pageRequest);
	}

}
