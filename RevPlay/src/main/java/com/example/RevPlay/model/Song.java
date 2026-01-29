package com.example.RevPlay.model;

public class Song {
    public int album_id;
    public int song_id;
    public int artist_id;
    public String title;
    public String genre;
    public int duration;
    public String relase_date;

    public Song() {
    }

    public Song(int album_id, int song_id, int artist_id, String title, String genre, int duration,
            String relase_date) {
        this.album_id = album_id;
        this.song_id = song_id;
        this.artist_id = artist_id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.relase_date = relase_date;
    }

    @Override
    public String toString() {
        return "Song [album_id=" + album_id + ", song_id=" + song_id + ", artist_id=" + artist_id + ", title=" + title
                + ", genre=" + genre + ", duration=" + duration + ", relase_date=" + relase_date + "]";
    }
}
