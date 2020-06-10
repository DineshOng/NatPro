package servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			OntoMngr ontoMngr = new OntoMngr();
			
			String compoundName = request.getParameter("compound");
			
			compoundName =  Compound.toOWLString(compoundName);
			
			ontoMngr.addIndiv_Compound(compoundName);
			
			compoundName = request.getParameter("compound").trim();
			
			ontoMngr.addDataPropCompound(compoundName);
			//ontoMngr.setCompoundIndiv(compoundName);
			
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
			
			HashSet<String> set = new HashSet<String>();
			
			int synonymCtr = Integer.parseInt(request.getParameter("synonymCtr").trim());
			
			String syn;
			
			for(int i=0; i<synonymCtr; i++) {
				syn = request.getParameter("synonym"+i).trim();
				if(!syn.equals("")) {
					if(!set.contains(syn)) {
						ontoMngr.addDataPropCompound_Synonym(syn);
						set.add(syn);
					}
				}
			}
			
			response.sendRedirect("ViewCompoundServlet?compound=" + compoundName);
			
		} catch (OWLOntologyCreationException | OWLOntologyStorageException e) {
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
