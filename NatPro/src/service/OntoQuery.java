package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.Compound;
import model.MedicinalPlant;
import model.Species;
import model.SpeciesPart;

public class OntoQuery {
	String uri;
	OWLModel owlModel;

	public OntoQuery() throws OntologyLoadException {
		String owlPath = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Ontology\\OntoNatPro.owl";
		owlPath = owlPath.replace("\\", "/");
		this.owlModel = ProtegeOWL.createJenaOWLModelFromURI("file:///" + owlPath);
	}

	public List<String> getAllMedPlantNames() throws SQWRLException {
		List<String> values = new ArrayList<String>();

		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");

//		System.out.println("Entered Get All PlantNames");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						values.add(individual.getPropertyValue(datatypeProperty_MedicinalPlant).toString());
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return values;
	}

	public List<String> getSynonyms(String MedicinalPlant) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");

		RDFProperty hasScientificName = owlModel.getRDFProperty("hasScientificName");

//		System.out.println("Entered Get Synonyms");
		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
//			System.out.println(classes.toString());
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the medicinal plant
						if (MedicinalPlant.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_MedicinalPlant).toString())) {
							Collection synonyms = individual.getPropertyValues(datatypeProperty_Synonym);
							// This is for entities from OntoPHerb, synonyms as datatype prop
							for (Iterator kt = synonyms.iterator(); kt.hasNext();) {
								if (!kt.next().toString().isEmpty()) {
									values.add(kt.next().toString());
								}
							}

							// This is for synonyms as objects
							Collection synonyms2 = individual.getPropertyValues(hasScientificName);
							for (Iterator lt = synonyms2.iterator(); lt.hasNext();) {
								OWLIndividual sciNameIndiv = (OWLIndividual) lt.next();
//								System.out.println(sciNameIndiv.getPropertyValue(datatypeProperty_Synonym).toString());
								if (!sciNameIndiv.getPropertyValue(datatypeProperty_Synonym).toString().isEmpty()) {
									values.add(sciNameIndiv.getPropertyValue(datatypeProperty_Synonym).toString());
								}
							}

						}

					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getLocations(String MedicinalPlant) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Location = owlModel.getRDFProperty("datatypeProperty_Location");
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");
		RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn");

//		System.out.println("Entered Get Synonyms");
		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
