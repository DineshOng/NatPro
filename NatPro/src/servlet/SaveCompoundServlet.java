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

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.BiologicalActivity;
import model.Compound;
import service.OntoMngr;
import service.OntoQuery;

/**
 * Servlet implementation class SaveCompoundServlet
 */
@WebServlet("/SaveCompoundServlet")
public class SaveCompoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveCompoundServlet() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			OntoQuery ontoQry = new OntoQuery();
			String oldCompoundName = request.getParameter("oldCompound");
			String newCompoundName = request.getParameter("compound");

			Compound compound = ontoQry.getCompound(oldCompoundName);

			if (compound != null) {
				
				OntoMngr ontoMngr = new OntoMngr();
				
				
				ontoMngr.setCompoundIndiv(OntoMngr.cleanString(oldCompoundName));

				String newVal = request.getParameter("pubCID").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_PubCID, compound.getPubCID());
				ontoMngr.addDataPropCompound_PubCID(newVal);

				newVal = request.getParameter("molForm").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_MolForm, compound.getMolForm());
				ontoMngr.addDataPropCompound_MolForm(newVal);

				newVal = request.getParameter("canSMILES").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_CanSMILES,
						compound.getCanSMILES());
				ontoMngr.addDataPropCompound_CanSMILES(newVal);

				newVal = request.getParameter("inchi").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_InChI, compound.getInchi());
				ontoMngr.addDataPropCompound_InChI(newVal);

				newVal = request.getParameter("inchikey").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_InChIkey,
						compound.getInchikey());
				ontoMngr.addDataPropCompound_InChIkey(newVal);

				newVal = request.getParameter("iupac").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_IUPACName, compound.getIupac());
				ontoMngr.addDataPropCompound_IUPACName(newVal);

				newVal = request.getParameter("molWeight").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_MolWeight,
						compound.getMolWeight());
				ontoMngr.addDataPropCompound_MolWeight(newVal);

				newVal = request.getParameter("xlogp").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_XLogP, compound.getXlogp());
				ontoMngr.addDataPropCompound_XLogP(newVal);

				newVal = request.getParameter("mass").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_Mass, compound.getMass());
				ontoMngr.addDataPropCompound_Mass(newVal);

				newVal = request.getParameter("tpsa").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_TPSA, compound.getTpsa());
				ontoMngr.addDataPropCompound_TPSA(newVal);

				newVal = request.getParameter("complexity").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_Complexity,
						compound.getComplexity());
				ontoMngr.addDataPropCompound_Complexity(newVal);

				newVal = request.getParameter("charge").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_Charge, compound.getCharge());
				ontoMngr.addDataPropCompound_Charge(newVal);

				newVal = request.getParameter("hBondDonor").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_HBondDonor,
						compound.getHBondDonor());
				ontoMngr.addDataPropCompound_HBondDonorCount(newVal);

				newVal = request.getParameter("hBondAcceptor").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_HBondAcceptor,
						compound.getHBondAcceptor());
				ontoMngr.addDataPropCompound_HBondAcceptorCount(newVal);

				newVal = request.getParameter("rotBondCount").trim();
				ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_RotatableBond,
						compound.getRotBondCount());
				ontoMngr.addDataPropCompound_RotatableBondCount(newVal);

				String[] synonyms = request.getParameter("synonym").trim().split("\n");

				HashSet<String> set = new HashSet<>(Arrays.asList(synonyms));

				// delete all dataproperty compound synonym
				if (compound.getCompoundSynonyms() != set) {
					for (String syn : compound.getCompoundSynonyms()) {
						ontoMngr.removeDataPropertyValue(compound.getCompoundOWL(), OntoMngr.DP_Synonym, syn);
					}
				}

				// add all new dataproperty compound synonym
				/*
				 * for(String syn : set) { if(!syn.equals("")) {
				 * ontoMngr.addDataPropCompound_Synonym(syn); } }
				 */

				Iterator it = set.iterator(); // get iterator
				while (it.hasNext()) {
					ontoMngr.addDataPropCompound_Synonym(it.next().toString().trim());
				}
				
				for(BiologicalActivity ba : compound.getBioActs()) {
					ontoMngr.removeObjectPropertyValue(compound.getCompoundOWL(), OntoMngr.OP_hasBiologicalActivity, OntoMngr.cleanString(ba.getBiologicalActivity()));
				}
				
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
						ontoMngr.addObjectHasBiologicalActivity(compound.getCompoundOWL(), bioActIndiv);
						
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

				// change indiv and dataprop name
				if (!oldCompoundName.equals(newCompoundName)) {
					ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), OntoMngr.cleanString(oldCompoundName),
							OntoMngr.DP_Compound, compound.getCompoundName(), newCompoundName);
					ontoMngr.changeNameIndividual(oldCompoundName, newCompoundName);
				}

				response.sendRedirect("ViewCompoundServlet?compound=" + newCompoundName);
			}

		} catch (OWLOntologyCreationException | OWLOntologyStorageException | OntologyLoadException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
