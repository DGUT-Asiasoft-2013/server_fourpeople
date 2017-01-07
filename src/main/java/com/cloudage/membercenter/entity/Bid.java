package com.cloudage.membercenter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.cloudage.membercenter.util.DateRecord;

@Entity
public class Bid extends DateRecord {
	User Bider;
	Auction auction;
	String price;
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
