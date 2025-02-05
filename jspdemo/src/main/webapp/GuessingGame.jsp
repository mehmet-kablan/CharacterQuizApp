<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.myjava.jsp.DBConnection" %>
<%@ page import="java.util.Map" %>
 <%
 
 DBConnection dbREF = new DBConnection();
 
 Map<String, String> resultMap = DBConnection.getGOTCharacter();

 if (request.getParameter("GOTGuessBtn") != null) {

	 request.setAttribute("CharName", resultMap.get("Name"));
	 request.setAttribute("House", resultMap.get("House"));
	 request.setAttribute("Age", resultMap.get("Age"));
	 request.setAttribute("MaritalStatus", resultMap.get("MaritalStatus"));
	 request.setAttribute("FirstAppearance", resultMap.get("FirstAppearance"));
	 request.setAttribute("Culture", resultMap.get("Culture"));
	 request.setAttribute("Religion", resultMap.get("Religion"));
	}
	else if(request.getParameter("LOTRBtn") != null){
    	
		}
 
 %>
    
    
<!DOCTYPE html>
<html>
<style>

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

<form method = "post">
	
  	<div class="search-container">
    <input type="text" id="searchBox" placeholder="Type to search...">
    <select id="suggestions" size = "1">
        <option>               </option>
    </select>
	</div>
  	
	</form>
 <p id="result"></p>


<div class = "parent" id = "Character">

	<h3 class = 'child inline-block-child'>Name</h3> 
	<h3 class = 'child inline-block-child'>House</h3>
	<h3 class = 'child inline-block-child'>Age</h3>
	<h3 class = 'child inline-block-child'>Marital Status</h3>
	<h3 class = 'child inline-block-child'>First Appearance</h3>
	<h3 class = 'child inline-block-child'>Religion</h3>
</div>

<div class = "parent" id = "Character">

	<h3 class = 'child inline-block-child'>${CharName}</h3> 
	<h3 class = 'child inline-block-child'>${House}</h3>
	<h3 class = 'child inline-block-child'>${Age}</h3>
	<h3 class = 'child inline-block-child'>${MaritalStatus}</h3>
	<h3 class = 'child inline-block-child'>${FirstAppearance}</h3>
	<h3 class = 'child inline-block-child'>${Religion}</h3>
	
</div>

<script src = "GuessingJS.js"></script>
</body>
</html>