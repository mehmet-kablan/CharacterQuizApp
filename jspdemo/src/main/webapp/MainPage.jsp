<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
    flex-direction: column;
    justify-content: center; 
    align-items: center;  
    
    }

.inline-block-child {
  display: inline-block;
  margin: 20px;
}
</style>
<body>


<form class = "child inline-block-child" action="GuessingGame.jsp">
<button class = "child inline-block-child">guess</button>
</form>

<form class = "child inline-block-child" action="deneme.jsp">
<button class = "child inline-block-child">quote</button>
</form>
</body>
</html>