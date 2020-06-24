package service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.BiologicalActivity;
import model.Compound;
import model.MedicinalPlant;
import model.Preparation;
import model.Species;
import model.SpeciesPart;

public class AdvancedCompoundSearch {
	
	private HashMap<String, String> properties;
	HashSet<Compound> resultCompounds;
	HashSet<String> hs;
	List<Compound> compounds;
	
	private OntoQuery q;
	
	private String location;
	private String family;
	private String genus;
	private String species;
	private String plantPart;
	
	private String illness;
	private String bioAct;
	private String cellLine;
	
	private String compoundName;
	private String molForm;
	private String compoundClass;
	
	private Double molWeight_lhs;
	private Double xlogp_lhs;
	private Double mass_lhs;
	private Double tpsa_lhs;
	private Double complexity_lhs;
	
	private Double charge_lhs;
	private Double hBondDonor_lhs;
	private Double hBondAcceptor_lhs;
	private Double rotBondCount_lhs;
	
	private Double molWeight_rhs;
	private Double xlogp_rhs;
	private Double mass_rhs;
	private Double tpsa_rhs;
	private Double complexity_rhs;
	
	private Double charge_rhs;
	private Double hBondDonor_rhs;
	private Double hBondAcceptor_rhs;
	private Double rotBondCount_rhs;
	
	public AdvancedCompoundSearch(HashMap<String, String> properties) throws OntologyLoadException {
		this.properties = new HashMap<>();
		resultCompounds = new HashSet<Compound>();
		hs = new HashSet<String>();
		compounds = null;
		
		q = new OntoQuery();
		
		location = properties.get("location");
		family = properties.get("family");
		genus = properties.get("genus");
		species = properties.get("species");
		plantPart = properties.get("plantPart");

		illness = properties.get("illness");
		bioAct = properties.get("bioAct");
		cellLine = properties.get("cellLine");

		compoundName = properties.get("compoundName");
		molForm = properties.get("molForm");
		compoundClass = properties.get("compoundClass");
		
		molWeight_lhs = properties.get("molWeight_lhs").equals("null") ? null : Double.parseDouble(properties.get("molWeight_lhs"));
		xlogp_lhs = properties.get("xlogp_lhs").equals("null") ? null : Double.parseDouble(properties.get("xlogp_lhs"));
		mass_lhs = properties.get("mass_lhs").equals("null") ? null : Double.parseDouble(properties.get("mass_lhs"));
		tpsa_lhs = properties.get("tpsa_lhs").equals("null") ? null : Double.parseDouble(properties.get("tpsa_lhs"));
		complexity_lhs = properties.get("complexity_lhs").equals("null") ? null : Double.parseDouble(properties.get("complexity_lhs"));
		
		charge_lhs = properties.get("charge_lhs").equals("null") ? null : Double.parseDouble(properties.get("charge_lhs"));
		hBondDonor_lhs = properties.get("hBondDonor_lhs").equals("null") ? null : Double.parseDouble(properties.get("hBondDonor_lhs"));
		hBondAcceptor_lhs = properties.get("hBondAcceptor_lhs").equals("null") ? null : Double.parseDouble(properties.get("hBondAcceptor_lhs"));
		rotBondCount_lhs = properties.get("rotBondCount_lhs").equals("null") ? null : Double.parseDouble(properties.get("rotBondCount_lhs"));
		
		molWeight_rhs = properties.get("molWeight_rhs").equals("null") ? null : Double.parseDouble(properties.get("molWeight_rhs"));
		xlogp_rhs = properties.get("xlogp_rhs").equals("null") ? null : Double.parseDouble(properties.get("xlogp_rhs"));
		mass_rhs = properties.get("mass_rhs").equals("null") ? null : Double.parseDouble(properties.get("mass_rhs"));
		tpsa_rhs = properties.get("tpsa_rhs").equals("null") ? null : Double.parseDouble(properties.get("tpsa_rhs"));
		complexity_rhs = properties.get("complexity_rhs").equals("null") ? null : Double.parseDouble(properties.get("complexity_rhs"));
		
		charge_rhs = properties.get("charge_rhs").equals("null") ? null : Double.parseDouble(properties.get("charge_rhs"));
		hBondDonor_rhs = properties.get("hBondDonor_rhs").equals("null") ? null : Double.parseDouble(properties.get("hBondDonor_rhs"));
		hBondAcceptor_rhs = properties.get("hBondAcceptor_rhs").equals("null") ? null : Double.parseDouble(properties.get("hBondAcceptor_rhs"));
		rotBondCount_rhs = properties.get("rotBondCount_rhs").equals("null") ? null : Double.parseDouble(properties.get("rotBondCount_rhs"));
	}
	
