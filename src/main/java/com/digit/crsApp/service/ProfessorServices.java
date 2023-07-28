package com.digit.crsApp.service;

import com.crsApp.CRSApp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ProfessorServices {
	private PreparedStatement pstmt;
	private PreparedStatement pstmt1;
	private Statement stmt;
	private ResultSet resultset;
	private ResultSet res;

	public ProfessorServices() {
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

	public void profrequest() {
		try {
			String sql = "insert into professorrequest values(?,?,?,?,?,?,?)";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter your name : ");
			String name = sc.nextLine();
			this.pstmt.setString(1, name);
			System.out.println("Enter your age : ");
			this.pstmt.setInt(2, Integer.parseInt(sc.nextLine()));
			System.out.println("Enter your experience : ");
			this.pstmt.setInt(3, Integer.parseInt(sc.nextLine()));
			System.out.println("Enter course id : ");
			this.pstmt.setInt(4, Integer.parseInt(sc.nextLine()));
			System.out.println("Selected course : ");
			this.pstmt.setString(5, sc.nextLine());
			String u = "";

			while (true) {
				System.out.println("Enter user name : ");
				u = sc.nextLine();
				String sql1 = "select * from professor where user_name=?";
				this.pstmt1 = CRSApp.con.prepareStatement(sql1);
				this.pstmt1.setString(1, u);
				this.res = this.pstmt1.executeQuery();
				if (!this.res.next()) {
					this.pstmt.setString(6, u);
					System.out.println("About you : ");
					this.pstmt.setString(7, sc.nextLine());
					int x = this.pstmt.executeUpdate();
					if (x > 0) {
						System.out.println("\033[0;1m"+"\nNew Professor Request by :" + name+"\033[0;0m\n");
					}
					break;
				}

				System.err.println("User already exists");
			}
		} catch (Exception var6) {
			var6.getMessage();
		}

	}

	public void enrolledstudents(String user) {
		try {
			String sql1 = "select cid from professor where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql1);
			this.pstmt.setString(1, user);
			this.resultset = this.pstmt.executeQuery();
			String sql = "select sname from student where cid = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			this.resultset.first();
			this.pstmt.setInt(1, this.resultset.getInt(1));
			this.resultset = this.pstmt.executeQuery();

			while (this.resultset.next()) {
				System.out.println(this.resultset.getString(1));
			}
		} catch (Exception var4) {
			var4.getMessage();
		}

	}

	public void gradestudent(String user) {
		try {
			Scanner sc = new Scanner(System.in);
			String sql1 = "select cid from professor where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql1);
			this.pstmt.setString(1, user);
			this.resultset = this.pstmt.executeQuery();
			String sql = "select sname from student where cid = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			this.resultset.first();
			this.pstmt.setInt(1, this.resultset.getInt(1));

			int var7;
			for (this.resultset = this.pstmt.executeQuery(); this.resultset.next(); var7 = this.pstmt.executeUpdate()) {
				System.out.print("Enter score for student " + this.resultset.getString(1) + " ");
				int score = sc.nextInt();
				System.out.print(this.resultset.getString(1) + " : " + score + "\n");
				String sql2 = "update student set score = ? where sname = ?";
				this.pstmt = CRSApp.con.prepareStatement(sql2);
				this.pstmt.setInt(1, score);
				this.pstmt.setString(2, this.resultset.getString(1));
			}
		} catch (Exception var8) {
			var8.getMessage();
		}

	}

	public void updatepword(String user) {
		try {
			Scanner sc = new Scanner(System.in);
			String sql = "update professor set password = ? where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter new password : ");
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
			String sql = "update professor set pname = ? where user_name = ?";
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

	public void updateexp(String user) {
		try {
			Scanner sc = new Scanner(System.in);
			String sql = "update professor set exp = ? where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter your experience : ");
			this.pstmt.setString(1, sc.next());
			this.pstmt.setString(2, user);
			int x = this.pstmt.executeUpdate();
			System.out.println("\033[0;1m"+"Details updated successfully"+"\033[0;0m");
		} catch (Exception var5) {
			var5.getMessage();
		}

	}
}
