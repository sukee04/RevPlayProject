package com.example.RevPlay.model;

public class UserRegistration {
	public int Userid;
	public String Username;
	public String email;
	public String Password;
	
	public UserRegistration() {
		
	}
	
	public UserRegistration(int Userid, String username, String email, String password) {
		this.Userid = Userid;
		this.Username = username;
		this.email = email;
		this.Password = password;
	}
}
