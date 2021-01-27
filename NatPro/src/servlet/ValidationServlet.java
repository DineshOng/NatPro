package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.BiologicalActivity;
import model.CellLine;
import model.Compound;
import model.MedicinalPlant;
import model.Species;
import model.SpeciesPart;
import model.Validation;
import service.OntoQuery;

/**
 * Servlet implementation class ValidationServlet
 */
@WebServlet({ "/ValidationServlet", "/GetPlantEntity" })
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String taggedFolder = "C:\\\\Users\\\\Unknown\\\\eclipse-workspace-jee\\\\NatPro\\\\Documents\\\\TaggedBootstrap\\";
	private static final String validationFolder = "C:\\\\Users\\\\Unknown\\\\eclipse-workspace-jee\\\\NatPro\\\\Documents\\\\validation\\";
	private static final String owlPath = "C:\\\\\\\\Users\\\\\\\\Unknown\\\\\\\\eclipse-workspace-jee\\\\\\\\NatPro\\\\\\\\Ontology\\\\\\\\OntoNatPro.owl";
	
//	private static final String taggedFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\TaggedBootstrap\\";
//	private static final String validationFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\validation\\";
//	private static final String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro.owl";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidationServlet() {
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
		switch (request.getServletPath()) {
		case "/ValidationServlet":
			try {
				getValidationXML(request, response);
			} catch (ServletException | IOException | OntologyLoadException | SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/GetPlantEntity":
			getPlantEntity(request, response);
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
//		doGet(request, response);
	}

	private void getPlantEntity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String plantFileName = request.getParameter("fileName");
		String sciName = request.getParameter("sciName");
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		Reader fileReader = null;
		String pdfFileName = null;
		HashSet<Validation> validations = new HashSet<Validation>();
		for (File xmlFile : listFiles) {
			ArrayList<String> lines = new ArrayList<String>();

			pdfFileName = getPdfFileName(xmlFile) + ".pdf";
			if (pdfFileName.equalsIgnoreCase(plantFileName)) {

				Validation xmlValidation = new Validation(pdfFileName);
				xmlValidation = findIfPresent(xmlValidation, validations);

				readXML(xmlValidation, xmlFile);

				validations.add(xmlValidation);
			}
		}
		String jsonEntry = "";
		Iterator<Validation> vIt = validations.iterator();
		while (vIt.hasNext()) {
			Validation v = vIt.next();
			if (v.getPdfFileName().equalsIgnoreCase(plantFileName)) {
//				Iterator<Species> sIt = v.getSynonyms().iterator();
//				while (sIt.hasNext()) {
//					Species s = sIt.next();
//					if(s.getSpecie().equalsIgnoreCase(sciName)) {
//						jsonEntry = new Gson().toJson(s);
//					}
//				}

				Iterator<MedicinalPlant> mIt = v.getMedicinalPlants().iterator();
				while (mIt.hasNext()) {
					MedicinalPlant m = mIt.next();
					try {
						if (m.getSpecies().get(0).getSpecie().equalsIgnoreCase(sciName) ) {
							jsonEntry = new Gson().toJson(m);
						}
						if ((!m.getMedicinalPlant().equalsIgnoreCase(sciName)) && m.getSpecies().get(0).getSpecie().equalsIgnoreCase(sciName)) {
							jsonEntry = new Gson().toJson(m);
							break;
						}
						System.out.println(jsonEntry);
					} catch (Exception e) {

					}
				}
			}
		}
//		String jsonValidations = new Gson().toJson(validations);
//		System.out.println(jsonValidations);
		PrintWriter out = response.getWriter();
		out.println(jsonEntry);

	}

	private void getValidationXML(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, SQWRLException {
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		Reader fileReader = null;
		String pdfFileName = null;
		HashSet<Validation> validations = new HashSet<Validation>();
		for (File xmlFile : listFiles) {
			ArrayList<String> lines = new ArrayList<String>();
//			System.out.println(xmlFile.getAbsolutePath());
			pdfFileName = getPdfFileName(xmlFile) + ".pdf";

			Validation xmlValidation = new Validation(pdfFileName);
			xmlValidation = findIfPresent(xmlValidation, validations);

			String xmlString = readFile(xmlFile, fileReader).toString();
			String[] xmlLine = xmlString.split("\\r?\\n");
			Collections.addAll(lines, xmlLine);
//			System.out.println(lines);

			TreeSet<String> CategoryList = new TreeSet<String>();
			readXML(xmlValidation, xmlFile);
//			readXML(xmlValidation, xmlFile, "Tag2");

			validations.add(xmlValidation);
		}

		request.setAttribute("Validations", validations);
		String jsonValidations = new Gson().toJson(validations);
		request.setAttribute("JsonValidations", jsonValidations);
		OntoQuery q = new OntoQuery(owlPath);
		List<String> plantParts = q.getAllPlantParts();
		request.setAttribute("plantPartsList", plantParts);
		List<String> sciNames = q.getAllSynonyms();
		request.setAttribute("sciNameList", sciNames);
		List<String> medPlantNames = q.getAllMedPlantNames();
		request.setAttribute("medPlantNameList", medPlantNames);

		request.getRequestDispatcher("validation.jsp").forward(request, response);

	}

	Validation findIfPresent(Validation curValidation, HashSet<Validation> validations) {
		if (validations.contains(curValidation)) {
			for (Validation v : validations) {
				if (v.equals(curValidation))
					return v;
			}
		}

		return curValidation;
	}

	public StringBuilder readFile(File xmlFile, Reader fileReader) {
		try {
			fileReader = new FileReader(xmlFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bufReader = new BufferedReader(fileReader);
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			line = bufReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (line != null) {
			sb.append(line).append("\n");
			try {
				line = bufReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (sb);
	}

	public String getPdfFileName(File xmlFile) {
		String pdfFileName = null;
		Pattern pattern = Pattern.compile("^([A-Z]*)\\w+");
		Matcher matcher = pattern.matcher(xmlFile.getName());
		if (matcher.find()) {
			pdfFileName = matcher.group();
		}
		return pdfFileName;

	}

	public void readXML(Validation validation, File xmlFile) {
		// CHECKS IF THE GENERATED XML FILE EXISTS
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			/*
			 * =============================================================================
			 * ==========================
			 * =============================================================================
			 * ========================== NOTE: CHECK IF FOLDER EXISTS
			 * =============================================================================
			 * ==========================
			 * =============================================================================
			 * ==========================
			 */
			Document doc = builder.parse(xmlFile.getAbsoluteFile());
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Seed");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String tag1 = eElement.getElementsByTagName("Tag1").item(0).getTextContent();
					String tag2 = eElement.getElementsByTagName("Tag2").item(0).getTextContent();

					Element elementTag1 = (Element) eElement.getElementsByTagName("Tag1").item(0);
					Element elementTag2 = (Element) eElement.getElementsByTagName("Tag2").item(0);

					NodeList nameElementTag1 = elementTag1.getElementsByTagName("Name");
					NodeList nameElementTag2 = elementTag2.getElementsByTagName("Name");

					// MEDICINAL PLANT
					if (tag1.contains("MedicinalPlant")) {
						MedicinalPlant medPlant = new MedicinalPlant(nameElementTag1.item(0).getTextContent());
						// check if species is already in the hash set, add new if specie is unique
						if (validation.getMedicinalPlants().contains(medPlant)) {
							for (MedicinalPlant m : validation.getMedicinalPlants()) {
								if (m.equals(medPlant))
									medPlant = m;
							}
						}

						if (tag2.contains("Synonym")) {
							ArrayList<Species> species = new ArrayList<>();
							Species specie = new Species(nameElementTag2.item(0).getTextContent());
							species.add(specie);
							medPlant.setSpecies(species);
						}

						validation.addMedicinalPlants(medPlant);
					}

					// SYNOYNM
					if (tag1.contains("Synonym")) {
						Species specie = new Species(nameElementTag1.item(0).getTextContent());
						// check if species is already in the hash set, add new if specie is unique
						if (validation.getSynonyms().contains(specie)) {
							for (Species s : validation.getSynonyms()) {
								if (s.equals(specie))
									specie = s;
							}
						}

						MedicinalPlant medPlant = new MedicinalPlant(specie.getSpecie());
						ArrayList<Species> sList = new ArrayList<Species>();
						sList.add(specie);
						medPlant.setSpecies(sList);
						try {
							for (MedicinalPlant mp : validation.getMedicinalPlants()) {
								if (mp.getSpecies().get(0).getSpecie().equalsIgnoreCase(specie.getSpecie()))
									medPlant = mp;
							}
						} catch (Exception e) {
						}

						if (tag2.contains("PlantPart")) {
							ArrayList<SpeciesPart> specieParts = new ArrayList<>();
							for (int j = 0; j < nameElementTag2.getLength(); j++) {
								SpeciesPart speciePart = new SpeciesPart(nameElementTag2.item(j).getTextContent());
								specieParts.add(speciePart);

							}
							specie.getSpeciesParts().addAll(specieParts);
						}

						if (tag2.contains("Compound")) {
							HashSet<Compound> compounds = new HashSet<Compound>();
							SpeciesPart tempSp = new SpeciesPart("tempSp");
							for (int j = 0; j < nameElementTag2.getLength(); j++) {
								Compound compound = new Compound(nameElementTag2.item(j).getTextContent());
								compounds.add(compound);
								ArrayList<Compound> compList = new ArrayList<Compound>(compounds);
								tempSp.setCompounds(compList);
							}

							specie.getSpeciesParts().add(tempSp);

						}

						if (tag2.contains("Location")) {
							ArrayList<String> locations = new ArrayList<String>();

							for (int j = 0; j < nameElementTag2.getLength(); j++) {
								locations.add(WordUtils.capitalizeFully(nameElementTag2.item(j).getTextContent()));
							}

							medPlant.setLocations(locations);

						}

						if (tag2.contains("Family")) {
							specie.setFamily(WordUtils.capitalizeFully(nameElementTag2.item(0).getTextContent()));
						}

						if (tag2.contains("Genus")) {
							specie.setGenus(WordUtils.capitalizeFully(nameElementTag2.item(0).getTextContent()));
						}

						validation.addSynonyms(specie);
						validation.addMedicinalPlants(medPlant);
					}

					// PLANT PART
					if (tag1.contains("PlantPart")) {
						SpeciesPart speciePart = new SpeciesPart(nameElementTag1.item(0).getTextContent());
						// check if species is already in the hash set, add new if specie is unique
						if (validation.getPlantParts().contains(speciePart)) {
							for (SpeciesPart sp : validation.getPlantParts()) {
								if (sp.equals(speciePart))
									speciePart = sp;
							}
						}

						if (tag2.contains("Compound")) {
							ArrayList<Compound> compounds = new ArrayList<>();
							for (int j = 0; j < nameElementTag2.getLength(); j++) {
								Compound compound = new Compound(nameElementTag2.item(j).getTextContent());
								compounds.add(compound);

							}
							speciePart.setCompounds(compounds);
						}

						validation.addPlantParts(speciePart);
					}

					// COMPOUND
					if (tag1.contains("Compound")) {
						Compound compound = new Compound(nameElementTag1.item(0).getTextContent());
						// check if bioact is already in the hash set, add new if bioact is unique
						if (validation.getCompounds().contains(compound)) {
							for (Compound c : validation.getCompounds()) {
								if (c.equals(compound))
									compound = c;
							}
						}

						if (tag2.contains("BioAct")) {
							HashSet<BiologicalActivity> bioActs = new HashSet<BiologicalActivity>();
							for (int j = 0; j < nameElementTag2.getLength(); j++) {
								BiologicalActivity bioAct = new BiologicalActivity(
										nameElementTag2.item(j).getTextContent());
								bioActs.add(bioAct);

							}
							compound.setBioActs(bioActs);
							validation.addCompounds(compound);
						}

					}

					// BIOLOGICAL ACTIVITY
					if (tag1.contains("BioAct")) {
						if (tag2.contains("CellLine")) {
							for (int j = 0; j < nameElementTag2.getLength(); j++) {
								BiologicalActivity bioAct = new BiologicalActivity(
										nameElementTag1.item(0).getTextContent());
								CellLine cellLine = new CellLine(nameElementTag2.item(j).getTextContent());
								bioAct.setCellLine(cellLine);
								validation.addBioActs(bioAct);
							}

						}
					}

					// CONNECT HASHSETS TO ITS CORRESPONDING OBJECTS
					// connect compounds to bioacts
					if (validation.getCompounds().size() > 0) {
						Iterator<Compound> cIt = validation.getCompounds().iterator();
						while (cIt.hasNext()) {
							Compound comp = cIt.next();
							for (SpeciesPart sp : validation.getPlantParts()) {
								for (Compound c : sp.getCompounds()) {
									if (c.getCompoundName().equals(comp.getCompoundName())) {
										c.setBioActs(comp.getBioActs());
									}
								}
							}

						}
					}

					// connect species to compounds
					Iterator<Species> sIt = validation.getSynonyms().iterator();
					while (sIt.hasNext()) {
						Species specie = sIt.next();

						Iterator<SpeciesPart> ppIt = validation.getPlantParts().iterator();
						while (ppIt.hasNext()) {
							SpeciesPart speciePart = ppIt.next();
							if (specie.getSpeciesParts() != null)
								for (SpeciesPart sp : specie.getSpeciesParts()) {
									if (sp.equals(speciePart)) {
										sp.setCompounds(speciePart.getCompounds());
									}
								}

						}

						if (validation.getCompounds().size() > 0) {
							Iterator<Compound> cIt = validation.getCompounds().iterator();
							while (cIt.hasNext()) {
								Compound comp = cIt.next();
								try {
									for (SpeciesPart sp : specie.getSpeciesParts()) {
										for (Compound c : sp.getCompounds()) {
											if (c.getCompoundName().equals(comp.getCompoundName())) {
												c.setBioActs(comp.getBioActs());
											}
										}
									}
								} catch (Exception e) {
								}

							}
						}

					}

					// connect locations to medplant
					if (validation.getMedicinalPlants().size() > 0) {
						Iterator<MedicinalPlant> mIt = validation.getMedicinalPlants().iterator();
						while (mIt.hasNext()) {
							MedicinalPlant medPlant = mIt.next();
							Iterator<MedicinalPlant> mIt2 = validation.getMedicinalPlants().iterator();
							while (mIt2.hasNext()) {
								MedicinalPlant medPlant2 = mIt2.next();
								try {
									if (medPlant.getMedicinalPlant()
											.equalsIgnoreCase(medPlant2.getSpecies().get(0).getSpecie())) {
										medPlant2.setLocations(medPlant.getLocations());
									}
								} catch (Exception e) {
								}
							}

						}

					}

					if (validation.getCompounds().size() > 0) {
						Iterator<Compound> cIt = validation.getCompounds().iterator();
						while (cIt.hasNext()) {
							Compound compound = cIt.next();
							HashSet<BiologicalActivity> baHs = new HashSet<BiologicalActivity>();
							Iterator<BiologicalActivity> cbIt = compound.getBioActs().iterator();
							while (cbIt.hasNext()) {
								BiologicalActivity compoundBioAct = cbIt.next();
								baHs.add(compoundBioAct);
								Iterator<BiologicalActivity> bIt = validation.getBioAct().iterator();
								while (bIt.hasNext()) {
									BiologicalActivity bioAct = bIt.next();
									if (compoundBioAct.getBiologicalActivity()
											.equalsIgnoreCase(bioAct.getBiologicalActivity())) {
										baHs.add(bioAct);
										baHs.remove(compoundBioAct);
									}
								}

							}
							compound.setBioActs(baHs);
						}

					}

					if (validation.getMedicinalPlants().size() > 0) {
						Iterator<MedicinalPlant> mIt = validation.getMedicinalPlants().iterator();
						while (mIt.hasNext()) {
							MedicinalPlant medPlant = mIt.next();
							Iterator<Species> sIt2 = validation.getSynonyms().iterator();
							while (sIt2.hasNext()) {
								Species specie = sIt2.next();
								try {
									if (medPlant.getSpecies().get(0).getSpecie().equalsIgnoreCase(specie.getSpecie())) {
										ArrayList<Species> sList = new ArrayList<Species>();
										sList.add(specie);
										medPlant.setSpecies(sList);
									}
								} catch (Exception e) {
								}
							}
						}

					}

				}
			}
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		}

	}

}
