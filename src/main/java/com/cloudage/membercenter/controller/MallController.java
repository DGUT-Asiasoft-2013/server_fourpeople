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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IGoodsService;
import com.cloudage.membercenter.service.IMallService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/mall")
public class MallController {
	@Autowired
	IMallService iMallService;
	@Autowired
	IUserService iUserService;
	@Autowired
	IGoodsService iGoodsService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public @ResponseBody String hello() {
		return "HELLO WORLD Mall !";
	}
//获得当前用户
	private User getCurrentUser(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession(true);
		Integer id = (Integer) httpSession.getAttribute("id");
		return iUserService.findById(id);
	}
	
	@RequestMapping(value = "/open", method = RequestMethod.POST)
	public Mall open(
			@RequestParam String shopName,   
			@RequestParam String shopType,
			MultipartFile shopAvatar,
			HttpServletRequest httpServletRequest){
		User currentUser = getCurrentUser(httpServletRequest);
		Mall mall=new Mall();
		mall.setShopName(shopName);
		mall.setShopType(shopType);
		mall.setUser(currentUser);
		if (shopAvatar!= null) {
			try {
				String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/shopUpload");
				FileUtils.copyInputStreamToFile(shopAvatar.getInputStream(), new File(realPath, shopName + ".png"));
				mall.setShopAvatar("shopUpload/" + shopName + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return iMallService.save(mall);
	}
	
	@RequestMapping(value = "/ishaveshop", method = RequestMethod.GET)
	public Boolean ishaveshop(HttpServletRequest httpServletRequest) {
		User currentUser = getCurrentUser(httpServletRequest);
		if (iMallService.ishavashop(currentUser)!=null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/goods/push", method = RequestMethod.POST)
	public Goods push(
			@RequestParam String goodsName,   
			@RequestParam String goodsPiece,
			@RequestParam String goodsNumber,   
			@RequestParam String goodsAbout,
			MultipartFile goodsAvatar,
			HttpServletRequest httpServletRequest){
		User currentUser = getCurrentUser(httpServletRequest);
	    Mall mall=iMallService.getMall(currentUser.getId());
	    Goods goods=new Goods();
	    goods.setGoodsName(goodsName);
	    goods.setGoodsPiece(goodsPiece);
	    goods.setGoodsNumber(goodsNumber);
	    goods.setGoodsAbout(goodsAbout);
	    goods.setMall(mall);
		if (goodsAvatar!= null) {
			try {
				String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/goodsUpload");
				FileUtils.copyInputStreamToFile(goodsAvatar.getInputStream(), new File(realPath, mall.getShopName()+goodsName + ".png"));
				goods.setGoodsAvatar("goodsUpload/" + mall.getShopName()+goodsName + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return iGoodsService.save(goods);
	}
	
	@RequestMapping("/goods/show/{page}")
	public Page<Goods> getGoods(
			HttpServletRequest httpServletRequest,
			@PathVariable int page) {
		User currentUser=getCurrentUser(httpServletRequest);
		return iGoodsService.getGoods(currentUser.getId(),page);
	}

	@RequestMapping("/goods/show")
	public Page<Goods> getGoods(
			HttpServletRequest httpServletRequest) {
		return getGoods(httpServletRequest,0);
	}
	
}
