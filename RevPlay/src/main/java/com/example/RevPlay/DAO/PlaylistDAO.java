package com.example.RevPlay.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.example.RevPlay.model.Playlist;

public class PlaylistDAO extends BaseDAO {
	private static Logger log = LogManager.getLogger(PlaylistDAO.class);

	public Playlist addPlaylistIntoDB(Playlist playlist) {
		String query = "INSERT INTO playlists VALUES(?,?,?);";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, playlist.userId);
			ps.setInt(2, playlist.playlistId);
			ps.setString(3, playlist.playlistName);

			ps.executeUpdate();
			log.info("Song Added Successfully into your PlayList");
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return playlist;
	}

	public void showPlaylistFromDB() {
		String query = "SELECT * FROM playlists;";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int playlist_id = rs.getInt("playlist_id");
				String playlist_name = rs.getString("playlist_name");

				System.out.println("playlist_id : " + playlist_id + " || " + "playlist_name : " + playlist_name);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public Playlist selectPlaylist(Playlist playlist) {
		String query = "SELECT * FROM playlists WHERE playlist_id = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, playlist.playlistId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("playlist_id : " + rs.getInt(1) + " || " + "playlist_name : " + rs.getString(2));
			} else {
				System.out.println("Artist login ERROR");
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return null;
	}

	public Playlist deletePlaylistFromDB(Playlist playlist) {
		String query1 = "DELETE FROM playlists WHERE playlist_id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query1);
			ps.setInt(1, playlist.playlistId);

			int rs = ps.executeUpdate();
			log.info("PlayList Deleted Successfully");
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return null;
	}
}
