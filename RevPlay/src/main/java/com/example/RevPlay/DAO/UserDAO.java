package com.example.RevPlay.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.example.RevPlay.model.LoginPage;
import com.example.RevPlay.model.UserRegistration;

public class UserDAO extends BaseDAO {

	private static Logger log = LogManager.getLogger(UserDAO.class);

	public boolean resetPassword(String email, String newPassword) {
		String sql = "UPDATE users SET password = ? WHERE email = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, newPassword);
			ps.setString(2, email);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				log.info("Password reset successfully for {}", email);
				return true;
			} else {
				log.warn("Password reset failed: Email not found - {}", email);
				return false;
			}
		} catch (SQLException e) {
			log.error("Error resetting password for " + email, e);
			return false;
		}
	}

	public UserRegistration addUser(UserRegistration user) {
		String query = "INSERT INTO users (user_id, user_name, email, password) VALUES (?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, user.Userid);
			ps.setString(2, user.Username);
			ps.setString(3, user.email);
			ps.setString(4, user.Password);

			if (ps.executeUpdate() > 0) {
				log.info("User registered successfully: {}", user.email);
				// System.out.println("Registration successful");
				return user;
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

	public LoginPage getUserDetails(LoginPage userLogin) {
		String query1 = "SELECT * FROM users WHERE email = ? AND password = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query1);
			ps.setString(1, userLogin.email);
			ps.setString(2, userLogin.password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				log.info("User login successful");
				// System.out.println("User login successful");
				return userLogin;
			} else {
				log.warn("Wrong credentials");
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		// System.out.println("User login unsuccessful");
		return null;
	}

	public boolean changePassword(String email, String oldPassword, String newPassword) {

		String checkSql = "SELECT 1 FROM users WHERE email = ? AND password = ?";

		String updateSql = "UPDATE users SET password = ? WHERE email = ?";

		try (
				PreparedStatement checkPs = connection.prepareStatement(checkSql)) {
			checkPs.setString(1, email);
			checkPs.setString(2, oldPassword);

			ResultSet rs = checkPs.executeQuery();

			if (!rs.next()) {
				log.warn("Change password failed: wrong old password for {}", email);
				return false;
			}

			PreparedStatement updatePs = connection.prepareStatement(updateSql);
			updatePs.setString(1, newPassword);
			updatePs.setString(2, email);
			updatePs.executeUpdate();

			log.info("Password changed successfully for {}", email);
			return true;

		} catch (SQLException e) {
			log.error("Error changing password for " + email, e);
			return false;
		}
	}

	public int getUserIdByEmail(String email) {

		String sql = "SELECT user_id FROM users WHERE email = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("user_id");
			}

		} catch (SQLException e) {
			log.error("Error fetching userId for email: {}", email, e);
		}

		return 0;
	}

}