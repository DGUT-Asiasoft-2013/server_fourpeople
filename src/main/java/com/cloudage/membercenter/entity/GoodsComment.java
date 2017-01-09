package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.cloudage.membercenter.util.DateRecord;

@Entity
public class GoodsComment extends DateRecord {

	User user;
	String text;
	Goods goods;
	Date myOrderDate;
		
	public Date getMyOrderDate() {
		return myOrderDate;
	}

	public void setMyOrderDate(Date myOrderDate) {
		this.myOrderDate = myOrderDate;
	}

	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ManyToOne(optional = false)
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}
