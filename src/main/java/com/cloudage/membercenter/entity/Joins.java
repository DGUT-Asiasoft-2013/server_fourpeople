package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.cloudage.membercenter.util.DateRecord;

@Entity
public class Joins {
	@Embeddable
	public static class Key implements Serializable
	{
		User user;
        Jobs jobs;
    	@ManyToOne(optional=false)
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@ManyToOne(optional=false)
		public Jobs getJobs() {
			return jobs;
		}
		public void setJobs(Jobs jobs) {
			this.jobs = jobs;
		}
		
	}
	Key id;
	Date createDate;
	@EmbeddedId
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@PreUpdate
	void onPreUpdate()
	{
		createDate=new Date();
	}
	@PrePersist
	void onPrePersist()
	{
		createDate=new Date();
				
	}

}
