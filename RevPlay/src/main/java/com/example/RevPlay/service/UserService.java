package com.example.RevPlay.service;

import com.example.RevPlay.DAO.UserDAO;
import com.example.RevPlay.model.LoginPage;
import com.example.RevPlay.model.UserRegistration;
import com.example.RevPlay.util.PasswordUtil;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(UserRegistration user) {
        // Hash the password before saving
        user.Password = PasswordUtil.hashPassword(user.Password);
        UserRegistration registeredUser = userDAO.addUser(user);
        return registeredUser != null;
    }

    public LoginPage loginUser(String email, String password) {
        // Hash the input password to check against existing hash
        String hashedPassword = PasswordUtil.hashPassword(password);
        // String hashedPassword = password;
        LoginPage loginPage = new LoginPage(email, hashedPassword);
        return userDAO.getUserDetails(loginPage);
    }

    public int getUserIdByEmail(String email) {
        return userDAO.getUserIdByEmail(email);
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        String hashedOldPassword = PasswordUtil.hashPassword(oldPassword);
        String hashedNewPassword = PasswordUtil.hashPassword(newPassword);
        return userDAO.changePassword(email, hashedOldPassword, hashedNewPassword);
    }

    public boolean resetPassword(String email, String newPassword) {
        String hashedNewPassword = PasswordUtil.hashPassword(newPassword);
        return userDAO.resetPassword(email, hashedNewPassword);
    }

}
