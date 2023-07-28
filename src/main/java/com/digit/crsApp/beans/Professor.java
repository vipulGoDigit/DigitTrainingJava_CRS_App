package com.digit.crsApp.beans;

public class Professor {
	int pid;
	String pname;
	int exp;

	public Professor(int pid, String pname, int exp) {
		this.pid = pid;
		this.pname = pname;
		this.exp = exp;
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getExp() {
		return this.exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
}
