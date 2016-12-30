package com.cloudage.membercenter.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Jobs;
import com.cloudage.membercenter.entity.Joins;
import com.cloudage.membercenter.entity.Resume;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IBookPartTime;
import com.cloudage.membercenter.service.IJobsService;
import com.cloudage.membercenter.service.IJoinsService;
import com.cloudage.membercenter.service.IResumeService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/parttime")
public class PartTimeController {
	@Autowired
	IUserService userService;
	@Autowired
	IJobsService jobsService;
	@Autowired
	IResumeService resumeService;
	@Autowired
	IJoinsService joinsService;
	@Autowired
	IBookPartTime bookService;

	// 获得当前用户
	private User getCurrentUser(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession(true);
		Integer id = (Integer) httpSession.getAttribute("id");
		return userService.findById(id);
	}

	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	public Jobs addJobs(@RequestParam String kind, @RequestParam String details, @RequestParam String phone,
			@RequestParam String time, @RequestParam String money, @RequestParam String remark,
			@RequestParam String amount, HttpServletRequest request) {

		/*
		 * Object obj = request.getSession().getAttribute("id"); String account
		 * = (String) obj; Object nm =
		 * request.getSession().getAttribute("name"); String name = (String) nm;
		 */
		User currentUser = getCurrentUser(request);
		String studentId = currentUser.getStudentId();
		String name = currentUser.getName();
		String authorAvater = currentUser.getAvatar();

		Jobs jobs = new Jobs();
		jobs.setTime(time);
		jobs.setKind(kind);
		jobs.setMoney(money);
		jobs.setPhone(phone);
		jobs.setRemark(remark);
		jobs.setDetails(details);
		jobs.setAmount(amount);
		jobs.setAccount(studentId);
		jobs.setName(name);
		jobs.setAuthorAvater(authorAvater);
		return jobsService.save(jobs);

	}

	@RequestMapping("/joins/{jobs_id}/release")
	public int countRelease(@PathVariable int jobs_id) {
		return joinsService.countReleases(jobs_id);
	}

	@RequestMapping("/joins/{jobs_id}/released")
	public boolean checkRelease(@PathVariable int jobs_id, HttpServletRequest request) {
		User me = getCurrentUser(request);
		return joinsService.checkLiked(me.getStudentId(), jobs_id);
	}

	@RequestMapping(value = "/joins/{jobs_id}/release", method = RequestMethod.POST)
	public int changeRelease(@PathVariable int jobs_id, @RequestParam boolean release, HttpServletRequest request) {
		User currentUser = getCurrentUser(request);
		Jobs jobs = jobsService.findOne(jobs_id);
		if (release) {
			joinsService.addRelease(currentUser, jobs);
		} else {
			joinsService.removeRelease(currentUser, jobs);
		}
		return joinsService.countReleases(jobs_id);

	}

	@RequestMapping("/book/{resume_id}/release")
	public int countBook(@PathVariable int resume_id) {
		return bookService.countBooks(resume_id);
	}

	@RequestMapping("/book/{resume_id}/released")
	public boolean checkBook(@PathVariable int resume_id, HttpServletRequest request) {
		User me = getCurrentUser(request);
		return bookService.checkBooked(me.getStudentId(), resume_id);
	}

	@RequestMapping(value = "/book/{resume_id}/release", method = RequestMethod.POST)
	public int changeBook(@PathVariable int resume_id, @RequestParam boolean release, HttpServletRequest request) {
		User me = getCurrentUser(request);
		Resume resume = resumeService.findOne(resume_id);
		if (release) {
			bookService.addBook(me, resume);
		} else {
			bookService.removeBook(me, resume);
		}
		return bookService.countBooks(resume_id);
	}

	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public Resume addResume(@RequestParam String name, @RequestParam String sex, @RequestParam String details,
			@RequestParam String time, @RequestParam String money, @RequestParam String phone,
			@RequestParam String area, MultipartFile avater, HttpServletRequest request) {
		/*
		 * Object obj = request.getSession().getAttribute("account"); String
		 * account = (String) obj;
		 */
		User currentUser = getCurrentUser(request);
		String studentId = currentUser.getStudentId();
		Resume resume = new Resume();
		resume.setAccount(studentId);
		resume.setArea(area);
		resume.setMoney(money);
		resume.setName(name);
		resume.setTime(time);
		resume.setDetails(details);
		resume.setSex(sex);
		resume.setPhone(phone);
		if (avater != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/ResumePicture");
				FileUtils.copyInputStreamToFile(avater.getInputStream(), new File(realPath, studentId + ".png"));
				resume.setAvater("ResumePicture/" + studentId + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resumeService.save(resume);
	}

	@RequestMapping(value = "/Jobs/{account}")
	public List<Jobs> getArticlesByUserID(@PathVariable String studentId) {
		return jobsService.findAllByAuthorAccount(studentId);
	}

	// page,是指可以获取url中的参数，然后根据这个数字进行翻页
	@RequestMapping(value = "/jobs/list/{page}")
	public Page<Jobs> getJobs(@PathVariable int page) {
		return jobsService.getJobs(page);
	}

	@RequestMapping("/jobs/list")
	public Page<Jobs> getFeeds() {
		return getJobs(0);
	}

	@RequestMapping(value = "/Resume/{account}")
	public List<Resume> getResumesByUserAccount(@PathVariable String studentId) {
		return resumeService.findAllByAuthorAccount(studentId);
	}

	@RequestMapping(value = "/resume/list/{page}")
	public Page<Resume> getResume(@PathVariable int page) {
		return resumeService.getResume(page);
	}

	@RequestMapping("/resume/list")
	public Page<Resume> getResume() {
		return getResume(0);
	}
	
	//找出当前登录用户发布的招聘
	// page,是指可以获取url中的参数，然后根据这个数字进行翻页
		@RequestMapping(value = "/releaseJobs/list/{page}")
		public Page<Jobs> releaseJobs(@PathVariable int page,
				String account) 
		{
			return jobsService.findJobsByAuthorAccount(page, account);
		}
		
		@RequestMapping("/releaseJobs/list")
		public Page<Jobs> getreleaseJobs(
				HttpServletRequest request) {
			User me=getCurrentUser(request);
			
			return releaseJobs(0,me.getStudentId());
		}
		//找出参加了发布者某份招聘的人的简历
		@RequestMapping("/resume/jobsId/{jobs_id}")
		public Page<Resume> findAllResumeByJobId(
				@PathVariable int jobs_id,
				@RequestParam(defaultValue ="0") int page)
		{
			return joinsService.getResumeByJobsId(jobs_id, page);
		}
	

}