//			System.out.println(classes.toString());
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the medicinal plant
						if (MedicinalPlant.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_MedicinalPlant).toString())) {
							Collection locations = individual.getPropertyValues(isLocatedIn);
							for (Iterator kt = locations.iterator(); kt.hasNext();) {
								OWLIndividual locIndiv = (OWLIndividual) kt.next();
//								System.out.println(locIndiv.getPropertyValue(datatypeProperty_Location).toString());
								values.add(locIndiv.getPropertyValue(datatypeProperty_Location).toString());
							}

						}

					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getCompounds(String MedicinalPlant) throws SQWRLException {
		List<String> compounds = new ArrayList<String>();
		LinkedHashSet<String> hashSet = new LinkedHashSet<String>(); // This set will only be used to check for
																		// duplicate compounds
		List<MedicinalPlant> medicinalPlantList = searchMedicinalPlant(MedicinalPlant);

		try {
//			System.out.println(medicinalPlantList.get(0).getMedicinalPlant());
			for (int i = 0; i < medicinalPlantList.size(); i++) {
				List<Species> speciesList = medicinalPlantList.get(i).getSpecies();
				for (int j = 0; j < speciesList.size(); j++) {
					List<SpeciesPart> speciesPartList = speciesList.get(j).getSpeciesParts();
					for (int k = 0; k < speciesPartList.size(); k++) {
						List<Compound> compoundList = speciesPartList.get(k).getCompounds();
						for (int l = 0; l < compoundList.size(); l++) {
							List<String> compoundSynList = compoundList.get(l).getCompoundSynonyms();
							for (int m = 0; m < compoundSynList.size(); m++) {
								// this is to ensure that no duplicate compounds will be added to the list
								if (hashSet.add(compoundSynList.get(m).toString()))
									compounds.add(compoundSynList.get(m).toString());
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}

		return compounds;

	}
	
	public Compound getCompound(String Compound) {
		
		Compound compound = null;
		
		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		RDFProperty datatypeProperty_CompoundSynonym = owlModel.getRDFProperty("datatypeProperty_CompoundSynonym");
		
		RDFProperty dp_pubCID = owlModel.getRDFProperty("datatypeProperty_PubCID");
		RDFProperty dp_molForm = owlModel.getRDFProperty("datatypeProperty_MolForm");
		RDFProperty dp_molWeight = owlModel.getRDFProperty("datatypeProperty_MolWeight");
		RDFProperty dp_canSMILES = owlModel.getRDFProperty("datatypeProperty_CanSMILES");
		RDFProperty dp_inchi = owlModel.getRDFProperty("datatypeProperty_InChI");
		RDFProperty dp_inchikey = owlModel.getRDFProperty("datatypeProperty_InChIkey");
		RDFProperty dp_iupac = owlModel.getRDFProperty("datatypeProperty_IUPACName");
		
		RDFProperty dp_xlogp = owlModel.getRDFProperty("datatypeProperty_XLogP");
		RDFProperty dp_mass = owlModel.getRDFProperty("datatypeProperty_Mass");
		RDFProperty dp_tpsa = owlModel.getRDFProperty("datatypeProperty_TPSA");
		RDFProperty dp_complexity = owlModel.getRDFProperty("datatypeProperty_Complexity");
		
		RDFProperty dp_charge = owlModel.getRDFProperty("datatypeProperty_Charge");
		RDFProperty dp_donor = owlModel.getRDFProperty("datatypeProperty_HBondDonorCount");
		RDFProperty dp_accept = owlModel.getRDFProperty("datatypeProperty_HBondAcceptorCount");
		RDFProperty dp_rotbont = owlModel.getRDFProperty("datatypeProperty_RotatableBondCount");
		
		
		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Compound")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						String compoundIndiv = individual.getPropertyValue(datatypeProperty_Compound).toString();
						Collection compoundSynCol = individual.getPropertyValues(datatypeProperty_CompoundSynonym);
						
						if (compoundIndiv.equalsIgnoreCase(Compound.toLowerCase())) {
							System.out.println(compoundIndiv);
							compound = new Compound(compoundIndiv);
							
							// get compound synonyms
							List<String> synonyms = new ArrayList<String>();
							for (Iterator jtt = compoundSynCol.iterator(); jtt.hasNext();) {
								String syno = jtt.next().toString();
								System.out.println(syno);
								if(!syno.equalsIgnoreCase(compoundIndiv))
									synonyms.add(syno);
							}
							compound.setCompoundSynonyms(synonyms);
							
							if(!individual.getPropertyValue(dp_pubCID).toString().isEmpty())
								compound.setPubCID(individual.getPropertyValue(dp_pubCID).toString());
							
							if(!individual.getPropertyValue(dp_molForm).toString().isEmpty())
								compound.setMolForm(individual.getPropertyValue(dp_molForm).toString());
							if(!individual.getPropertyValue(dp_molWeight).toString().isEmpty())
								compound.setMolWeight(individual.getPropertyValue(dp_molWeight).toString());
							if(!individual.getPropertyValue(dp_canSMILES).toString().isEmpty())
								compound.setCanSMILES(individual.getPropertyValue(dp_canSMILES).toString());
							if(!individual.getPropertyValue(dp_inchi).toString().isEmpty())
								compound.setInchi(individual.getPropertyValue(dp_inchi).toString());
							if(!individual.getPropertyValue(dp_inchikey).toString().isEmpty())
								compound.setInchikey(individual.getPropertyValue(dp_inchikey).toString());
							if(!individual.getPropertyValue(dp_iupac).toString().isEmpty())
								compound.setIupac(individual.getPropertyValue(dp_iupac).toString());
							
							if(!individual.getPropertyValue(dp_xlogp).toString().isEmpty())
								compound.setXlogp(individual.getPropertyValue(dp_xlogp).toString());
							if(!individual.getPropertyValue(dp_mass).toString().isEmpty())
								compound.setMass(individual.getPropertyValue(dp_mass).toString());
							if(!individual.getPropertyValue(dp_tpsa).toString().isEmpty())
								compound.setTpsa(individual.getPropertyValue(dp_tpsa).toString());
							if(!individual.getPropertyValue(dp_complexity).toString().isEmpty())
								compound.setComplexity(individual.getPropertyValue(dp_complexity).toString());
							
							if(!individual.getPropertyValue(dp_charge).toString().isEmpty())
								compound.setCharge(individual.getPropertyValue(dp_charge).toString());
							if(!individual.getPropertyValue(dp_donor).toString().isEmpty())
								compound.sethBondDonor(individual.getPropertyValue(dp_donor).toString());
							if(!individual.getPropertyValue(dp_accept).toString().isEmpty())
								compound.sethBondAcceptor(individual.getPropertyValue(dp_accept).toString());
							if(!individual.getPropertyValue(dp_rotbont).toString().isEmpty())
								compound.setRotBondCount(individual.getPropertyValue(dp_rotbont).toString());
							
							
							System.out.println(compound.getMolForm());
							System.out.println(compound.getCanSMILES());
							System.out.println(compound.getInchikey());
							
							return compound;
						}
					} catch (Exception e) {
						
					}
				}
			}
		}
		
		return null;
	}
	
	
	public static void main(String[] args) throws OntologyLoadException {
		OntoQuery q = new OntoQuery();
		//List<Compound> compounds = q.searchCompound("d");
		Compound c = q.getCompound("alstoNerine");
	}
	
	public List<Compound> searchCompound(String Compound) {
		if(Compound.contains("α")) {
			Compound = Compound.replaceAll("α","alpha");
		} else if(Compound.contains("β")) {
			Compound = Compound.replaceAll("β","beta");
		} else if(Compound.contains("γ")) {
			Compound = Compound.replaceAll("γ","gamma");
		} 
		
		List<Compound> values = new ArrayList<Compound>();
		
		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		RDFProperty datatypeProperty_CompoundSynonym = owlModel.getRDFProperty("datatypeProperty_CompoundSynonym");
		
		Boolean found = false;
		
		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Compound")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					found = false;
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						String compoundIndiv = individual.getPropertyValue(datatypeProperty_Compound).toString();
						Collection compoundSynCol = individual.getPropertyValues(datatypeProperty_CompoundSynonym);
						
						Compound mp = null;
						
						// EDIT THIS CODE FOR OPTIMAL SEARCH FUNCTION
						if (compoundIndiv.toLowerCase().contains(Compound.toLowerCase())) {
							System.out.println(compoundIndiv);
							mp = new Compound(compoundIndiv);
							List<String> synonyms = new ArrayList<String>();
							
							for (Iterator jtt = compoundSynCol.iterator(); jtt.hasNext();) {
								if(!jtt.next().toString().isEmpty()) {
									String syno = jtt.next().toString();
									System.out.println(syno + " " + compoundIndiv);
									mp = new Compound(compoundIndiv);
									System.out.println(syno);
									synonyms.add(syno);
								}
							}
							
							mp.setCompoundSynonyms(synonyms);
							found = true;
							values.add(mp);
						}
						
						if(!found) {
							synonym:
							for (Iterator jtt = compoundSynCol.iterator(); jtt.hasNext();) {
								if(!jtt.next().toString().isEmpty()) {
									String syno = jtt.next().toString();
									if (syno.toLowerCase().contains(Compound.toLowerCase())) {
										System.out.println(syno + " " + compoundIndiv);
										mp = new Compound(compoundIndiv);
										List<String> synonyms = new ArrayList<String>();
										synonyms.add(syno);
										mp.setCompoundSynonyms(synonyms);
										found = true;
										values.add(mp);
										//break synonym;
									}
								}
							}
						}
					} catch (Exception e) {
						
					}
				}
			}
		}
		
		System.out.println(values.size());
		
		return values;
	}

	public List<MedicinalPlant> searchMedicinalPlant(String MedicinalPlant) {
		List<MedicinalPlant> values = new ArrayList<MedicinalPlant>();
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");
		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						String medPlantIndiv = individual.getPropertyValue(datatypeProperty_MedicinalPlant).toString();
						// EDIT THIS CODE FOR OPTIMAL SEARCH FUNCTION
						if (medPlantIndiv.toLowerCase().contains(MedicinalPlant.toLowerCase())) {
							System.out.println(medPlantIndiv);
							MedicinalPlant mp = new MedicinalPlant(medPlantIndiv);
//							try {
								// get scientific name/synonyms
								ArrayList<Species> species = new ArrayList<Species>();
								species.addAll(getSpeciesList(individual));
								mp.setSpecies(species);
//							} catch (Exception e) {
//								System.err.println("This specie has no synonym/s");
//							}
							// get locations
							ArrayList<String> locations = new ArrayList<String>();
							locations.addAll(getLocations(medPlantIndiv));
							mp.setLocations(locations);

							values.add(mp);
						}
					} catch (Exception e) {
						
					}
				}
			}
		}
		return values;
	}

	public ArrayList<Species> getSpeciesList(OWLIndividual MedicinalPlant) {
		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");
		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");

		RDFProperty hasScientificName = owlModel.getRDFProperty("hasScientificName");
		RDFProperty belongsToGenus = owlModel.getRDFProperty("belongsToGenus");
		RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

		ArrayList<Species> species = new ArrayList<Species>();

		Collection speciesCol = MedicinalPlant.getPropertyValues(datatypeProperty_Synonym);
		// This is for entities from OntoPHerb, synonyms as datatype prop
		for (Iterator it = speciesCol.iterator(); it.hasNext();) {
			String synonym = it.next().toString();
			if (!synonym.isEmpty()) {				
				Species sp = new Species(synonym);
				species.add(sp);
			}
		}

		// This is for synonyms as objects
		Collection speciesCol2 = MedicinalPlant.getPropertyValues(hasScientificName);
		for (Iterator it = speciesCol2.iterator(); it.hasNext();) {
			OWLIndividual sciNameIndiv = (OWLIndividual) it.next();
			if (!sciNameIndiv.getPropertyValue(datatypeProperty_Synonym).toString().isEmpty()) {
				Species sp = new Species(sciNameIndiv.getPropertyValue(datatypeProperty_Synonym).toString());
				sp.setSpeciesParts(getSpeciesPartList(sciNameIndiv));
				try {
					// get genus
					OWLIndividual genus = (OWLIndividual) sciNameIndiv.getPropertyValue(belongsToGenus);
					sp.setGenus(genus.getPropertyValue(datatypeProperty_Genus).toString());
					try {
						// get family
						OWLIndividual family = (OWLIndividual) genus.getPropertyValue(belongsToFamily);
						sp.setFamily(family.getPropertyValue(datatypeProperty_Family).toString());
					} catch (Exception e) {
						System.err.println("No Family");
					}
				} catch (Exception e) {
					System.err.println("No Genus");
				}
				System.out.println(sp.getSpecie());
				species.add(sp);
			}
		}
		// If there is no synonym, create empty object
		if (species.size() == 0) {
			species.add(new Species(""));
		}
		return species;
	}

	public ArrayList<SpeciesPart> getSpeciesPartList(OWLIndividual Species) {
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");
		RDFProperty hasChildPlantPart = owlModel.getRDFProperty("hasChildPlantPart");
		RDFProperty hasPlantPart = owlModel.getRDFProperty("hasPlantPart");
		ArrayList<SpeciesPart> speciesParts = new ArrayList<SpeciesPart>();

		// Enter hasChildPlantPart object
		Collection speciesPartCol = Species.getPropertyValues(hasChildPlantPart);
		for (Iterator it = speciesPartCol.iterator(); it.hasNext();) {
			OWLIndividual speciesPartIndiv = (OWLIndividual) it.next();

			// Enter hasPlantPart object
			OWLIndividual plantPartIndiv = (OWLIndividual) speciesPartIndiv.getPropertyValue(hasPlantPart);
			// Get datatype property from hasPlantPart object
			SpeciesPart spp = new SpeciesPart(plantPartIndiv.getPropertyValue(datatypeProperty_PlantPart).toString());
			spp.setCompounds(getCompoundList(speciesPartIndiv));
			speciesParts.add(spp);
		}
		return speciesParts;
	}

	public ArrayList<Compound> getCompoundList(OWLIndividual SpeciesPart) {

		RDFProperty hasCompound = owlModel.getRDFProperty("hasCompound");
		RDFProperty datatypeProperty_CompoundSynonym = owlModel.getRDFProperty("datatypeProperty_CompoundSynonym");
		ArrayList<Compound> compounds = new ArrayList<Compound>();

		Collection compoundCol = SpeciesPart.getPropertyValues(hasCompound);
		for (Iterator it = compoundCol.iterator(); it.hasNext();) {
			OWLIndividual compoundIndiv = (OWLIndividual) it.next();
			String comp = compoundIndiv.getBrowserText().replaceAll("http.+#", "");
			comp = comp.replaceAll("\\.", ",");
			comp = comp.replaceAll("_", " ");
			Compound compound = new Compound(comp);
			ArrayList<String> compoundSynonyms = new ArrayList<String>();
			Collection compoundSynCol = compoundIndiv.getPropertyValues(datatypeProperty_CompoundSynonym);
			for (Iterator jt = compoundSynCol.iterator(); jt.hasNext();) {
				comp = jt.next().toString();
				if (comp.equalsIgnoreCase(compound.getCompoundName())) {
					compound.setCompoundName(comp);
				}
				compoundSynonyms.add(comp);
			}
			compound.setCompoundSynonyms(compoundSynonyms);
			compounds.add(compound);
		}
		return compounds;
	}

}
