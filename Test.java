package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
	public String prodtest = "Kingston";
	public String desctest = "Technical details n such";
	public int qtytest = 5;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String user;
	private String password;
	
	

	public void TestWrite() {
		
		try {
			//String err = "this should throw an error"
			
			/*
			
			
			//connect to db
			DB db = DB.getConnector("root", "Burnkeyblueberrycrumble1515@");
			Connection connect = db.getConnection();
			
			
			st = connect.createStatement();
			//ps = connect.prepareStatement("select * from hardwareStore where PRODUCT_NAME = ?");
			ps = connect.prepareStatement("insert into hardwareStore(PRODUCT_NAME, DESCRIPTION, QUANTITY) values(?, ?, ?)");
			
			ps.setString(1, prod_name);
			ps.setString(2, prod_desc);
			ps.setString(3, prod_qty);
			
			
			//ps.setString(1, prodtest);
			//ps.setString(2, desctest);
			//ps.setInt(3, qtytest);
			ps.executeUpdate();
			
			rs = st.executeQuery("select * from hardwareStore where PRODUCT_NAME = 'Test'");
			rs = st.executeQuery("select * from hardwareStore where PRODUCT_NAME = '" +  prod_name + "'");
			
			
			while(rs.next()) {
				String product_name = rs.getString("PRODUCT_NAME");
				String description = rs.getString("DESCRIPTION");
				int qty = rs.getInt("QUANTITY");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Product: " + product_name + "\nDescription: " + description + "\nQuantity: " + qty);
			}
			*/
			
			/*

			//grab prod info
			String product_name = rs.getString("PRODUCT_NAME");
			String description = rs.getString("DESCRIPTION");
			int qty = rs.getInt("QUANTITY");
			//print to verify
			System.out.println("Product: " + tester + "\nDescription: " + description + "\nQuantity: " + qty);
			*/
			
		} catch(Exception ex) {
			System.out.println("Whoops! " + ex.getMessage());
			Loggit.log(ex);
		}
	}
	
}


