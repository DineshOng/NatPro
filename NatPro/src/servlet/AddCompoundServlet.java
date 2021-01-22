package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import model.Compound;
import service.OntoMngr;

/**
 * Servlet implementation class AddCompoundServlet
 */
@WebServlet("/AddCompoundServlet")
public class AddCompoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro.owl";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCompoundServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			OntoMngr ontoMngr = new OntoMngr(owlPath);
			
			String compoundName = request.getParameter("compound");
			
			compoundName =  OntoMngr.cleanString(compoundName);
			
			ontoMngr.addIndiv_Compound(compoundName);
			
			compoundName = request.getParameter("compound").trim();
			
			ontoMngr.addDataPropCompound(compoundName);
			//ontoMngr.setCompoundIndiv(compoundName);
			
			System.out.println("req: " + request.getParameter("bioActjson"));
			if(!request.getParameter("bioActjson").equals("[{}]")) {
				String bioActjson = request.getParameter("bioActjson").replaceAll("\\{\\},", "").replaceAll("\\[", "{\"json\":\\[").concat("}");
				System.out.println("mod req: " + bioActjson);
				
				
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(bioActjson);
				System.out.println(json);
				
				JSONArray msg = (JSONArray) json.get("json");
				System.out.println(msg);
				
				
				
				Iterator ib = msg.iterator();
	
				while (ib.hasNext()) {
					JSONObject obj = (JSONObject) ib.next();
					
					String bioAct = (String)obj.get("bioAct");
					System.out.println(bioAct);
					
					String bioActIndiv = bioAct.trim().toLowerCase().replaceAll(" ", "_");
					// create individual for BiologicalActivity
					ontoMngr.addIndiv_BiologicalActivity(bioActIndiv);
					// add data property for BiologicalActivity individual
					ontoMngr.addDataPropBiologicalActivity(bioAct);
					// add object property Compound -> BiologicalActivity
					ontoMngr.addObjectHasBiologicalActivity(compoundName, bioActIndiv);
					
					String cellLine = (String)obj.get("cellLine");
					System.out.println(cellLine);
					
					if (!cellLine.equals("")) {
						String cellLineIndiv = cellLine.trim().toLowerCase().replaceAll(" ", "_");
						// create individual for CellLine
						ontoMngr.addIndiv_CellLine(cellLineIndiv);
						// add data property for CellLine individual
						ontoMngr.addDataPropCellLine(cellLine);
						// add object property BiologicalActivity -> CellLine
						ontoMngr.addObjectAffects(bioActIndiv, cellLineIndiv);
					}
				}
			}
			
			
			
			String pubCID = request.getParameter("pubCID").trim();
			String molForm = request.getParameter("molForm").trim();
			String canSMILES = request.getParameter("canSMILES").trim();
			String inchi = request.getParameter("inchi").trim();
			String inchikey = request.getParameter("inchikey").trim();
			String iupac = request.getParameter("iupac").trim();
			
			String molWeight = request.getParameter("molWeight").trim();
			String xlogp = request.getParameter("xlogp").trim();
			String mass = request.getParameter("mass").trim();
			String tpsa = request.getParameter("tpsa").trim();
			String complexity = request.getParameter("complexity").trim();
			
			String charge = request.getParameter("charge").trim();
			String hBondDonor = request.getParameter("hBondDonor").trim();
			String hBondAcceptor = request.getParameter("hBondAcceptor").trim();
			String rotBondCount = request.getParameter("rotBondCount").trim();
			
			ontoMngr.addDataPropCompound_PubCID(pubCID);
			ontoMngr.addDataPropCompound_MolForm(molForm);
			ontoMngr.addDataPropCompound_CanSMILES(canSMILES);
			ontoMngr.addDataPropCompound_InChI(inchi);
			ontoMngr.addDataPropCompound_InChIkey(inchikey);
			ontoMngr.addDataPropCompound_IUPACName(iupac);
			
			ontoMngr.addDataPropCompound_MolWeight(molWeight);
			ontoMngr.addDataPropCompound_XLogP(xlogp);
			ontoMngr.addDataPropCompound_Mass(mass);
			ontoMngr.addDataPropCompound_TPSA(tpsa);
			ontoMngr.addDataPropCompound_Complexity(complexity);
			
			ontoMngr.addDataPropCompound_Charge(charge);
			ontoMngr.addDataPropCompound_HBondDonorCount(hBondDonor);
			ontoMngr.addDataPropCompound_HBondAcceptorCount(hBondAcceptor);
			ontoMngr.addDataPropCompound_RotatableBondCount(rotBondCount);
			
			String []synonyms = request.getParameter("synonym").trim().split("\n");
			
			HashSet<String> set = new HashSet<>(Arrays.asList(synonyms));
			
			/*for(String syn : set) {
				if(!syn.equals("")) {
					ontoMngr.addDataPropCompound_Synonym(syn);
				}
			}*/
			
			 Iterator i = set.iterator(); // get iterator
			 while(i.hasNext()) {
				 ontoMngr.addDataPropCompound_Synonym(i.next().toString().trim());
			 }
			
			response.sendRedirect("ViewCompoundServlet?compound=" + compoundName);
			
		} catch (OWLOntologyCreationException | OWLOntologyStorageException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
