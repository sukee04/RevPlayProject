package com.example.RevPlay.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.example.RevPlay.model.Album;
import com.example.RevPlay.model.Song;
import com.example.RevPlay.service.AlbumService;
import com.example.RevPlay.service.ArtistService;
import com.example.RevPlay.service.SongService;

public class ArtistProfile {

	private ArtistService artistService;
	private SongService songService;
	private AlbumService albumService;

	public ArtistProfile() {
		this.artistService = new ArtistService();
		this.songService = new SongService();
		this.albumService = new AlbumService();
	}

	public void artistMenu(String artistEmail, Scanner scan) {
		int operation_choice;
		do {
			System.out.println("Artist Operations: ");

			System.out.println("1. Upload Songs");
			System.out.println("2. Create Albums and add songs to album");
			System.out.println("3. View my uploaded songs");
			System.out.println("4. View my uploaded albums");
			System.out.println("5. Delete song / album");
			System.out.println("6. View play count");
			System.out.println("7. Artist Logged Out");

			System.out.println("which operation you want to perform: ");
			operation_choice = scan.nextInt();

			switch (operation_choice) {
				case 1: {
					ArrayList<Song> songsList = new ArrayList<>();

					int artistId = artistService.getArtistIdByEmail(artistEmail);

					System.out.println("Available Albums:");
					albumService.getAlbumsByArtist(artistId);
					System.out.println("------------------------------------------------");

					System.out.println("How many songs you want to add: ");
					int noOfSongs = scan.nextInt();
					scan.nextLine();
					for (int i = 0; i < noOfSongs; i++) {
						Song song = new Song();

						System.out.print("Enter album id from the above list: ");
						song.album_id = scan.nextInt();
						scan.nextLine();

						System.out.print("song id: ");
						song.song_id = scan.nextInt();
						scan.nextLine();

						// System.out.print("artist id: ");
						song.artist_id = artistId;
						// scan.nextLine();

						System.out.print("song title: ");
						song.title = scan.nextLine();

						System.out.print("song genre: ");
						song.genre = scan.nextLine();

						System.out.print("song duration: ");
						song.duration = scan.nextInt();
						scan.nextLine();

						System.out.print("song relase_date: ");
						song.relase_date = scan.nextLine();

						songsList.add(song);
						songService.addSong(song);
					}
					System.out.println(songsList);
				}
					break;
				case 2: {
					ArrayList<Album> albumsList = new ArrayList<>();

					int artistId = artistService.getArtistIdByEmail(artistEmail);
					System.out.println("How many albums you want to add: ");
					int noOfAlbums = scan.nextInt();
					scan.nextLine();
					for (int i = 0; i < noOfAlbums; i++) {
						Album album = new Album();

						System.out.print("Album Id: ");
						album.album_id = scan.nextInt();
						scan.nextLine();

						// System.out.print("Artist Id: ");
						album.artist_id = artistId;
						// scan.nextLine();

						System.out.print("Album Name: ");
						album.album_name = scan.nextLine();

						albumsList.add(album);
						albumService.addAlbum(album);
					}

					System.out.println(albumsList);
				}
					break;
				case 3: {
					int id = artistService.getArtistIdByEmail(artistEmail);
					songService.getSongsByArtistId(id);
				}
					break;
				case 4: {
					int id = artistService.getArtistIdByEmail(artistEmail);
					albumService.getAlbumsByArtist(id);
				}
					break;
				case 5: {
					System.out.println("Enter Delete album id: ");
					int delete = scan.nextInt();

					albumService.deleteAlbum(delete);
				}
					break;
				case 6: {
					int id = artistService.getArtistIdByEmail(artistEmail);
					artistService.viewPlayStats(id);
				}
					break;
				case 7: {
					System.out.println("Artist Logged Out");
					System.out.println("Artist Logged Out");
					return;
				}
				default:
					System.out.println("choose valid option");
					break;
			}
		} while (operation_choice != 7);
	}
}