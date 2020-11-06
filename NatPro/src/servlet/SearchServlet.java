package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
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
import model.Compound;
import model.Family;
import model.Genus;
import model.MedicinalPlant;
import service.OntoQuery;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet({ "/SearchServlet", "/BrowseServlet" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		try {
//			browse(request, response);
//		} catch (OntologyLoadException | SQWRLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		switch (request.getServletPath()) {
		case "/BrowseServlet":
			try {
				browse(request, response);
			} catch (OntologyLoadException | ServletException | IOException | SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		default:
			System.out.println("aERROR(Inside userServlet *doPost*): url pattern doesn't match existing patterns.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (request.getServletPath()) {
		case "/SearchServlet":
			try {
				performSearch(request, response);
			} catch (OntologyLoadException | SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/BrowseServlet":
			try {
				browse(request, response);
			} catch (OntologyLoadException | ServletException | IOException | SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		default:
			System.out.println("ERROR(Inside userServlet *doPost*): url pattern doesn't match existing patterns.");
		}
//		response.sendRedirect("5asearchresults.jsp");
		doGet(request, response);
	}

	private void performSearch(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, SQWRLException {
		// TODO Auto-generated method stub
		String searchKey = request.getParameter("searchKey").trim();
		System.out.println("hehe" + request.getParameter("searchCategory"));
		OntoQuery q = new OntoQuery();
		if (request.getParameter("searchCategory").equals("1")) {
			List<MedicinalPlant> medPlants = q.searchMedicinalPlant(searchKey);
//			System.out.println(medPlants.get(0).getPreparations().get(0).getIllness());
			request.setAttribute("medPlantsList", medPlants);
		} else if (request.getParameter("searchCategory").equals("2")) {
			List<String> species = q.searchSynonyms(searchKey);
			request.setAttribute("speciesList", species);
		} else if (request.getParameter("searchCategory").equals("3")) {
			List<Genus> genus = q.searchGenus(searchKey);
			request.setAttribute("genusList", genus);
		} else if (request.getParameter("searchCategory").equals("4")) {
			List<Family> family = q.searchFamily(searchKey);
			request.setAttribute("familyList", family);
		} else if (request.getParameter("searchCategory").equals("5")) {
			List<Compound> compoundList = q.searchCompound(searchKey);
			request.setAttribute("compoundList", compoundList);
		} else if (request.getParameter("searchCategory").equals("6")) {
			List<String> locList = q.searchLocations(searchKey);
			request.setAttribute("locList", locList);
		}

//		for(MedicinalPlant m: medPlants) {
//			System.out.println(m.getMedicinalPlant().toString());
//		}

		request.setAttribute("searchCategory", request.getParameter("searchCategory"));

		request.setAttribute("searchKey", searchKey);
		
		request.getRequestDispatcher("searchresults.jsp").forward(request, response);
//		System.out.println(medPlants.get(0));
	}
	
	private void browse(HttpServletRequest request, HttpServletResponse response) throws OntologyLoadException, ServletException, IOException, SQWRLException {
		String searchKey = "";
		System.out.println("hehe" + request.getParameter("searchCategory"));
		OntoQuery q = new OntoQuery();
		if (request.getParameter("searchCategory").equals("1")) {
			List<MedicinalPlant> medPlants = q.searchMedicinalPlant(searchKey);
//			System.out.println(medPlants.get(0).getPreparations().get(0).getIllness());
			request.setAttribute("medPlantsList", medPlants);
		} else if (request.getParameter("searchCategory").equals("2")) {
			List<String> species = new ArrayList<String>();
			try {
				species = q.getAllSynonyms();
			} catch (SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("speciesList", species);
		} else if (request.getParameter("searchCategory").equals("3")) {
			List<Genus> genus = q.searchGenus(searchKey);
			request.setAttribute("genusList", genus);
		} else if (request.getParameter("searchCategory").equals("4")) {
			List<Family> family = q.searchFamily(searchKey);
			request.setAttribute("familyList", family);
		} else if (request.getParameter("searchCategory").equals("5")) {
			List<Compound> compoundList = q.searchCompound(searchKey);
			request.setAttribute("compoundList", compoundList);
		} else if (request.getParameter("searchCategory").equals("6")) {
			List<String> locList = q.getAllLocations();
			request.setAttribute("locList", locList);
		}

//		for(MedicinalPlant m: medPlants) {
//			System.out.println(m.getMedicinalPlant().toString());
//		}

		request.setAttribute("searchCategory", request.getParameter("searchCategory"));

		//request.setAttribute("searchKey", searchKey);
		
		request.getRequestDispatcher("searchresults.jsp").forward(request, response);
//		System.out.println(medPlants.get(0));
	}

}
