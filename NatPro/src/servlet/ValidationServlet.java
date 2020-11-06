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

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.flickr4java.flickr.FlickrException;
import com.google.gson.Gson;

import edu.stanford.smi.protege.exception.OntologyLoadException;
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
	private static final String taggedFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\TaggedBootstrap\\";
	private static final String validationFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\validation\\";

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
			getValidationXML(request, response);
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
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		Reader fileReader = null;
		String pdfFileName = null;
		HashSet<Validation> validations = new HashSet<Validation>();
		for (File xmlFile : listFiles) {
			ArrayList<String> lines = new ArrayList<String>();
//			System.out.println(xmlFile.getAbsolutePath());
			pdfFileName = getPdfFileName(xmlFile) + ".pdf";
			if (pdfFileName.equalsIgnoreCase(plantFileName)) {

//			request.setAttribute("pdfFileName", pdfFileName);
				Validation xmlValidation = new Validation(pdfFileName);
				xmlValidation = findIfPresent(xmlValidation, validations);

				String xmlString = readFile(xmlFile, fileReader).toString();
				String[] xmlLine = xmlString.split("\\r?\\n");
				Collections.addAll(lines, xmlLine);
//			System.out.println(lines);

				TreeSet<String> CategoryList = new TreeSet<String>();
				readXML(xmlValidation, xmlFile);
//				readXML(xmlValidation, xmlFile, "Tag2");

				validations.add(xmlValidation);
			}
		}
		String jsonString = new Gson().toJson(validations);
		System.out.println(jsonString);
		PrintWriter out = response.getWriter();
		out.println(jsonString);

	}

	private void getValidationXML(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		request.getRequestDispatcher("validation-dan.jsp").forward(request, response);

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

					if (tag1.contains("Synonym")) {
						Species specie = new Species(nameElementTag1.item(0).getTextContent());
						// check if species is already in the hash set, add new if specie is unique
						if (validation.getSynonyms().contains(specie)) {
							for (Species s : validation.getSynonyms()) {
								if (s.equals(specie))
									specie = s;
							}
						}
						ArrayList<SpeciesPart> specieParts = new ArrayList<>();
						if (tag2.contains("PlantPart")) {
							for (int j = 0; j < nameElementTag2.getLength(); j++) {
								SpeciesPart speciePart = new SpeciesPart(nameElementTag2.item(j).getTextContent());
								specieParts.add(speciePart);

							}
						}
						specie.setSpeciesParts(specieParts);
						validation.addSynonyms(specie);
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
