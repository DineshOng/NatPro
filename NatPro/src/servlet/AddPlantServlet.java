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
@WebServlet({ "/AddPlantServlet", "/AddPlantPageServlet" })
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
		case "/AddPlantPageServlet":
			try {
				getOptions(request, response);
			} catch (OntologyLoadException e) {
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

	private String cleanCompoundString(String value) {
		String cleaned;
		cleaned = value.trim();
		cleaned = cleaned.toLowerCase();
		cleaned = cleaned.replaceAll(" ", "_");
		cleaned = cleaned.replaceAll(",", ".");
		cleaned = cleaned.replaceAll("\u03B1", "alpha");
		cleaned = cleaned.replaceAll("\u03B2", "beta");
		cleaned = cleaned.replaceAll("\u0393", "gamma");
		return cleaned;
	}

	private void getOptions(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException {
		OntoQuery q = new OntoQuery();
		List<String> plantParts = q.getAllPlantParts();
		request.setAttribute("plantPartsList", plantParts);
		request.getRequestDispatcher("3add.jsp").forward(request, response);

	}

	private void addPlant(HttpServletRequest request, HttpServletResponse response) throws OntologyLoadException,
			ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException {
		OntoMngr m = new OntoMngr();

		if (!request.getParameterValues("speciesCtr").equals(null)) {
			String[] plantPartCtr = request.getParameterValues("speciesCtr");
			int plantPartCtrMax = Integer.parseInt(plantPartCtr[plantPartCtr.length - 1]); // get maximum number of
																							// speciesPart
			plantPartCtrMax++; // add one since incremention starts at 0
			for (int i = 0; i < plantPartCtrMax; i++) {
				System.out.println(request.getParameter("plantPart[" + i + "]"));
				String[] compoundCtr = request.getParameterValues("compound["+i+"]");
				for(int j=0; j<compoundCtr.length; j++) {
					System.out.println(compoundCtr[j]);
				}
//				System.out.println(request.getParameter("compoundCtr"));

			}
		}

//		if (request.getParameter("plantPart").equals("-1")) {
//			System.out.println(request.getParameter("plantPartOther"));
//		}else {
//			System.out.println(request.getParameter("plantPart"));
//		}

//		if (request.getParameter("plantPart") != null) {
//			String speciesPartIndiv = request.getParameter("plantPart").trim().toLowerCase().replaceAll(" ", "_");
////			System.out.println(request.getParameter("plantPart"));
//		} else {
////			System.out.println("null");
//		}
//		System.out.println(request.getParameter("speciesPart"));

//		if (!request.getParameter("commonPlantName").isBlank()) {
//			String commonPlantNameIndiv = request.getParameter("commonPlantName").trim().toLowerCase().replaceAll(" ",
//					"_");
//			m.addIndiv_MedPlant(commonPlantNameIndiv);
//			m.addDataPropMedPlant(request.getParameter("commonPlantName").trim());
//
//			if (!request.getParameter("scientificName").isBlank()) {
//				String speciesNameIndiv = request.getParameter("scientificName").trim().toLowerCase().replaceAll(" ",
//						"_");
//				m.addIndiv_Species(speciesNameIndiv);
//				m.addDataPropSpecies(request.getParameter("scientificName").trim());
//
//				m.addObjectHasScientificName(commonPlantNameIndiv, speciesNameIndiv);
//
//				if (!request.getParameter("genus").isBlank()) {
//					String genusNameIndiv = request.getParameter("genus").trim().toLowerCase().replaceAll(" ", "_");
//					m.addIndiv_Genus(genusNameIndiv);
//					m.addDataPropGenus(request.getParameter("genus").trim());
//
//					m.addObjectBelongsToGenus(speciesNameIndiv, genusNameIndiv);
//
//					if (!request.getParameter("family").isBlank()) {
//						String familyNameIndiv = request.getParameter("family").trim().toLowerCase().replaceAll(" ",
//								"_");
//						m.addIndiv_Genus(familyNameIndiv);
//						m.addDataPropGenus(request.getParameter("family").trim());
//
//						m.addObjectBelongsToFamily(genusNameIndiv, familyNameIndiv);
//					}
//				}
//				if (request.getParameter("plantPart") != null) {
//					String plantPart = request.getParameter("plantPart").trim().toLowerCase().replaceAll(" ", "_");
//					String speciesPartIndiv = speciesNameIndiv + "_" + plantPart;
//					m.addIndiv_SpeciesPart(speciesPartIndiv);
//
//					m.addObjectHasChildPlantPart(speciesNameIndiv, speciesPartIndiv);
//					m.addObjectHasPlantPart(speciesPartIndiv, plantPart);
//
//					String[] compounds;
//					compounds = request.getParameterValues("compound");
//					for (int i = 0; i < compounds.length; i++) {
//						if (!compounds[i].isBlank()) {
//							String compoundIndiv = cleanCompoundString(compounds[i]);
//							m.addIndiv_Compound(compoundIndiv);
//							m.addDataPropCompound(compounds[i]);
//
////								m.addObjectHasCompound(speciesPart, compound);
//						}
//					}
//
//				}
//			}
//
//			String[] locationNames;
//			locationNames = request.getParameterValues("location");
//			for (int i = 0; i < locationNames.length; i++) {
//				if (!locationNames[i].isBlank()) {
//					String locationNameIndiv = locationNames[i].trim().toLowerCase().replaceAll(" ", "_");
//					m.addIndiv_Location(locationNameIndiv);
//					m.addDataPropLocation(locationNames[i].trim());
//
//					m.addObjectIsLocatedIn(commonPlantNameIndiv, locationNameIndiv);
//				}
//			}
//
//		}
//
//		System.out.println("Common Name: " + request.getParameter("commonPlantName").trim());
//		String[] sciNames;
//		sciNames = request.getParameterValues("scientificName");
//		for (int i = 0; i < sciNames.length; i++) {
//			System.out.println("Scientific Name#" + (i + 1) + ": " + sciNames[i]);
//		}
//		System.out.println("Genus: " + request.getParameter("genus"));
//		System.out.println("Family: " + request.getParameter("family"));
//		String[] locs;
//		locs = request.getParameterValues("location");
//		for (int i = 0; i < locs.length; i++) {
//			System.out.println("Location#" + (i + 1) + ": " + locs[i]);
//		}
//		request.getRequestDispatcher("3aadded.jsp").forward(request, response);

	}

}
