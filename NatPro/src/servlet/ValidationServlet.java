package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.flickr4java.flickr.FlickrException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import service.OntoQuery;

/**
 * Servlet implementation class ValidationServlet
 */
@WebServlet("/ValidationServlet")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		switch (request.getServletPath()) {
		case "/ValidationServlet":
			try {
				getValidationXML(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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

	private void getValidationXML(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		Reader fileReader = null;
		ArrayList<String> lines = new ArrayList<String>();
		for (File xmlFile : listFiles) {
			System.out.println(xmlFile.getAbsolutePath());
			String xmlString = readFile(xmlFile, fileReader).toString();
			String[] xmlLine = xmlString.split("\\r?\\n");
			Collections.addAll(lines, xmlLine);
//			System.out.println(lines);
			
			TreeSet<String> CategoryList = new TreeSet<String>();
			readXML(CategoryList, xmlFile);
			System.out.println(CategoryList);
			
//			for (String pLine : lines) {
//				pLine = pLine.toLowerCase();
//				 System.out.println(pLine+"/n");
//
//			}
		}
		
		request.getRequestDispatcher("_validation-new.jsp").forward(request, response);

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
	
	public void readXML(String tag, TreeSet<String> matches, File xmlFile) {
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
			// System.out.println("Root element: " +
			// doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("Seed");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				// System.out.println("Node Name: " + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					// System.out.println("I am here");
					
					Element eElement = (Element) nNode;
					int eCount = eElement.getElementsByTagName(tag).getLength();
					
					for (int j = 0; j < eCount; j++) {
						// System.out.println(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
						matches.add(eElement.getElementsByTagName(tag).item(j).getTextContent());
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
