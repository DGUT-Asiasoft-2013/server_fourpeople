package com.cloudage.membercenter.entity;

import javax.persistence.Entity;

import com.cloudage.membercenter.util.DateRecord;
@Entity
public class InterviewRequest extends DateRecord{
	String time;
	String area;
	String phone;
	String remark;
	String interviewer;
	String employer;
	String authorAvater;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInterviewer() {
		return interviewer;
	}
	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getAuthorAvater() {
		return authorAvater;
	}
	public void setAuthorAvater(String authorAvater) {
		this.authorAvater = authorAvater;
	}
	


}
