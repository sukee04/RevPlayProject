package com.example.RevPlay.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.example.RevPlay.model.ArtistRegistrationPage;
import com.example.RevPlay.model.LoginPage;

public class ArtistDAO extends BaseDAO {
	private static Logger log = LogManager.getLogger(ArtistDAO.class);

	public boolean resetPassword(String email, String newPassword) {
		String sql = "UPDATE artists SET artist_password = ? WHERE artist_email = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, newPassword);
			ps.setString(2, email);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				log.info("Artist password reset successfully for {}", email);
				return true;
			} else {
				log.warn("Artist password reset failed: Email not found - {}", email);
				return false;
			}
		} catch (SQLException e) {
			log.error("Error resetting password for " + email, e);
			return false;
		}
	}

	public ArtistRegistrationPage addArtist(ArtistRegistrationPage artistregistration) {
		String query = "INSERT INTO artists VALUES (?,?,?,?,?,?);";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, artistregistration.Artist_Id);
			ps.setString(2, artistregistration.ArtistName);
			ps.setString(3, artistregistration.ArtistEmail);
			ps.setString(4, artistregistration.Artist_Bio);
			ps.setString(5, artistregistration.Artist_Social_Media_Links);
			ps.setString(6, artistregistration.ArtistPassword);

			if (ps.executeUpdate() > 0) {
				log.info("Registration successful");
				// System.out.println("Registration successful");
				return artistregistration;
			}
		} catch (SQLException se) {
			if (se.getErrorCode() == 1062) {
				log.error("User already exists (Email or ID)");
				// System.out.println("User already exists (Email or ID)");
			} else {
				System.out.println("Database error: " + se.getMessage());
			}
		}
		return null;
	}

	public LoginPage getArtistDetails(LoginPage userLogin) {
		String query1 = "SELECT * FROM artists WHERE artist_email = ? AND artist_password = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query1);
			ps.setString(1, userLogin.email);
			ps.setString(2, userLogin.password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				log.info("Artist login successful");
				// System.out.println("Artist login successful");
				return userLogin;
			}

		} catch (SQLException se) {
			log.warn("Wrong creditionals", se);
			// System.out.println(se.getMessage());
			// System.out.println("Artist login unsuccessful");
		}
		return null;
	}

	public int getArtistIdByEmail(String email) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT artist_id FROM artists WHERE artist_email = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt("artist_id");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	public void viewPlayStats(int artistId) {

		String sql = "SELECT title, play_count FROM songs WHERE artist_id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, artistId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("title") + " | Plays: " + rs.getInt("play_count"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
