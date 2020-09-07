package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.flickr4java.flickr.FlickrException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import service.OntoMngr;
import service.OntoQuery;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet({ "/EditServlet", "/EditMedPlant", "/EditFamilyName", "/EditFamily", "/EditGenusName", "/EditGenus",
		"/EditSciName" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		switch (request.getServletPath()) {
		case "/EditMedPlant":
			try {
				editMedPlant(request, response);
			} catch (OWLOntologyCreationException | OWLOntologyStorageException | ServletException | IOException
					| SQWRLException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditFamilyName":
			try {
				editFamilyName(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditFamily":
			try {
				editFamily(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditGenusName":
			try {
				editGenusName(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditGenus":
			try {
				editGenus(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditSciName":
			try {
				editSciName(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
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

	private void editMedPlant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String oldMedPlantName = request.getParameter("oldMedPlantName");
		String newMedPlantName = request.getParameter("newMedPlantName");

		// Get the individual name of the MedicinalPlant
		OntoQuery q = new OntoQuery();
		String oldMedPlantNameIndiv = q.getMedPlantIndivName(oldMedPlantName);

		String checkIfIndivNameExists = q.getMedPlantIndivName(newMedPlantName);
		if (checkIfIndivNameExists == null) {
			// Change the individual name
			OntoMngr m = new OntoMngr();
			m.setMedPlantIndiv(oldMedPlantNameIndiv);
			m.addDataPropMedPlant(newMedPlantName);
			m.removeDataPropertyValue(oldMedPlantNameIndiv, "datatypeProperty_MedicinalPlant", oldMedPlantName);
			m.changeNameIndividual(oldMedPlantNameIndiv, newMedPlantName);

			PrintWriter out = response.getWriter();
			String message = "Plant Name Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Plant Name Already Exists!";
			out.println(message);
		}
	}

	private void editFamily(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String oldFamily = request.getParameter("oldFamilyVal");
		String newFamily = request.getParameter("newFamilyVal");

		System.out.println(oldFamily);
		System.out.println(newFamily);

		OntoQuery q = new OntoQuery();
		String oldFamilyIndiv = q.getFamilyIndivName(oldFamily);
		List<String> genus = q.getAllGenus();
		List<String> families = q.getAllFamily();
		System.out.println(families);

		// check if new family indiv already exists, if not create new indiv
		boolean createNewIndiv = false;
		for (String family : families) {
			if (family.equalsIgnoreCase(newFamily.toLowerCase())) {
				createNewIndiv = false;
			} else {
				createNewIndiv = true;
			}
		}

		OntoMngr m = new OntoMngr();
		if (createNewIndiv) {
			m.addIndiv_Family(m.cleanString(newFamily));
			m.addDataPropFamily(newFamily);

		} else {
			String FamilyIndiv = q.getFamilyIndivName(newFamily);
			m.setFamilyIndiv(FamilyIndiv);
		}

		// Get the genus of the old family to be updated
		for (int i = 0; i < genus.size(); i++) {
			String genusIndiv = q.getGenusIndivName(genus.get(i));
			System.out.println(genusIndiv);
			List<String> genusToFamily = q.getGenusFamily(genus.get(i));
			System.out.println(genusToFamily);
			if (genusToFamily.contains(oldFamily)) {
				m.removeObjectPropertyValue(genusIndiv, "belongsToFamily", oldFamilyIndiv);
				m.addObjectBelongsToFamily(genusIndiv, m.cleanString(newFamily));
			}
		}

		PrintWriter out = response.getWriter();
		String message = "Family Successfully Edited";
		out.println(message);
	}

	private void editFamilyName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String oldFamilyName = request.getParameter("oldFamilyName");
		String newFamilyName = request.getParameter("newFamilyName");

		// Get the individual name of the MedicinalPlant
		OntoQuery q = new OntoQuery();
		String oldFamilyNameIndiv = q.getFamilyIndivName(oldFamilyName);

		String checkIfIndivNameExists = q.getFamilyIndivName(newFamilyName);
		if (checkIfIndivNameExists == null) {
			// Change the individual name
			OntoMngr m = new OntoMngr();
			m.setFamilyIndiv(oldFamilyNameIndiv);
			m.addDataPropFamily(newFamilyName);
			m.removeDataPropertyValue(oldFamilyNameIndiv, "datatypeProperty_Family", oldFamilyName);
			m.changeNameIndividual(oldFamilyNameIndiv, newFamilyName);

			PrintWriter out = response.getWriter();
			String message = "Family Name Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Family Name Already Exists!";
			out.println(message);
		}
	}
	
	private void editGenus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String oldGenus = request.getParameter("oldGenusVal");
		String newGenus = request.getParameter("newGenusVal");
		
		OntoQuery q = new OntoQuery();
		String oldGenusIndiv = q.getGenusIndivName(oldGenus);
		List<String> species = q.getAllSynonyms();
		List<String> genusList = q.getAllGenus();
		
		// check if new genus indiv already exists, if not create new indiv
		boolean createNewIndiv = false;
		for (String genus : genusList) {
			if (genus.equalsIgnoreCase(newGenus.toLowerCase())) {
				createNewIndiv = false;
			} else {
				createNewIndiv = true;
			}
		}
		
		OntoMngr m = new OntoMngr();
		if (createNewIndiv) {
			m.addIndiv_Genus(m.cleanString(newGenus));
			m.addDataPropGenus(newGenus);

		} else {
			String GenusIndiv = q.getGenusIndivName(newGenus);
			m.setGenusIndiv(GenusIndiv);
		}
		
		// Get the specie/s of the old genus to be updated
		for (int i = 0; i < species.size(); i++) {
			String speciesIndiv = q.getSpeciesIndivName(species.get(i));
			System.out.println(speciesIndiv);
			List<String> synToGenus = q.getSynonymGenus(species.get(i));
			System.out.println(synToGenus);
			if (synToGenus.contains(oldGenus)) {
				m.removeObjectPropertyValue(speciesIndiv, "belongsToGenus", oldGenusIndiv);
				m.addObjectBelongsToGenus(speciesIndiv, m.cleanString(newGenus));
			}
		}

		PrintWriter out = response.getWriter();
		String message = "Genus Successfully Edited";
		out.println(message);
	}

	private void editGenusName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldGenusName = request.getParameter("oldGenusName");
		String newGenusName = request.getParameter("newGenusName");

		// Get the individual name of the MedicinalPlant
		OntoQuery q = new OntoQuery();
		String oldGenusNameIndiv = q.getGenusIndivName(oldGenusName);

		String checkIfIndivNameExists = q.getGenusIndivName(newGenusName);
		if (checkIfIndivNameExists == null) {
			// Change the individual name
			OntoMngr m = new OntoMngr();
			m.setGenusIndiv(oldGenusNameIndiv);
			m.addDataPropGenus(newGenusName);
			m.removeDataPropertyValue(oldGenusNameIndiv, "datatypeProperty_Genus", oldGenusName);
			m.changeNameIndividual(oldGenusNameIndiv, newGenusName);

			PrintWriter out = response.getWriter();
			String message = "Genus Name Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Genus Name Already Exists!";
			out.println(message);
		}
	}

	private void editSciName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldSciName = request.getParameter("oldSpecieName");
		String newSciName = request.getParameter("newSpecieName");

		// Get the individual name of the MedicinalPlant
		OntoQuery q = new OntoQuery();
		String oldSciNameIndiv = q.getSpeciesIndivName(oldSciName);

		String checkIfIndivNameExists = q.getSpeciesIndivName(newSciName);
		if (checkIfIndivNameExists == null) {
			// Change the individual name
			OntoMngr m = new OntoMngr();
			m.setSpeciesIndiv(oldSciNameIndiv);
			m.addDataPropSpecies(newSciName);
			m.removeDataPropertyValue(oldSciNameIndiv, "datatypeProperty_Synonym", oldSciName);
			m.changeNameIndividual(oldSciNameIndiv, newSciName);

			PrintWriter out = response.getWriter();
			String message = "Scientific Name Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Scientific Name Already Exists!";
			out.println(message);
		}

	}

}
