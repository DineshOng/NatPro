package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.Compound;
import model.Family;
import model.Genus;
import model.MedicinalPlant;
import service.OntoQuery;

/**
 * Servlet implementation class ViewLocationServlet
 */
@WebServlet("/ViewBiologicalActivityServlet")
public class ViewBiologicalActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro.owl";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewBiologicalActivityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (request.getServletPath()) {
		case "/ViewBiologicalActivityServlet":
			try {
				viewBioAct(request, response);
			} catch (SQWRLException | OntologyLoadException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void viewBioAct(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, SQWRLException {
		// TODO Auto-generated method stub
		String bioAct = request.getParameter("bioact");
		String cellLine = request.getParameter("cellline");
		OntoQuery q = new OntoQuery(owlPath);
		List<String> compounds = q.getBioActCompound(bioAct, cellLine);
		
		request.setAttribute("compounds", compounds);
		request.setAttribute("bioact", bioAct);
		if(cellLine.isEmpty()) {
			cellLine = "blank";
		}
		request.setAttribute("cellline", cellLine);
		
		request.getRequestDispatcher("6fbioact.jsp").forward(request, response);
//		System.out.println(medPlants.get(0));
	}

}
