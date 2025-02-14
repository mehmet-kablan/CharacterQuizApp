import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    
    List<String> characters = new LinkedList<String>();
    DBConnection dbCon = new DBConnection();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getSession().getAttribute("Theme") == "GOT") {
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
		else if(request.getSession().getAttribute("Theme") == "LOTR") {
			try {
				characters = dbCon.getAllLOTRCharacters();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
        LinkedList<String> suggestions = new LinkedList<String>();
        if (searchTerm == null || searchTerm.isEmpty()) {
            return suggestions;
        }

        String lowerSearchTerm = searchTerm.toLowerCase(); 

        for (String item : characters) {
            if (item.toLowerCase().contains(lowerSearchTerm)) { 
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
        String theme = request.getSession().getAttribute("Theme").toString();
        
        // Process the selected option (e.g., database query, business logic, etc.)
        String message = "You selected: " + selectedOption;
        Map<String, String> resultMap = null;
		try {
			if(theme == "GOT") {
				resultMap = dbCon.getGOTCharacterWithName(selectedOption);
				request.getSession().setAttribute("CharName", resultMap.get("Name"));
		   	 	request.getSession().setAttribute("House", resultMap.get("House"));
		   	 	request.getSession().setAttribute("Age", resultMap.get("Age"));
		   	 	request.getSession().setAttribute("MaritalStatus", resultMap.get("Relation"));
		   	 	request.getSession().setAttribute("FirstAppearance", resultMap.get("Episodes"));
		   	 	request.getSession().setAttribute("Culture", resultMap.get("FirstAppearance"));
		   	 	request.getSession().setAttribute("Religion", resultMap.get("Culture"));
			}
			else if (theme == "LOTR") {
				resultMap = dbCon.getLOTRCharacterWithName(selectedOption);
				request.getSession().setAttribute("CharName", resultMap.get("Name"));
		   	 	request.getSession().setAttribute("House", resultMap.get("House"));
		   	 	request.getSession().setAttribute("Age", resultMap.get("Age"));
		   	 	request.getSession().setAttribute("MaritalStatus", resultMap.get("MaritalStatus"));
		   	 	request.getSession().setAttribute("FirstAppearance", resultMap.get("FirstAppearance"));
		   	 	request.getSession().setAttribute("Culture", resultMap.get("Culture"));
		   	 	request.getSession().setAttribute("Religion", resultMap.get("Religion"));
			}
			
			
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
