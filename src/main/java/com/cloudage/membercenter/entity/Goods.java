package com.cloudage.membercenter.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.cloudage.membercenter.util.BaseEntity;

@Entity
public class Goods extends BaseEntity {

	String goodsName;
	String goodsPiece;
	String goodsNumber;
	String goodsAbout;
	String goodsAvatar;
	Mall mall;
	Date createDate;
	Date editDate;
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

	@ManyToOne(optional = false)
	public Mall getMall() {
		return mall;
	}

	public void setMall(Mall mall) {
		this.mall = mall;
	}

	@Column(nullable = false)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(nullable = false)
	public String getGoodsPiece() {
		return goodsPiece;
	}

	public void setGoodsPiece(String goodsPiece) {
		this.goodsPiece = goodsPiece;
	}

	@Column(nullable = false)
	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	@Column(nullable = true)
	public String getGoodsAbout() {
		return goodsAbout;
	}

	public void setGoodsAbout(String goodsAbout) {
		this.goodsAbout = goodsAbout;
	}

	@Column(nullable = true)
	public String getGoodsAvatar() {
		return goodsAvatar;
	}

	public void setGoodsAvatar(String goodsAvatar) {
		this.goodsAvatar = goodsAvatar;
	}
	@PreUpdate
	void onPreUpdate() {
		editDate = new Date();
	}

	@PrePersist
	void onPrePersist() {
		createDate = new Date();
		editDate=new Date();
	}
}
