package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flickr4java.flickr.FlickrException;

import edu.stanford.nlp.util.StringUtils;
import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.MedicinalPlant;
import model.Species;
import service.FlickrService;
import service.OntoQuery;

/**
 * Servlet implementation class ViewPlantServlet
 */
@WebServlet({ "/ViewPlantServlet", "/ViewSciPlantServlet" })
public class ViewPlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewPlantServlet() {
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
		case "/ViewPlantServlet":
			try {
				viewPlant(request, response);
			} catch (SQWRLException | OntologyLoadException | ServletException | IOException | FlickrException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/ViewSciPlantServlet":
			try {
				viewSciPlant(request, response);
			} catch (OntologyLoadException | ServletException | IOException | FlickrException | SQWRLException e) {
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

	private void viewPlant(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, FlickrException, SQWRLException {
		// TODO Auto-generated method stub
		String searchKey = request.getParameter("medPlant");
		OntoQuery q = new OntoQuery();
		try {
			List<MedicinalPlant> medPlants = q.searchMedicinalPlant(searchKey);
			request.setAttribute("medPlantsList", medPlants);
		} catch (Exception e) {
		}
//		List<String> photos = new FlickrService(searchKey).getPhotoURL();
//		 System.err.println(photos.get(0));
//		request.setAttribute("photos", photos);

		// Get synonyms without individual
		try {
			List<String> synIndiv = q.getSynonyms(searchKey.replaceAll("_", ""));
			List<String> removeSynNoIndiv = new ArrayList<String>();
			for (String syn : synIndiv) {
				if (q.getSpeciesIndivName(syn) == null) {
					removeSynNoIndiv.add(StringUtils.capitalize(syn));
				}
			}
			request.setAttribute("synNoIndiv", removeSynNoIndiv);
		} catch (Exception e) {

		}

		// retrieve for auto complete
		List<String> family = q.getAllFamily();
		request.setAttribute("familyList", family);

		List<String> genus = q.getAllGenus();
		request.setAttribute("genusList", genus);

		List<String> syns = q.getAllSynonyms();
		request.setAttribute("synList", syns);

		List<String> locs = q.getAllLocations();
		request.setAttribute("locList", locs);

		List<String> plantParts = q.getAllPlantParts();
		request.setAttribute("plantPartsList", plantParts);

		List<String> illness = q.getAllIllness();
		request.setAttribute("illnessList", illness);

		List<String> compounds = q.getAllCompounds();
		request.setAttribute("compoundList", compounds);

		request.getRequestDispatcher("6dentry.jsp").forward(request, response);
	}

	private void viewSciPlant(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, FlickrException, SQWRLException {
		// TODO Auto-generated method stub

		String specieKey = request.getParameter("specie");
		OntoQuery q = new OntoQuery();
		Species specie = q.searchSpecie(specieKey);
		String medPlantName = q.getSynonymMP(specieKey);
		request.setAttribute("medPlantName", medPlantName);
		request.setAttribute("SpecieObject", specie);
		request.setAttribute("specie", specieKey);
		request.getRequestDispatcher("6dentry-sci.jsp").forward(request, response);
	}
}
