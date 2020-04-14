package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.MedicinalPlant;
import service.OntoMngr;
import service.OntoQuery;

/**
 * Servlet implementation class AddPlantServlet
 */
@WebServlet("/AddPlantServlet")
public class AddPlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPlantServlet() {
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
		case "/AddPlantServlet":
			try {
				addPlant(request, response);
			} catch (OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OWLOntologyCreationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OWLOntologyStorageException e) {
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

	private void addPlant(HttpServletRequest request, HttpServletResponse response) throws OntologyLoadException,
			ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException {
		OntoMngr m = new OntoMngr();

		if (!request.getParameter("commonPlantName").isBlank()) {
			String commonPlantNameIndiv = request.getParameter("commonPlantName").trim().toLowerCase().replaceAll(" ", "_");
			m.addIndiv_MedPlant(commonPlantNameIndiv);
			m.addDataPropMedPlant(request.getParameter("commonPlantName").trim());

			String[] speciesNames;
			speciesNames = request.getParameterValues("scientificName");
			for (int i = 0; i < speciesNames.length; i++) {
				if (!speciesNames[i].isBlank()) {
					String speciesNameIndiv = speciesNames[i].trim().toLowerCase().replaceAll(" ", "_");
					m.addIndiv_Species(speciesNameIndiv);
					m.addDataPropSpecies(speciesNames[i].trim());

					m.addObjectHasScientificName(commonPlantNameIndiv, speciesNameIndiv);

					if (!request.getParameter("genus").isBlank()) {
						String genusNameIndiv = request.getParameter("genus").trim().toLowerCase().replaceAll(" ", "_");
						m.addIndiv_Genus(genusNameIndiv);
						m.addDataPropGenus(request.getParameter("genus").trim());
						
						m.addObjectBelongsToGenus(speciesNameIndiv, genusNameIndiv);
						
						if (!request.getParameter("family").isBlank()) {
							String familyNameIndiv = request.getParameter("family").trim().toLowerCase().replaceAll(" ", "_");
							m.addIndiv_Genus(familyNameIndiv);
							m.addDataPropGenus(request.getParameter("family").trim());
							
							m.addObjectBelongsToFamily(genusNameIndiv, familyNameIndiv);
						}
					}
				}
			}

			String[] locationNames;
			locationNames = request.getParameterValues("location");
			for (int i = 0; i < locationNames.length; i++) {
				if (!locationNames[i].isBlank()) {
					String locationNameIndiv = locationNames[i].trim().toLowerCase().replaceAll(" ", "_");
					m.addIndiv_Location(locationNameIndiv);
					m.addDataPropLocation(locationNames[i].trim());

					m.addObjectIsLocatedIn(commonPlantNameIndiv,locationNameIndiv);
				}
			}

		}

		System.out.println("Common Name: " + request.getParameter("commonPlantName").trim());
		String[] sciNames;
		sciNames = request.getParameterValues("scientificName");
		for (int i = 0; i < sciNames.length; i++) {
			System.out.println("Scientific Name#" + (i + 1) + ": " + sciNames[i]);
		}
		System.out.println("Genus: " + request.getParameter("genus"));
		System.out.println("Family: " + request.getParameter("family"));
		String[] locs;
		locs = request.getParameterValues("location");
		for (int i = 0; i < locs.length; i++) {
			System.out.println("Location#" + (i + 1) + ": " + locs[i]);
		}

	}

}
