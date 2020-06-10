package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import service.CompoundInformationRetrieval;

/**
 * Servlet implementation class RetrieveCompoundServet
 */
@WebServlet("/RetrieveCompoundServlet")
public class RetrieveCompoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveCompoundServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String searchKey = request.getParameter("compound");
		CompoundInformationRetrieval cir = null;
		try {
			cir = new CompoundInformationRetrieval(searchKey);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///response.getWriter().write(cir.getCompound().getIupac());
		//response.addHeader("smiles", cir.getCompound().getCanSMILES()+"!!");
		//request.setAttribute("smiles", cir.getCompound().getCanSMILES()+"!!");
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		/* construct your json */
		JSONObject obj = new JSONObject();
		
		String molForm = cir.getCompound().getMolForm();
		
		try {
		
			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(molForm);
			
			while(matcher.find()) {
				molForm = molForm.replace(matcher.group(), "<sub>"+matcher.group()+"</sub>");
			}
		
		
			molForm = molForm.replaceAll("(<sub>)+", "<sub>").replaceAll("(</sub>)+", "</sub>");
		
		} catch(Exception e) {
			
		}
		
		obj.put("pubCID", cir.getCompound().getPubCID());     
		obj.put("molForm", molForm);     
		obj.put("canSMILES", cir.getCompound().getCanSMILES());
		obj.put("inchi", cir.getCompound().getInchi());
		obj.put("inchikey", cir.getCompound().getInchikey());
		obj.put("iupac", cir.getCompound().getIupac());
		
		obj.put("molWeight", cir.getCompound().getMolWeight());
		obj.put("xlogp", cir.getCompound().getXlogp());
		obj.put("mass", cir.getCompound().getMass());
		obj.put("tpsa", cir.getCompound().getTpsa());
		obj.put("complexity", cir.getCompound().getComplexity());
		
		obj.put("charge", cir.getCompound().getCharge());
		obj.put("hBondDonor", cir.getCompound().getHBondDonor());
		obj.put("hBondAcceptor", cir.getCompound().getHBondAcceptor());
		obj.put("rotBondCount", cir.getCompound().getRotBondCount());
		
		
		
		String synonym = "";
		
		for(String str : cir.getCompound().getCompoundSynonyms()) {
			if(!str.equals(""))
				synonym += str + "@$@";
		}
		
		obj.put("synonym", synonym);
		
		/* send to the client the JSON string */
		response.getWriter().write(obj.toString());
		
		//response.sendRedirect("ViewCompoundServlet?compound="+searchKey);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
