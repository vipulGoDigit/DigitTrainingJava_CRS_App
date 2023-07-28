package com.digit.crsApp.beans;

public class Student {
	int sid;
	String sname;
	int age;
	String email;
	String course;
	String score;
	String pid;
	String user_name;
	String password;

	public Student(int sid, String sname, int age, String email, String course, String score, String pid,
			String user_name, String password) {
		this.sid = sid;
		this.sname = sname;
		this.age = age;
		this.email = email;
		this.course = course;
		this.score = score;
		this.pid = pid;
		this.user_name = user_name;
		this.password = password;
	}

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUser_name() {
		return this.user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
