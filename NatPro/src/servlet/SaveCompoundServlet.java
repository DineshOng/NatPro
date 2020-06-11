package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		OntoMngr ontoMngr;
		OntoQuery ontoQry;
		
		try {
			ontoMngr = new OntoMngr();
			ontoQry = new OntoQuery();
			
			//indiv 1.2-dioleylglycerol
			
			String oldCompoundName = request.getParameter("oldCompound"); // 1,2-dioleyl glycerol
			String newCompoundName = request.getParameter("newCompound"); // 1,3-dioleyl glycerol
			
			Compound compound = ontoQry.getCompound(oldCompoundName);
			
			String newVal = request.getParameter("pubCID").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_PubCID, compound.getPubCID()+"", newVal);
			
			newVal = request.getParameter("molForm").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_MolForm, compound.getMolForm(), newVal);
			
			newVal = request.getParameter("canSMILES").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_CanSMILES, compound.getCanSMILES(), newVal);
			
			newVal = request.getParameter("inchi").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_InChI, compound.getInchi(), newVal);
			
			newVal = request.getParameter("inchikey").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_InChIkey, compound.getInchikey(), newVal);
			
			newVal = request.getParameter("iupac").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_IUPACName, compound.getIupac(), newVal);
			
			
			newVal = request.getParameter("molWeight").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_MolWeight, compound.getMolWeight()+"", newVal);
			
			newVal = request.getParameter("xlogp").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_XLogP, compound.getXlogp()+"", newVal);
			
			newVal = request.getParameter("mass").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_Mass, compound.getMass()+"", newVal);
			
			newVal = request.getParameter("tpsa").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_TPSA, compound.getTpsa()+"", newVal);
			
			newVal = request.getParameter("complexity").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_Complexity, compound.getComplexity()+"", newVal);
			
			
			newVal = request.getParameter("charge").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_Charge, compound.getCharge()+"", newVal);
			
			newVal = request.getParameter("hBondDonor").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_HBondDonor, compound.getHBondDonor()+"", newVal);

			newVal = request.getParameter("hBondAcceptor").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_HBondAcceptor, compound.getHBondAcceptor()+"", newVal);
			
			newVal = request.getParameter("rotBondCount").trim();
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), compound.getCompoundOWL(), Compound.DP_RotatableBond, compound.getRotBondCount()+"", newVal);
			
			// ToDo
			// delete all dataproperty compound synonym
			
			String []synonyms = request.getParameter("synonym").split("\n");
			
			HashSet<String> set = new HashSet<>(Arrays.asList(synonyms));
			
			for(String syn : set) {
				if(!syn.equals("")) {
					ontoMngr.addDataPropCompound_Synonym(syn);
				}
			}
			
			
			if(!oldCompoundName.equals(newCompoundName)) {
				ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), Compound.toOWLIndivString(oldCompoundName), Compound.DP_Compound, compound.getCompoundName(), newCompoundName);
				ontoMngr.changeNameIndividual(oldCompoundName, newCompoundName);
			}
			
			response.sendRedirect("ViewCompoundServlet?compound=" + newCompoundName);
			
		} catch (OWLOntologyCreationException | OWLOntologyStorageException | OntologyLoadException e) {
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
