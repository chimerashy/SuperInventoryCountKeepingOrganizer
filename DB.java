package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

	public class DB {
		private static volatile DB db;
		private Connection connect = null;
		private Statement st = null;
		private PreparedStatement ps = null;
		private ResultSet rs = null;
		private String input;
		private String action;
		//private String user;
		//private String password;

		private DB(String user, String password){
			try {
		
				//connect to DB
				connect = DriverManager.getConnection("jdbc:mysql://localhost/sicko", user, password);
				//validate connection
				if(connect.isClosed()) {
					System.out.println("Oops! The connection didn't work. Your credentials might be incorrect.");
				} else {
					System.out.println("Connected to the DB\n");
				}
				// System.out.println("Okie dokie, " + user + ", time to " + action + " with " + input + "!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Loggit.log(e);
			}

		}
		
	    public Connection getConnection() {
	        return connect;
	    }
	    
		//callable public instance of private class with double lock to ensure only one instance is created
		public static DB getConnector(String user, String password) {
			if(db == null) {
				synchronized(DB.class) {
					if(db == null) {
						db = new DB(user, password);
					}
				}
			}
			return db;
		}
}
