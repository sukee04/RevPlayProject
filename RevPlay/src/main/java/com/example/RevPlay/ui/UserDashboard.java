package com.example.RevPlay.ui;

import java.util.Scanner;

import com.example.RevPlay.model.Playlist;
import com.example.RevPlay.service.PlaylistService;
import com.example.RevPlay.service.SongService;
import com.example.RevPlay.service.UserService;

public class UserDashboard {

	private SongService songService;
	private PlaylistService playlistService;
	private UserService userService;

	public UserDashboard() {
		this.songService = new SongService();
		this.playlistService = new PlaylistService();
		this.userService = new UserService();
	}

	public void userDashboard(Scanner scan) {
		int option;
		do {
			System.out.println("=======================");
			System.out.println("    User Interface     ");
			System.out.println("=======================");

			System.out.println("1. Music Player");
			System.out.println("2. Songs Library");
			System.out.println("3. Search Songs");
			System.out.println("4. Show Playlists");
			System.out.println("5. Create Playlist");
			System.out.println("6. Delete Playlist");
			System.out.println("7. Change Password");
			System.out.println("8. Log Out");

			System.out.println("Select Option: ");
			option = scan.nextInt();

			switch (option) {
				case 1: {
					MusicPlayer musicPlayer = new MusicPlayer();
					musicPlayer.musicPlayerOption(scan);
				}
					break;
				case 2: {
					songService.listAllSongs();
				}
					break;
				case 3: {
					scan.nextLine();
					System.out.print("Enter keyword to search song: ");
					String key = scan.nextLine();
					songService.searchSongs(key);

				}
					break;
				case 4: {
					playlistService.showAllPlaylists();
				}
					break;
				case 5: {

					System.out.println("Enter User Id: ");
					int userId = scan.nextInt();
					scan.nextLine();

					System.out.println("Enter Playlist Id: ");
					int playlistId = scan.nextInt();
					scan.nextLine();

					System.out.println("Enter Playlist Name: ");
					String playlistName = scan.nextLine();

					Playlist playlist = new Playlist(userId, playlistId, playlistName);
					playlistService.createPlaylist(playlist);
					System.out.println("Playlist created successfully");
				}
					break;
				case 6: {
					System.out.println("Which playlist you want to Delete(Enter playlist Id): ");
					int playlistId = scan.nextInt();
					playlistService.deletePlaylist(playlistId);
				}
					break;
				case 7: {
					scan.nextLine();
					System.out.print("Enter Email: ");
					String email = scan.nextLine();

					System.out.print("Enter Old Password: ");
					String oldPwd = scan.nextLine();

					System.out.print("Enter New Password: ");
					String newPwd = scan.nextLine();

					boolean changed = userService.changePassword(email, oldPwd, newPwd);

					if (changed) {
						System.out.println("Password changed successfully");
					} else {
						System.out.println("Old password incorrect");
					}
				}
					break;
				case 8: {
					System.out.println("User Logged Out");
					return;
				}
				default:
					System.out.println("Enter Invalid Option.");
					break;
			}
		} while (option != 8);
	}
}