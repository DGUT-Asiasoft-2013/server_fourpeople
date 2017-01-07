package com.cloudage.membercenter.controller;

import java.io.File;
import java.util.List;

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
import com.cloudage.membercenter.entity.Transaction;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IAuctionsService;
import com.cloudage.membercenter.service.IBidService;
import com.cloudage.membercenter.service.ITransationService;
import com.cloudage.membercenter.service.IUserService;
import com.mysql.jdbc.log.Log;

@RestController
@RequestMapping("/auction")
public class AuctionController {

	@Autowired
	IAuctionsService iAuctionsService;
	@Autowired
	IUserService iUserSevice;
	@Autowired
	IBidService iBidSevice;
	@Autowired
	ITransationService iTransactionService;

	@RequestMapping(value = "/addAuction", method = RequestMethod.POST)
	public Auction addAuction(@RequestParam String itemName, @RequestParam String price, @RequestParam String method,
			@RequestParam String introduction, @RequestParam String others, @RequestParam String days,
			@RequestParam String bidPrice, MultipartFile picture, HttpServletRequest request) {
		Auction auction = new Auction();
		auction.setAuctinner(getCurrentUser(request));
		auction.setAuctionName(itemName);
		auction.setIntroduction(introduction);
		auction.setMethod(method);
		auction.setPrice(price);
		auction.setOthers(others);
		auction.setDays(days);
		auction.setBidPrice(bidPrice);
		auction.setStateInfo("正在拍卖");

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
		return iAuctionsService.getAuctions(0);
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
					auction.setStateInfo("拍卖过期");
					iAuctionsService.save(auction);
				}
			}

		}

	}

	@RequestMapping(value = "/bid", method = RequestMethod.POST)
	public Bid addBid(@RequestParam String price, @RequestParam String auctionId, @RequestParam String type,
			HttpServletRequest request) {
		Bid bid = null;
		Auction auction = iAuctionsService.findAuction(Integer.parseInt(auctionId));
		User bider = getCurrentUser(request);
		if (type.equals("1")) {
			bid = new Bid();
			bid.setPrice(price);
			bid.setMethodIsPrice(false);
		} else {
			bid = iBidSevice.findByAuctionId(Integer.parseInt(auctionId));
			if (bid == null) {
				bid = new Bid();
				bid.setPrice(auction.getPrice());
				bid.setCount("1");
			} else {
				bid.setCount(String.valueOf(Integer.parseInt(bid.getCount()) + 1));
				int auctionPrice = Integer.parseInt(bid.getPrice()) + Integer.parseInt(auction.getBidPrice());
				bid.setPrice(String.valueOf(auctionPrice));
			}
			bid.setMethodIsPrice(true);
		}
		bid.setAuction(auction);
		bid.setBider(bider);

		return iBidSevice.save(bid);

	}

	@RequestMapping(value = "/bid/{auctionId}")
	public Bid findByAuctionId(@PathVariable String auctionId) {
		return iBidSevice.findByAuctionId(Integer.parseInt(auctionId));
	}

	@RequestMapping(value = "/mybid/{auctionId}")
	public List<Bid> findBidsByAuctionId(@PathVariable String auctionId) {
		return iBidSevice.findBidByAuctionId(Integer.parseInt(auctionId));
	}

	@RequestMapping(value = "/bid/counts/{auctionId}")
	public Integer countBidNumber(@PathVariable String auctionId) {
		Bid bid = iBidSevice.findByAuctionId(Integer.parseInt(auctionId));
		return iBidSevice.countBidNumber(Integer.parseInt(auctionId)) + Integer.parseInt(bid.getCount()) - 1;
	}

	@RequestMapping(value = "/auctions/my")
	public Page<Auction> getMyAuctions(HttpServletRequest request) {
		autoCheck();
		User user = getCurrentUser(request);
		return iAuctionsService.getMyAction(user.getId(), 0);
	}

	
	
	@RequestMapping(value = "/transaction/", method = RequestMethod.POST)
	public Transaction getTransaction(@RequestParam String bidId, HttpServletRequest request) {
		User auctionner = getCurrentUser(request);
		Bid bid = iBidSevice.findById(Integer.parseInt(bidId));
		Transaction transaction = new Transaction();
		transaction.setBid(bid);
		transaction.setAuctionner(auctionner);
		transaction.setState("正在交易");
		transaction.setIsFinish(false);
		Transaction result = iTransactionService.save(transaction);
		if (result != null) {
			Auction auction = bid.getAuction();
			auction.setIsAuctioning(false);
			auction.setStateInfo("正在交易");
			iAuctionsService.save(auction);
		}
		return result;
	}

	@RequestMapping(value = "/findbid/{auctionId}")
	public Bid findById(@PathVariable String auctionId) {
		return iBidSevice.findById(Integer.parseInt(auctionId));
	}

	@RequestMapping(value = "/findtransaction/")
	public List<Transaction> findAllTransactionById(HttpServletRequest request) {
		User bidder = getCurrentUser(request);
		return iTransactionService.findAllTransactionById(bidder.getId());
	}
	
	@RequestMapping(value = "/begintransaction/{transactionId}")
	public Transaction beginTransaction(@PathVariable String transactionId) {
		Transaction transaction=iTransactionService.findTransactionById(Integer.parseInt(transactionId));
		transaction.setState("交易中");
		Transaction result=iTransactionService.save(transaction);
		if(result!=null){
			Auction auction=result.getBid().getAuction();
			auction.setStateInfo("交易中");
			iAuctionsService.save(auction);
		}
		return result;
	}
	

	
	@RequestMapping(value = "/finishtransaction/{auctionId}")
	public Transaction finishTransaction(@PathVariable String auctionId,HttpServletRequest request) {
		//Transaction transaction=iTransactionService.findTransactionById(Integer.parseInt(transactionId));
		Transaction transaction=findTransactionByAuctionId(auctionId);
		User user=getCurrentUser(request);
		Integer id=user.getId();
		System.out.println(id);
		String state=transaction.getState();
		if(state.equals("交易中")){
			if(id==transaction.getAuctionner().getId()){
				transaction.setState("卖方确认");
			}else if(id==transaction.getBid().getBider().getId()){
				transaction.setState("买家确认");
			}
		}else{
			if(id==transaction.getBid().getBider().getId()&&state.equals("卖方确认")){
				transaction.setState(state+"买家确认");
			}else if(id==transaction.getAuctionner().getId()&&state.equals("买家确认")){
				transaction.setState(state+"卖方确认");
			}
			
		}	
		Transaction item=iTransactionService.save(transaction);
		if(item!=null){
			if(item.getState().equals("卖方确认买家确认")||item.getState().equals("买家确认卖方确认")){
				item.setIsFinish(true);
				Auction auction=item.getBid().getAuction();
				auction.setStateInfo("完成交易");
				iAuctionsService.save(auction);
			}
		}	
		return iTransactionService.save(item);
	}
	
	@RequestMapping(value = "/findfinishtransaction/")
	public List<Transaction> findAllFinishTransactionById(HttpServletRequest request) {
		User bidder = getCurrentUser(request);
		return iTransactionService.findAllFinishTransactionById(bidder.getId());
	}
	@RequestMapping(value = "/findfinishauction/")
	public List<Transaction> findAllFinishAuctionById(HttpServletRequest request) {
		User auctionner = getCurrentUser(request);
		return iTransactionService.findAllFinishActionById(auctionner.getId());
	}
	
	

	@RequestMapping(value = "/findtransaction/{auctionId}")
	public Transaction findTransactionByAuctionId(@PathVariable String auctionId) {
		return iTransactionService.findTransactionByAuctionId(Integer.parseInt(auctionId));
	}

}
