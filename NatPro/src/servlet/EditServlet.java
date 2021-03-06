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

import org.apache.commons.lang.WordUtils;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import service.OntoMngr;
import service.OntoQuery;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet({ "/EditServlet", "/EditMedPlant", "/DeletePlant", "/EditFamilyName", "/EditFamily", "/EditGenusName",
		"/EditGenus", "/AddGenus", "/RemoveGenus", "/EditSci", "/EditSciName", "/AddLoc", "/RemoveLoc", "/EditPrep",
		"/AddPrep", "/EditComp" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro.owl";

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
		case "/DeletePlant":
			try {
				deleteMedPlant(request, response);
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
		case "/AddGenus":
			try {
				addGenus(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/RemoveGenus":
			try {
				removeGenus(request, response);
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
		case "/EditSci":
			try {
				editSci(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/AddLoc":
			try {
				addLoc(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/RemoveLoc":
			try {
				removeLoc(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditPrep":
			try {
				editPrep(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/AddPrep":
			try {
				addPrep(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditComp":
			try {
				editComp(request, response);
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
		OntoQuery q = new OntoQuery(owlPath);
		String oldMedPlantNameIndiv = q.getMedPlantIndivName(oldMedPlantName);

		String checkIfIndivNameExists = q.getMedPlantIndivName(newMedPlantName);
		if (checkIfIndivNameExists == null) {
			// Change the individual name
			OntoMngr m = new OntoMngr(owlPath);
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

	private void deleteMedPlant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String medPlantName = request.getParameter("plantVal");

		// Get the individual name of the MedicinalPlant
		OntoQuery q = new OntoQuery(owlPath);
		String MedPlantNameIndiv = q.getMedPlantIndivName(medPlantName);

		try {
			OntoMngr m = new OntoMngr(owlPath);
			m.deleteMedicinalPlant(MedPlantNameIndiv);
			PrintWriter out = response.getWriter();
			String message = "Plant Successfully Removed";
			out.println(message);

		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			String message = "Remove Plant Unsuccessful";
			out.println(message);
		}

	}

	private void editFamily(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String oldFamily = request.getParameter("oldFamilyVal");
		String newFamily = request.getParameter("newFamilyVal");

		OntoQuery q = new OntoQuery(owlPath);
		String oldFamilyIndiv = q.getFamilyIndivName(oldFamily);
		List<String> genus = q.getAllGenus();
		List<String> families = q.getAllFamily();

		// returns null if indiv not yet exists
		String checkIfFamilyIndivExists = q.getFamilyIndivName(newFamily);

		OntoMngr m = new OntoMngr(owlPath);
		if (checkIfFamilyIndivExists == null) {
			m.addIndiv_Family(m.cleanString(newFamily));
			m.addDataPropFamily(newFamily);

		} else {
			String FamilyIndiv = q.getFamilyIndivName(newFamily);
			m.setFamilyIndiv(FamilyIndiv);
		}

		if (oldFamily.isEmpty()) {
			System.out.println("empty");
		} else {
			System.out.println(oldFamily);
		}

		// Get the genus of the old family to be updated
		for (int i = 0; i < genus.size(); i++) {
			String genusIndiv = q.getGenusIndivName(genus.get(i));
			List<String> genusToFamily = q.getGenusFamily(genus.get(i));
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

		// Get the individual name of the Family
		OntoQuery q = new OntoQuery(owlPath);
		String oldFamilyNameIndiv = q.getFamilyIndivName(oldFamilyName);

		String checkIfIndivNameExists = q.getFamilyIndivName(newFamilyName);
		if (checkIfIndivNameExists == null) {
			// Change the individual name
			OntoMngr m = new OntoMngr(owlPath);
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
		String sciName = request.getParameter("sciNameVal");

		OntoQuery q = new OntoQuery(owlPath);
		String oldGenusIndiv = q.getGenusIndivName(oldGenus);
		List<String> species = q.getAllSynonyms();
		List<String> genusList = q.getAllGenus();

		// returns null if indiv not yet exists
		String checkIfGenusIndivExists = q.getGenusIndivName(newGenus);

		OntoMngr m = new OntoMngr(owlPath);
		if (checkIfGenusIndivExists == null) {
			m.addIndiv_Genus(m.cleanString(newGenus));
			m.addDataPropGenus(newGenus);

		} else {
			String GenusIndiv = q.getGenusIndivName(newGenus);
			m.setGenusIndiv(GenusIndiv);
		}

		if (oldGenus.isEmpty()) {
			if (sciName.isEmpty()) {
				PrintWriter out = response.getWriter();
				String message = "Edit Unsuccessful, Missing Scientific Name";
				out.println(message);
			} else {
				String sciNameIndiv = q.getSpeciesIndivName(sciName);
				m.addObjectBelongsToGenus(sciNameIndiv, m.cleanString(newGenus));

				PrintWriter out = response.getWriter();
				String message = "Genus Successfully Edited";
				out.println(message);
			}
		} else {
			// Get the specie/s of the old genus to be updated
			for (int i = 0; i < species.size(); i++) {
				String speciesIndiv = q.getSpeciesIndivName(species.get(i));
				List<String> synToGenus = q.getSynonymGenus(species.get(i));
				if (synToGenus.contains(oldGenus)) {
					m.removeObjectPropertyValue(speciesIndiv, "belongsToGenus", oldGenusIndiv);
					m.addObjectBelongsToGenus(speciesIndiv, m.cleanString(newGenus));
				}
			}
			PrintWriter out = response.getWriter();
			String message = "Genus Successfully Edited";
			out.println(message);
		}

	}

	private void editGenusName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldGenusName = request.getParameter("oldGenusName");
		String newGenusName = request.getParameter("newGenusName");

		// Get the individual name of the Genus
		OntoQuery q = new OntoQuery(owlPath);
		String oldGenusNameIndiv = q.getGenusIndivName(oldGenusName);

		String checkIfIndivNameExists = q.getGenusIndivName(newGenusName);
		if (checkIfIndivNameExists == null) {
			// Change the individual name
			OntoMngr m = new OntoMngr(owlPath);
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

	private void addGenus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String genus = request.getParameter("genusVal");
		String familyName = request.getParameter("familyName");

		try {
			OntoQuery q = new OntoQuery(owlPath);
			String familyIndiv = q.getFamilyIndivName(familyName);

			String checkIfGenusIndivExists = q.getGenusIndivName(genus);
			OntoMngr m = new OntoMngr(owlPath);
			if (checkIfGenusIndivExists == null) {
				m.addIndiv_Genus(m.cleanString(genus));
				m.addDataPropGenus(genus);
				m.addObjectBelongsToFamily(m.cleanString(genus), familyIndiv);

			} else {
				String oldFamily;
				try {
					oldFamily = q.getGenusFamily(checkIfGenusIndivExists).get(0);
				} catch (Exception e) {
					oldFamily = null;
				}
				if (oldFamily == null) {
					m.setGenusIndiv(checkIfGenusIndivExists);
					m.addObjectBelongsToFamily(checkIfGenusIndivExists, familyIndiv);
				} else {
					String oldFamilyIndiv = q.getFamilyIndivName(oldFamily);
					m.removeObjectPropertyValue(checkIfGenusIndivExists, "belongsToFamily", oldFamilyIndiv);
					m.setGenusIndiv(checkIfGenusIndivExists);
					m.addObjectBelongsToFamily(checkIfGenusIndivExists, familyIndiv);
				}
			}

			PrintWriter out = response.getWriter();
			String message = "Genus Added Successfully";
			out.println(message);
		} catch (Exception e) {
			System.err.println(e);
			PrintWriter out = response.getWriter();
			String message = "Genus Failed to Add";
			out.println(message);
		}

	}

	private void removeGenus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String genus = request.getParameter("genusVal");
		String familyName = request.getParameter("familyName");

		try {
			OntoQuery q = new OntoQuery(owlPath);
			String checkIfGenusIndivExists = q.getGenusIndivName(genus);
			String familyIndiv = q.getFamilyIndivName(familyName);

			OntoMngr m = new OntoMngr(owlPath);
			m.removeObjectPropertyValue(checkIfGenusIndivExists, "belongsToFamily", familyIndiv);

			PrintWriter out = response.getWriter();
			String message = "Genus Removed";
			out.println(message);
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			String message = "Remove Genus Failed";
			out.println(message);
		}

	}

	private void editSciName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldSciName = request.getParameter("oldSpecieName");
		String newSciName = request.getParameter("newSpecieName");

		// Get the individual name of the Specie
		OntoQuery q = new OntoQuery(owlPath);
		String oldSciNameIndiv = q.getSpeciesIndivName(oldSciName);

		String checkIfIndivNameExists = q.getSpeciesIndivName(newSciName);
		if (checkIfIndivNameExists == null) {
			// Change the individual name
			OntoMngr m = new OntoMngr(owlPath);
			m.setSpeciesIndiv(oldSciNameIndiv);
			m.addDataPropSpecies(newSciName);
			m.removeDataPropertyValue(oldSciNameIndiv, "datatypeProperty_Synonym", oldSciName);
			m.changeSpeciePartNameIndividual(oldSciNameIndiv, newSciName);
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

	private void editSci(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			OWLOntologyCreationException, OWLOntologyStorageException, OntologyLoadException, SQWRLException {
		String oldSci = request.getParameter("oldSpecieVal");
		String newSci = request.getParameter("newSpecieVal");
		String medPlantName = request.getParameter("medPlantName");

		OntoQuery q = new OntoQuery(owlPath);
		String oldSciIndiv = q.getSpeciesIndivName(oldSci);
		List<String> medplants = q.getAllMedPlantNames();
		List<String> synList = q.getAllSynonyms();

		// returns null if indiv not yet exists
		String checkIfSpecieIndivExists = q.getSpeciesIndivName(newSci);

		OntoMngr m = new OntoMngr(owlPath);
		if (checkIfSpecieIndivExists == null) {
			m.addIndiv_Species(m.cleanString(newSci));
			m.addDataPropSpecies(newSci);

		} else {
			String specieIndiv = q.getSpeciesIndivName(newSci);
			m.setSpeciesIndiv(specieIndiv);
		}

		// if no scientific name yet
		if (oldSci.isEmpty()) {
			String medPlantIndiv = q.getMedPlantIndivName(medPlantName);
			m.addObjectHasScientificName(medPlantIndiv, m.cleanString(newSci));

			PrintWriter out = response.getWriter();
			String message = "Scientific Name Successfully Edited";
			out.println(message);
		} else {
			// Get the medicinal plant of the old scientific name to be updated
			for (int i = 0; i < medplants.size(); i++) {
				String medPlantIndiv = q.getMedPlantIndivName(medplants.get(i));
				List<String> medPlantToSpecie = q.getSynonyms(medplants.get(i));
				if (medPlantToSpecie.contains(oldSci)) {
					m.removeObjectPropertyValue(medPlantIndiv, "hasScientificName", oldSciIndiv);
					m.addObjectHasScientificName(medPlantIndiv, m.cleanString(newSci));
				}
			}

			PrintWriter out = response.getWriter();
			String message = "Scientific Name Successfully Edited";
			out.println(message);
		}

	}

	private void addLoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			OntologyLoadException, OWLOntologyCreationException, OWLOntologyStorageException, SQWRLException {
		String location = request.getParameter("locVal");
		String medPlantName = request.getParameter("medPlantName");

		// Get the individual name of the MedicinalPlant
		OntoQuery q = new OntoQuery(owlPath);
		String medPlantIndiv = q.getMedPlantIndivName(medPlantName);

		// returns null if indiv not yet exists
		String checkIfLocIndivExists = q.getLocIndivName(location);
		OntoMngr m = new OntoMngr(owlPath);
		if (checkIfLocIndivExists == null) {
			m.addIndiv_Location(m.cleanString(location));
			m.addDataPropLocation(location);
			m.addObjectIsLocatedIn(medPlantIndiv, m.cleanString(location));

		} else {
			String locIndiv = q.getLocIndivName(location);
			m.setLocationIndiv(locIndiv);
			m.addObjectIsLocatedIn(medPlantIndiv, locIndiv);
		}

		PrintWriter out = response.getWriter();
		String message = "Location Added Successfully";
		out.println(message);

	}

	private void removeLoc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String location = request.getParameter("locVal");
		String medPlantName = request.getParameter("medPlantName");

		// Get the individual name of the MedicinalPlant
		OntoQuery q = new OntoQuery(owlPath);
		String medPlantIndiv = q.getMedPlantIndivName(medPlantName);
		String locIndiv = q.getLocIndivName(location);
		OntoMngr m = new OntoMngr(owlPath);
		m.removeObjectPropertyValue(medPlantIndiv, "isLocatedIn", locIndiv);

		PrintWriter out = response.getWriter();
		String message = "Location Removed";
		out.println(message);

	}

	private void editPrep(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldPrepVal = request.getParameter("oldPrepVal");
		String oldPlantPartVal = request.getParameter("oldPlantPartVal");
		String oldIllnessVal = request.getParameter("oldIllnessVal");

		String prepVal = request.getParameter("prepVal");
		String plantPartVal = request.getParameter("plantPartVal");
		String illnessVal = request.getParameter("illnessVal");
		String medPlantName = request.getParameter("medPlantName");

		OntoQuery q = new OntoQuery(owlPath);
		List<String> prepList = q.getAllPreparations();
		List<String> plantPartList = q.getAllPlantParts();
		List<String> illList = q.getAllIllness();

		String checkIfPrepIndivExists = q.getPrepIndivName(prepVal);
		String checkIfIllIndivExists = q.getIllnessIndivName(illnessVal);
		String checkIfPlantPartIndivExists = q.getIllnessIndivName(plantPartVal);

		OntoMngr m = new OntoMngr(owlPath);

		try {
			if (checkIfPrepIndivExists == null) {
				String prepIndiv = m.cleanString(prepVal).replaceAll("\\.", "");
				String oldPrepIndiv = q.getPrepIndivName(oldPrepVal);
				String medPlantIndiv = q.getMedPlantIndivName(medPlantName);

				m.addIndiv_Preparation(prepIndiv);
				m.addDataPropPreparation(prepVal);
				m.addObjectHasPreparation(medPlantIndiv, prepIndiv);

				String oldIllIndiv = q.getIllnessIndivName(oldIllnessVal);
				m.removeObjectPropertyValue(oldPrepIndiv, "treats", oldIllIndiv);
				if (checkIfIllIndivExists == null) {
					m.addIndiv_Illness(m.cleanString(illnessVal));
					m.addDataPropIllness(illnessVal);
					m.addObjectTreats(prepIndiv, m.cleanString(illnessVal));
				} else {
					String illIndiv = q.getIllnessIndivName(illnessVal);
					m.setIllnessIndiv(illIndiv);
					m.addObjectTreats(prepIndiv, illIndiv);
				}

				List<String> remainingIllness = q.getPreparationIllness(oldPrepVal);
				// If there are still remaining illness, don't remove utilized plant part from
				// old preparation yet
				if (!(remainingIllness.size() > 1)) {
					String oldPlantPartIndiv = q.getPlantPartIndivName(oldPlantPartVal);
					m.removeObjectPropertyValue(oldPrepIndiv, "utilizedPart", oldPlantPartIndiv);
					m.removeObjectPropertyValue(medPlantIndiv, "hasPreparation", oldPrepIndiv);
				}

				if (checkIfPlantPartIndivExists == null) {
					m.addIndiv_PlantPart(m.cleanString(plantPartVal));
					m.addDataPropPlantPart(plantPartVal);
					m.addObjectUtilizedPart(prepIndiv, m.cleanString(plantPartVal));
				} else {
					String plantPartIndiv = q.getPlantPartIndivName(plantPartVal);
					m.setPlantPartIndiv(plantPartIndiv);
					m.addObjectUtilizedPart(prepIndiv, plantPartIndiv);
				}

			} else {

				List<String> plantParts = q.getPreparationPlantPart(prepVal);
				if (!plantParts.get(0).equalsIgnoreCase(plantPartVal)) {
					PrintWriter out = response.getWriter();
					String message = "Failed to Edit Preparation, Existing preparation has different plant part.";
					out.println(message);
					return;
				}

				String prepIndiv = q.getPrepIndivName(prepVal);
				m.setPreparationIndiv(prepIndiv);

				String oldPlantPartIndiv = q.getPlantPartIndivName(oldPlantPartVal);
				m.removeObjectPropertyValue(prepIndiv, "utilizedPart", oldPlantPartIndiv);
				if (checkIfPlantPartIndivExists == null) {
					m.addIndiv_PlantPart(m.cleanString(plantPartVal));
					m.addDataPropPlantPart(plantPartVal);
					m.addObjectUtilizedPart(prepIndiv, m.cleanString(plantPartVal));
				} else {
					String plantPartIndiv = q.getPlantPartIndivName(plantPartVal);
					m.setPlantPartIndiv(plantPartIndiv);
					m.addObjectUtilizedPart(prepIndiv, plantPartIndiv);
				}

				String oldIllIndiv = q.getIllnessIndivName(oldIllnessVal);
				m.removeObjectPropertyValue(prepIndiv, "treats", oldIllIndiv);
				if (checkIfIllIndivExists == null) {
					m.addIndiv_Illness(m.cleanString(illnessVal));
					m.addDataPropIllness(illnessVal);
					m.addObjectTreats(prepIndiv, m.cleanString(illnessVal));
				} else {
					String illIndiv = q.getIllnessIndivName(illnessVal);
					m.setIllnessIndiv(illIndiv);
					m.addObjectTreats(prepIndiv, illIndiv);
				}

			}

			PrintWriter out = response.getWriter();
			String message = "Preparation Successfully Edited";
			out.println(message);
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			String message = "Preparation Edit Unsuccessful";
			out.println(message);
		}

	}

	private void addPrep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			OntologyLoadException, OWLOntologyCreationException, OWLOntologyStorageException, SQWRLException {
		String prepVal = request.getParameter("prepVal");
		String plantPartVal = request.getParameter("plantPartVal");
		String illnessVal = request.getParameter("illnessVal");
		String medPlantName = request.getParameter("medPlantName");

		OntoQuery q = new OntoQuery(owlPath);

		String checkIfPrepIndivExists = q.getPrepIndivName(prepVal);
		String checkIfIllIndivExists = q.getIllnessIndivName(illnessVal);
		String checkIfPlantPartIndivExists = q.getIllnessIndivName(plantPartVal);

		OntoMngr m = new OntoMngr(owlPath);

		String prepIndiv;
		if (checkIfPrepIndivExists == null) {
			prepIndiv = m.cleanString(prepVal).replaceAll("\\.", "");
			String medPlantIndiv = q.getMedPlantIndivName(medPlantName);

			m.addIndiv_Preparation(prepIndiv);
			m.addDataPropPreparation(prepVal);
			m.addObjectHasPreparation(medPlantIndiv, prepIndiv);
		} else {
			prepIndiv = q.getPrepIndivName(prepVal);
			m.setPreparationIndiv(prepIndiv);

			List<String> plantParts = q.getPreparationPlantPart(prepVal);
			System.out.println(plantParts);
			if (!plantParts.get(0).equalsIgnoreCase(plantPartVal)) {
				PrintWriter out = response.getWriter();
				String message = "Failed to Add Preparation, Existing preparation has different plant part.";
				out.println(message);
				return;
			}

		}

		if (checkIfPlantPartIndivExists == null) {
			m.addIndiv_PlantPart(m.cleanString(plantPartVal));
			m.addDataPropPlantPart(plantPartVal);
			m.addObjectUtilizedPart(prepIndiv, m.cleanString(plantPartVal));
		} else {
			String plantPartIndiv = q.getPlantPartIndivName(plantPartVal);
			m.setPlantPartIndiv(plantPartIndiv);
			m.addObjectUtilizedPart(prepIndiv, plantPartIndiv);
		}

		if (checkIfIllIndivExists == null) {
			m.addIndiv_Illness(m.cleanString(illnessVal));
			m.addDataPropIllness(illnessVal);
			m.addObjectTreats(prepIndiv, m.cleanString(illnessVal));
		} else {
			String illIndiv = q.getIllnessIndivName(illnessVal);
			m.setIllnessIndiv(illIndiv);
			m.addObjectTreats(prepIndiv, illIndiv);
		}
		PrintWriter out = response.getWriter();
		String message = "Preparation Added Successfully";
		out.println(message);

	}

	private void editComp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldPlantPartCompVal = request.getParameter("oldPlantPartCompVal");
		String oldChemCompVal = request.getParameter("oldChemCompVal");
		String plantPartCompVal = request.getParameter("plantPartCompVal");
		String chemCompVal = request.getParameter("chemCompVal");
		String specieName = request.getParameter("specieName");

		OntoQuery q = new OntoQuery(owlPath);
		OntoMngr m = new OntoMngr(owlPath);

		try {
			String oldSpeciesPart = m.cleanString(specieName + " " + oldPlantPartCompVal);

			String checkIfCompIndivExists = q.getCompoundIndivName(chemCompVal);

			String checkIfPlantPartIndivExists = q.getPlantPartIndivName(plantPartCompVal);

			String newSpeciesPart = m.cleanString(specieName + " " + plantPartCompVal);
			String checkIfSpeciesPartIndivExists = q.getSpeciesPartIndivName(newSpeciesPart);

			String oldChemCompIndiv = q.getCompoundIndivName(oldChemCompVal);
			m.removeObjectPropertyValue(oldSpeciesPart, "hasCompound", oldChemCompIndiv);

			// remove species part if no more remaining compounds
			if (q.getSpeciesPartCompound(oldSpeciesPart).size() == 1) {
				m.removeObjectPropertyValue(m.cleanString(specieName), "hasChildPlantPart", oldSpeciesPart);
			}

			if (checkIfSpeciesPartIndivExists == null) {
				m.addIndiv_SpeciesPart(newSpeciesPart);
				m.addObjectHasChildPlantPart(m.cleanString(specieName), newSpeciesPart);
			}

			if (checkIfPlantPartIndivExists == null) {
				m.addIndiv_PlantPart(m.cleanString(plantPartCompVal));
				m.addDataPropPlantPart(plantPartCompVal);
				m.addObjectHasPlantPart(newSpeciesPart, m.cleanString(plantPartCompVal));
			} else {
				m.addObjectHasPlantPart(newSpeciesPart, m.cleanString(plantPartCompVal));
			}

			if (checkIfCompIndivExists == null) {
				m.addIndiv_Compound(m.cleanString(chemCompVal));
				m.addDataPropCompound(chemCompVal);
				m.addObjectHasCompound(newSpeciesPart, m.cleanString(chemCompVal));
			} else {
				m.addObjectHasCompound(newSpeciesPart, m.cleanString(chemCompVal));
			}

			PrintWriter out = response.getWriter();
			String message = "Chemical Compound Successfully Edited";
			out.println(message);
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			String message = "Chemical Compound Edit Failed";
			out.println(message);
		}

	}
}
