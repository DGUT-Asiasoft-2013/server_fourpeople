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
import com.cloudage.membercenter.entity.Bid;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IAuctionsService;
import com.cloudage.membercenter.service.IBidService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/auction")
public class AuctionController {

	@Autowired
	IAuctionsService iAuctionsService;
	@Autowired
	IUserService iUserSevice;
	@Autowired
	IBidService iBidSevice;

	@RequestMapping(value = "/addAuction", method = RequestMethod.POST)
	public Auction addAuction(@RequestParam String itemName, @RequestParam String price, @RequestParam String method,
			@RequestParam String introduction, @RequestParam String others, @RequestParam String days,
			MultipartFile picture, HttpServletRequest request) {
		Auction auction = new Auction();
		auction.setAuctinner(getCurrentUser(request));
		auction.setAuctionName(itemName);
		auction.setIntroduction(introduction);
		auction.setMethod(method);
		auction.setPrice(price);
		auction.setOthers(others);
		auction.setDays(days);

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

	/** 获取所有拍卖物品 */
	@RequestMapping(value = "/auctions/{page}")
	public Page<Auction> getAuctions(@PathVariable int page) {
		return iAuctionsService.getAuctions(page);
	}

	@RequestMapping(value = "/auctions")
	public Page<Auction> getAuctions() {
		autoCheck();
		return iAuctionsService.getAllAuctions(0);
	}

	@RequestMapping(value = "/auction/check")
	public void autoCheck() {
		Page<Auction> auctions = iAuctionsService.getAllAuctions(0);
		long s2 = System.currentTimeMillis();// 得到当前的毫秒
		for (Auction auction : auctions) {
			// 将字符串转为日期
			java.util.Date date = auction.getCreateDate();
			long s1 = date.getTime();// 将时间转为毫秒
			long day = (s2 - s1) / 1000 / 60 / 60 / 24;
			if (!(auction.getDays().equals(""))) {
				int days = Integer.parseInt(auction.getDays());
				if (auction.getIsAuctioning() && day > days) {
					auction.setIsAuctioning(false);
					iAuctionsService.save(auction);
				}
			}

		}

	}

	@RequestMapping(value = "/bid", method = RequestMethod.POST)
	public Bid addBid(@RequestParam String price, @RequestParam String auctionId,
			HttpServletRequest request) {
		Auction auction = iAuctionsService.findAuction(Integer.parseInt(auctionId));
		User bider = getCurrentUser(request);
		Bid bid = new Bid();
		bid.setAuction(auction);
		bid.setBider(bider);
		bid.setPrice(price);

		return iBidSevice.save(bid);

	}
	
	@RequestMapping(value = "/bid/counts/{auctionId}")
	public Integer countBidNumber(@PathVariable String auctionId) {
		return iBidSevice.countBidNumber(Integer.parseInt(auctionId));
	}
	
	@RequestMapping(value = "/auctions/my")
	public Page<Auction> getMyAuctions(HttpServletRequest request) {
		autoCheck();
		User user=getCurrentUser(request);
		return iAuctionsService.getMyAction(user.getId(),0);
	}
}
