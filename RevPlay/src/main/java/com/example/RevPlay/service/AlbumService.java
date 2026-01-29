package com.example.RevPlay.service;

import com.example.RevPlay.DAO.AlbumDAO;
import com.example.RevPlay.model.Album;

public class AlbumService {

    private AlbumDAO albumDAO;

    public AlbumService() {
        this.albumDAO = new AlbumDAO();
    }

    public boolean addAlbum(Album album) {
        Album addedAlbum = albumDAO.addAlbum(album);
        return addedAlbum != null;
    }

    public void getAlbumsByArtist(int artistId) {
        albumDAO.getAlbums(artistId);
    }

    public void deleteAlbum(int albumId) {
        Album album = new Album();
        album.album_id = albumId;
        albumDAO.deleteAlbum(album, albumId);
    }
}
