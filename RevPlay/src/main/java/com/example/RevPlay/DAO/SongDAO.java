package com.example.RevPlay.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.example.RevPlay.model.Song;

public class SongDAO extends BaseDAO {
	private static Logger log = LogManager.getLogger(SongDAO.class);

	public Song addSong(Song uploadSong) {
		String query = "INSERT INTO songs (song_id, album_id, artist_id, title, genre, duration, release_date, play_count) VALUES (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, uploadSong.song_id);
			ps.setInt(2, uploadSong.album_id);
			ps.setInt(3, uploadSong.artist_id);
			ps.setString(4, uploadSong.title);
			ps.setString(5, uploadSong.genre);
			ps.setInt(6, uploadSong.duration);
			ps.setString(7, uploadSong.relase_date);
			ps.setInt(8, 0);

			if (ps.executeUpdate() > 0) {
				log.info("Song Uploaded Successfully");
				return uploadSong;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return null;
	}

	public void ListOfSongs() {
		String query = "SELECT * FROM songs;";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int songId = rs.getInt("song_id");
				int albumId = rs.getInt("album_id");
				int artistId = rs.getInt("artist_id");
				String title = rs.getString("title");
				String genre = rs.getString("genre");
				int duration = rs.getInt("duration");

				System.out.println("SongId : " + songId + " || " + "albumId : " + albumId + " || " + "artistId : "
						+ artistId + " || " + "title : " + title + " || " + "genre : " + genre + " || " + "duration : "
						+ duration);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public Song selectSong(Song song) {
		String query = "SELECT * FROM songs WHERE song_id = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, song.song_id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println(" Song: " + rs.getString(4) + "||" + " Genre: " + rs.getString(5) + "||"
						+ " Duration(secounds): " + rs.getString(6));
			} else {
				System.out.println("Artist login ERROR");
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return null;
	}

	public void getSongs(int id) {
		String query = "SELECT * FROM songs where artist_id = ?;";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int song_id = rs.getInt("song_id");
				int album_id = rs.getInt("album_id");
				int artist_id = rs.getInt("artist_id");
				String title = rs.getString("title");
				String genre = rs.getString("genre");
				int duration = rs.getInt("duration");

				System.out.println("Song id : " + song_id + " || " + " Album id: " + album_id + " || " + " Artist id: "
						+ artist_id + " || " + " Title: " + title + " || " + "Genre: " + genre + " || " + "Duration: "
						+ duration);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public void searchSongs(String keyword) {
		String sql = "SELECT * FROM songs WHERE title LIKE ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out
						.println(rs.getInt("song_id") + " | " + rs.getString("title") + " | " + rs.getString("genre"));
			}
		} catch (SQLException e) {
			log.error("Error searching songs", e);
		}
	}

	public boolean addToFavorites(int userId, int songId) {

		String sql = "INSERT INTO favorites (user_id, song_id) VALUES (?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, songId);
			ps.executeUpdate();

			log.info("Song {} added to favorites by user {}", songId, userId);
			return true;

		} catch (SQLException e) {
			log.warn("Song {} already in favorites for user {}", songId, userId);
			return false;
		}
	}

	public void incrementPlayCount(int songId) {

		String sql = "UPDATE songs SET play_count = play_count + 1 WHERE song_id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, songId);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
