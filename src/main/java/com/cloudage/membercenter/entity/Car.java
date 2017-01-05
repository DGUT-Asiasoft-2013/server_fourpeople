package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.cloudage.membercenter.util.BaseEntity;

@Entity
public class Car extends BaseEntity {
	Goods goods;
	Date createDate;
	Date editDate;
	Integer customerId;
	Boolean choice;
	int buyNumber;


	public int getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(int buyNumber) {
		this.buyNumber = buyNumber;
	}

	public Boolean getChoice() {
		return choice;
	}

	public void setChoice(Boolean choice) {
		this.choice = choice;
	}

	@ManyToOne(optional = false)
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
