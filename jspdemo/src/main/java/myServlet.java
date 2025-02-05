

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.myjava.jsp.DBConnection;
import com.myjava.jsp.GotAPI;
import com.myjava.jsp.LOTRAPI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class myServlet
 */
@WebServlet("/myServlet")
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    List<String> characters;
    GotAPI gotAPI = new GotAPI();
    LOTRAPI lotrAPI = new LOTRAPI();
    DBConnection dbCon = new DBConnection();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
		if(request.getSession().getAttribute("theme") == "GOT") {
			characters = gotAPI.getCharactersList();
		}
		else if(request.getSession().getAttribute("theme") == "LOTR") {
			characters = lotrAPI.getLOTRCharactersList();
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
        
		String answerText = request.getParameter("answerText");
        String comparisonResult;
        if (answerText != null) {
            int comparison = answerText.compareTo(request.getSession().getAttribute("Answer").toString()); //String comparison
            if (comparison == 0) {
                comparisonResult = "equal";
            } else if (comparison > 0) {
                comparisonResult = "greater";
            } else {
                comparisonResult = "lesser";
            }
            response.getWriter().write(comparisonResult); // Send the result back to the client
        } else {
            response.getWriter().write("error"); // Send an error message
        }
       
        
        
        
	}

}
