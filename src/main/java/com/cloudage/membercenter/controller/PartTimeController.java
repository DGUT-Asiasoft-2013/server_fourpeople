package com.cloudage.membercenter.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	@RequestMapping(value="/jobs",method=RequestMethod.POST)
	public Jobs addJobs(
			@RequestParam String kind,
			@RequestParam String details,
			@RequestParam String phone,
			@RequestParam String time,
			@RequestParam String money,
			@RequestParam String remark,
			@RequestParam String amount,
			HttpServletRequest request)
	{
		
		Object obj=request.getSession().getAttribute("id");
		String account=(String)obj;
		Object nm=request.getSession().getAttribute("name");
		String name=(String)nm;
	
		Jobs jobs=new Jobs();
		jobs.setTime(time);
		jobs.setKind(kind);
		jobs.setMoney(money);
		jobs.setPhone(phone);
		jobs.setRemark(remark);
		jobs.setDetails(details);
		jobs.setAmount(amount);
		jobs.setAccount(account);
		jobs.setName(name);
		return jobsService.save(jobs); 

	}
	@RequestMapping(value="/joins",method=RequestMethod.POST)
	public Joins addJoins(
			@RequestParam String joinsId,
			HttpServletRequest request)
	{
		Object obj=request.getSession().getAttribute("account");
		String account=(String)obj;
		Joins joins=new Joins();
		joins.setJoinsId(joinsId);
		joins.setUserAccount(account);
		return joinsService.save(joins);
		
	}
		
	
	@RequestMapping(value="/resume",method=RequestMethod.POST)
	public Resume addResume(
			@RequestParam String name,
			@RequestParam String sex,
			@RequestParam String details,
			@RequestParam String time,
			@RequestParam String money,
			@RequestParam String phone,
			@RequestParam String area,
			MultipartFile avater,
			HttpServletRequest request)
	{
		Object obj=request.getSession().getAttribute("account");
		String account=(String)obj;
		Resume resume=new Resume();
		resume.setAccount(account);
		resume.setArea(area);
		resume.setMoney(money);
		resume.setName(name);
		resume.setTime(time);
		resume.setDetails(details);
		resume.setSex(sex);
		resume.setPhone(phone);
		if(avater !=null)
		{
			try
			{
				String realPath=request.getSession().getServletContext().getRealPath("/WEB-INF/ResumePicture");
				FileUtils.copyInputStreamToFile(avater.getInputStream(), new File(realPath,account+".png"));
				resume.setAvater("ResumePicture/"+account+".png");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return resumeService.save(resume);
	}
	
	@RequestMapping(value="/Jobs/{account}")
	public List<Jobs>getArticlesByUserID(@PathVariable String  account)
	{
		return jobsService.findAllByAuthorAccount(account);
	}
	//page,是指可以获取url中的参数，然后根据这个数字进行翻页
		@RequestMapping(value="/jobs/list/{page}")
		public  Page<Jobs> getJobs(@PathVariable int page)
		{
			return jobsService.getJobs(page);
		}
		@RequestMapping("/jobs/list")
		public Page<Jobs> getFeeds()
		{
			return getJobs(0);
		}

		@RequestMapping(value="/Resume/{account}")
		public List<Resume>getResumesByUserAccount(@PathVariable String account)
		{
			return resumeService.findAllByAuthorAccount(account);
		}
		@RequestMapping(value="/resume/list/{page}")
		public Page<Resume>getResume(@PathVariable int page)
		{
			return resumeService.getResume(page);
		}
		@RequestMapping("/resume/list")
		public Page<Resume>getResume()
		{
			return getResume(0);
		}


	
}
