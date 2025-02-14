<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.myjava.jsp.DBConnection" %>
<%@ page import="java.util.Map" %>
 <%
 
 DBConnection dbREF = new DBConnection();
 
 
 Map<String, String> resultMap;
 if (request.getParameter("GOTGuessBtn") != null) {
	
	 request.getSession().setAttribute("Theme", "GOT");
	 
	 resultMap = DBConnection.getGOTCharacter();
	 request.getSession().setAttribute("Name", resultMap.get("Name"));
	 request.getSession().setAttribute("Gender", resultMap.get("Gender"));
	 request.getSession().setAttribute("Third", resultMap.get("House"));
	 request.getSession().setAttribute("Fourth", resultMap.get("Age"));
	 request.getSession().setAttribute("Fifth", resultMap.get("Relation"));
	 request.getSession().setAttribute("Sixth", resultMap.get("Episodes"));
	 request.getSession().setAttribute("Seventh", resultMap.get("FirstAppearance"));
	 request.getSession().setAttribute("Eight", resultMap.get("Culture"));
	 
	 request.getSession().setAttribute("FirstAttribute", "Name");
	 request.getSession().setAttribute("SecondAttribute", "Gender");
	 request.getSession().setAttribute("ThirdAttribute", "House");
	 request.getSession().setAttribute("FourthAttribute", "Age");
	 request.getSession().setAttribute("FifthAttribute", "Relation");
	 request.getSession().setAttribute("SixthAttribute", "Episodes");
	 request.getSession().setAttribute("SeventhAttribute", "First Appearance");
	 request.getSession().setAttribute("EightAttribute", "Culture");
	 

	}
	else if(request.getParameter("LOTRGuessBtn") != null){
    	
		request.getSession().setAttribute("Theme", "LOTR");
		
		resultMap = DBConnection.getLOTRCharacter();
		request.getSession().setAttribute("Name", resultMap.get("Name"));
		request.getSession().setAttribute("Gender", resultMap.get("Gender"));
		request.getSession().setAttribute("Third", resultMap.get("Race"));
		request.getSession().setAttribute("Fourth", resultMap.get("Allegiance"));
		request.getSession().setAttribute("Fifth", resultMap.get("Weapon"));
		request.getSession().setAttribute("Sixth", resultMap.get("Culture"));
		request.getSession().setAttribute("Seventh", resultMap.get("VitalStatus"));
		request.getSession().setAttribute("Eight", resultMap.get("MaritalStatus"));
	
		
		request.getSession().setAttribute("FirstAttribute", "Name");
		request.getSession().setAttribute("SecondAttribute", "Gender");
		request.getSession().setAttribute("ThirdAttribute", "Race");
		request.getSession().setAttribute("FourthAttribute", "Allegiance");
		request.getSession().setAttribute("FifthAttribute", "Weapon");
		request.getSession().setAttribute("SixthAttribute", "Culture");
		request.getSession().setAttribute("SeventhAttribute", "Vital Status");
		request.getSession().setAttribute("EightAttribute", "Marital Status");
		
	}
 
 %>
    
    
<!DOCTYPE html>
<html>
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
    
    }
    
.inline-block-child {
  display: inline-block;
  margin: 20px;
}

h3 {

	width: 75px;

}
</style>
<head>
<meta charset="UTF-8">
<title>Guessing Game</title>
</head>
<body>
<script src = "https://code.jquery.com/jquery-3.6.3.min.js"></script>

<form class = "parent" method = "post">

<button class = "child inline-block-child" type="submit" name = "GOTGuessBtn">Guess GOT Character</button>
<button class = "child inline-block-child" type="submit" name = "LOTRGuessBtn">Guess LOTR Character</button>

</form>

<div class = "parent" id = "Character">

	<h3 class = 'child inline-block-child'>${Name}</h3> 
	<h3 class = 'child inline-block-child'>${Gender}</h3> 
	<h3 class = 'child inline-block-child'>${Third}</h3>
	<h3 class = 'child inline-block-child'>${Fourth}</h3>
	<h3 class = 'child inline-block-child'>${Fifth}</h3>
	<h3 class = 'child inline-block-child'>${Sixth}</h3>
	<h3 class = 'child inline-block-child'>${Seventh}</h3>
	<h3 class = 'child inline-block-child'>${Eight}</h3>
	
</div>

<form method = "post">
	
  	<div class="search-container">
    <input type="text" id="searchBox" placeholder="Type to search...">
    <select id="suggestions" size = "1">
	<option disabled selected> -- select an option -- </option>
    </select>
	</div>
  	
</form>
 


<div class = "parent" id = "Character">

	<h3 class = 'child inline-block-child'>${FirstAttribute}</h3> 
	<h3 class = 'child inline-block-child'>${SecondAttribute}</h3> 
	<h3 class = 'child inline-block-child'>${ThirdAttribute}</h3>
	<h3 class = 'child inline-block-child'>${FourthAttribute}</h3>
	<h3 class = 'child inline-block-child'>${FifthAttribute}</h3>
	<h3 class = 'child inline-block-child'>${SixthAttribute}</h3>
	<h3 class = 'child inline-block-child'>${SeventhAttribute}</h3>
	<h3 class = 'child inline-block-child'>${EightAttribute}</h3>
	
</div>

<p id="result"></p>


<script src = "GuessingJS.js"></script>
</body>
</html>