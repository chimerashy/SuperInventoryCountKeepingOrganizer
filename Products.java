package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Products {

	private Connection connect = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String user = null;
	String pass = null;
	
	public void GetAllProds() {
		try {
			//connect to db
			System.out.println("Connecting to DB\n...");
			DB db = DB.getConnector(user, pass);
			Connection connect = db.getConnection();
			//statement to read from the db later
			st = connect.createStatement();
			rs = st.executeQuery("select * from hardwareStore");
			
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
