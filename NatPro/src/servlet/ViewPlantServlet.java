package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flickr4java.flickr.FlickrException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.MedicinalPlant;
import service.FlickrService;
import service.OntoQuery;

/**
 * Servlet implementation class ViewPlantServlet
 */
@WebServlet("/ViewPlantServlet")
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
			} catch (OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FlickrException e) {
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
			throws OntologyLoadException, ServletException, IOException, FlickrException {
		// TODO Auto-generated method stub
		String searchKey = request.getParameter("medPlant");
		System.out.println(searchKey);
		OntoQuery q = new OntoQuery();
		List<MedicinalPlant> medPlants = q.searchMedicinalPlant(searchKey);
		//System.out.println(medPlants.get(0).getSpecies().get(0).getSpeciesParts().get(0).getCompounds().get(0));
//		for(MedicinalPlant m: medPlants) {
//			System.out.println(m.getMedicinalPlant().toString());
//		}
		//System.out.println(medPlants.get(0).getSpecies());
		List<String> photos = new FlickrService(searchKey).getPhotoURL();
		//System.err.println(photos.get(0));
		request.setAttribute("photos", photos);
		request.setAttribute("medPlantsList", medPlants);
		request.getRequestDispatcher("6dentry.jsp").forward(request, response);
	}

}
