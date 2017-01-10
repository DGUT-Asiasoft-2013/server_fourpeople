package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class MallLike {

	@Embeddable
	public static class Key implements Serializable {
		User user;
		Mall mall;

		@ManyToOne(optional = false)
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@ManyToOne(optional = false)
		public Mall getMall() {
			return mall;
		}

		public void setMall(Mall mall) {
			this.mall = mall;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if (obj instanceof Key) {
				Key other = (Key) obj;
				return mall.getId() == other.mall.getId() && user.getId() == other.user.getId();
			}
			return false;
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return mall.getId();
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

	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@PrePersist
	void onPrePersist() {
		createDate = new Date();
	}

}
