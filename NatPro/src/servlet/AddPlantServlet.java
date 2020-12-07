package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.WordUtils;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.BiologicalActivity;
import model.CellLine;
import model.Compound;
import model.MedicinalPlant;
import model.Species;
import model.SpeciesPart;
import model.Validation;
import service.OntoMngr;
import service.OntoQuery;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Servlet implementation class AddPlantServlet
 */
@WebServlet({ "/AddPlantServlet", "/AddPlantPageServlet", "/ApprovePlantServlet", "/RejectPlantServlet" })
public class AddPlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String taggedFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\TaggedBootstrap\\";
	private static final String validationFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\validation\\";

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
			} catch (OWLOntologyCreationException | OWLOntologyStorageException | OntologyLoadException
					| ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		case "/ApprovePlantServlet":
			removeApproved(request, response);
			try {
				addPlant(request, response);
			} catch (OWLOntologyCreationException | OWLOntologyStorageException | OntologyLoadException
					| ServletException | IOException e1) {
			}

			break;
		case "/RejectPlantServlet":
			removeRejected(request, response);
			request.getRequestDispatcher("/ValidationServlet").forward(request, response);
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

		if (!request.getParameter("commonPlantName").equals("")) { // check if common plant name is filled
			String commonPlantNameIndiv = request.getParameter("commonPlantName").trim().toLowerCase().replaceAll(" ",
					"_");
			String commonPlantName = request.getParameter("commonPlantName").trim();
			// create individual for MedicinalPlant
			m.addIndiv_MedPlant(commonPlantNameIndiv);
			// add data property for MedicinalPlant individual
			m.addDataPropMedPlant(commonPlantName);

			if (!request.getParameter("scientificName").equals("")) { // check if scientific name is filled
				String speciesNameIndiv = request.getParameter("scientificName").trim().toLowerCase().replaceAll(" ",
						"_");
				// create individual for Species
				m.addIndiv_Species(speciesNameIndiv);
				// add data property for Species individual
				m.addDataPropSpecies(request.getParameter("scientificName").trim());
				// add object property MedicinalPlant -> Species
				m.addObjectHasScientificName(commonPlantNameIndiv, speciesNameIndiv);

				if (!request.getParameter("genus").equals("")) { // check if genus is filled
					String genusNameIndiv = request.getParameter("genus").trim().toLowerCase().replaceAll(" ", "_");
					// create individual for Genus
					m.addIndiv_Genus(genusNameIndiv);
					// add data property for Genus individual
					m.addDataPropGenus(request.getParameter("genus").trim());
					// add object property Species -> Genus
					m.addObjectBelongsToGenus(speciesNameIndiv, genusNameIndiv);

					if (!request.getParameter("family").equals("")) { // check if family is filled
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
						String temp = request.getParameter("plantPart[" + i + "]");
						if (temp == null || temp.equals("")) {
							temp = "plant";
						}
						if (temp != null) { // check if a plant part is selected
							String plantPart = temp.replaceAll(" ", "_");
							String speciesPartIndiv = speciesNameIndiv + "_" + plantPart;
							// create individual for SpeciesPart
							m.addIndiv_SpeciesPart(speciesPartIndiv);
							// add object property Species -> SpeciesPart
							m.addObjectHasChildPlantPart(speciesNameIndiv, speciesPartIndiv);
							// add object property SpeciesPart -> PlantPart
							m.addObjectHasPlantPart(speciesPartIndiv, plantPart);

							String[] compoundCtr = request.getParameterValues("lengthCC" + i);
							int compoundCtrMax = Integer.parseInt(compoundCtr[compoundCtr.length - 1]);
							compoundCtrMax++;

							for (int j = 0; j < compoundCtrMax; j++) {
								String compound = request.getParameter("compound[" + i + "][" + j + "]");
								if (compound != null && !compound.equals("")) {
									String compoundIndiv = cleanCompoundString(compound);
									// create individual for Compound
									m.addIndiv_Compound(compoundIndiv);
									// add data property for Compound individual
									m.addDataPropCompound(compound);
									// add object property SpeciesPart -> Compound
									m.addObjectHasCompound(speciesPartIndiv, compoundIndiv);

									try {
										String[] bioCellCtr = request
												.getParameterValues("lengthBC[" + i + "][" + j + "]");
										int bioCellMax = Integer.parseInt(bioCellCtr[bioCellCtr.length - 1]);
										bioCellMax++;
										for (int k = 0; k < bioCellMax; k++) {
											String bioAct = request
													.getParameter("bioAct[" + i + "][" + j + "][" + k + "]");
											if (!bioAct.equals("")) {
												String bioActIndiv = bioAct.trim().toLowerCase().replaceAll(" ", "_");
												// create individual for BiologicalActivity
												m.addIndiv_BiologicalActivity(bioActIndiv);
												// add data property for BiologicalActivity individual
												m.addDataPropBiologicalActivity(bioAct);
												// add object property Compound -> BiologicalActivity
												m.addObjectHasBiologicalActivity(compoundIndiv, bioActIndiv);
												String cellLine = request
														.getParameter("cellLine[" + i + "][" + j + "][" + k + "]");
												if (!cellLine.equals("")) {
													String cellLineIndiv = cellLine.trim().toLowerCase().replaceAll(" ",
															"_");
													// create individual for CellLine
													m.addIndiv_CellLine(cellLineIndiv);
													// add data property for CellLine individual
													m.addDataPropCellLine(cellLine);
													// add object property BiologicalActivity -> CellLine
													m.addObjectAffects(bioActIndiv, cellLineIndiv);
												}
											}
										}
									} catch (Exception e) {
										System.out.println("No Bio Act");
									}

								}

							}
						}

					}
				}
			}

			String[] locationNames;
			locationNames = request.getParameterValues("location");
			for (int i = 0; i < locationNames.length; i++) {
				if (!locationNames[i].equals("")) {
					String locationNameIndiv = locationNames[i].trim().toLowerCase().replaceAll(", ", " ")
							.replaceAll(",", " ").replaceAll(" ", "_");
					m.addIndiv_Location(locationNameIndiv);
					m.addDataPropLocation(locationNames[i].trim());

					m.addObjectIsLocatedIn(commonPlantNameIndiv, locationNameIndiv);
				}
			}

			String[] prepCtr = request.getParameterValues("prepCtr");
			int prepCtrMax = Integer.parseInt(prepCtr[prepCtr.length - 1]);
			prepCtrMax++;
			for (int i = 0; i < prepCtrMax; i++) {
				String allPreparation = "";
				String allIllness = "";
				String prepPartIndiv = "";
				ArrayList<String> illnessIndivs = new ArrayList<>();
				if (request.getParameter("preparation[" + i + "]") != null
						&& !request.getParameter("preparation[" + i + "]").equals("")) {
					String preparation = request.getParameter("preparation[" + i + "]");
					allPreparation = allPreparation.concat(preparation + " ");

					String temp = request.getParameter("prepPart[" + i + "]");
					if (temp == null || temp.equals("")) {
						temp = "plant";
					}
					if (temp != null) {
						String prepPart = temp;
						prepPartIndiv = prepPart.trim().toLowerCase().replaceAll(" ", "_");
						allPreparation = allPreparation.concat(prepPart + " ");
					}

					String[] illnessCtr = request.getParameterValues("illnessCtr[" + i + "]");
					int illnessCtrMax = Integer.parseInt(illnessCtr[illnessCtr.length - 1]);
					illnessCtrMax++;
					for (int j = 0; j < illnessCtrMax; j++) {
						if (request.getParameter("illness[" + i + "][" + j + "]") != null) {
							String illness = request.getParameter("illness[" + i + "][" + j + "]");
							String illnessIndiv = illness.trim().toLowerCase().replaceAll(" ", "_");
							// create individual for Illness
							m.addIndiv_Illness(illnessIndiv);
							// add data property for Illness individual
							m.addDataPropIllness(illness);
							illnessIndivs.add(illnessIndiv);
							if (!allIllness.equals("")) {
								allIllness = allIllness.concat(", " + illness);
							} else {
								allIllness = allIllness.concat(illness);
							}

						}
					}
					if (!allIllness.equals("")) {
						allPreparation = allPreparation.concat("used for " + allIllness);
					}
					System.out.println(allPreparation);
					String allPreparationIndiv = allPreparation.trim().toLowerCase().replaceAll(" ", "_")
							.replaceAll(",", "");
					// create individual for Preparation
					m.addIndiv_Preparation(allPreparationIndiv);
					// add data property for Preparation individual
					m.addDataPropPreparation(allPreparation);
					for (int j = 0; j < illnessIndivs.size(); j++) {
						// add object property Preparation -> Illness
						m.addObjectTreats(allPreparationIndiv, illnessIndivs.get(j));
					}
					// add object property MedicinalPlant -> Preparation
					m.addObjectHasPreparation(commonPlantNameIndiv, allPreparationIndiv);
					if (!prepPartIndiv.equals("")) {
						// add object property Preparation -> PlantPart
						m.addObjectUtilizedPart(allPreparationIndiv, prepPartIndiv);
					}
					System.out.println(allPreparationIndiv);
				}

			}
		}

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
		request.getRequestDispatcher("3aadded.jsp").forward(request, response);

	}

	private void removeApproved(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("fileNameApprove").replaceAll(".pdf", "");
		String specieName = request.getParameter("specieNameApprove").toLowerCase();
		removeValidationFile(fileName, specieName);
	}

	private void removeRejected(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("fileNameReject").replaceAll(".pdf", "");
		String specieName = request.getParameter("specieNameReject").toLowerCase();
		removeValidationFile(fileName, specieName);
	}

	private void removeValidationFile(String fileName, String specieName) {
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		List<File> deleteFiles = new ArrayList<>();
		for (File xmlFile : listFiles) {
			String deleteFile = fileName + "-" + specieName;
			Pattern p = Pattern.compile("^(.)*(" + deleteFile + ")(.)*");
			Matcher m = p.matcher(xmlFile.toString());
			boolean b = m.matches();
			System.out.println(b);
			System.out.println(deleteFile);
			System.out.println(xmlFile);
			if (b) {
				deleteFiles.add(xmlFile);
//				if (xmlFile.delete()) {
//					System.out.println("Deleted the file: " + xmlFile.getName());
//				} else {
//					System.out.println("Failed to delete the file.");
//				}
			}

			Pattern p1 = Pattern.compile("^(.)*(" + fileName + ")(.)*");
			Matcher m1 = p1.matcher(xmlFile.toString());
			boolean b1 = m1.matches();
			if (b1) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				boolean delete = false;
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
								if (tag2.contains("Synonym")) {
									if (nameElementTag2.item(0).getTextContent().equalsIgnoreCase(specieName)) {
										delete = true;
									}
								}
							}
						}
					}
				} catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
					e.printStackTrace();
				}

				if (delete) {
					deleteFiles.add(xmlFile);
				}
			}

		}
		System.out.println(deleteFiles);
		System.gc();
		for (File deleteXml : deleteFiles) {
			if (deleteXml.delete()) {
				System.out.println("Deleted the file");
			} else {
				System.err.println("Failed to delete the file.");
			}
		}

	}

}
