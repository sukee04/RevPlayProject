package com.example.RevPlay.model;

public class Album {
    public int album_id;
    public int artist_id;
    public String album_name;

    public Album() {
    }

    public Album(int album_id, int artist_id, String album_name) {
        this.album_id = album_id;
        this.artist_id = artist_id;
        this.album_name = album_name;
    }

    @Override
    public String toString() {
        return "Album [album_id=" + album_id + ", album_name=" + album_name + "]";
    }
}
