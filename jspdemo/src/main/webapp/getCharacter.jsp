<%@ page import="java.sql.*, java.util.*" %>
<%
    String name = request.getParameter("name");

    if (name != null && !name.trim().isEmpty()) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=JSPDatabase;encrypt=true;trustServerCertificate=true;",
                "Mehmet",
                "585858"
            );

            String query = "SELECT * FROM GOTCharacters WHERE Name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                out.println("<h3>Name: " + rs.getString("Name") + "</h3>");
                out.println("<h3>House: " + rs.getString("House") + "</h3>");
                out.println("<h3>Age: " + rs.getString("Age") + "</h3>");
            } else {
                out.println("<h3>No data found!</h3>");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
%>