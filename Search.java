package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Search {
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String sch_name = null;
	String sch_id = null;
	String sch_desc = null;
	char action = 'x';
	int i = 1;
		public void FindProduct() {
			try {
			//connect to db
			DB db = DB.getConnector("root", "Burnkeyblueberrycrumble1515@");
			Connection connect = db.getConnection();
			st = connect.createStatement();
			
			//get search term
			System.out.println("What would you like to search by?\n1: Name\n2: ID#\n3: Description\nX: Quit to menu");
			Scanner input = new Scanner(System.in);
			action = input.nextLine().charAt(0);

			if(action == '1') {
				//search name input
				System.out.println("What is the product name? ");
				sch_name = input.nextLine();
				
				//search the db for product name
				System.out.println("Checking the db for your product\n...");
				
				rs = st.executeQuery("select * from hardwareStore where PRODUCT_NAME like '%" + sch_name + "%'");
				System.out.println("Behold, the things! ");
				while(rs.next()) {
					String product_name = rs.getString("PRODUCT_NAME");
					String description = rs.getString("DESCRIPTION");
					String product_id = rs.getString("PRODUCT_ID");
					int qty = rs.getInt("QUANTITY");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
				}

			} 
			else if(action == '2') {
				//search ID input
				System.out.println("What is the product ID? ");
				sch_id = input.nextLine();
				
				//search the db for product ID
				System.out.println("Checking the db for your product\n...");
				
				rs = st.executeQuery("select * from hardwareStore where PRODUCT_ID = '" +  sch_id + "'");
				System.out.println("Behold, the things! ");
				while(rs.next()) {
					String product_name = rs.getString("PRODUCT_NAME");
					String description = rs.getString("DESCRIPTION");
					String product_id = rs.getString("PRODUCT_ID");
					int qty = rs.getInt("QUANTITY");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
				}
			}
			else if(action == '3') {
				//search description input
				System.out.println("What is in the product description? ");
				sch_desc = input.nextLine();
				
				//search the db for product description
				System.out.println("Checking the db for your product\n...");
				
				rs = st.executeQuery("select * from hardwareStore where DESCRIPTION like '%" + sch_desc + "%'");
				System.out.println("Behold, the things! ");
				while(rs.next()) {
					String product_name = rs.getString("PRODUCT_NAME");
					String description = rs.getString("DESCRIPTION");
					String product_id = rs.getString("PRODUCT_ID");
					int qty = rs.getInt("QUANTITY");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
				}
			}
			else if(action == 'x' || action == 'X') {
				System.out.println("Okie dokie! Back to the menu we go!");
			}else {
				System.out.println("Whoops lol looks like you had a typo or something. :P");
			}

			}catch (Exception ex) {
				System.out.println("Whoops! " + ex.getMessage());
				Loggit.log(ex);
			}
		}
		
}
