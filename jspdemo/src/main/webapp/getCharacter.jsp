<%@ page import="java.sql.*, java.util.*" %>
 <%@ page import="com.myjava.jsp.MyUtilFunctions" %>
<%

    String name = request.getParameter("name");
	String[] attributeNames = {"Name","Gender","Third","Fourth","Fifth","Sixth","Seventh","Eight"};
    if (name != null && !name.trim().isEmpty()) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=JSPDatabase;encrypt=true;trustServerCertificate=true;",
                "Mehmet",
                "585858"
            );
            String query = "";
			if(request.getSession().getAttribute("Theme") == "GOT"){query = "SELECT * FROM GOTCharacters WHERE Name = ?";}
			else if(request.getSession().getAttribute("Theme") == "LOTR") {query = "SELECT * FROM LOTRCharacters WHERE Name = ?";}
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            while (rs.next()) {
            	for(int i=0;i < attributeNames.length; i++){
            		String value = rs.getString(i+2).trim();
            		String playerInput = request.getSession().getAttribute(attributeNames[i]).toString().trim();
            		
            		if(MyUtilFunctions.isNumeric(value)){
            			switch(MyUtilFunctions.CompareEpisodes(playerInput, value)){
            			
            			case "Greater":
            				out.println("<h3 style = 'color:red' class = 'child inline-block-child'>&#8593;" + value + "&#8593;</h3>");
            				break;
            			case "Lesser":
            				out.println("<h3 style = 'color:red' class = 'child inline-block-child'>&#8595;" + value + "&#8595;</h3>");
            				break;
            			case "Equal":
            				out.println("<h3 style = 'color:green' class = 'child inline-block-child'>" + value + "</h3>");
            			}
            		}
            		
            		else{
            			if(playerInput.equals(value)){
                			out.println("<h3 style = 'color:green' class = 'child inline-block-child'>" + value + "</h3>");
                		}
                		else if (playerInput.contains(value) || value.contains(playerInput)) {
                			out.println("<h3 style = 'color:yellow' class = 'child inline-block-child'>" + value + "</h3>");
                		}
                		else{
                			out.println("<h3 style = 'color:red' class = 'child inline-block-child'>" + value + "</h3>");
                		}
            		}
            		
            		
            	}
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
    
    
%>