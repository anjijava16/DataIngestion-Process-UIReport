package com.iws.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
	
		
	
	private static Connection connection = null;

	public static Connection getConnection(String driver,	String url,String user ,	String password ) {
		System.out.println("Connection method is stared");
		if (connection != null)
			return connection;
		else {
			try {
				// KEY VALUE (EX: jdbc.driverClassName  Value com.mysql.jdbc.Driver
				
			/*	String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/retail_db";
				String user = "root";
				String password ="root";*/
			
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Error into the dbUtils:db error" + e.getMessage());
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error into the dbUtils:db error" + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error into the dbUtils:file not found"
						+ e.getMessage());
			} 
			System.out.println("Connection method is ended");
			return connection;
		}

	}
	
	public static Integer getMaxId(String driver,	String url,String user ,	String password){
		
		int maxId=0;
		try {
			Connection con=DbUtils.getConnection(driver,url,user,password);

			String insertQuery="select max(order_item_id) as maxId from order_items";
			
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(insertQuery);
			
			while(rs.next()){
				maxId=rs.getInt("maxId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxId;
	}

	public static void process(String driver,	String url,String user ,	String password) {
		try{
		Connection con=DbUtils.getConnection(driver,url,user,password);
		int maxPlusId=getMaxId(driver,url,user,password)+1;
		for(int i=maxPlusId ;i<=997219800;i++){
		String insertQuery="insert into order_items (order_item_id,order_item_order_id,order_item_product_id,order_item_quantity,order_item_subtotal,order_item_product_price) values("+i+",10,20,30,40,50)";
		Statement stmt=con.createStatement();
		int x=stmt.executeUpdate(insertQuery);
		}
		System.out.println(con.getClass());//class com.mysql.jdbc.JDBC4Connection
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}