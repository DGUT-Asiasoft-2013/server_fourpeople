package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Engage {
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
	Date creataDate;
	@EmbeddedId
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Date getCreataDate() {
		return creataDate;
	}
	public void setCreataDate(Date creataDate) {
		this.creataDate = creataDate;
	}
	

	

}
