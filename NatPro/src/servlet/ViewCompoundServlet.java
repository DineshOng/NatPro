package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.Compound;
import model.MedicinalPlant;
import service.OntoQuery;

/**
 * Servlet implementation class ViewPlantServlet
 */
@WebServlet("/ViewCompoundServlet")
public class ViewCompoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewCompoundServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		switch (request.getServletPath()) {
		case "/ViewCompoundServlet":
			try {
				viewCompound(request, response);
			} catch (OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("ERROR(Inside userServlet *doPost*): url pattern doesn't match existing patterns.");
		}
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

	private void viewCompound(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException {
		// TODO Auto-generated method stub
		String searchKey = request.getParameter("compound");
		System.out.println(searchKey);
		OntoQuery q = new OntoQuery();
		//List<MedicinalPlant> medPlants = q.searchMedicinalPlant(searchKey);
//		for(MedicinalPlant m: medPlants) {
//			System.out.println(m.getMedicinalPlant().toString());
//		}
		//System.out.println(medPlants.get(0).getSpecies());
		
		Compound compound = q.getCompound(searchKey);
		System.out.println(compound.getCompoundName());
		request.setAttribute("compound", compound);
		request.getRequestDispatcher("5dchemicalcompound.jsp").forward(request, response);
	}

}