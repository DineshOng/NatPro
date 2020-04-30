package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.Compound;
import model.Family;
import model.Genus;
import model.MedicinalPlant;
import service.OntoQuery;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet({ "/SearchServlet" })
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			} catch (OntologyLoadException e) {
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
			throws OntologyLoadException, ServletException, IOException {
		// TODO Auto-generated method stub
		String searchKey = request.getParameter("searchKey");
		System.out.println("hehe" + request.getParameter("searchCategory"));
		OntoQuery q = new OntoQuery();
		if (request.getParameter("searchCategory").equals("1")) {
			List<MedicinalPlant> medPlants = q.searchMedicinalPlant(searchKey);
			request.setAttribute("medPlantsList", medPlants);
		} else if (request.getParameter("searchCategory").equals("3")) {
			List<Genus> genus = q.searchGenus(searchKey);
			request.setAttribute("genusList", genus);
		} else if (request.getParameter("searchCategory").equals("4")) {
			List<Family> family = q.searchFamily(searchKey);
			request.setAttribute("familyList", family);
		} else if (request.getParameter("searchCategory").equals("5")) {
			List<Compound> compoundList = q.searchCompound(searchKey);
			request.setAttribute("compoundList", compoundList);
		}

//		for(MedicinalPlant m: medPlants) {
//			System.out.println(m.getMedicinalPlant().toString());
//		}

		request.setAttribute("searchCategory", request.getParameter("searchCategory"));

		request.setAttribute("searchKey", searchKey);

		request.getRequestDispatcher("5asearchresults.jsp").forward(request, response);
//		System.out.println(medPlants.get(0));
	}

}
