package com.myjava.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=JSPDatabase;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "Mehmet";
    private static final String PASSWORD = "585858";

    public static Map<String, String> getGOTCharacter() {
    	Connection conn = null;
    	Map<String, String> resultMap = new HashMap<>();
        try {
            // Load the SQL Server JDBC Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Establish the connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            Statement stmt = conn.createStatement();
            Random rand = new Random();
            int randomID = rand.nextInt(1,10);
            String query = "SELECT * FROM GOTCharacters WHERE ID = '" + randomID + "'";
            ResultSet rs = stmt.executeQuery(query);
            resultMap = new HashMap<>();
            
            while (rs.next()) {

            	resultMap.put("Name", rs.getString("Name"));
            	resultMap.put("House", rs.getString("House"));
            	resultMap.put("Age", rs.getString("Age"));
            	resultMap.put("MaritalStatus", rs.getString("MaritalStatus"));
            	resultMap.put("FirstAppearance", rs.getString("FirstAppearance"));
            	resultMap.put("Culture", rs.getString("Culture"));
            	resultMap.put("Religion", rs.getString("Religion"));
            	
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("Connected to Database!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Connection failed!");
            e.printStackTrace();
        }
        
        
        return resultMap;
    }
    
    public List<String> getAllGOTCharacters() throws SQLException, ClassNotFoundException{
    	List<String> characters = new ArrayList<String>();
    	
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        String query = "SELECT Name FROM GOTCharacters";
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()) {
        	characters.add(rs.getString(1));
        }
        
    	return characters;
    }
    
    public Map<String, String> getGOTCharacterWithName(String name) throws SQLException, ClassNotFoundException{
    	
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //Statement stmt = conn.createStatement();
        String query = "SELECT * FROM GOTCharacters WHERE CAST(Name AS VARCHAR(MAX)) = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, name.trim());
        ResultSet rs = stmt.executeQuery();
        Map<String, String> resultMap = new HashMap<>();
        while(rs.next()) {
        	System.out.println(rs.getString("Name"));
        	resultMap.put("Name", rs.getString("Name"));
        	resultMap.put("House", rs.getString("House"));
        	resultMap.put("Age", rs.getString("Age"));
        	resultMap.put("MaritalStatus", rs.getString("MaritalStatus"));
        	resultMap.put("FirstAppearance", rs.getString("FirstAppearance"));
        	resultMap.put("Culture", rs.getString("Culture"));
        	resultMap.put("Religion", rs.getString("Religion"));
        }
        
        return resultMap;
    }
  

}