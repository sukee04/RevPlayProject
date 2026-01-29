package com.example.RevPlay.model;

public class Playlist {
    public int userId;
    public int playlistId;
    public String playlistName;

    public Playlist() {
    }

    public Playlist(int userId, int playlistId, String playlistName) {
        this.userId = userId;
        this.playlistId = playlistId;
        this.playlistName = playlistName;
    }

    @Override
    public String toString() {
        return "Playlist [userId=" + userId + ", playlistId=" + playlistId + ", playlistName=" + playlistName + "]";
    }
}
