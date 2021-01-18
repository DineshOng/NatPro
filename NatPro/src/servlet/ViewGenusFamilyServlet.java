package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flickr4java.flickr.FlickrException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.MedicinalPlant;
import service.OntoQuery;

/**
 * Servlet implementation class viewGenusServlet
 */
@WebServlet({ "/ViewGenusServlet", "/ViewFamilyServlet" })
public class ViewGenusFamilyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewGenusFamilyServlet() {
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
		case "/ViewGenusServlet":
			try {
				viewGenus(request, response);
			} catch (OntologyLoadException | ServletException | IOException | SQWRLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		case "/ViewFamilyServlet":
			try {
				viewFamily(request, response);
			} catch (SQWRLException | OntologyLoadException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		default:
			System.out.println("URL pattern doesn't match existing patterns.");
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

	private void viewGenus(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, SQWRLException {
		String genus = request.getParameter("genus");
		// TODO Auto-generated method stub
		OntoQuery q = new OntoQuery();
		List<MedicinalPlant> medPlants = q.searchMedicinalPlant(""); // get all plants
		List<MedicinalPlant> genusMedPlants = new ArrayList<MedicinalPlant>(); // get plants with the same genus
		for (int i = 0; i < medPlants.size(); i++) {
			for (int j = 0; j < medPlants.get(i).getSpecies().size(); j++) {
				try {
					if (medPlants.get(i).getSpecies().get(j).getGenus().equals(genus)) {
						genusMedPlants.add(medPlants.get(i));
					}
				} catch (Exception e) {

				}
			}
		}
		String family;
		try {
			family = q.getGenusFamily(genus).get(0);
		} catch (Exception e) {
			family = "";
		}
		request.setAttribute("medPlantsList", genusMedPlants);
		request.setAttribute("genus", genus);
		request.setAttribute("family", family);
		List<String> familyList = q.getAllFamily();
		request.setAttribute("allFamilyList", familyList);
		request.getRequestDispatcher("5bgenus.jsp").forward(request, response);
	}

	private void viewFamily(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, SQWRLException {
		String family = request.getParameter("family");
		// TODO Auto-generated method stub
		OntoQuery q = new OntoQuery();
		List<MedicinalPlant> medPlants = q.searchMedicinalPlant(""); // get all plants
		List<MedicinalPlant> familyMedPlants = new ArrayList<MedicinalPlant>(); // get plants with the same family
		for (int i = 0; i < medPlants.size(); i++) {
			for (int j = 0; j < medPlants.get(i).getSpecies().size(); j++) {
				try {
					if (medPlants.get(i).getSpecies().get(j).getFamily().equals(family)) {
						familyMedPlants.add(medPlants.get(i));
					}
				} catch (Exception e) {

				}
			}
		}
		request.setAttribute("medPlantsList", familyMedPlants);

		List<String> genusList = q.getFamilyGenus(family);
		request.setAttribute("genusList", genusList);

		List<String> genus = q.getAllGenus();
		request.setAttribute("allGenusList", genus);

		request.setAttribute("family", family);
		request.getRequestDispatcher("5cfamily.jsp").forward(request, response);
	}

}
