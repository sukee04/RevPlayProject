package com.example.RevPlay.service;

import com.example.RevPlay.DAO.SongDAO;
import com.example.RevPlay.model.Song;

public class SongService {

    private SongDAO songDAO;

    public SongService() {
        this.songDAO = new SongDAO();
    }

    public boolean addSong(Song song) {
        Song uploadedSong = songDAO.addSong(song);
        return uploadedSong != null;
    }

    public void listAllSongs() {
        songDAO.ListOfSongs();
    }

    public Song getSongDetails(int songId) {
        Song song = new Song();
        song.song_id = songId;
        // Note: selectSong in DAO currently returns null but prints details.
        // Ideally DAO should return the object. For now we just call it.
        return songDAO.selectSong(song);
    }

    public void getSongsByArtistId(int artistId) {
        songDAO.getSongs(artistId);
    }

    public void searchSongs(String keyword) {
        songDAO.searchSongs(keyword);
    }

    public boolean addSongToFavorites(int userId, int songId) {
        return songDAO.addToFavorites(userId, songId);
    }

    public void incrementPlayCount(int songId) {
        songDAO.incrementPlayCount(songId);
    }
}
