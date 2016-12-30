package com.cloudage.membercenter.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class APIController {
	@Autowired
	IUserService iUserService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)

	public @ResponseBody String hello(HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		return "好久不见，你还好吗？";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User register(
			@RequestParam String studentId, 
			@RequestParam String name,
			@RequestParam String passwordHash,
			@RequestParam String sex, 
			@RequestParam String email, 
			@RequestParam String address,
			@RequestParam String tel,
			@RequestParam String balance,
			MultipartFile avatar,
			HttpServletRequest request) {
		User user = new User();
		user.setStudentId(studentId);
		user.setName(name);
		user.setPasswordHash(passwordHash);
		user.setSex(sex);
		user.setEmail(email);
		user.setAddress(address);
		user.setTel(tel);
		user.setBalance(balance);
		if (avatar != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realPath, studentId + ".png"));
				user.setAvatar("upload/" + studentId + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return iUserService.save(user);

	}

	@RequestMapping(value = "/passwordrecover", method = RequestMethod.POST)
	public Boolean passwordrecover(
			@RequestParam String email, 
			@RequestParam String passwordHash) {
		User user = iUserService.findByEmail(email);
		if (user != null) {
			user.setPasswordHash(passwordHash);
			iUserService.save(user);
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(
			@RequestParam String studentId,
			@RequestParam String passwordHash,
			HttpServletRequest httpServletRequest) {
		User user = iUserService.findByStudentId(studentId);
		if (user != null && user.getPasswordHash().equals(passwordHash)) {
			HttpSession httpSession = httpServletRequest.getSession(true);
			httpSession.setAttribute("id", user.getId());
			return user;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	private User getCurrentUser(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession(true);
		Integer id = (Integer) httpSession.getAttribute("id");
		return iUserService.findById(id);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public User modify(
			@RequestParam String name, 
			@RequestParam String sex,
			@RequestParam String address,
			@RequestParam String tel,
			MultipartFile avatar, HttpServletRequest request) {
		User user = getCurrentUser(request);
		user.setName(name);
		user.setSex(sex);
		user.setAddress(address);
		user.setTel(tel);
		if (avatar != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realPath, user.getStudentId() + ".png"));
				user.setAvatar("upload/" + user.getStudentId() + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return iUserService.save(user);
	}
	
	@RequestMapping(value = "/charge", method = RequestMethod.POST)
	public User charge(
			@RequestParam String balance, HttpServletRequest request) {
		User user = getCurrentUser(request);
		user.setBalance( String.valueOf( Double.parseDouble(user.getBalance()) + Double.parseDouble(balance) ) );

		return iUserService.save(user);
	}
}
