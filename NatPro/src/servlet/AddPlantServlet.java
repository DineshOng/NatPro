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

		if (!request.getParameter("commonPlantName").isBlank()) { // check if common plant name is filled
			String commonPlantNameIndiv = request.getParameter("commonPlantName").trim().toLowerCase().replaceAll(" ",
					"_");
			// create individual for MedicinalPlant
			m.addIndiv_MedPlant(commonPlantNameIndiv);
			// add data property for MedicinalPlant individual
			m.addDataPropMedPlant(request.getParameter("commonPlantName").trim());

			if (!request.getParameter("scientificName").isBlank()) { // check if scientific name is filled
				String speciesNameIndiv = request.getParameter("scientificName").trim().toLowerCase().replaceAll(" ",
						"_");
				// create individual for Species
				m.addIndiv_Species(speciesNameIndiv);
				// add data property for Species individual
				m.addDataPropSpecies(request.getParameter("scientificName").trim());
				// add object property MedicinalPlant -> Species
				m.addObjectHasScientificName(commonPlantNameIndiv, speciesNameIndiv);

				if (!request.getParameter("genus").isBlank()) { // check if genus is filled
					String genusNameIndiv = request.getParameter("genus").trim().toLowerCase().replaceAll(" ", "_");
					// create individual for Genus
					m.addIndiv_Genus(genusNameIndiv);
					// add data property for Genus individual
					m.addDataPropGenus(request.getParameter("genus").trim());
					// add object property Species -> Genus
					m.addObjectBelongsToGenus(speciesNameIndiv, genusNameIndiv);

					if (!request.getParameter("family").isBlank()) { // check if family is filled
						String familyNameIndiv = request.getParameter("family").trim().toLowerCase().replaceAll(" ",
								"_");
						// create individual for Family
						m.addIndiv_Family(familyNameIndiv);
						// add data property for Family individual
						m.addDataPropFamily(request.getParameter("family").trim());

						m.addObjectBelongsToFamily(genusNameIndiv, familyNameIndiv);
					}
				}

				if (!request.getParameterValues("speciesCtr").equals(null)) { // check if there are any plant parts
																				// selected
					String[] plantPartCtr = request.getParameterValues("speciesCtr"); // we will only get the last value
																						// which is the max
					int plantPartCtrMax = Integer.parseInt(plantPartCtr[plantPartCtr.length - 1]); // get maximum number
																									// of
																									// speciesPart to be
																									// used for
																									// iteration later
					plantPartCtrMax++; // add one since incremention starts at 0 at front end
					for (int i = 0; i < plantPartCtrMax; i++) { // traverse through the list of all plant parts selected
						if (request.getParameter("plantPart[" + i + "]") != null) { // check if a plant part is selected
							String plantPart = request.getParameter("plantPart[" + i + "]").trim().toLowerCase()
									.replaceAll(" ", "_");
							String speciesPartIndiv = speciesNameIndiv + "_" + plantPart;
							// create individual for SpeciesPart
							m.addIndiv_SpeciesPart(speciesPartIndiv);
							// add object property Species -> SpeciesPart
							m.addObjectHasChildPlantPart(speciesNameIndiv, speciesPartIndiv);
							// add object property SpeciesPart -> PlantPart
							m.addObjectHasPlantPart(speciesPartIndiv, plantPart);
							String[] compounds = request.getParameterValues("compound[" + i + "]");
							for (int j = 0; j < compounds.length; j++) {
								if (!compounds[j].isBlank()) {
									String compoundIndiv = cleanCompoundString(compounds[j]);
									// create individual for Compound
									m.addIndiv_Compound(compoundIndiv);
									// add data property for Compound individual
									m.addDataPropCompound(compounds[j]);
									// add object property SpeciesPart -> Compound
									m.addObjectHasCompound(speciesPartIndiv, compoundIndiv);

								}
							}
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

					m.addObjectIsLocatedIn(commonPlantNameIndiv, locationNameIndiv);
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
		request.getRequestDispatcher("3aadded.jsp").forward(request, response);

	}

}
