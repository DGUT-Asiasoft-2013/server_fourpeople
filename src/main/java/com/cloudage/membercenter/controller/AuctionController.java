package com.cloudage.membercenter.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Auction;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IAuctionsService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/auction")
public class AuctionController {
	
	@Autowired
	IAuctionsService iAuctionsService;
	@Autowired
	IUserService iUserSevice;

	@RequestMapping(value = "/addAuction", method = RequestMethod.POST)
	public Auction addAuction(
			@RequestParam String itemName, 
			@RequestParam String price,
			@RequestParam String method, 
			@RequestParam String introduction,
			@RequestParam String others,
			MultipartFile picture, HttpServletRequest request) {
		    Auction auction=new Auction();
		    auction.setAuctinner(getCurrentUser(request));
		    auction.setAuctionName(itemName);
		    auction.setIntroduction(introduction);
		    auction.setMethod(method);
		    auction.setPrice(price);
		    auction.setOthers(others);
		    
		
		if (picture != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/auctionPicture");
				FileUtils.copyInputStreamToFile(picture.getInputStream(), new File(realPath, itemName + ".png"));
				auction.setPicture("auctionPicture/" + itemName + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return iAuctionsService.save(auction);

	}
	
	/** 当前登录用户信息 **/
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("id");
		User user = iUserSevice.findById(uid);
		if (user != null) {
			return user;
		} else
			return null;

	}
	
	/**获取所有拍卖物品*/
	@RequestMapping(value = "/auctions/{page}")
	public Page<Auction> getFeeds(@PathVariable int page) {
		return iAuctionsService.getAuctions(page);
	}
	@RequestMapping(value = "/auctions")
	public Page<Auction> getFeeds() {
		return iAuctionsService.getAuctions(0);
	}





}
