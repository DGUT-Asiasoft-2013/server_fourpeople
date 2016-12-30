package com.cloudage.membercenter.entity;

import javax.persistence.Entity;

import com.cloudage.membercenter.util.DateRecord;

@Entity
public class Joins extends DateRecord{
	String  joinsId;
	String userAccount;
	long count;
	
	public String getJoinsId() {
		return joinsId;
	}
	public void setJoinsId(String joinsId) {
		this.joinsId = joinsId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	

}
