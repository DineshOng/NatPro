package servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import jena.qtest;
import model.Compound;
import service.OntoQuery;

/**
 * Servlet implementation class StructureSearch
 */
@WebServlet("/StructureSearch_nix")
public class StructureSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StructureSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		OntoQuery q;
		try {
			q = new OntoQuery();
			
			List<Compound> data = q.getCompoundwStructure();
			int size = data.size();
			
			request.setAttribute("size", size);
			request.setAttribute("data", data);
			
		} catch (OntologyLoadException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("structSearch_nix.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
