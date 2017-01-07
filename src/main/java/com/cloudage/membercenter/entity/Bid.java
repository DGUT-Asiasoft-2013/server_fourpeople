package com.cloudage.membercenter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.cloudage.membercenter.util.DateRecord;

@Entity
public class Bid extends DateRecord {
	User Bider;
	Auction auction;
	String price;
	String count;
	public Boolean getMethodIsPrice() {
		return methodIsPrice;
	}

	public void setMethodIsPrice(Boolean methodIsPrice) {
		this.methodIsPrice = methodIsPrice;
	}

	Boolean methodIsPrice;
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@ManyToOne(optional = false)
	public User getBider() {
		return Bider;
	}

	public void setBider(User bider) {
		Bider = bider;
	}	

	@ManyToOne(optional = false)
	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
