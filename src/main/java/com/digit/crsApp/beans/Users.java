package com.digit.crsApp.beans;

import com.crsApp.CRSApp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {
	static String user_name;
	static String password;
	private static PreparedStatement pstmt;
	private static ResultSet res;

	public Users(String user_name, String password) {
		Users.user_name = user_name;
		Users.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		Users.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Users.password = password;
	}

	public static String login(String s) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("\033[0;1m"+"Enter the user_name:"+"\033[0;0m");
			user_name = sc.next();
			System.out.println("\033[0;1m"+"Enter the password:"+"\033[0;0m");
			password = sc.next();
			String sql;
			if (s.equals("admin")) {
				sql = "select * from users where user_name=? and password=?";
				pstmt = CRSApp.con.prepareStatement(sql);
			} else if (s.equals("professor")) {
				sql = "select * from professor where user_name=? and password=?";
				pstmt = CRSApp.con.prepareStatement(sql);
			} else {
				sql = "select * from student where user_name=? and password=?";
				pstmt = CRSApp.con.prepareStatement(sql);
			}

			pstmt.setString(1, user_name);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			return res.next() ? user_name : "false";
		} catch (Exception var3) {
			var3.printStackTrace();
			var3.getMessage();
			return "false";
		}
	}
}
