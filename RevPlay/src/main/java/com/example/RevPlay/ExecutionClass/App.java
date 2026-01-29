package com.example.RevPlay.ExecutionClass;

import java.util.Scanner;

import com.example.RevPlay.ui.ArtistProfile;
import com.example.RevPlay.service.ArtistService;
import com.example.RevPlay.service.UserService;
import com.example.RevPlay.model.ArtistRegistrationPage;
import com.example.RevPlay.model.LoginPage;
import com.example.RevPlay.model.UserId;
import com.example.RevPlay.model.UserRegistration;
import com.example.RevPlay.ui.UserDashboard;

public class App {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("====================");
		System.out.println("    Music Player    ");
		System.out.println("====================");

		System.out.println("1. User Registration");
		System.out.println("2. Artist Registration");
		System.out.println("3. Login");
		System.out.println("4. Forgot Password");
		System.out.println("5. Exit");

		boolean condition = true;
		while (condition) {

			System.out.print("Enter Choice: ");
			int choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {
				case 1: {
					System.out.print("User Id: ");
					int uid = scan.nextInt();
					scan.nextLine();

					System.out.print("User Name: ");
					String uname = scan.nextLine();

					System.out.print("email: ");
					String email = scan.nextLine();

					System.out.print("Password: ");
					String password = scan.nextLine();

					UserRegistration userRegistration = new UserRegistration(uid, uname, email, password);
					UserService userService = new UserService();
					if (userService.registerUser(userRegistration)) {
						System.out.println("Registration successful");
					}

				}
					break;
				case 2: {
					System.out.print("Create Id: ");
					int Artist_Id = scan.nextInt();
					scan.nextLine();

					System.out.print("Add your name: ");
					String Artist_Name = scan.nextLine();

					System.out.print("Add your email: ");
					String Artist_Email = scan.nextLine();

					System.out.print("Add Bio: ");
					String Artist_Bio = scan.nextLine();

					System.out.print("Add your social media links: ");
					String Artist_Social_Media_Links = scan.nextLine();

					System.out.print("Add Password: ");
					String Artist_password = scan.nextLine();

					ArtistRegistrationPage artistRegistrationPage = new ArtistRegistrationPage(Artist_Id, Artist_Name,
							Artist_Email, Artist_Bio, Artist_Social_Media_Links, Artist_password);
					ArtistService artistService = new ArtistService();
					if (artistService.registerArtist(artistRegistrationPage)) {
						System.out.println("Registration successful");
					}

				}
					break;
				case 3: {
					System.out.print("Which Login Are you ? (User / Artist) : ");
					String WhichLogin = scan.nextLine().toLowerCase();

					if (WhichLogin.equals("user")) {
						System.out.print("Email_Id: ");
						String email = scan.nextLine();

						System.out.print("Password: ");
						String password = scan.nextLine();

						UserService userService = new UserService();
						LoginPage loggedInUser = userService.loginUser(email, password);

						if (loggedInUser != null) {
							UserId.loggedInUserId = userService.getUserIdByEmail(loggedInUser.email);

							UserDashboard userDashboard = new UserDashboard();
							userDashboard.userDashboard(scan);
						} else {
							System.out.println("User login unsuccessful");
						}

					} else if (WhichLogin.equals("artist")) {
						System.out.print("Artist Email_Id: ");
						String email = scan.nextLine();

						System.out.print("Artist Password: ");
						String password = scan.nextLine();

						ArtistService artistService = new ArtistService();
						LoginPage loggedInArtist = artistService.loginArtist(email, password);

						if (loggedInArtist != null) {
							ArtistProfile artistProfile = new ArtistProfile();
							artistProfile.artistMenu(loggedInArtist.email, scan);
						} else {
							System.out.println("Artist login unsuccessful");
						}
					}

				}
					break;
				case 4: {
					System.out.print("Are you a User or Artist? (User / Artist) : ");
					String userType = scan.nextLine().toLowerCase();

					System.out.print("Enter your Registered Email: ");
					String email = scan.nextLine();

					System.out.print("Enter New Password: ");
					String newPassword = scan.nextLine();

					if (userType.equals("user")) {
						UserService userService = new UserService();
						if (userService.resetPassword(email, newPassword)) {
							System.out.println("Password reset successful. You can now login.");
						} else {
							System.out.println("Password reset failed. Please check your email and try again.");
						}
					} else if (userType.equals("artist")) {
						ArtistService artistService = new ArtistService();
						if (artistService.resetPassword(email, newPassword)) {
							System.out.println("Password reset successful. You can now login.");
						} else {
							System.out.println("Password reset failed. Please check your email and try again.");
						}
					} else {
						System.out.println("Invalid option.");
					}

				}
					break;
				case 5: {
					System.out.println("Thanks For Using Music Player... :)");
					condition = false;
				}
					break;
				default:
					System.out.println("choose valid option");
			}
		}
		scan.close();
	}
}
