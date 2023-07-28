package com.digit.crsApp.service;

import com.crsApp.CRSApp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentServices {
	private PreparedStatement pstmt;
	private PreparedStatement pstmt1;
	private Statement stmt;
	private ResultSet resultset;
	private ResultSet res;

	public StudentServices() {
	}

	public void availablecourses() {
		try {
			String sql = "select * from course";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql);

			while (this.resultset.next()) {
				System.out.println(this.resultset.getString(1) + ". " + this.resultset.getString(2));
			}
		} catch (Exception var2) {
			var2.getMessage();
		}

	}

	public void studrequest() {
		try {
			String sql = "insert into studentrequest values(?,?,?,?,?,?,?)";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter your name : ");
			String name = sc.nextLine();
			this.pstmt.setString(1, name);
			System.out.println("Enter your age : ");
			this.pstmt.setInt(2, Integer.parseInt(sc.nextLine()));
			System.out.println("Enter your email : ");
			this.pstmt.setString(3, sc.nextLine());
			System.out.println("Enter course id : ");
			this.pstmt.setInt(4, Integer.parseInt(sc.nextLine()));
			System.out.println("Selected course : ");
			this.pstmt.setString(5, sc.nextLine());
			String u = "";

			while (true) {
				System.out.println("Enter user name : ");
				u = sc.nextLine();
				String sql1 = "select * from student where user_name=?";
				this.pstmt1 = CRSApp.con.prepareStatement(sql1);
				this.pstmt1.setString(1, u);
				this.res = this.pstmt1.executeQuery();
				if (!this.res.next()) {
					this.pstmt.setString(6, u);
					System.out.println("About you : ");
					this.pstmt.setString(7, sc.nextLine());
					int x = this.pstmt.executeUpdate();
					if (x > 0) {
						System.out.println("\033[0;1m"+"\nNew student Request by :" + name+"\033[0;0m\n");
					}
					break;
				}

				System.err.println("User already exists");
			}
		} catch (Exception var6) {
			var6.getMessage();
		}

	}

	public void getscores(String user) {
		try {
			String sql = "select score from student where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			this.pstmt.setString(1, user);
			this.resultset = this.pstmt.executeQuery();

			while (this.resultset.next()) {
				System.out.println("Score : " + this.resultset.getInt(1));
			}
		} catch (Exception var3) {
			var3.getMessage();
		}

	}

	public void markscard(String user) {
		try {
			String sql = "select * from student where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			this.pstmt.setString(1, user);
			this.resultset = this.pstmt.executeQuery();

			while (this.resultset.next()) {
				System.out.println("ID          : " + this.resultset.getInt(1));
				System.out.println("Name        : " + this.resultset.getString(2));
				System.out.println("User Name   : " + this.resultset.getString(8));
				System.out.println("Age         : " + this.resultset.getInt(3));
				System.out.println("Email       : " + this.resultset.getString(4));
				System.out.println("Course id   : " + this.resultset.getInt(5));
				System.out.println("Course name : " + this.resultset.getString(6));
				System.out.println("Score       : " + this.resultset.getInt(7));
				if (this.resultset.getInt(7) > 0) {
					System.out.println("You have completed the course with "+"\033[0;1m" + this.resultset.getInt(7) + "%"+"\033[0;0m");
				} else {
					System.err.println("Grades are not released yet.");
				}
			}
		} catch (Exception var3) {
			var3.getMessage();
		}

	}

	public void updatepword(String user) {
		try {
			Scanner sc = new Scanner(System.in);
			String sql = "update student set password = ? where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("\033[0;1m"+"Enter new password : "+"\033[0;0m");
			this.pstmt.setString(1, sc.next());
			this.pstmt.setString(2, user);
			int x = this.pstmt.executeUpdate();
			System.out.println("\033[0;1m"+"Details updated successfully"+"\033[0;0m");
		} catch (Exception var5) {
			var5.getMessage();
		}

	}

	public void updatemail(String user) {
		try {
			Scanner sc = new Scanner(System.in);
			String sql = "update student set email = ? where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter new email : ");
			this.pstmt.setString(1, sc.next());
			this.pstmt.setString(2, user);
			int x = this.pstmt.executeUpdate();
			System.out.println("\033[0;1m"+"Details updated successfully"+"\033[0;0m");
		} catch (Exception var5) {
			var5.getMessage();
		}

	}

	public void updatename(String user) {
		try {
			Scanner sc = new Scanner(System.in);
			String sql = "update student set sname = ? where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter your name : ");
			this.pstmt.setString(1, sc.next());
			this.pstmt.setString(2, user);
			int x = this.pstmt.executeUpdate();
			System.out.println("\033[0;1m"+"Details updated successfully"+"\033[0;0m");
		} catch (Exception var5) {
			var5.getMessage();
		}

	}
}
