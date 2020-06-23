package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.Compound;
import model.MedicinalPlant;
import model.Species;
import model.SpeciesPart;
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
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println(">>>>>" + request.getParameter("location").trim());
		String location = request.getParameter("location").trim().equals("") ? null : request.getParameter("location").trim();
		String family = request.getParameter("family").trim().equals("") ? null : request.getParameter("family").trim();
		String genus = request.getParameter("genus").trim().equals("") ? null : request.getParameter("genus").trim();
		String plantPart = request.getParameter("plantPart").trim().equals("") ? null : request.getParameter("plantPart").trim();

		String compoundName = request.getParameter("compound").trim().equals("") ? null
				: request.getParameter("compound").trim();

		String molForm = request.getParameter("molForm").trim().equals("") ? null
				: request.getParameter("molForm").trim();

		Double molWeight_lhs = request.getParameter("molWeight_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("molWeight_lhs").trim());
		Double xlogp_lhs = request.getParameter("xlogp_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("xlogp_lhs").trim());
		Double mass_lhs = request.getParameter("mass_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("mass_lhs").trim());
		Double tpsa_lhs = request.getParameter("tpsa_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("tpsa_lhs").trim());
		Double complexity_lhs = request.getParameter("complexity_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("complexity_lhs").trim());

		Double charge_lhs = request.getParameter("charge_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("charge_lhs").trim());
		Double hBondDonor_lhs = request.getParameter("hBondDonor_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("hBondDonor_lhs").trim());
		Double hBondAcceptor_lhs = request.getParameter("hBondAcceptor_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("hBondAcceptor_lhs").trim());
		Double rotBondCount_lhs = request.getParameter("rotBondCount_lhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("rotBondCount_lhs").trim());

		Double molWeight_rhs = request.getParameter("molWeight_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("molWeight_rhs").trim());
		Double xlogp_rhs = request.getParameter("xlogp_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("xlogp_rhs").trim());
		Double mass_rhs = request.getParameter("mass_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("mass_rhs").trim());
		Double tpsa_rhs = request.getParameter("tpsa_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("tpsa_rhs").trim());
		Double complexity_rhs = request.getParameter("complexity_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("complexity_rhs").trim());

		Double charge_rhs = request.getParameter("charge_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("charge_rhs").trim());
		Double hBondDonor_rhs = request.getParameter("hBondDonor_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("hBondDonor_rhs").trim());
		Double hBondAcceptor_rhs = request.getParameter("hBondAcceptor_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("hBondAcceptor_rhs").trim());
		Double rotBondCount_rhs = request.getParameter("rotBondCount_rhs").trim().equals("") ? null
				: Double.parseDouble(request.getParameter("rotBondCount_rhs").trim());

		OntoQuery q;
		HashSet<Compound> resultCompounds = new HashSet<Compound>();
		HashSet<String> hs = new HashSet<String>();
		Boolean bool;

		try {
			q = new OntoQuery();
			List<MedicinalPlant> mps = q.searchMedicinalPlant("");
			for (MedicinalPlant mp : mps) {
				bool = true;
				List<String> locs = mp.getLocations();
				
				Boolean temp = false;
				
				if((locs.size() == 0 || locs == null) && location != null) {
					continue;
				} 
				if((locs.size() != 0 || locs != null) && location != null) {
					for(String loc : locs) {
						if(loc.toLowerCase().contains(location.toLowerCase())) {
							temp = true;
							//break; //maybe remove if rip
						}
					}
				}
				
				if(!temp)
					continue;
				
				System.out.println(mp.getMedicinalPlant());
				System.out.println("hey");
				
				List<Species> sps = mp.getSpecies();
				for (Species sp : sps) {

					if (sp.getFamily() != null && family != null)
						if (!sp.getFamily().toLowerCase().contains(family.toLowerCase()))
							continue;
					if (sp.getFamily() == null && family != null)
						continue;

					if (sp.getGenus() != null && genus != null)
						if (!sp.getGenus().toLowerCase().contains(genus.toLowerCase()))
							continue;
					if (sp.getGenus() == null && genus != null)
						continue;

					List<SpeciesPart> spts = sp.getSpeciesParts();
					if(spts == null)
						continue;
					
					System.out.println("hey there");
					
					for (SpeciesPart spt : spts) {
						//List<Compound> compounds = compoundName != null ? q.searchCompound(compoundName)
						//		: spt.getCompounds(); //fix, sumting wong here search loc:fiji compound:a
						
						List<Compound> compounds = spt.getCompounds();

						if (spt.getPlantPart() != null && plantPart != null)
							if (!spt.getPlantPart().toLowerCase().contains(plantPart.toLowerCase()))
								continue;
						if (spt.getPlantPart() == null && plantPart != null)
							continue;
						
						System.out.println("yo");

						// List<Compound> compounds = q.searchCompound(compoundName);
						if (compounds != null) {
							for (Compound cc : compounds) {
								
								if(resultCompounds.contains(cc))
									continue;
								if (compoundName != null)
									if(!cc.getCompoundName().toLowerCase().contains(compoundName.toLowerCase()))
										continue;
								
								Compound compound = q.getCompound(cc.getCompoundName());

								Double value;

								if (compound.getMolForm() != null && molForm != null) {
									if (!(compound.getMolForm().toLowerCase().contains(molForm.toLowerCase())))
										continue;
								}
								if (compound.getMolForm() == null && molForm != null) {
									continue;
								}

								if (compound.getMolWeight() != null) {
									value = Double.parseDouble(compound.getMolWeight());
									if (molWeight_lhs != null) {
										if (!(molWeight_lhs <= value))
											continue;
									}
									if (molWeight_rhs != null) {
										if (!(molWeight_rhs >= value))
											continue;
									}
								}
								if (compound.getMolWeight() == null
										&& (molWeight_lhs != null || molWeight_rhs != null)) {
									continue;
								}

								if (compound.getXlogp() != null) {
									value = Double.parseDouble(compound.getXlogp());
									if (xlogp_lhs != null) {
										if (!(xlogp_lhs <= value))
											continue;
									}
									if (xlogp_rhs != null) {
										if (!(xlogp_rhs >= value))
											continue;
									}
								}
								if (compound.getXlogp() == null && (xlogp_lhs != null || xlogp_rhs != null)) {
									continue;
								}

								if (compound.getMass() != null) {
									value = Double.parseDouble(compound.getMass());
									if (mass_lhs != null) {
										if (!(mass_lhs <= value))
											continue;
									}
									if (mass_rhs != null) {
										if (!(mass_rhs >= value))
											continue;
									}
								}
								if (compound.getMass() == null && (mass_lhs != null || mass_rhs != null)) {
									continue;
								}

								if (compound.getTpsa() != null) {
									value = Double.parseDouble(compound.getTpsa());
									if (tpsa_lhs != null) {
										if (!(tpsa_lhs <= value))
											continue;
									}
									if (tpsa_rhs != null) {
										if (!(tpsa_rhs >= value))
											continue;
									}
								}
								if (compound.getTpsa() == null && (tpsa_lhs != null || tpsa_rhs != null)) {
									continue;
								}

								if (compound.getComplexity() != null) {
									value = Double.parseDouble(compound.getComplexity());
									if (complexity_lhs != null) {
										if (!(complexity_lhs <= value))
											continue;
									}
									if (complexity_rhs != null) {
										if (!(complexity_rhs >= value))
											continue;
									}
								}
								if (compound.getComplexity() == null
										&& (complexity_lhs != null || complexity_rhs != null)) {
									continue;
								}

								if (compound.getCharge() != null) {
									value = Double.parseDouble(compound.getCharge());
									if (charge_lhs != null) {
										if (!(charge_lhs <= value))
											continue;
									}
									if (charge_rhs != null) {
										if (!(charge_rhs >= value))
											continue;
									}
								}
								if (compound.getCharge() == null && (charge_lhs != null || charge_rhs != null)) {
									continue;
								}

								if (compound.getHBondDonor() != null) {
									value = Double.parseDouble(compound.getHBondDonor());
									if (hBondDonor_lhs != null) {
										if (!(hBondDonor_lhs <= value))
											continue;
									}
									if (hBondDonor_rhs != null) {
										if (!(hBondDonor_rhs >= value))
											continue;
									}
								}
								if (compound.getHBondDonor() == null
										&& (hBondDonor_lhs != null || hBondDonor_rhs != null)) {
									continue;
								}

								if (compound.getHBondAcceptor() != null) {
									value = Double.parseDouble(compound.getHBondAcceptor());
									if (hBondAcceptor_lhs != null) {
										if (!(hBondAcceptor_lhs <= value))
											continue;
									}
									if (hBondAcceptor_rhs != null) {
										if (!(hBondAcceptor_rhs >= value))
											continue;
									}
								}
								if (compound.getHBondAcceptor() == null
										&& (hBondAcceptor_lhs != null || hBondAcceptor_rhs != null)) {
									continue;
								}

								if (compound.getRotBondCount() != null) {
									value = Double.parseDouble(compound.getRotBondCount());
									if (rotBondCount_lhs != null) {
										if (!(rotBondCount_lhs <= value))
											continue;
									}
									if (rotBondCount_rhs != null) {
										if (!(rotBondCount_rhs >= value))
											continue;
									}
								}
								if (compound.getRotBondCount() == null
										&& (rotBondCount_lhs != null || rotBondCount_rhs != null)) {
									continue;
								}

								if (bool) {
									hs.add(compound.getCompoundName());
									resultCompounds.add(compound);
									//System.out.println(compound.getCompoundName());
								}
							}
						}
					}
				}
			}

		} catch (OntologyLoadException e) {
		}
		
		System.out.println(hs.size());
		for(String s : hs) {
			System.out.println(s);
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
