package com.myjava.jsp;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LOTRAPI {

	String RandomQuote = "";
	String Owner = "";
	public String[] SetVariables() throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./LOTRquotes.json"));
			StringBuilder apiResponse = new StringBuilder();
			String line;
            while ((line = reader.readLine()) != null) {
                apiResponse.append(line);
            }
            reader.close();
            
			//String content = new String(Files.readAllBytes(Paths.get("/LOTRquotes.json")));
			
			JSONArray jsonArray = new JSONArray(apiResponse.toString());
			Random rand = new Random();
			int randIndex = rand.nextInt(51);
			JSONObject jsonObject = new JSONObject(jsonArray.get(randIndex).toString());
			String quote = jsonObject.getString("quote");
			String owner = jsonObject.getString("owner");
			RandomQuote = quote;
			Owner = owner;

			String[] variables = {quote,owner};
			return variables;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JSONObject jsonObject = new JSONObject(LOTRquotes.json.toString());
		
		String[] variables = {"",""};
		return variables ;
	}
	
	public List<String> getLOTRCharactersList() throws IOException {
		List<String> characters = new ArrayList<String>();
		
		
		BufferedReader reader = new BufferedReader(new FileReader("./LOTRquotes.json"));
		StringBuilder apiResponse = new StringBuilder();
		String line;
        while ((line = reader.readLine()) != null) {
            apiResponse.append(line);
        }
        reader.close();
		
        JSONArray charactersJSON = new JSONArray(apiResponse.toString());
		
        for(int i = 0; i<charactersJSON.length();i++) {
        	
        	JSONObject character = charactersJSON.getJSONObject(i);

        	characters.add(character.get("owner").toString());
        	
        	
        }
		
        Set<String> set = new HashSet<>(characters);
        characters.clear();
        characters.addAll(set);
		return characters;
	}
	
	
}
