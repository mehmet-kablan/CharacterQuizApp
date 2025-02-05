package com.myjava.jsp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GotAPI {

	
	String apiUrl = "https://api.gameofthronesquotes.xyz/v1/random";
	String characterApiUrl = "https://api.gameofthronesquotes.xyz/v1/characters";
	StringBuilder apiResponse = new StringBuilder();
	StringBuilder characterApiResponse = new StringBuilder();
	String quoteText = ""; 
	String owner = "";
	String ownerName = "";
	
	public String[] SetVariables() {
	try {
        // Create a URL object
        URL url = new URL(apiUrl);

        // Open a connection to the API
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        // Check the response code
        if (conn.getResponseCode() == 200) {
            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                apiResponse.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonObject = new JSONObject(apiResponse.toString());
            quoteText = jsonObject.getString("sentence"); 
            owner = jsonObject.getJSONObject("character").getString("name");
            //int index = owner.indexOf(' ');
            //ownerName = (index != -1) ? owner.substring(0, index) : owner;
            
        } else {
            quoteText = "Error: API returned status code " + conn.getResponseCode();
        }
        
        conn.disconnect();
    }catch (Exception e) {
        quoteText = "Error: " + e.getMessage();
    }
	
	String[] variables = {quoteText,owner};
	return variables;
  }
	
	public List<String> getCharactersList() {
		List<String> characters = new ArrayList<String>();
		
		try {
	        // Create a URL object
	        URL url = new URL(characterApiUrl);

	        // Open a connection to the API
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

	        // Check the response code
	        if (conn.getResponseCode() == 200) {
	            // Read the response
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	characterApiResponse.append(line);
	            }
	            reader.close();

	            // Parse JSON response
	            JSONArray charactersJSON = new JSONArray(characterApiResponse.toString());
	            for(int i = 0; i<charactersJSON.length();i++) {
	            	
	            	JSONObject character = charactersJSON.getJSONObject(i);


	            	characters.add(character.get("name").toString());
	            	
	            }
	            
	        } else {
	        	
	            quoteText = "Error: API returned status code " + conn.getResponseCode();
	        }
	        
	        conn.disconnect();
	    }catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
		return characters;
	}
	
}


