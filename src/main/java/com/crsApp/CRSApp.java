package com.crsApp;

import com.digit.crsApp.beans.Users;
import com.digit.crsApp.service.AdminServices;

import com.digit.crsApp.beans.Users;
import com.digit.crsApp.service.AdminServices;
import com.digit.crsApp.service.ProfessorServices;
import com.digit.crsApp.service.StudentServices;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class CRSApp {
	public static Connection con;

	public CRSApp() {
	}

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		String url = "jdbc:mysql://localhost:3306/test22";
		String user = "root";
		String pwd = "Vipul@8800";
		con = DriverManager.getConnection(url, user, pwd);

		while (true) {
			while (true) {
				try {
					label74: while (true) {
						System.out.println("\033[0;1m"+"Select the Type of User:\n"+"\033[0;0m");
						System.out.println(
								"1. Admin\n2. Professor\n3. Student\n4. New Professor\n5. New Student\n6. Exit");
						Scanner sc = new Scanner(System.in);
						int n = sc.nextInt();
						int m;
						String b;
						switch (n) {
						case 1:
							b = Users.login("admin");
							if (!b.equals("false")) {
								System.out.println("\033[0;1m"+"\nAdmin Login Success"+"\033[0;0m");
								AdminServices adsrv = new AdminServices();
								adsrv.menu();
							} else {
								System.err.println("Wrong credentials. Try again with correct credentials");
							}
							break;
						case 2:
							try {
								b = Users.login("professor");
								if (!b.equals("false")) {
									System.out.println("\033[0;1m"+"\nProfessor Login Success\n"+"\033[0;0m");
									ProfessorServices ps = new ProfessorServices();

									while (true) {
										System.out.println("\033[0;1m"+"Select the Type of User:"+"\033[0;0m");
										System.out.println(
												"1. Enrolled students\n2. Grade students\n3. update password\n4. update name\n5. update experience\n6. Exit");
										m = sc.nextInt();
										switch (m) {
										case 1:
											ps.enrolledstudents(b);
											break;
										case 2:
											ps.gradestudent(b);
											break;
										case 3:
											ps.updatepword(b);
											break;
										case 4:
											ps.updatename(b);
											break;
										case 5:
											ps.updateexp(b);
										case 6:
											break;
										default:
											System.err.println("Select only from given options");
										}

										if (m == 6) {
											continue label74;
										}
									}
								}

								System.err.println("Wrong credentials. Try again with correct credentials");
							} catch (Exception var10) {
								var10.getMessage();
								System.err.println("Select only from given options");
							}
							break;
						case 3:
							try {
								b = Users.login("student");
								if (!b.equals("false")) {
									System.out.println("\033[0;1m"+"\nStudent Login Success\n"+"\033[0;0m");
									StudentServices ss = new StudentServices();

									while (true) {
										System.out.println("\033[0;1m"+"Select the Type of User:"+"\033[0;0m");
										System.out.println(
												"1. Score\n2. Marks card\n3. update password\n4. update email\n5. update name\n6. Exit");
										m = sc.nextInt();
										switch (m) {
										case 1:
											ss.getscores(b);
											break;
										case 2:
											ss.markscard(b);
											break;
										case 3:
											ss.updatepword(b);
											break;
										case 4:
											ss.updatemail(b);
											break;
										case 5:
											ss.updatename(b);
										case 6:
											break;
										default:
											System.err.println("Select only from given options");
										}

										if (m == 6) {
											continue label74;
										}
									}
								}

								System.err.println("Wrong credentials. Try again with correct credentials");
							} catch (Exception var9) {
								System.err.println("Select only from given options");
							}
							break;
						case 4:
							ProfessorServices ps = new ProfessorServices();
							System.out.println("\033[0;1m"+"\nSelect course from the following : \n"+"\033[0;0m");
							ps.availablecourses();
							ps.profrequest();
							break;
						case 5:
							StudentServices ss = new StudentServices();
							ss.availablecourses();
							ss.studrequest();
							break;
						case 6:
							System.exit(0);
						default:
							break label74;
						}
					}

					System.err.println("Select only from given options");
				} catch (Exception var11) {
					System.err.println("Select only from given options");
				}
			}
		}
	}
}
