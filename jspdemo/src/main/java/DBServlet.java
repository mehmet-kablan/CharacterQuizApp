import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import com.myjava.jsp.DBConnection;

/**
 * Servlet implementation class DBServlet
 */
@WebServlet("/DBServlet")
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    List<String> characters;
    DBConnection dbCon = new DBConnection();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			characters = dbCon.getAllGOTCharacters();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(request.getSession().getAttribute("theme") == "GOT") {
			try {
				characters = dbCon.getAllGOTCharacters();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getSession().getAttribute("theme") == "LOTR") {
			//characters = lotrAPI.getLOTRCharactersList();
		}
		String searchTerm = request.getParameter("search");

        List<String> suggestions = getSuggestions(searchTerm);
        
        response.setContentType("text/plain"); // Important!
        StringBuilder sb = new StringBuilder();
        for (String item : suggestions) {
            sb.append(item).append("\n"); // Newline delimiter
        }
        response.getWriter().write(sb.toString());
	}
	
	private List<String> getSuggestions(String searchTerm) {
        List<String> suggestions = new ArrayList<String>();
        if (searchTerm == null || searchTerm.isEmpty()) {
            return suggestions; // Return empty list if search term is null or empty
        }

        String lowerSearchTerm = searchTerm.toLowerCase(); // For case-insensitive search

        for (String item : characters) {
            if (item.toLowerCase().contains(lowerSearchTerm)) { // Case-insensitive contains
                suggestions.add(item);
            }
        }
        return suggestions;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String selectedOption = request.getParameter("selectedOption");
        
        // Process the selected option (e.g., database query, business logic, etc.)
        String message = "You selected: " + selectedOption;
        Map<String, String> resultMap = null;
		try {
			resultMap = dbCon.getGOTCharacterWithName(selectedOption);
			request.getSession().setAttribute("CharName", resultMap.get("Name"));
	   	 	request.getSession().setAttribute("House", resultMap.get("House"));
	   	 	request.getSession().setAttribute("Age", resultMap.get("Age"));
	   	 	request.getSession().setAttribute("MaritalStatus", resultMap.get("MaritalStatus"));
	   	 	request.getSession().setAttribute("FirstAppearance", resultMap.get("FirstAppearance"));
	   	 	request.getSession().setAttribute("Culture", resultMap.get("Culture"));
	   	 	request.getSession().setAttribute("Religion", resultMap.get("Religion"));
	        response.setContentType("text/plain");
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        response.getWriter().write(message);
	}

}
