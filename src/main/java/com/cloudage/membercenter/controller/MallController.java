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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Car;
import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.ICarService;
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
	@Autowired
	ICarService iCarService;

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
			@RequestParam String shopAbout,
			MultipartFile shopAvatar,
			HttpServletRequest httpServletRequest){
		User currentUser = getCurrentUser(httpServletRequest);
		Mall mall=new Mall();
		mall.setShopName(shopName);
		mall.setShopType(shopType);
		mall.setUser(currentUser);
		mall.setShopAbout(shopAbout);
		mall.setShopLiked(0);
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
	    Mall mall=iMallService.findMallByUserId(currentUser.getId());
	    Goods goods=new Goods();
	    goods.setGoodsName(goodsName);
	    goods.setGoodsPiece(goodsPiece);
	    goods.setGoodsNumber(goodsNumber);
	    goods.setGoodsAbout(goodsAbout);
	    goods.setMall(mall);
	    goods.setGoodsLiked(0);
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
	
	@RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
	public Boolean delete(
			@RequestParam String id){
		Integer goodsId=Integer.valueOf(id);
		if(iGoodsService.delete(goodsId)){
			return true;
		}else{
			return false;
		}
	}

	@RequestMapping(value = "/goods/dispose", method = RequestMethod.POST)
	public Goods dispose(
			@RequestParam String id, 
			@RequestParam String goodsName, 
			@RequestParam String goodsPiece,
			@RequestParam String goodsNumber, 
			@RequestParam String goodsAbout) {
		Integer goodsId = Integer.valueOf(id);
		Goods goods = iGoodsService.findGoodsById(goodsId);
		goods.setGoodsName(goodsName);
		goods.setGoodsPiece(goodsPiece);
		goods.setGoodsNumber(goodsNumber);
		goods.setGoodsAbout(goodsAbout);
		return iGoodsService.save(goods);
	}
	
	@RequestMapping("/shop/shopcenter")
	public Mall getMall(
			HttpServletRequest httpServletRequest){
		User currentUser=getCurrentUser(httpServletRequest);
		Integer id=currentUser.getId();
		return iMallService.findMallByUserId(id);
	}
	
	@RequestMapping("/shop/show/{page}")
	public Page<Mall> getMall(
			@PathVariable int page) {
		return iMallService.getMall(page);
	}

	@RequestMapping("/shop/show")
	public Page<Mall> getMall() {
		return getMall(0);
	}
	
	@RequestMapping(value = "/shop/goods/show/{page}", method = RequestMethod.POST)
	public Page<Goods> getGoodsByMall(
			@RequestParam String id,
			@PathVariable int page) {
		Integer mallId=Integer.valueOf(id);
		return iGoodsService.getGoodsByMall(mallId, page);
	}

	@RequestMapping(value = "/shop/goods/show", method = RequestMethod.POST)
	public Page<Goods> getGoodsByMall(
			@RequestParam String id) {
		return getGoodsByMall(id, 0);
	}
	
	@RequestMapping(value = "/shop/goods/addCart", method = RequestMethod.POST)
	public Boolean buildCar(
			@RequestParam String goodsId,
			HttpServletRequest httpServletRequest) {
		Integer currentGoodsId = Integer.valueOf(goodsId);
		Goods goods = iGoodsService.findGoodsById(currentGoodsId);
		User currnetUser = getCurrentUser(httpServletRequest);
		Car car = new Car();
		car.setGoods(goods);
		car.setChoice(false);
		car.setCustomerId(currnetUser.getId());
		if(!iCarService.check(currnetUser.getId(),currentGoodsId)){
			iCarService.save(car);
			return true;
		}else{
			return false;
		}
	}
	@RequestMapping("/shop/goods/getCart")
	public List<Car> getCart(
			HttpServletRequest httpServletRequest){
		User currentUser=getCurrentUser(httpServletRequest);
		List<Car> car=iCarService.findCarByUserId(currentUser.getId());
	    return car;
	}
	
	@RequestMapping(value = "/shop/goods/deleteCart", method = RequestMethod.POST)
	public Boolean deleteCar(
			@RequestParam String carId) {
		Integer cartId = Integer.valueOf(carId);
		return iCarService.deleteCarById(cartId);
	}
	
}
	
