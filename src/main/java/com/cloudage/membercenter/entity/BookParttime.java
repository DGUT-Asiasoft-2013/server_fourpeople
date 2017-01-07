package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
@Entity
public class BookParttime {
	@Embeddable
	public static class Book implements Serializable
	{
		User user;
		Resume resume;
		@ManyToOne(optional=false)
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@ManyToOne(optional=false)
		public Resume getResume() {
			return resume;
		}
		public void setResume(Resume resume) {
			this.resume = resume;
		}
		
	}
	Book id;
	Date createDate;
	@EmbeddedId
	public Book getId() {
		return id;
	}
	public void setId(Book id) {
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
