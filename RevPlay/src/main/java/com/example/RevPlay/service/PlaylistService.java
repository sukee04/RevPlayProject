package com.example.RevPlay.service;

import com.example.RevPlay.DAO.PlaylistDAO;
import com.example.RevPlay.model.Playlist;

public class PlaylistService {

    private PlaylistDAO playlistDAO;

    public PlaylistService() {
        this.playlistDAO = new PlaylistDAO();
    }

    public boolean createPlaylist(Playlist playlist) {
        Playlist createdPlaylist = playlistDAO.addPlaylistIntoDB(playlist);
        return createdPlaylist != null;
    }

    public void showAllPlaylists() {
        playlistDAO.showPlaylistFromDB();
    }

    public Playlist getPlaylist(int playlistId) {
        Playlist playlist = new Playlist();
        playlist.playlistId = playlistId;
        // Note: selectPlaylist in DAO currently returns null but prints details
        return playlistDAO.selectPlaylist(playlist);
    }

    public void deletePlaylist(int playlistId) {
        Playlist playlist = new Playlist();
        playlist.playlistId = playlistId;
        playlistDAO.deletePlaylistFromDB(playlist);
    }
}
