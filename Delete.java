package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String prod_id = null;
	String updt_qty = null;
	String updt_name = null;
	String updt_desc = null;
	String action = null;
	String user = null;
	String pass = null;
	String delete = "DELETE";
	int i = 1;
	
	public void DeleteProduct() {
		try {
		updater:
		do {
		//connect to db
		DB db = DB.getConnector(user, pass);
		Connection connect = db.getConnection();
		st = connect.createStatement();
		
		//get product id
		System.out.println("Enter the product ID# for the product you'd like to delete\nOr enter 'X' to quit to the menu: ");
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

		//get confirmation that they want to delete
		System.out.println("Please confirm that you would like to delete this product by entering 'DELETE'\nOr enter 'X' to quit to the menu:");
		action = input.nextLine();
			if(action.equals(delete)) {
				
				//statement to delete product
				ps = connect.prepareStatement("DELETE FROM hardwareStore where PRODUCT_ID = ?");
				
				ps.setString(1,  prod_id);
				
				//send the update to db
				System.out.println("Sending your update to the db\n...");
				ps.executeUpdate();
				
				System.out.println("Your product has been deleted :D");
				i = 1;

			}
			else if(action == "x" || action == "X") {
				System.out.println("Okie dokie! Back to the menu we go!");
			}else {
				System.out.println("Whoops lol looks like you had a typo or something. :P");
			}
			
		}while(action != "x");
		} catch (Exception ex) {
			System.out.println("Whoops! " + ex.getMessage());
			Loggit.log(ex);
		}
	}
}