	public AdvancedCompoundSearch search() {
		if(location == null && family == null && genus == null && species == null && plantPart == null && illness == null && bioAct == null && cellLine == null) {
			if(compoundName != null) {
				//System.out.println("here");
				//System.println(q.searchCompound(compoundName).get(0).getCompoundName());
				compoundSearch(q.searchCompound(compoundName), true);
			} else {
				compoundSearch(q.searchCompound(""), true);
			}
			return this;
		}
		
		List<MedicinalPlant> mps = q.searchMedicinalPlant("");
		for (MedicinalPlant mp : mps) {
			//Boolean bool = true;
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
				if(!temp)
					continue;
			}
			
			//System.err.println("hey i just met you");
			
			temp = false;
			
			List<Preparation> preps = mp.getPreparations();
			
			if((preps.size() == 0 || preps == null) && illness != null) {
				continue;
			} 
			
			if((preps.size() != 0 || preps != null) && illness != null) {
				for(Preparation prep : preps) {
					List<String> ills = prep.getIllness();
					//System.err.println("and this is crazy");
					
					if((ills.size() != 0 || ills != null) && illness != null) {
						for(String ill : ills) {
							if(ill.toLowerCase().contains(illness.toLowerCase())) {
								temp = true;
								//break; //maybe remove if rip
							}
						}
					}
				}
				if(!temp)
					continue;
			}
			
			
			//System.err.println(mp.getMedicinalPlant());
			//System.out.println("hey");
			
			List<Species> sps = mp.getSpecies();
			for (Species sp : sps) {
				
				if (sp.getSpecie() != null && species != null)
					if (!sp.getSpecie().toLowerCase().contains(species.toLowerCase()))
						continue;
				if (sp.getSpecie() == null && species != null)
					continue;

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
				
				//System.out.println("hey there");
				
				for (SpeciesPart spt : spts) {
					//List<Compound> compounds = compoundName != null ? q.searchCompound(compoundName)
					//		: spt.getCompounds(); //fix, sumting wong here search loc:fiji compound:a
					
					//List<Compound> compounds = spt.getCompounds();

					if (spt.getPlantPart() != null && plantPart != null)
						if (!spt.getPlantPart().toLowerCase().contains(plantPart.toLowerCase()))
							continue;
					if (spt.getPlantPart() == null && plantPart != null)
						continue;
					
					//System.out.println("yo");
					
					compoundSearch(spt.getCompounds(), false);
				}
			}
		}
		
		return this;
	}
	
	public void compoundSearch(List<Compound> compounds, Boolean onlyCompound) {
		Boolean temp;
		if (compounds != null) {
			for (Compound cc : compounds) {
				
				if(resultCompounds.contains(cc))
					continue;
				if (compoundName != null && !onlyCompound) {
					if(!cc.getCompoundName().toLowerCase().contains(compoundName.toLowerCase()))
						continue;
				} 
				
				if ((cc.getCompoundSynonyms().size() == 0 || cc.getCompoundSynonyms() == null) && compoundName != null){
					temp = false;
					for(String syno : cc.getCompoundSynonyms()) {
						if(syno.toLowerCase().contains(compoundName.toLowerCase()))
							temp = true;
							
					}
					if(cc.getCompoundName().toLowerCase().contains(compoundName.toLowerCase()))
						temp = true;
					if(!temp)
						continue;
				}
				
				Compound compound = q.getCompound(cc.getCompoundName());
				
				if((compound.getBioActs().size() == 0 || compound.getBioActs() == null) && bioAct != null) {
					continue;
				} 
				if((compound.getBioActs().size() != 0 || compound.getBioActs() != null) && bioAct != null) {
					temp = false;
					
					Boolean cl = false;
					for(BiologicalActivity bioact : compound.getBioActs()) {
						if(bioact.getBiologicalActivity().toLowerCase().contains(bioAct.toLowerCase())) {
							temp = true;
						}
						
						if(bioact.getCellLine() == null && cellLine != null)
							continue;
						if(bioact.getCellLine() != null && cellLine != null) {
							if(bioact.getCellLine().getCellLine().toLowerCase().contains(cellLine.toLowerCase())) {
								cl = true;
							}
						}
					}
					if(!temp && !cl)
						continue;
					
				}

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

				//if (bool) {
					hs.add(compound.getCompoundName());
					resultCompounds.add(compound);
					//System.out.println(compound.getCompoundName());
				//}
			}
		}
	}

	public HashSet<Compound> getResultCompounds() {
		return resultCompounds;
	}

	public void setResultCompounds(HashSet<Compound> resultCompounds) {
		this.resultCompounds = resultCompounds;
	}

	public HashSet<String> getHs() {
		return hs;
	}

	public void setHs(HashSet<String> hs) {
		this.hs = hs;
	}

	public List<Compound> getCompounds() {
		return compounds;
	}

	public void setCompounds(List<Compound> compounds) {
		this.compounds = compounds;
	}
}
