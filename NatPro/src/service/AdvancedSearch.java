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

public class AdvancedSearch {
	private static final String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro.owl";
	protected HashMap<String, String> properties;
	protected HashSet<String> resultPlant;
	protected HashSet<String> resultCompound;
	protected List<Compound> compounds;
	
	protected OntoQuery q;
	
	protected String location;
	protected String medPlantName;
	protected String plantPart;
	
	protected String family;
	protected String genus;
	protected String species;
	
	protected String illness;
	protected String bioAct;
	protected String cellLine;
	
	protected String compoundName;
	protected String molForm;
	protected String compoundClass;
	
	protected Double molWeight_lhs;
	protected Double xlogp_lhs;
	protected Double mass_lhs;
	protected Double tpsa_lhs;
	protected Double complexity_lhs;
	
	protected Double charge_lhs;
	protected Double hBondDonor_lhs;
	protected Double hBondAcceptor_lhs;
	protected Double rotBondCount_lhs;
	
	protected Double molWeight_rhs;
	protected Double xlogp_rhs;
	protected Double mass_rhs;
	protected Double tpsa_rhs;
	protected Double complexity_rhs;
	
	protected Double charge_rhs;
	protected Double hBondDonor_rhs;
	protected Double hBondAcceptor_rhs;
	protected Double rotBondCount_rhs;
	
	public AdvancedSearch(HashMap<String, String> props) throws OntologyLoadException {
		this.properties = props;
		resultPlant = new HashSet<String>();
		resultCompound = new HashSet<String>();
		compounds = null;
		
		q = new OntoQuery(owlPath);
		
		location = properties.get("location");
		medPlantName = properties.get("medPlantName");
		plantPart = properties.get("plantPart");
		
		family = properties.get("family");
		genus = properties.get("genus");
		species = properties.get("species");
		
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
	
	public void startSearch() {

		List<MedicinalPlant> mps = null;
		
		if(medPlantName == null)
			mps = q.searchMedicinalPlant("");
		else if(medPlantName != null)
			mps = q.searchMedicinalPlant(medPlantName);
		
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
			

			if(illness != null && (preps == null)) {
				continue;
			} 
			
			if(illness != null && (preps.size() != 0 || preps != null)) {
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
			
			if(family == null && genus == null && species == null && plantPart == null && bioAct == null && cellLine == null && compoundName == null) {
				resultPlant.add(mp.getMedicinalPlant());
			}
			
			//System.err.println(mp.getMedicinalPlant());
			//System.out.println("hey");
			
			List<Species> sps = mp.getSpecies();
			for (Species sp : sps) {
				System.err.println("##### " + sp.getSpecie());
				
				if (sp.getSpecie() != null && species != null)
					if (!sp.getSpecie().toLowerCase().contains(species.toLowerCase()))
						continue;
				if (sp.getSpecie() == null && species != null)
					continue;
				
				if(family == null && genus == null && plantPart == null && bioAct == null && cellLine == null && compoundName == null) {
					resultPlant.add(mp.getMedicinalPlant());
				}
				
				System.err.println("hey there!!!");
				
				if (sp.getSpecie() != null && genus != null)
					if (!sp.getSpecie().toLowerCase().contains(genus.toLowerCase()))
						continue;
				if (sp.getSpecie() == null && genus != null)
					continue;
				
				if(family == null && species == null && plantPart == null && bioAct == null && cellLine == null && compoundName == null) {
					resultPlant.add(mp.getMedicinalPlant());
				}


				if (sp.getFamily() != null && family != null)
					if (!sp.getFamily().toLowerCase().contains(family.toLowerCase()))
						continue;
				if (sp.getFamily() == null && family != null)
					continue;
				
				if(genus == null && plantPart == null && bioAct == null && cellLine == null && compoundName == null) {
					resultPlant.add(mp.getMedicinalPlant());
				}

				if (sp.getGenus() != null && genus != null)
					if (!sp.getGenus().toLowerCase().contains(genus.toLowerCase()))
						continue;
				if (sp.getGenus() == null && genus != null)
					continue;
				
				if(plantPart == null && bioAct == null && cellLine == null && compoundName == null) {
					resultPlant.add(mp.getMedicinalPlant());
				}
				
				List<SpeciesPart> spts = sp.getSpeciesParts();
				if(spts == null)
					continue;
				
				System.err.println("hey there");
				
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
					
					compoundSearch(spt.getCompounds(), false, mp.getMedicinalPlant());
				}
			}
		}
	}
	
	public void compoundSearch(List<Compound> compounds, Boolean onlyCompound, String plant) {
		Boolean temp;
		if (compounds != null) {
			for (Compound cc : compounds) {
				
				if(resultCompound.contains(cc.getCompoundName()))
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
				
				System.out.println(">>> " + compound.getBioActs() + " " + compound.getCompoundName());
				
				if((compound.getBioActs().size() == 0 || compound.getBioActs() == null) && bioAct != null) {
					continue;
				} 
				if((compound.getBioActs().size() != 0 || compound.getBioActs() != null) && bioAct != null) {
					temp = false;
					
					for(BiologicalActivity bioact : compound.getBioActs()) {
						if(bioact.getBiologicalActivity().toLowerCase().contains(bioAct.toLowerCase())) {
							temp = true;
						}
					}
					if(!temp)
						continue;
					
				}
				
				if((compound.getBioActs().size() != 0 || compound.getBioActs() != null) && cellLine != null) {
					temp = false;
					for(BiologicalActivity bioact : compound.getBioActs()) {
						if(bioact.getCellLine() == null && cellLine != null)
							continue;
						if(bioact.getCellLine() != null && cellLine != null) {
							if(bioact.getCellLine().getCellLine().toLowerCase().contains(cellLine.toLowerCase())) {
								temp = true;
							}
						}
					}
					if(!temp)
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

				resultPlant.add(plant);
				resultCompound.add(compound.getCompoundName());
			}
		}
	}
	
	

	public HashSet<String> getResultPlant() {
		return resultPlant;
	}

	public void setResultPlant(HashSet<String> resultPlant) {
		this.resultPlant = resultPlant;
	}

	public HashSet<String> getResultCompound() {
		return resultCompound;
	}

	public void setResultCompound(HashSet<String> resultCompound) {
		this.resultCompound = resultCompound;
	}

	public List<Compound> getCompounds() {
		return compounds;
	}

	public void setCompounds(List<Compound> compounds) {
		this.compounds = compounds;
	}
}
