package com.example.RevPlay.model;

public class ArtistRegistrationPage {
	public int Artist_Id;
	public String ArtistName;
	public String ArtistEmail;
	public String Artist_Bio;
	public String Artist_Social_Media_Links;
	public String ArtistPassword;
	
	public ArtistRegistrationPage() {
		
	}

	public ArtistRegistrationPage(int artist_Id, String artistName, String artistEmail, String artist_Bio,
			String artist_Social_Media_Links, String artistPassword) {
		this.Artist_Id = artist_Id;
		this.ArtistName = artistName;
		this.ArtistEmail = artistEmail;
		this.Artist_Bio = artist_Bio;
		this.Artist_Social_Media_Links = artist_Social_Media_Links;
		this.ArtistPassword = artistPassword;
	}
	
	
}
