package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String prod_id = null;
	String updt_qty = null;
	String updt_name = null;
	String updt_desc = null;
	String user = null;
	String pass = null;
	char action = 'x';
	int i = 1;
	
	public void UpdateProduct() {
		try {
		updater:
		do {
		//connect to db
		DB db = DB.getConnector(user, pass);
		Connection connect = db.getConnection();
		st = connect.createStatement();
		
		//get product id
		System.out.println("Enter the product ID# for the product you'd like to update\nOr enter 'X' to quit to the menu: ");
		Scanner input = new Scanner(System.in);
		prod_id = input.nextLine();
		
		//option to quit instead of proceeding
		if(prod_id == "x" || prod_id == "X"){
			break updater;
		}else {

			//get product so user can validate that it's the right one
			rs = st.executeQuery("select * from hardwareStore where PRODUCT_ID = '" +  prod_id + "'");
			while(rs.next()) {
				String product_name = rs.getString("PRODUCT_NAME");
				String description = rs.getString("DESCRIPTION");
				String product_id = rs.getString("PRODUCT_ID");
				int qty = rs.getInt("QUANTITY");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
				i--;
			}
			//make sure their product ID exists, go to menu if not
			if (i == 1) {
				System.out.println("Oh no, looks like that ID couldn't be found!\nMaybe try searching the product to make sure you have the correct ID?");
				break updater;
			}
		}

		//get their desired action
		System.out.println("What would you like to do next?\n1: Update Qty\n2: Rename product\n3: Insert new description\nX: Quit to menu");
		action = input.nextLine().charAt(0);
			if(action == '1') {
				//update qty code
				System.out.println("What is the new quantity? ");
				updt_qty = input.nextLine();
				//testing why my input was skipped
				//System.out.println("Qty: " + updt_qty);
				
				//statement to insert new product qty, ? will be set with setString
				ps = connect.prepareStatement("update hardwareStore set QUANTITY = ? where PRODUCT_ID = ?");
				
				ps.setString(1, updt_qty);
				ps.setString(2,  prod_id);
				
				//send the new product to db
				System.out.println("Sending your update to the db\n...");
				ps.executeUpdate();
				
				rs = st.executeQuery("select * from hardwareStore where PRODUCT_ID = '" +  prod_id + "'");
				System.out.println("Behold, your updates!! ");
				while(rs.next()) {
					String product_name = rs.getString("PRODUCT_NAME");
					String description = rs.getString("DESCRIPTION");
					String product_id = rs.getString("PRODUCT_ID");
					int qty = rs.getInt("QUANTITY");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
				}

			} 
			else if(action == '2') {
				//rename prod code
				System.out.println("What is the new name? ");
				updt_name = input.nextLine();
				
				//statement to insert new product name, ? will be set with setString
				ps = connect.prepareStatement("update hardwareStore set PRODUCT_NAME = ? where PRODUCT_ID = ?");
				
				ps.setString(1, updt_name);
				ps.setString(2,  prod_id);
				
				//send the new product to db
				System.out.println("Sending your update to the db\n...");
				ps.executeUpdate();
				
				rs = st.executeQuery("select * from hardwareStore where PRODUCT_ID = '" +  prod_id + "'");
				System.out.println("Behold, your updates!! ");
				while(rs.next()) {
					String product_name = rs.getString("PRODUCT_NAME");
					String description = rs.getString("DESCRIPTION");
					String product_id = rs.getString("PRODUCT_ID");
					int qty = rs.getInt("QUANTITY");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
				}
			}
			else if(action == '3') {
				//new desc code
				System.out.println("What is the new Description? ");
				updt_desc = input.nextLine();
				
				//statement to insert new product DESCRIPTION, ? will be set with setString
				ps = connect.prepareStatement("update hardwareStore set DESCRIPTION = ? where PRODUCT_ID = ?");
				
				ps.setString(1, updt_desc);
				ps.setString(2,  prod_id);
				
				//send the new product to db
				System.out.println("Sending your update to the db\n...");
				ps.executeUpdate();
				
				rs = st.executeQuery("select * from hardwareStore where PRODUCT_ID = '" +  prod_id + "'");
				System.out.println("Behold, your updates!! ");
				while(rs.next()) {
					String product_name = rs.getString("PRODUCT_NAME");
					String description = rs.getString("DESCRIPTION");
					String product_id = rs.getString("PRODUCT_ID");
					int qty = rs.getInt("QUANTITY");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("ID#: " + product_id + "\nProduct: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");				
				}
			}
			else if(action == 'x' || action == 'X') {
				System.out.println("Okie dokie! Back to the menu we go!");
			}else {
				System.out.println("Whoops lol looks like you had a typo or something. :P");
			}
			
		}while(action != 'x');
		} catch (Exception ex) {
			System.out.println("Whoops! " + ex.getMessage());
			Loggit.log(ex);
		}
	}
}
