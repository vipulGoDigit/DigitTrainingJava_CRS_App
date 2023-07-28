package com.digit.crsApp.service;

import com.crsApp.CRSApp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AdminServices {
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultset;

	public AdminServices() {
	}

	public void menu() {
		System.out.println("\033[0;1m"+"\nSelect Option:"+"\033[0;0m");
		System.out.println(
				"1. Add course\n2. Add Student\n3. Add Professor\n4. Remove Course\n5. Remove Professor\n6. Remove Student\n7. View All Students\n8. View All Courses\n9. View All Professors\n10. View All Users\n11. View New Professor Requests\n12. View New Student Requests\n0. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 0:
		default:
			break;
		case 1:
			this.addCourse();
			break;
		case 2:
			this.addStudent();
			break;
		case 3:
			this.addProfessor();
			break;
		case 4:
			this.removeCourse();
			break;
		case 5:
			this.removeProfessor();
			break;
		case 6:
			this.removeStudent();
			break;
		case 7:
			this.allstudents();
			break;
		case 8:
			this.allcourses();
			break;
		case 9:
			this.allpro();
			break;
		case 10:
			this.allusers();
			break;
		case 11:
			this.allproreq();
			break;
		case 12:
			this.allstudentsreq();
		}

	}

	public void addCourse() {
		try {
			String sql = "insert into course(cname, fees, dur_months) values(?,?,?)";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the course name : ");
			String c = sc.next();
			this.pstmt.setString(1, c);
			System.out.println("Enter the fees : ");
			this.pstmt.setInt(2, sc.nextInt());
			System.out.println("Enter the duration in months : ");
			this.pstmt.setInt(3, sc.nextInt());
			int x = this.pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("New Course Added :" + c);
				this.menu();
			}
		} catch (Exception var5) {
			var5.getMessage();
		}

	}

	public void addStudent() {
		try {
			String sql1 = "select * from studentrequest";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql1);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"Name        :  " + this.resultset.getString(1));
				System.out.println("Age         :  " + this.resultset.getString(2));
				System.out.println("Email       :  " + this.resultset.getString(3));
				System.out.println("Course id   :  " + this.resultset.getString(4));
				System.out.println("Course      :  " + this.resultset.getString(5));
				System.out.println("User Name   :  " + this.resultset.getString(6));
				System.out.println("Description :  " + this.resultset.getString(7));
				System.out.println("---------------"+"\033[0;0m");
			}

			String sql = "insert into student(sname, age, email, cid, course, user_name, password)  values(?,?,?,?,?,?,?)";
			Scanner sc = new Scanner(System.in);
			System.out.println("\033[0;1m"+"\nEnter next to approve later."+"\033[0;0m");
			System.out.println("\nEnter user name of the student to be added: ");
			String u = sc.nextLine();
			if (u.equals("next")) {
				this.menu();
			}

			String sql3 = "select * from studentrequest where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql3);
			this.pstmt.setString(1, u);
			this.resultset = this.pstmt.executeQuery();
			this.resultset.first();
			this.pstmt = CRSApp.con.prepareStatement(sql);
			this.pstmt.setString(1, this.resultset.getString(1));
			this.pstmt.setString(2, this.resultset.getString(2));
			this.pstmt.setString(3, this.resultset.getString(3));
			this.pstmt.setString(4, this.resultset.getString(4));
			this.pstmt.setString(5, this.resultset.getString(5));
			this.pstmt.setString(6, this.resultset.getString(6));
			this.pstmt.setString(7, "student");
			int x1 = this.pstmt.executeUpdate();
			if (x1 > 0) {
				System.out.println("New Student Added :" + this.resultset.getString(1));
			}

			String sql2 = "delete from studentrequest where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql2);
			this.pstmt.setString(1, u);
			int x3 = this.pstmt.executeUpdate();
			if (x3 > 0) {
				this.menu();
			}
		} catch (Exception var9) {
			var9.getMessage();
		}

	}

	public void addProfessor() {
		try {
			String sql1 = "select * from professorrequest";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql1);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"Name        :  " + this.resultset.getString(1));
				System.out.println("Age         :  " + this.resultset.getInt(2));
				System.out.println("Experience  :  " + this.resultset.getInt(3));
				System.out.println("Course id   :  " + this.resultset.getInt(4));
				System.out.println("Course      :  " + this.resultset.getString(5));
				System.out.println("User Name   :  " + this.resultset.getString(6));
				System.out.println("Description :  " + this.resultset.getString(7));
				System.out.println("---------------"+"\033[0;0m");
			}

			String sql = "insert into professor(pname, age, exp, cid, course, user_name, password)  values(?,?,?,?,?,?,?)";
			Scanner sc = new Scanner(System.in);
			System.out.println("\033[0;1m"+"Enter next to approve later.");
			System.out.println("\nEnter user name of the professor to be added: "+"\033[0;0m");
			String u = sc.nextLine();
			if (u.equals("next")) {
				this.menu();
			}

			String sql3 = "select * from professorrequest where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql3);
			this.pstmt.setString(1, u);
			this.resultset = this.pstmt.executeQuery();
			this.resultset.first();
			this.pstmt = CRSApp.con.prepareStatement(sql);
			this.pstmt.setString(1, this.resultset.getString(1));
			this.pstmt.setString(2, this.resultset.getString(2));
			this.pstmt.setString(3, this.resultset.getString(3));
			this.pstmt.setString(4, this.resultset.getString(4));
			this.pstmt.setString(5, this.resultset.getString(5));
			this.pstmt.setString(6, this.resultset.getString(6));
			this.pstmt.setString(7, "professor");
			int x = this.pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("New Professor Added :" + u);
			}

			String sql2 = "delete from professorrequest where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql2);
			this.pstmt.setString(1, u);
			int x1 = this.pstmt.executeUpdate();
			if (x1 > 0) {
				this.menu();
			}
		} catch (Exception var9) {
			var9.getMessage();
		}

	}

	public void removeCourse() {
		try {
			String sql1 = "select * from course";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql1);

			while (this.resultset.next()) {
				System.out.println("Course ID   : " + this.resultset.getInt(1));
				System.out.println("Course Name : " + this.resultset.getString(2));
				System.out.println("---------------");
			}

			String sql = "delete from course where cid = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the course ID to be removed : ");
			int c = Integer.parseInt(sc.nextLine());
			this.pstmt.setInt(1, c);
			int x = this.pstmt.executeUpdate();
			if (x > 0) {
				System.err.println("Course removed ");
				this.menu();
			}
		} catch (Exception var6) {
			var6.getMessage();
		}

	}

	public void removeProfessor() {
		try {
			String sql1 = "select * from professor";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql1);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"ID          : " + this.resultset.getInt(1));
				System.out.println("Name        : " + this.resultset.getString(2));
				System.out.println("Age         : " + this.resultset.getInt(3));
				System.out.println("Experience  : " + this.resultset.getInt(4));
				System.out.println("Course ID   : " + this.resultset.getInt(5));
				System.out.println("Course Name : " + this.resultset.getString(6));
				System.out.println("User Name   : " + this.resultset.getString(7));
				System.out.println("-----------------"+"\033[0;0m");
			}

			String sql = "delete from professor where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the professor user name to be removed : ");
			String c = sc.next();
			this.pstmt.setString(1, c);
			int x = this.pstmt.executeUpdate();
			if (x > 0) {
				System.err.println("Professor removed ");
				this.menu();
			}
		} catch (Exception var6) {
			var6.getMessage();
		}

	}

	public void removeStudent() {
		try {
			String sql1 = "select * from student";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql1);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"ID          : " + this.resultset.getInt(1));
				System.out.println("Name        : " + this.resultset.getString(2));
				System.out.println("Age         : " + this.resultset.getInt(3));
				System.out.println("Email       : " + this.resultset.getString(4));
				System.out.println("Course ID   : " + this.resultset.getInt(5));
				System.out.println("Course Name : " + this.resultset.getString(6));
				System.out.println("User Name   : " + this.resultset.getString(8));
				System.out.println("------------------"+"\033[0;0m");
			}

			String sql = "delete from student where user_name = ?";
			this.pstmt = CRSApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the student user name to be removed : ");
			String c = sc.next();
			this.pstmt.setString(1, c);
			int x = this.pstmt.executeUpdate();
			if (x > 0) {
				System.err.println("student removed ");
				this.menu();
			}
		} catch (Exception var6) {
			var6.getMessage();
		}

	}

	public void allstudents() {
		try {
			String sql = "select * from student";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"ID          : " + this.resultset.getInt(1));
				System.out.println("Name        : " + this.resultset.getString(2));
				System.out.println("Age         : " + this.resultset.getInt(3));
				System.out.println("Email       : " + this.resultset.getString(4));
				System.out.println("Course ID   : " + this.resultset.getInt(5));
				System.out.println("Course Name : " + this.resultset.getString(6));
				System.out.println("User Name   : " + this.resultset.getString(7));
				System.out.println("------------------"+"\033[0;0m");
			}

			this.menu();
		} catch (Exception var2) {
			var2.getMessage();
		}

	}

	public void allstudentsreq() {
		try {
			String sql = "select * from studentrequest";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"Name        : " + this.resultset.getString(1));
				System.out.println("Age         : " + this.resultset.getInt(2));
				System.out.println("Email       : " + this.resultset.getString(3));
				System.out.println("Course ID   : " + this.resultset.getInt(4));
				System.out.println("Course Name : " + this.resultset.getString(5));
				System.out.println("User Name   : " + this.resultset.getString(6));
				System.out.println("Description : " + this.resultset.getString(7));
				System.out.println("------------------"+"\033[0;0m");
			}

			this.menu();
		} catch (Exception var2) {
			var2.getMessage();
		}

	}

	public void allcourses() {
		try {
			String sql = "select * from course";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"Course ID   : " + this.resultset.getInt(1));
				System.out.println("Course Name : " + this.resultset.getString(2));
				System.out.println("---------------"+"\033[0;0m");
			}

			this.menu();
		} catch (Exception var2) {
			var2.getMessage();
		}

	}

	public void allpro() {
		try {
			String sql = "select * from professor";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"ID          : " + this.resultset.getInt(1));
				System.out.println("Name        : " + this.resultset.getString(2));
				System.out.println("Age         : " + this.resultset.getInt(3));
				System.out.println("Experience  : " + this.resultset.getInt(4));
				System.out.println("Course ID   : " + this.resultset.getInt(5));
				System.out.println("Course Name : " + this.resultset.getString(6));
				System.out.println("User Name   : " + this.resultset.getString(7));
				System.out.println("-----------------"+"\033[0;0m");
			}

			this.menu();
		} catch (Exception var2) {
			var2.getMessage();
		}

	}

	public void allproreq() {
		try {
			String sql = "select * from professorrequest";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"Name        : " + this.resultset.getString(1));
				System.out.println("Age         : " + this.resultset.getInt(2));
				System.out.println("Experience  : " + this.resultset.getInt(3));
				System.out.println("Course ID   : " + this.resultset.getInt(4));
				System.out.println("Course Name : " + this.resultset.getString(5));
				System.out.println("User Name   : " + this.resultset.getString(6));
				System.out.println("Description : " + this.resultset.getString(7));
				System.out.println("-----------------"+"\033[0;0m");
			}

			this.menu();
		} catch (Exception var2) {
			var2.getMessage();
		}

	}

	public void allusers() {
		try {
			String sql = "select * from users";
			this.stmt = CRSApp.con.createStatement();
			this.resultset = this.stmt.executeQuery(sql);

			while (this.resultset.next()) {
				System.out.println("\033[0;1m"+"User Name : " + this.resultset.getString(1));
				System.out.println("---------------"+"\033[0;0m");
			}

			this.menu();
		} catch (Exception var2) {
			var2.getMessage();
		}

	}
}
