package com.cloudage.membercenter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.cloudage.membercenter.util.DateRecord;

@Entity
public class MyOrder extends DateRecord {
	
	User user;
	Goods goods;
	String money;
	int orderState;
    Boolean commentState;
    Boolean over;
    int BuyNumber;

	public int getBuyNumber() {
		return BuyNumber;
	}

	public void setBuyNumber(int buyNumber) {
		BuyNumber = buyNumber;
	}

	public Boolean getCommentState() {
		return commentState;
	}

	public void setCommentState(Boolean commentState) {
		this.commentState = commentState;
	}

	public Boolean getOver() {
		return over;
	}

	public void setOver(Boolean over) {
		this.over = over;
	}

	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(optional = false)
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}


}
