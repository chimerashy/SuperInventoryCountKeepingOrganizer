package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LowStockList {
	private Connection connect = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	public char dewit;
	String user = null;
	String pass = null;
	
	public void LowStock() {
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like to do? \n1. View Shopping List\n2. View all low stock.\n3. Quit");
		dewit = input.next().charAt(0);
		if(dewit == '1') {
			ShoppingList();
		}
		else if(dewit == '2') {
			LSL();
		}
	}
	
	public void LSL() {
		try {
			//connect to db
			System.out.println("Checking the DB\n...");
			DB db = DB.getConnector(user, pass);
			Connection connect = db.getConnection();
			//statement to read from the db later
			st = connect.createStatement();
			rs = st.executeQuery("select * from hardwareStore where QUANTITY < 100");
			
			while(rs.next()) {
				String product_name = rs.getString("PRODUCT_NAME");
				String description = rs.getString("DESCRIPTION");
				String product_id = rs.getString("PRODUCT_ID");
				int qty = rs.getInt("QUANTITY");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
			}
			
		} catch(Exception ex) {
			System.out.println("Whoops! " + ex.getMessage());
			Loggit.log(ex);
		}
	
	}
	public void ShoppingList() {
		try {
			//connect to db
			System.out.println("Checking the DB\n...");
			DB db = DB.getConnector(user, pass);
			Connection connect = db.getConnection();
			//statement to read from the db later
			st = connect.createStatement();
			rs = st.executeQuery("select * from hardwareStore where QUANTITY < 100");
			
			while(rs.next()) {
				String product_name = rs.getString("PRODUCT_NAME");
				String product_id = rs.getString("PRODUCT_ID");
				int qty = rs.getInt("QUANTITY");
				int buy = 100 - qty;
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nHave: " + qty + "\nPurchase: " + buy);
			}
			
		} catch(Exception ex) {
			System.out.println("Whoops! " + ex.getMessage());
			Loggit.log(ex);
		}
		
	}

}
