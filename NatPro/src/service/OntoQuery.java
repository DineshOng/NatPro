package service;

import java.util.ArrayList;
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
		String owlPath = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\Ontology\\OntoNatPro.owl";
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
