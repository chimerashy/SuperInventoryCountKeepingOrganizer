package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Create {

	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String newProdName = null;
	String newProdDesc = null;
	String newProdQty = null;
	
	public void CreateNewProduct() {
		try {
			//get product name, description, qty from user
			System.out.println("Enter the product name: ");
			Scanner input = new Scanner(System.in);
			newProdName = input.nextLine();
			
			System.out.println("Enter the product description: ");
			newProdDesc = input.nextLine();
			
			System.out.println("Enter the product quantity: ");
			newProdQty = input.nextLine();
			
			//connect to db
			System.out.println("Connecting to DB\n...");
			DB db = DB.getConnector("root", "Burnkeyblueberrycrumble1515@");
			Connection connect = db.getConnection();
			//statement to read from the db later
			st = connect.createStatement();
			
			//statement to insert new product, ? will be set with setString
			ps = connect.prepareStatement("insert into hardwareStore(PRODUCT_NAME, DESCRIPTION, QUANTITY) values(?, ?, ?)");
			
			
			ps.setString(1, newProdName);
			ps.setString(2, newProdDesc);
			ps.setString(3, newProdQty);
			
			//send the new product to db
			System.out.println("Sending your new product to the db\n...");
			ps.executeUpdate();
			
			//validate
			rs = st.executeQuery("select * from hardwareStore where PRODUCT_NAME = '" +  newProdName + "'");
			System.out.println("Product added to the db: ");
			
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
}