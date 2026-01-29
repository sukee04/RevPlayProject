package com.example.RevPlay.ui;

import java.util.Scanner;

import com.example.RevPlay.DAO.SongDAO;
import com.example.RevPlay.model.Playlist;
import com.example.RevPlay.model.Song;
import com.example.RevPlay.model.UserId;
import com.example.RevPlay.service.PlaylistService;

public class MusicPlayer {
	public void musicPlayerOption(Scanner scan) {

		SongDAO songDAO = new SongDAO();
		songDAO.ListOfSongs();

		System.out.println("Which Song you want to listen, Enter song_id: ");
		int sngId = scan.nextInt();
		Song song = new Song();
		song.song_id = sngId;
		songDAO.selectSong(song);

		int option;
		do {
			System.out.println("1. Play a song");
			System.out.println("2. pause a song");
			System.out.println("3. next song");
			System.out.println("4. add to playlist");
			System.out.println("5. add to favorites");
			System.out.println("6. Back for user menu");

			System.out.println("Which Option: ");
			option = scan.nextInt();
			scan.nextLine();

			switch (option) {
				case 1: {
					songDAO.incrementPlayCount(sngId);
					System.out.println("Song is playing...");
				}
					break;
				case 2: {
					System.out.println("Song is paused .......");
				}
					break;
				case 3: {
					System.out.println("Skipped .......");
				}
					break;
				case 4: {
					PlaylistService playlistService = new PlaylistService();

					System.out.println("1. Show Playlists");
					System.out.println("2. Create Playlists");
					System.out.println("3. Select Playlist");
					System.out.println("4. Delete Playlist");

					int x = scan.nextInt();
					scan.nextLine();

					if (x == 1) {
						playlistService.showAllPlaylists();
					} else if (x == 2) {
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
					} else if (x == 3) {
						System.out.println("Which playlist you want (Enter playlist Id): ");
						int playlistId = scan.nextInt();
						playlistService.getPlaylist(playlistId);
					} else if (x == 4) {
						System.out.println("Which playlist you want to Delete(Enter playlist Id): ");
						int playlistId = scan.nextInt();
						playlistService.deletePlaylist(playlistId);
					} else {
						System.out.println("Select valid option.");
					}
				}
					break;
				case 5: {
					songDAO.addToFavorites(UserId.loggedInUserId, sngId);
				}
					break;
				case 6: {
					System.out.println("Back to User Menu");
				}
					break;
				default:
					System.out.println("Invalid Option is Selected");
					break;
			}
		} while (option != 6);
	}
}
