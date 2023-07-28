package com.digit.crsApp.beans;

public class Course {
	int cid;
	String cname;
	int fees;
	int dur_months;

	public Course(int cid, String cname, int fees, int dur_months) {
		this.cid = cid;
		this.cname = cname;
		this.fees = fees;
		this.dur_months = dur_months;
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getFees() {
		return this.fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public int getDur_months() {
		return this.dur_months;
	}

	public void setDur_months(int dur_months) {
		this.dur_months = dur_months;
	}
}
