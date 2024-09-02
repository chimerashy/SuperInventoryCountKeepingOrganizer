package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LowStockAlert {
	
	private Connection connect = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	public int i = 0;
	
	public void LSA() {
		try {
			//connect to db
			DB db = DB.getConnector("root", "Burnkeyblueberrycrumble1515@");
			Connection connect = db.getConnection();
			
			//statement to read from the db later
			st = connect.createStatement();
			rs = st.executeQuery("select * from hardwareStore where QUANTITY <= 100");
			
			while(rs.next()) {
				i++;
			}
			
			if(i > 1) {
			System.out.println("<<<~~ Just so's ya know, there's " + i + " items that are low in stock. ~~>>>");
			}
			else if(i == 1) {
				System.out.println("<<<~~ Just so's ya know, there's " + i + " item that is low in stock. ~~>>>");	
			}
			
		} catch(Exception ex) {
			System.out.println("Whoops! " + ex.getMessage());
			Loggit.log(ex);
		}
	}
}
