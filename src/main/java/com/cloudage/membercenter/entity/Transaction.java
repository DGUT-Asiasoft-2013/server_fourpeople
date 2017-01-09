package com.cloudage.membercenter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.cloudage.membercenter.util.DateRecord;


@Entity
public class Transaction extends DateRecord {
	User auctionner;
	Bid bid;
	String state;
	public Boolean getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(Boolean isFinish) {
		this.isFinish = isFinish;
	}
	Boolean isFinish;
	@ManyToOne(optional = false)
	public User getAuctionner() {
		return auctionner;
	}
	public void setAuctionner(User auctionner) {
		this.auctionner = auctionner;
	}
	@OneToOne
	public Bid getBid() {
		return bid;
	}
	public void setBid(Bid bid) {
		this.bid = bid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
