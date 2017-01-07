package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.cloudage.membercenter.util.BaseEntity;

@Entity
public class Mall extends BaseEntity {
	String shopName;
	String shopType;
	String shopAvatar;
	User user;
	Date createDate;
	Date editDate;
	String shopAbout;
	int shopLiked;

	public String getShopAbout() {
		return shopAbout;
	}

	public void setShopAbout(String shopAbout) {
		this.shopAbout = shopAbout;
	}

	public int getShopLiked() {
		return shopLiked;
	}

	public void setShopLiked(int shopLiked) {
		this.shopLiked = shopLiked;
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

	@Column(unique = true, nullable = false)
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(nullable = false)
	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	@Column(nullable = true)
	public String getShopAvatar() {
		return shopAvatar;
	}

	public void setShopAvatar(String shopAvatar) {
		this.shopAvatar = shopAvatar;
	}

	@OneToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PreUpdate
	void onPreUpdate() {
		editDate = new Date();
	}

	@PrePersist
	void onPrePersist() {
		createDate = new Date();
		editDate = new Date();
	}

}
