package com.example.RevPlay.config;

import java.sql.Connection;

public class DBController {
	public static void main(String[] args) {
		Connection con = DBConnection.getConnection();
		
		if(con != null) {
			System.out.println("Connection established");
		}else {
			System.out.println("Connection Failure");
		}
	}
}
