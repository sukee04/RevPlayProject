package com.example.RevPlay.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.RevPlay.model.Album;

public class AlbumDAO extends BaseDAO {

	public Album addAlbum(Album album) {
		String query = "INSERT INTO albums VALUES (?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, album.album_id);
			ps.setInt(2, album.artist_id);
			ps.setString(3, album.album_name);

			if (ps.executeUpdate() > 0) {
				return album;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return null;
	}

	public void getAlbums(int id) {
		String query = "SELECT * FROM albums where artist_id = ?;";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int albumId = rs.getInt("album_id");
				String albumName = rs.getString("album_name");

				System.out.println("Album id : " + albumId + " || " + " Ablum : " + albumName);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public Album deleteAlbum(Album album, int id) {
		String query1 = "DELETE FROM albums WHERE album_id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query1);
			ps.setInt(1, album.album_id);

			int rs = ps.executeUpdate();

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		return null;
	}
}
