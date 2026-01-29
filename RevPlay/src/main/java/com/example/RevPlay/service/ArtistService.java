package com.example.RevPlay.service;

import com.example.RevPlay.DAO.ArtistDAO;
import com.example.RevPlay.model.ArtistRegistrationPage;
import com.example.RevPlay.model.LoginPage;
import com.example.RevPlay.util.PasswordUtil;

public class ArtistService {

    private ArtistDAO artistDAO;

    public ArtistService() {
        this.artistDAO = new ArtistDAO();
    }

    public boolean registerArtist(ArtistRegistrationPage artist) {
        // Hash the password before saving
        artist.ArtistPassword = PasswordUtil.hashPassword(artist.ArtistPassword);
        ArtistRegistrationPage registeredArtist = artistDAO.addArtist(artist);
        return registeredArtist != null;
    }

    public LoginPage loginArtist(String email, String password) {
        // Hash the input password to check against existing hash
        String hashedPassword = PasswordUtil.hashPassword(password);
        // String hashedPassword = password;
        LoginPage loginPage = new LoginPage(email, hashedPassword);
        return artistDAO.getArtistDetails(loginPage);
    }

    public int getArtistIdByEmail(String email) {
        return artistDAO.getArtistIdByEmail(email);
    }

    public void viewPlayStats(int artistId) {
        artistDAO.viewPlayStats(artistId);
    }

    public boolean resetPassword(String email, String newPassword) {
        String hashedNewPassword = PasswordUtil.hashPassword(newPassword);
        return artistDAO.resetPassword(email, hashedNewPassword);
    }

}
