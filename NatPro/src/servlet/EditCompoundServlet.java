package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.Compound;
import service.OntoQuery;

/**
 * Servlet implementation class EditCompoundServlet
 */
@WebServlet("/EditCompoundServlet")
public class EditCompoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCompoundServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String cc = request.getParameter("compound").trim();
		OntoQuery oq;
		try {
			oq = new OntoQuery();
			
			Compound compound = oq.getCompound(cc);
			
			//request.setAttribute("compound", compound);
			request.setAttribute("action", "SaveCompoundServlet");
			request.setAttribute("compound", compound);
			
			request.getRequestDispatcher("add-compound.jsp").forward(request, response);
			
		} catch (OntologyLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		String pubCID = request.getParameter("pubCID").trim();
		request.setAttribute("pubCID", pubCID);
		String molForm = request.getParameter("molForm").trim();
		request.setAttribute("molForm", molForm);
		String canSMILES = request.getParameter("canSMILES").trim();
		request.setAttribute("canSMILES", canSMILES);
		String inchi = request.getParameter("inchi").trim();
		request.setAttribute("inchi", inchi);
		String inchikey = request.getParameter("inchikey").trim();
		request.setAttribute("inchikey", inchikey);
		String iupac = request.getParameter("iupac").trim();
		request.setAttribute("iupac", iupac);
		
		String molWeight = request.getParameter("molWeight").trim();
		request.setAttribute("molWeight", molWeight);
		String xlogp = request.getParameter("xlogp").trim();
		request.setAttribute("xlogp", xlogp);
		String mass = request.getParameter("mass").trim();
		request.setAttribute("mass", mass);
		String tpsa = request.getParameter("tpsa").trim();
		request.setAttribute("tpsa", tpsa);
		String complexity = request.getParameter("complexity").trim();
		request.setAttribute("complexity", complexity);
		
		String charge = request.getParameter("charge").trim();
		request.setAttribute("charge", charge);
		String hBondDonor = request.getParameter("hBondDonor").trim();
		request.setAttribute("hBondDonor", hBondDonor);
		String hBondAcceptor = request.getParameter("hBondAcceptor").trim();
		request.setAttribute("hBondAcceptor", hBondAcceptor);
		String rotBondCount = request.getParameter("rotBondCount").trim();
		request.setAttribute("rotBondCount", rotBondCount);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
