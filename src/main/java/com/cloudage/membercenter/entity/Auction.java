package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.cloudage.membercenter.util.BaseEntity;

@Entity
public class Auction extends BaseEntity {

	User auctinner;
	Date createDate;
	Date editDate;
	String introduction;
	String auctionName;
	String picture;
	String price;
	String method;
	String others;
	String days;
	Boolean isAuctioning;

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Boolean getIsAuctioning() {
		return isAuctioning;
	}

	public void setIsAuctioning(Boolean isAuctioning) {
		this.isAuctioning = isAuctioning;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@ManyToOne(optional = false)
	public User getAuctinner() {
		return auctinner;
	}

	public void setAuctinner(User auctinner) {
		this.auctinner = auctinner;
	}

	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@PreUpdate
	void onPreUpdate() {
		editDate = new Date();
	}

	@PrePersist
	void onPrePersist() {
		createDate = new Date();
		editDate = new Date();
		isAuctioning=true;
	}

}
