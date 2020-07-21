package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.BiologicalActivity;
import model.Compound;
import model.MedicinalPlant;
import model.Preparation;
import model.Species;
import model.SpeciesPart;
import service.AdvancedCompoundSearch;
import service.OntoQuery;

/**
 * Servlet implementation class AdvancedSearchCompoundServlet
 */
@WebServlet("/AdvancedSearchCompoundServlet")
public class AdvancedSearchCompoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdvancedSearchCompoundServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HashMap<String, String> props = new HashMap<>();
		
		String location = request.getParameter("location").trim().equals("") ? null : request.getParameter("location").trim();
		String family = request.getParameter("family").trim().equals("") ? null : request.getParameter("family").trim();
		String genus = request.getParameter("genus").trim().equals("") ? null : request.getParameter("genus").trim();
		String species = request.getParameter("species").trim().equals("") ? null : request.getParameter("species").trim();
		String plantPart = request.getParameter("plantPart").trim().equals("") ? null : request.getParameter("plantPart").trim();
		
		String illness = request.getParameter("illness").trim().equals("") ? null : request.getParameter("illness").trim();
		String bioAct = request.getParameter("bioAct").trim().equals("") ? null : request.getParameter("bioAct").trim();
		String cellLine = request.getParameter("cellLine").trim().equals("") ? null : request.getParameter("cellLine").trim();

		String compoundName = request.getParameter("compound").trim().equals("") ? null
				: request.getParameter("compound").trim();
		String molForm = request.getParameter("molForm").trim().equals("") ? null
				: request.getParameter("molForm").trim();
		String compoundClass = request.getParameter("compoundClass").trim().equals("") ? null
				: request.getParameter("compoundClass").trim();

		String molWeight_lhs = request.getParameter("molWeight_lhs").trim().equals("") ? null
				: request.getParameter("molWeight_lhs").trim();
		String xlogp_lhs = request.getParameter("xlogp_lhs").trim().equals("") ? null
				: request.getParameter("xlogp_lhs").trim();
		String mass_lhs = request.getParameter("mass_lhs").trim().equals("") ? null
				: request.getParameter("mass_lhs").trim();
		String tpsa_lhs = request.getParameter("tpsa_lhs").trim().equals("") ? null
				: request.getParameter("tpsa_lhs").trim();
		String complexity_lhs = request.getParameter("complexity_lhs").trim().equals("") ? null
				: request.getParameter("complexity_lhs").trim();

		String charge_lhs = request.getParameter("charge_lhs").trim().equals("") ? null
				: request.getParameter("charge_lhs").trim();
		String hBondDonor_lhs = request.getParameter("hBondDonor_lhs").trim().equals("") ? null
				: request.getParameter("hBondDonor_lhs").trim();
		String hBondAcceptor_lhs = request.getParameter("hBondAcceptor_lhs").trim().equals("") ? null
				: request.getParameter("hBondAcceptor_lhs").trim();
		String rotBondCount_lhs = request.getParameter("rotBondCount_lhs").trim().equals("") ? null
				: request.getParameter("rotBondCount_lhs").trim();

		String molWeight_rhs = request.getParameter("molWeight_rhs").trim().equals("") ? null
				: request.getParameter("molWeight_rhs").trim();
		String xlogp_rhs = request.getParameter("xlogp_rhs").trim().equals("") ? null
				: request.getParameter("xlogp_rhs").trim();
		String mass_rhs = request.getParameter("mass_rhs").trim().equals("") ? null
				: request.getParameter("mass_rhs").trim();
		String tpsa_rhs = request.getParameter("tpsa_rhs").trim().equals("") ? null
				: request.getParameter("tpsa_rhs").trim();
		String complexity_rhs = request.getParameter("complexity_rhs").trim().equals("") ? null
				: request.getParameter("complexity_rhs").trim();

		String charge_rhs = request.getParameter("charge_rhs").trim().equals("") ? null
				: request.getParameter("charge_rhs").trim();
		String hBondDonor_rhs = request.getParameter("hBondDonor_rhs").trim().equals("") ? null
				: request.getParameter("hBondDonor_rhs").trim();
		String hBondAcceptor_rhs = request.getParameter("hBondAcceptor_rhs").trim().equals("") ? null
				: request.getParameter("hBondAcceptor_rhs").trim();
		String rotBondCount_rhs = request.getParameter("rotBondCount_rhs").trim().equals("") ? null
				: request.getParameter("rotBondCount_rhs").trim();

		props.put("location", location);
		props.put("family", family);
		props.put("genus", genus);
		props.put("species", species);
		props.put("plantPart", plantPart);
		
		props.put("illness", illness);
		props.put("bioAct", bioAct);
		props.put("cellLine", cellLine);
		
		props.put("compoundName", compoundName);
		props.put("molForm", molForm);
		props.put("compoundClass", compoundClass);
		
		props.put("molWeight_lhs", molWeight_lhs+""+"");
		props.put("xlogp_lhs", xlogp_lhs+"");
		props.put("mass_lhs", mass_lhs+"");
		props.put("tpsa_lhs", tpsa_lhs+"");
		props.put("complexity_lhs", complexity_lhs+"");
		
		props.put("charge_lhs", charge_lhs+"");
		props.put("hBondDonor_lhs", hBondDonor_lhs+"");
		props.put("hBondAcceptor_lhs", hBondAcceptor_lhs+"");
		props.put("rotBondCount_lhs", rotBondCount_lhs+"");
		
		props.put("molWeight_rhs", molWeight_rhs+"");
		props.put("xlogp_rhs", xlogp_rhs+"");
		props.put("mass_rhs", mass_rhs+"");
		props.put("tpsa_rhs", tpsa_rhs+"");
		props.put("complexity_rhs", complexity_rhs+"");
		
		props.put("charge_rhs", charge_rhs+"");
		props.put("hBondDonor_rhs", hBondDonor_rhs+"");
		props.put("hBondAcceptor_rhs", hBondAcceptor_rhs+"");
		props.put("rotBondCount_rhs", rotBondCount_rhs+"");
		
		AdvancedCompoundSearch acs;
		try {
			acs = new AdvancedCompoundSearch(props).search();
			System.out.println("ACTUAL:" + acs.getHs().size());
			for(String s : acs.getHs()) {
				System.err.println("::" + s);
			}
		} catch (OntologyLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		response.sendRedirect("search.jsp");
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

}
