<%@ page import="java.io.BufferedReader, java.io.InputStreamReader, java.net.HttpURLConnection, java.net.URL" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="com.myjava.jsp.GotAPI" %>
<%@ page import="com.myjava.jsp.LOTRAPI" %>
<%@ page import="com.myjava.jsp.DBConnection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>

<%
    GotAPI gotAPI = new GotAPI();
	String[] gotQuotes = gotAPI.SetVariables();
    String GOTQuote = gotQuotes[0];
    String GOTOwner = gotQuotes[1];
    
    
    LOTRAPI lotrAPI = new LOTRAPI();
    String[] lotrQuotes = lotrAPI.SetVariables();
    String lotrQuote = lotrQuotes[0];
    String lotrOwner = lotrQuotes[1];
    lotrAPI.getLOTRCharactersList();
    
    List<String> myList = gotAPI.getCharactersList();
    request.setAttribute("myList", myList);

    if (request.getParameter("GOTBtn") != null) {
           
		String GOTQuoteText = "<p><strong>Quote:</strong>" + GOTQuote + "</p>" ;
            
		request.setAttribute("QuoteText", GOTQuoteText);
        	
		session.setAttribute("Answer", GOTOwner);
        	
		session.setAttribute("theme", "GOT");
        	
		session.setAttribute("backgroundImage", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEh0WvmlhJRV5kVAF5KHiCkcFJuYQM6isyoMCjrOQ337pMNxdRt9fWuYBW-mNjrdb9aQMmBTBLRTV-43mZctZGtGkQIq413jngTt8KmyVQxt7gIZcfed1LhrMSQf8euBs52fc5-djgsqMEs7/w3840-h2400-c/game-of-thrones-season-8-cast-and-characters-uhdpaper.com-4K-57.jpg");
	}
	else if(request.getParameter("LOTRBtn") != null){
        	
		String LOTRQuoteText = "<p><strong>Quote:</strong>" + lotrQuote + "</p>" ;
            
		request.setAttribute("QuoteText", LOTRQuoteText);
        	
		session.setAttribute("Answer", lotrOwner);
        	
		session.setAttribute("theme", "LOTR");
        	
		session.setAttribute("backgroundImage", "file:///C:/Users/mehme/Desktop/Screenshot_2.png");
	}
    
%>
    
<!DOCTYPE html>
<html>
<head>
    <title>Quote quiz</title>
</head>


<style>

.search-container {
    display: flex;
    flex-direction: column;
    width: 250px; 
}
body {
    display: flex;
    flex-direction: column;
    justify-content: center; 
    align-items: center;  
    background-image: url(<%= "\"" + session.getAttribute("backgroundImage") + "\"" %>);
    background-size: cover;
    background-repeat: no-repeat;
    
    }
	
.box {
  border: 2px solid rgba(0, 0, 0, 0.5); /* Semi-transparent border */
  background-color: rgba(0, 0, 0, 0.5); /* Light blue with low opacity */
  padding: 15px; /* Space inside the box */
  width: fit-content; /* Adjust to content size */
  max-width: 450px; /* Limit width */
  border-radius: 8px; /* Rounded corners */
  font-size:125%;
}

.box.notVisible {
  border-color: rgba(0, 0, 0, 0.0);
  background-color: rgba(0, 0, 0, 0);
}
</style>

<body>

<script src = "https://code.jquery.com/jquery-3.6.3.min.js"></script>

	<h1 style="color:white" class = "box">Random Quote Quiz</h1>

	<form method="post">
        <button type="submit" name="GOTBtn">GOT</button>
        
        <button type="submit" name="LOTRBtn">LOTR</button>
    </form>
	
    
    <div style="color:white" class = "box" id = "quoteDiv">${QuoteText}</div>
	<script>
	
	</script>
	<form method = "post">
	
  	<div class="search-container">
    <input type="text" id="searchBox" placeholder="Type to search...">
    <select id="suggestions" size = "1" onClick="setTextbox(this)">
        <option>               </option>
    </select>
	</div>
  	
	</form>
	<div style="color:white" class = "box" id="result"></div>
  	
  	<button onClick = "compareText()"> Guess </button>

    <script src = "ServerProcesses.js"></script>
</body>
</html>
