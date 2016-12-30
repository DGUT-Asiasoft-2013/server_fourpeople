package com.cloudage.membercenter.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User extends BaseEntity {
	String studentId;
	String name;
	String passwordHash;
	String sex;
	String email;
	String address;
	String tel;
	String avatar;
	String balance;  //余额
	
//	List<Resume> resumes;
//	@OneToMany
//	public List<Resume> resumes(){
//		return resumes;
//	}
	
	@Column(nullable = false)
	public String getBalance() {
		return balance;
	}

	
	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Column(nullable = false, unique = true)
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(nullable = false)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(nullable = true)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(nullable = false)
	@JsonIgnore
	public String getPasswordHash() {
		return passwordHash;
	}

	@Column(unique = false)
	public String getName() {
		return name;
	}

	@Column(nullable = true)
	public String getAvatar() {
		return avatar;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
