package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.WordUtils;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.BiologicalActivity;
import model.CellLine;
import model.Compound;
import model.Family;
import model.Genus;
import model.MedicinalPlant;
import model.Preparation;
import model.Species;
import model.SpeciesPart;

public class OntoQuery {
	String uri;
	OWLModel owlModel;

	public OntoQuery() throws OntologyLoadException {
		/* Change local path */
//		String owlPath = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Ontology\\OntoNatPro.owl";
//		String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro2.1.owl";
		String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro2.owl";
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

	public List<String> getAllSynonyms() throws SQWRLException {
		List<String> values = new ArrayList<String>();

		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Species")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						values.add(individual.getPropertyValue(datatypeProperty_Synonym).toString());
					} catch (Exception e) {
					}
				}
			}

		}
		return values;
	}

	public List<String> getAllGenus() throws SQWRLException {
		List<String> values = new ArrayList<String>();

		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Genus")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						values.add(individual.getPropertyValue(datatypeProperty_Genus).toString());
					} catch (Exception e) {
					}
				}
			}

		}
		return values;
	}

	public List<String> getAllLocations() throws SQWRLException {
		List<String> values = new ArrayList<String>();

		RDFProperty datatypeProperty_Location = owlModel.getRDFProperty("datatypeProperty_Location");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Location")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						values.add(WordUtils
								.capitalize(individual.getPropertyValue(datatypeProperty_Location).toString()));

					} catch (Exception e) {
					}
				}
			}

		}
		return values;
	}

	public List<String> getAllFamily() throws SQWRLException {
		List<String> values = new ArrayList<String>();

		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Family")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						values.add(individual.getPropertyValue(datatypeProperty_Family).toString());
					} catch (Exception e) {
					}
				}
			}

		}
		return values;
	}

	public List<String> getAllPreparations() {
		List<String> preparation = new ArrayList<String>();
		RDFProperty datatypeProperty_Preparation = owlModel.getRDFProperty("datatypeProperty_Preparation");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Preparation")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						preparation.add(individual.getPropertyValue(datatypeProperty_Preparation).toString());
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return preparation;

	}

	public List<String> getAllPlantParts() {
		List<String> speciesParts = new ArrayList<String>();
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("PlantPart")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						speciesParts.add(individual.getPropertyValue(datatypeProperty_PlantPart).toString());
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return speciesParts;

	}

	public List<String> getAllCompounds() {
		List<String> compounds = new ArrayList<String>();
		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Compound")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						compounds.add(individual.getPropertyValue(datatypeProperty_Compound).toString());
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return compounds;

	}

	public List<String> getAllBioAct() {
		List<String> bioacts = new ArrayList<String>();
		RDFProperty datatypeProperty_BiologicalActivity = owlModel
				.getRDFProperty("datatypeProperty_BiologicalActivity");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("BiologicalActivity")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					OWLIndividual individual = (OWLIndividual) jt.next();
					try {
						bioacts.add(individual.getPropertyValue(datatypeProperty_BiologicalActivity).toString());
					} catch (Exception e) {
						bioacts.add(individual.getBrowserText().replaceAll("_", " "));
					}
				}
			}

		}
		return bioacts;

	}

	public List<String> getAllIllness() {
		List<String> illness = new ArrayList<String>();
		RDFProperty datatypeProperty_Illness = owlModel.getRDFProperty("datatypeProperty_Illness");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Illness")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					OWLIndividual individual = (OWLIndividual) jt.next();
					try {
						illness.add(individual.getPropertyValue(datatypeProperty_Illness).toString());
					} catch (Exception e) {
						illness.add(individual.getBrowserText().replaceAll("_", " "));
					}
				}
			}

		}
		return illness;

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
								String syn = kt.next().toString();
								if (!syn.isEmpty()) {
									values.add(syn);
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

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
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
								values.add(locIndiv.getPropertyValue(datatypeProperty_Location).toString()
										.substring(0, 1).toUpperCase()
										+ locIndiv.getPropertyValue(datatypeProperty_Location).toString().substring(1));
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
		HashSet<String> values = new HashSet<String>(); // This set will only be used to check for
														// duplicate compounds
		List<MedicinalPlant> medicinalPlantList = searchMedicinalPlant(MedicinalPlant);

		try {
			for (int i = 0; i < medicinalPlantList.size(); i++) {
				List<Species> speciesList = medicinalPlantList.get(i).getSpecies();
				for (int j = 0; j < speciesList.size(); j++) {
					List<SpeciesPart> speciesPartList = speciesList.get(j).getSpeciesParts();
					for (int k = 0; k < speciesPartList.size(); k++) {
						List<Compound> compoundList = speciesPartList.get(k).getCompounds();
						for (int l = 0; l < compoundList.size(); l++) {
							values.add(compoundList.get(l).getCompoundName());
							Set<String> compoundSynList = compoundList.get(l).getCompoundSynonyms();
							values.addAll(compoundSynList);
						}
					}
				}
			}
		} catch (Exception e) {
		}
		List<String> compounds = new ArrayList<String>(values);
		return compounds;

	}

	public String getSynonymMP(String Synonym) throws SQWRLException {
		String value = "";
		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");

		RDFProperty hasScientificName = owlModel.getRDFProperty("hasScientificName");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						OWLIndividual synIndiv = (OWLIndividual) individual.getPropertyValue(hasScientificName);
						// find the synonym
						if (Synonym.equalsIgnoreCase(synIndiv.getPropertyValue(datatypeProperty_Synonym).toString())) {
							value = individual.getPropertyValue(datatypeProperty_MedicinalPlant).toString();
						}
					} catch (Exception e) {
					}
				}
			}

		}

		return value;
	}

	public List<String> getSynCompounds(String Synonym) throws SQWRLException {
		HashSet<String> values = new HashSet<String>();

		String MedicinalPlant = "";

		List<MedicinalPlant> medicinalPlantList = searchMedicinalPlant(MedicinalPlant);
		try {
			for (int i = 0; i < medicinalPlantList.size(); i++) {
				List<Species> speciesList = medicinalPlantList.get(i).getSpecies();
				for (int j = 0; j < speciesList.size(); j++) {
					if (speciesList.get(j).getSpecie().equals(Synonym)) {
						List<SpeciesPart> speciesPartList = speciesList.get(j).getSpeciesParts();
						for (int k = 0; k < speciesPartList.size(); k++) {
							List<Compound> compoundList = speciesPartList.get(k).getCompounds();
							for (int l = 0; l < compoundList.size(); l++) {
								values.add(compoundList.get(l).getCompoundName());
								Set<String> compoundSynList = compoundList.get(l).getCompoundSynonyms();
								values.addAll(compoundSynList);
							}

						}
					}
				}
			}
		} catch (Exception e) {
		}
		List<String> compounds = new ArrayList<String>(values);
		return compounds;

	}

	public List<String> getMPGenus(String MedicinalPlant) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");
		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");

		RDFProperty hasScientificName = owlModel.getRDFProperty("hasScientificName");
		RDFProperty belongsToGenus = owlModel.getRDFProperty("belongsToGenus");

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
							Collection synonyms = individual.getPropertyValues(hasScientificName);
							for (Iterator lt = synonyms.iterator(); lt.hasNext();) {
								OWLIndividual sciNameIndiv = (OWLIndividual) lt.next();
								OWLIndividual genusIndiv = (OWLIndividual) sciNameIndiv
										.getPropertyValue(belongsToGenus);
								values.add(genusIndiv.getPropertyValue(datatypeProperty_Genus).toString());
							}

						}

					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getSynonymGenus(String Synonym) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");
		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");

		RDFProperty belongsToGenus = owlModel.getRDFProperty("belongsToGenus");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Species")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the synonym
						if (Synonym
								.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Synonym).toString())) {
							OWLIndividual genusIndiv = (OWLIndividual) individual.getPropertyValue(belongsToGenus);
							values.add(genusIndiv.getPropertyValue(datatypeProperty_Genus).toString());
						}
					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getMPFamily(String MedicinalPlant) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");

		RDFProperty hasScientificName = owlModel.getRDFProperty("hasScientificName");
		RDFProperty belongsToGenus = owlModel.getRDFProperty("belongsToGenus");
		RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

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
							Collection synonyms = individual.getPropertyValues(hasScientificName);
							for (Iterator lt = synonyms.iterator(); lt.hasNext();) {
								OWLIndividual sciNameIndiv = (OWLIndividual) lt.next();
								OWLIndividual genusIndiv = (OWLIndividual) sciNameIndiv
										.getPropertyValue(belongsToGenus);
								OWLIndividual familyIndiv = (OWLIndividual) genusIndiv
										.getPropertyValue(belongsToFamily);
								values.add(familyIndiv.getPropertyValue(datatypeProperty_Family).toString());
							}
						}

					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getSynonymFamily(String Synonym) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");

		RDFProperty belongsToGenus = owlModel.getRDFProperty("belongsToGenus");
		RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Species")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the synonym
						if (Synonym
								.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Synonym).toString())) {
							OWLIndividual genusIndiv = (OWLIndividual) individual.getPropertyValue(belongsToGenus);
							OWLIndividual familyIndiv = (OWLIndividual) genusIndiv.getPropertyValue(belongsToFamily);
							values.add(familyIndiv.getPropertyValue(datatypeProperty_Family).toString());
						}
					} catch (Exception e) {
					}
				}
			}
		}

		return values;
	}

	public List<String> getMPPlantParts(String MedicinalPlant) throws SQWRLException {
		List<String> speciesParts = new ArrayList<String>();
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");

		RDFProperty hasScientificName = owlModel.getRDFProperty("hasScientificName");
		RDFProperty hasChildPlantPart = owlModel.getRDFProperty("hasChildPlantPart");
		RDFProperty hasPlantPart = owlModel.getRDFProperty("hasPlantPart");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the medicinal plant
						if (MedicinalPlant.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_MedicinalPlant).toString())) {
							// This is for synonyms as objects
							Collection synonyms = individual.getPropertyValues(hasScientificName);
							for (Iterator kt = synonyms.iterator(); kt.hasNext();) {
								OWLIndividual sciNameIndiv = (OWLIndividual) kt.next();
								Collection childPlantPart = sciNameIndiv.getPropertyValues(hasChildPlantPart);
								for (Iterator lt = childPlantPart.iterator(); lt.hasNext();) {
									OWLIndividual childPlantPartIndiv = (OWLIndividual) lt.next();
									OWLIndividual plantPartIndiv = (OWLIndividual) childPlantPartIndiv
											.getPropertyValue(hasPlantPart);
									speciesParts.add(
											plantPartIndiv.getPropertyValue(datatypeProperty_PlantPart).toString());
								}
							}

						}
					} catch (Exception e) {
					}
				}
			}

		}

		return speciesParts;
	}

	public List<String> getMPPreparation(String MedicinalPlant) throws SQWRLException {
		List<String> preparations = new ArrayList<String>();
		RDFProperty datatypeProperty_Preparation = owlModel.getRDFProperty("datatypeProperty_Preparation");
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");

		RDFProperty hasPreparation = owlModel.getRDFProperty("hasPreparation");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the medicinal plant
						if (MedicinalPlant.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_MedicinalPlant).toString())) {
							// This is for synonyms as objects
							Collection preps = individual.getPropertyValues(hasPreparation);
							for (Iterator kt = preps.iterator(); kt.hasNext();) {
								OWLIndividual preparationIndiv = (OWLIndividual) kt.next();
								preparations.add(
										preparationIndiv.getPropertyValue(datatypeProperty_Preparation).toString());
							}

						}
					} catch (Exception e) {
					}
				}
			}

		}

		return preparations;
	}

	public List<String> getSynonymPlantParts(String Synonym) throws SQWRLException {
		List<String> speciesParts = new ArrayList<String>();
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");
		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");

		RDFProperty hasScientificName = owlModel.getRDFProperty("hasScientificName");
		RDFProperty hasChildPlantPart = owlModel.getRDFProperty("hasChildPlantPart");
		RDFProperty hasPlantPart = owlModel.getRDFProperty("hasPlantPart");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Species")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the species/synonym
						if (Synonym
								.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Synonym).toString())) {
							// This is for synonyms as objects
							Collection childPlantPart = individual.getPropertyValues(hasChildPlantPart);
							for (Iterator kt = childPlantPart.iterator(); kt.hasNext();) {
								OWLIndividual childPlantPartIndiv = (OWLIndividual) kt.next();
								OWLIndividual plantPartIndiv = (OWLIndividual) childPlantPartIndiv
										.getPropertyValue(hasPlantPart);
								speciesParts
										.add(plantPartIndiv.getPropertyValue(datatypeProperty_PlantPart).toString());
							}
						}

					} catch (Exception e) {
					}
				}
			}

		}

		return speciesParts;

	}

	public List<String> getGenusFamily(String Genus) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");

		RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Genus")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the genus
						if (Genus.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Genus).toString())) {
							OWLIndividual familyIndiv = (OWLIndividual) individual.getPropertyValue(belongsToFamily);
							values.add(familyIndiv.getPropertyValue(datatypeProperty_Family).toString());
						}
					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getFamilyGenus(String Family) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");

		RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Genus")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the genus
						OWLIndividual familyIndiv = (OWLIndividual) individual.getPropertyValue(belongsToFamily);
						if (Family.equalsIgnoreCase(familyIndiv.getPropertyValue(datatypeProperty_Family).toString())) {
							values.add(individual.getPropertyValue(datatypeProperty_Genus).toString());
						}

					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getLocationMP(String location) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Location = owlModel.getRDFProperty("datatypeProperty_Location");
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");

		RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					OWLIndividual individual = (OWLIndividual) jt.next();
					try {
						Collection locations = individual.getPropertyValues(isLocatedIn);
						for (Iterator kt = locations.iterator(); kt.hasNext();) {
							OWLIndividual locIndiv = (OWLIndividual) kt.next();
							if (location.equalsIgnoreCase(
									locIndiv.getPropertyValue(datatypeProperty_Location).toString())) {
								values.add(individual.getPropertyValue(datatypeProperty_MedicinalPlant).toString());
							}
						}
					} catch (Exception e) {
					}

				}
			}
		}

		return values;
	}

	public List<String> getPreparationIllness(String preparation) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Preparation = owlModel.getRDFProperty("datatypeProperty_Preparation");
		RDFProperty datatypeProperty_Illness = owlModel.getRDFProperty("datatypeProperty_Illness");

		RDFProperty treats = owlModel.getRDFProperty("treats");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Preparation")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the genus
						if (preparation.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Preparation).toString())) {
//							OWLIndividual illnessIndiv = (OWLIndividual) individual.getPropertyValues(treats);
							Collection illness = individual.getPropertyValues(treats);
							for (Iterator kt = illness.iterator(); kt.hasNext();) {
								OWLIndividual illnessIndiv = (OWLIndividual) kt.next();
								values.add(illnessIndiv.getPropertyValue(datatypeProperty_Illness).toString());
							}

						}
					} catch (Exception e) {
					}
				}
			}

		}
		return values;
	}

	public List<String> getPreparationPlantPart(String preparation) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Preparation = owlModel.getRDFProperty("datatypeProperty_Preparation");
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");

		RDFProperty utilizedPart = owlModel.getRDFProperty("utilizedPart");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Preparation")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the genus
						if (preparation.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Preparation).toString())) {
							Collection plantPart = individual.getPropertyValues(utilizedPart);
							for (Iterator kt = plantPart.iterator(); kt.hasNext();) {
								OWLIndividual plantPartIndiv = (OWLIndividual) kt.next();
								values.add(plantPartIndiv.getPropertyValue(datatypeProperty_PlantPart).toString());
							}

						}
					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getPreparationPlantPartIllness(String preparation) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Preparation = owlModel.getRDFProperty("datatypeProperty_Preparation");
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");

		RDFProperty utilizedPart = owlModel.getRDFProperty("utilizedPart");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Preparation")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						// find the genus
						if (preparation.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Preparation).toString())) {
							OWLIndividual plantPartIndiv = (OWLIndividual) individual.getPropertyValue(utilizedPart);
							values.add(plantPartIndiv.getPropertyValue(datatypeProperty_PlantPart).toString());
						}
					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getPlantPartCompound(String plantpart) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");
		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");

		RDFProperty hasPlantPart = owlModel.getRDFProperty("hasPlantPart");
		RDFProperty hasCompound = owlModel.getRDFProperty("hasCompound");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("SpeciesPart")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						OWLIndividual plantPartIndiv = (OWLIndividual) individual.getPropertyValue(hasPlantPart);
						if (plantpart.equalsIgnoreCase(
								plantPartIndiv.getPropertyValue(datatypeProperty_PlantPart).toString())) {
							Collection compounds = individual.getPropertyValues(hasCompound);
							for (Iterator kt = compounds.iterator(); kt.hasNext();) {
								OWLIndividual compIndiv = (OWLIndividual) kt.next();
								values.add(compIndiv.getPropertyValue(datatypeProperty_Compound).toString());
							}
						}
					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getSpeciesPartCompound(String speciesPart) throws SQWRLException {
		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		RDFProperty hasCompound = owlModel.getRDFProperty("hasCompound");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("SpeciesPart")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						if (speciesPart.equalsIgnoreCase(individual.getBrowserText())) {
							Collection compounds = individual.getPropertyValues(hasCompound);
							for (Iterator kt = compounds.iterator(); kt.hasNext();) {
								OWLIndividual compIndiv = (OWLIndividual) kt.next();
								values.add(compIndiv.getPropertyValue(datatypeProperty_Compound).toString());
							}
						}
					} catch (Exception e) {
					}
				}
			}

		}

		return values;
	}

	public List<String> getCompoundBioAct(String compound) throws SQWRLException {

		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		RDFProperty datatypeProperty_BiologicalActivity = owlModel
				.getRDFProperty("datatypeProperty_BiologicalActivity");

		RDFProperty hasBiologicalActivity = owlModel.getRDFProperty("hasBiologicalActivity");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Compound")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						if (compound
								.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Compound).toString())) {

							Collection bioActs = individual.getPropertyValues(hasBiologicalActivity);

							for (Iterator kt = bioActs.iterator(); kt.hasNext();) {
								OWLIndividual bioActIndiv = (OWLIndividual) kt.next();
								try {
									values.add(bioActIndiv.getPropertyValue(datatypeProperty_BiologicalActivity)
											.toString());
								} catch (Exception e) {
									values.add(bioActIndiv.getBrowserText().replaceAll("_", " "));
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

	public List<String> getBioActCell(String bioact) throws SQWRLException {

		List<String> values = new ArrayList<String>();
		RDFProperty datatypeProperty_CellLine = owlModel.getRDFProperty("datatypeProperty_CellLine");
		RDFProperty datatypeProperty_BiologicalActivity = owlModel
				.getRDFProperty("datatypeProperty_BiologicalActivity");

		RDFProperty affects = owlModel.getRDFProperty("affects");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("BiologicalActivity")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					OWLIndividual individual = (OWLIndividual) jt.next();
					try {
						if (bioact.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_BiologicalActivity).toString())) {
							try {
								OWLIndividual cellIndiv = (OWLIndividual) individual.getPropertyValue(affects);
								try {
									values.add(cellIndiv.getPropertyValue(datatypeProperty_CellLine).toString());
								} catch (Exception e) {
									values.add(cellIndiv.getBrowserText().replaceAll("_", " "));
								}
							} catch (Exception e) {
							}

						}
					} catch (Exception e) {
						if (bioact.equalsIgnoreCase(individual.getBrowserText().replaceAll("_", " "))) {
							try {
								OWLIndividual cellIndiv = (OWLIndividual) individual.getPropertyValue(affects);
								try {
									values.add(cellIndiv.getPropertyValue(datatypeProperty_CellLine).toString());
								} catch (Exception e2) {
									values.add(cellIndiv.getBrowserText().replaceAll("_", " "));
								}
							} catch (Exception e2) {
							}
						}
					}

				}
			}
		}

		return values;

	}

	public Compound getCompound(String compoundName) {

		Compound compound = null;

		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty(OntoMngr.DP_Compound);
		RDFProperty datatypeProperty_CompoundSynonym = owlModel.getRDFProperty(OntoMngr.DP_Synonym);

		RDFProperty dp_pubCID = owlModel.getRDFProperty(OntoMngr.DP_PubCID);
		RDFProperty dp_molForm = owlModel.getRDFProperty(OntoMngr.DP_MolForm);
		RDFProperty dp_molWeight = owlModel.getRDFProperty(OntoMngr.DP_MolWeight);
		RDFProperty dp_canSMILES = owlModel.getRDFProperty(OntoMngr.DP_CanSMILES);
		RDFProperty dp_inchi = owlModel.getRDFProperty(OntoMngr.DP_InChI);
		RDFProperty dp_inchikey = owlModel.getRDFProperty(OntoMngr.DP_InChIkey);
		RDFProperty dp_iupac = owlModel.getRDFProperty(OntoMngr.DP_IUPACName);

		RDFProperty dp_xlogp = owlModel.getRDFProperty(OntoMngr.DP_XLogP);
		RDFProperty dp_mass = owlModel.getRDFProperty(OntoMngr.DP_Mass);
		RDFProperty dp_tpsa = owlModel.getRDFProperty(OntoMngr.DP_TPSA);
		RDFProperty dp_complexity = owlModel.getRDFProperty(OntoMngr.DP_Complexity);

		RDFProperty dp_charge = owlModel.getRDFProperty(OntoMngr.DP_Charge);
		RDFProperty dp_donor = owlModel.getRDFProperty(OntoMngr.DP_HBondDonor);
		RDFProperty dp_accept = owlModel.getRDFProperty(OntoMngr.DP_HBondAcceptor);
		RDFProperty dp_rotbont = owlModel.getRDFProperty(OntoMngr.DP_RotatableBond);

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Compound")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						String compoundIndiv = individual.getPropertyValue(datatypeProperty_Compound).toString();
						if (compoundIndiv.equalsIgnoreCase(compoundName.toLowerCase())) {
							// System.out.println(compoundIndiv);
							compound = new Compound(compoundIndiv);
							HashSet<BiologicalActivity> bioActs = new HashSet<BiologicalActivity>();
							bioActs.addAll(getBioActList(individual));
							compound.setBioActs(bioActs);
							try {
								Collection compoundSynCol = individual
										.getPropertyValues(datatypeProperty_CompoundSynonym);
								// get compound synonyms
								HashSet<String> synonyms = new HashSet<String>();
								for (Iterator jtt = compoundSynCol.iterator(); jtt.hasNext();) {
									String syno = jtt.next().toString();
									// System.out.println("dis>" + syno);
									// if (!syno.equalsIgnoreCase(compoundIndiv))

									synonyms.add(syno);
								}
								compound.setCompoundSynonyms(synonyms);
							} catch (Exception e) {
								System.err.println("Syn err");
							}

							try {
								if (!individual.getPropertyValue(dp_pubCID).toString().isEmpty())
									compound.setPubCID(individual.getPropertyValue(dp_pubCID).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_molForm).toString().isEmpty())
									compound.setMolForm(individual.getPropertyValue(dp_molForm).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_molWeight).toString().isEmpty())
									compound.setMolWeight(individual.getPropertyValue(dp_molWeight).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_canSMILES).toString().isEmpty())
									compound.setCanSMILES(individual.getPropertyValue(dp_canSMILES).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_inchi).toString().isEmpty())
									compound.setInchi(individual.getPropertyValue(dp_inchi).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_inchikey).toString().isEmpty())
									compound.setInchikey(individual.getPropertyValue(dp_inchikey).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_iupac).toString().isEmpty())
									compound.setIupac(individual.getPropertyValue(dp_iupac).toString());
							} catch (Exception e) {
							}

							try {
								if (!individual.getPropertyValue(dp_xlogp).toString().isEmpty())
									compound.setXlogp(individual.getPropertyValue(dp_xlogp).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_mass).toString().isEmpty())
									compound.setMass(individual.getPropertyValue(dp_mass).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_tpsa).toString().isEmpty())
									compound.setTpsa(individual.getPropertyValue(dp_tpsa).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_complexity).toString().isEmpty())
									compound.setComplexity(individual.getPropertyValue(dp_complexity).toString());
							} catch (Exception e) {
							}

							try {
								if (!individual.getPropertyValue(dp_charge).toString().isEmpty())
									compound.setCharge(individual.getPropertyValue(dp_charge).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_donor).toString().isEmpty())
									compound.setHBondDonor(individual.getPropertyValue(dp_donor).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_accept).toString().isEmpty())
									compound.setHBondAcceptor(individual.getPropertyValue(dp_accept).toString());
							} catch (Exception e) {
							}
							try {
								if (!individual.getPropertyValue(dp_rotbont).toString().isEmpty())
									compound.setRotBondCount(individual.getPropertyValue(dp_rotbont).toString());
							} catch (Exception e) {
							}

							// System.out.println(compound.getMolForm());
							// System.out.println(compound.getCanSMILES());
							// System.out.println(compound.getMolWeight());

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
		// List<Compound> compounds = q.searchCompound("pr");
//		Compound c = q.getCompound("alstoNerine");
	}

	public List<Compound> searchCompound(String Compound) {
		if (Compound.contains("&#945;")) {
			Compound = Compound.replaceAll("&#945;", "alpha");
		} else if (Compound.contains("&#946;")) {
			Compound = Compound.replaceAll("&#946;", "beta");
		} else if (Compound.contains("&#947;")) {
			Compound = Compound.replaceAll("&#947;", "gamma");
		}

		System.out.println("search " + Compound);

		List<Compound> values = new ArrayList<Compound>();

		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		RDFProperty datatypeProperty_CompoundSynonym = owlModel.getRDFProperty("datatypeProperty_CompoundSynonym");
		RDFProperty datatypeProperty_IUPACName = owlModel.getRDFProperty("datatypeProperty_IUPACName");

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
							// System.out.println(compoundIndiv);
							mp = new Compound(compoundIndiv);
							HashSet<String> synonyms = new HashSet<String>();

							for (Iterator jtt = compoundSynCol.iterator(); jtt.hasNext();) {
								// if(!jtt.next().toString().isEmpty()) {
								String syno = jtt.next().toString();
								// System.out.println(syno + " " + compoundIndiv);
								mp = new Compound(compoundIndiv);
								// System.out.println(syno);
								synonyms.add(syno);
								// }
							}

							mp.setCompoundSynonyms(synonyms);
							found = true;
							values.add(mp);
						}

						try {
							if (!found) {
								String compoundIndivIUPAC = individual.getPropertyValue(datatypeProperty_IUPACName)
										.toString();
								if (compoundIndivIUPAC.toLowerCase().contains(Compound.toLowerCase())) {
									mp = new Compound(compoundIndiv);
									HashSet<String> synonyms = new HashSet<String>();

									for (Iterator jtt = compoundSynCol.iterator(); jtt.hasNext();) {
										// if(!jtt.next().toString().isEmpty()) {
										String syno = jtt.next().toString();
										// System.out.println(syno + " " + compoundIndiv);
										mp = new Compound(compoundIndiv);
										// System.out.println(syno);
										synonyms.add(syno);
										// }
									}

									synonyms.add(compoundIndivIUPAC);

									mp.setCompoundSynonyms(synonyms);
									found = true;
									values.add(mp);
								}
							}
						} catch (Exception ee) {
							// System.out.println(ee);
						}

						if (!found) {
							synonym: for (Iterator jtt = compoundSynCol.iterator(); jtt.hasNext();) {
								// if(!jtt.next().toString().isEmpty()) {
								String syno = jtt.next().toString();
								if (syno.toLowerCase().contains(Compound.toLowerCase())) {
									// System.out.println(syno + " " + compoundIndiv);
									mp = new Compound(compoundIndiv);
									HashSet<String> synonyms = new HashSet<String>();
									synonyms.add(syno);
									mp.setCompoundSynonyms(synonyms);
									found = true;
									values.add(mp);
									// break synonym;
								}
								// }
							}
						}
					} catch (Exception e) {
						// System.out.println("eeeek");
					}
				}
			}
		}

		System.out.println(values.size());

		return values;
	}

	public HashSet<String> getAllCompoundNames() {
		HashSet<String> values = new HashSet<String>();

		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		RDFProperty datatypeProperty_CompoundSynonym = owlModel.getRDFProperty("datatypeProperty_CompoundSynonym");

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

						Compound mp = null;
						values.add(compoundIndiv);
						for (Iterator jtt = compoundSynCol.iterator(); jtt.hasNext();) {
							String syno = jtt.next().toString();
							values.add(syno);
						}
					} catch (Exception e) {
						// System.out.println("eeeek");
					}
				}
			}
		}

		System.out.println(values.size());

		return values;
	}

	public List<Compound> getCompoundwStructure() {
		List<Compound> values = new ArrayList<Compound>();

		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		RDFProperty datatypeProperty_CanSMILES = owlModel.getRDFProperty("datatypeProperty_CanSMILES");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Compound")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						String compoundIndiv = individual.getPropertyValue(datatypeProperty_Compound).toString();
						String smile = individual.getPropertyValue(datatypeProperty_CanSMILES).toString();

						System.out.println("=> " + compoundIndiv + " " + smile);

						// if(!smile.equals("") &&
						// smile.matches("^([^J][0-9BCOHNSOPrIFla@+\\-\\[\\]\\(\\)\\\\\\/%=#$,.~&!]{6,})$"))
						// {
						if (!smile.equals("")) {
							Compound cc = new Compound(compoundIndiv);
							cc.setCanSMILES(smile);

							values.add(cc);
						}

					} catch (Exception e) {
						// System.out.println("eeeek");
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
//							System.err.println(medPlantIndiv);
							System.out.println(individual.getBrowserText());
							MedicinalPlant mp = new MedicinalPlant(medPlantIndiv);

////							 get scientific name/synonyms
							ArrayList<Species> species = new ArrayList<Species>();
							try {
								species.addAll(getSpeciesList(individual));
							} catch (Exception e) {
								System.err.println(e);
							}
							mp.setSpecies(species);

							try {
								// get locations
								ArrayList<String> locations = new ArrayList<String>();
								locations.addAll(getLocations(medPlantIndiv));
								mp.setLocations(locations);
							} catch (Exception e) {

							}

							try {
								// get preparations
								ArrayList<Preparation> preparations = new ArrayList<Preparation>();
								preparations.addAll(getPreparationList(individual));
								mp.setPreparations(preparations);
							} catch (Exception e) {

							}

							values.add(mp);
						}
					} catch (Exception e) {
					}
				}
			}
		}
		return values;
	}

	public Species searchSpecie(String specie) {
		Species searchSpecie = new Species(specie);

		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");
		RDFProperty belongsToGenus = owlModel.getRDFProperty("belongsToGenus");
		RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");
		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Species")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					OWLIndividual specieIndiv = (OWLIndividual) jt.next();
					if (specieIndiv.getPropertyValue(datatypeProperty_Synonym).equals(specie)) {
						try { // check if specie has genus
							OWLIndividual genusIndiv = (OWLIndividual) specieIndiv.getPropertyValue(belongsToGenus);
							searchSpecie.setGenus(genusIndiv.getPropertyValue(datatypeProperty_Genus).toString());
							try { // check if specie has family
								OWLIndividual familyIndiv = (OWLIndividual) genusIndiv
										.getPropertyValue(belongsToFamily);
								searchSpecie
										.setFamily(familyIndiv.getPropertyValue(datatypeProperty_Family).toString());
							} catch (Exception e) { // if no family, disregard then proceed

							}
						} catch (Exception e) { // if no genus, disregard then proceed
						}

						searchSpecie.setSpeciesParts(getSpeciesPartList(specieIndiv));
					}
				}
			}
		}
		return searchSpecie;
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
//				System.out.println(synonym);
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
//				System.out.println(sp.getSpecie());
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
			try {
				spp.setCompounds(getCompoundList(speciesPartIndiv));
			} catch (Exception e) {
				System.err.println("no compound");
			}
			speciesParts.add(spp);
		}
		return speciesParts;
	}

	public ArrayList<Compound> getCompoundList(OWLIndividual SpeciesPart) {
		RDFProperty hasCompound = owlModel.getRDFProperty("hasCompound");
		RDFProperty includesCompound = owlModel.getRDFProperty("includesCompound");
		RDFProperty datatypeProperty_CompoundSynonym = owlModel.getRDFProperty("datatypeProperty_CompoundSynonym");
		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		ArrayList<Compound> compounds = new ArrayList<Compound>();

		Collection compoundCol = SpeciesPart.getPropertyValues(hasCompound);
		for (Iterator it = compoundCol.iterator(); it.hasNext();) {
			OWLIndividual compoundIndiv = (OWLIndividual) it.next();
			String comp = compoundIndiv.getPropertyValue(datatypeProperty_Compound).toString();
			System.out.println(comp);
//			String comp = compoundIndiv.getBrowserText().replaceAll("http.+#", "");
//			comp = comp.replaceAll("\\.", ",");
//			comp = comp.replaceAll("_", " ");
			if (comp.split(" ")[0].equals("mixtures")) { // if the compound is a mixture, we should get property
															// "includesCompound"
				Collection includeCompoundCol = compoundIndiv.getPropertyValues(includesCompound);
				for (Iterator iit = includeCompoundCol.iterator(); iit.hasNext();) {
					OWLIndividual includeCompoundIndiv = (OWLIndividual) iit.next();
					String includecomp = includeCompoundIndiv.getBrowserText().replaceAll("http.+#", "");
					includecomp = includecomp.replaceAll("\\.", ",");
					includecomp = includecomp.replaceAll("_", " ");
					Compound compound = getCompound(includecomp);
					HashSet<BiologicalActivity> bioActs = new HashSet<BiologicalActivity>();
					bioActs.addAll(getBioActList(includeCompoundIndiv));
					compound.setBioActs(bioActs);
					compounds.add(compound);
					System.out.println(compound.getCompoundName() + "@");
				}
			} else {
				Compound compound = getCompound(comp);
				HashSet<BiologicalActivity> bioActs = new HashSet<BiologicalActivity>();
				bioActs.addAll(getBioActList(compoundIndiv));
				compound.setBioActs(bioActs);
				try {
					HashSet<String> compoundSynonyms = new HashSet<String>();
					Collection compoundSynCol = compoundIndiv.getPropertyValues(datatypeProperty_CompoundSynonym);
					for (Iterator jt = compoundSynCol.iterator(); jt.hasNext();) {
						comp = jt.next().toString();
						if (comp.equalsIgnoreCase(compound.getCompoundName())) {
							compound.setCompoundName(comp);
						}
						compoundSynonyms.add(comp);
					}
					compound.setCompoundSynonyms(compoundSynonyms);
				} catch (Exception e) {

				}
				compounds.add(compound);

				System.out.println(compound.getCompoundName() + "@");
			}
		}
		return compounds;
	}

	public ArrayList<BiologicalActivity> getBioActList(OWLIndividual Compound) {
		RDFProperty hasBiologicalActivity = owlModel.getRDFProperty("hasBiologicalActivity");
		RDFProperty affects = owlModel.getRDFProperty("affects");

		RDFProperty datatypeProperty_BiologicalActivity = owlModel
				.getRDFProperty("datatypeProperty_BiologicalActivity");
		RDFProperty datatypeProperty_CellLine = owlModel.getRDFProperty("datatypeProperty_CellLine");

		ArrayList<BiologicalActivity> bioActs = new ArrayList<BiologicalActivity>();
		BiologicalActivity bioAct = new BiologicalActivity("");
		try { // check if the compound has biological activity
			Collection BioActCol = Compound.getPropertyValues(hasBiologicalActivity);
			for (Iterator jt = BioActCol.iterator(); jt.hasNext();) {
				OWLIndividual bioActIndiv = (OWLIndividual) jt.next();
				try { // check if the biological activity has data property
					bioAct = new BiologicalActivity(
							bioActIndiv.getPropertyValue(datatypeProperty_BiologicalActivity).toString());
				} catch (Exception e) { // if none, just get the object name
					bioAct = new BiologicalActivity(bioActIndiv.getBrowserText().replaceAll("_", " "));
				}
				try { // check if there is a cell line object
					OWLIndividual CellLineIndiv = (OWLIndividual) bioActIndiv.getPropertyValue(affects);
					try { // check if the cell line has data property
						CellLine cellLine = new CellLine(
								CellLineIndiv.getPropertyValue(datatypeProperty_CellLine).toString());
						bioAct.setCellLine(cellLine);
					} catch (Exception e) { // if none, just get the object name
						CellLine cellLine = new CellLine(CellLineIndiv.getBrowserText().replaceAll("_", " "));
						bioAct.setCellLine(cellLine);
					}

				} catch (Exception e) { // if none, disregard cellline and continue to the next
				}
				bioActs.add(bioAct);
			}
		} catch (Exception e) { // if none, disregard bioact and continue to the next
		}
		return bioActs;
	}

	public ArrayList<Preparation> getPreparationList(OWLIndividual MedicinalPlant) {
		RDFProperty hasPreparation = owlModel.getRDFProperty("hasPreparation");
		RDFProperty treats = owlModel.getRDFProperty("treats");
		RDFProperty utilizedPart = owlModel.getRDFProperty("utilizedPart");
		RDFProperty datatypeProperty_Preparation = owlModel.getRDFProperty("datatypeProperty_Preparation");
		RDFProperty datatypeProperty_Illness = owlModel.getRDFProperty("datatypeProperty_Illness");
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");
		ArrayList<Preparation> preparations = new ArrayList<Preparation>();

		Collection prepCol = MedicinalPlant.getPropertyValues(hasPreparation);
		for (Iterator it = prepCol.iterator(); it.hasNext();) {
			OWLIndividual prepIndiv = (OWLIndividual) it.next();
			String prep = prepIndiv.getPropertyValue(datatypeProperty_Preparation).toString();
			Preparation preparation = new Preparation(prep);
			OWLIndividual utilizedPartIndiv = (OWLIndividual) prepIndiv.getPropertyValue(utilizedPart);
			String utilPart = utilizedPartIndiv.getPropertyValue(datatypeProperty_PlantPart).toString();
			preparation.setUtilizedPlantPart(utilPart);
			ArrayList<String> illnessList = new ArrayList<String>();
			Collection illnessCol = prepIndiv.getPropertyValues(treats);
			for (Iterator jt = illnessCol.iterator(); jt.hasNext();) {
				OWLIndividual illnessIndiv = (OWLIndividual) jt.next();
				String illness = illnessIndiv.getPropertyValue(datatypeProperty_Illness).toString();
				illnessList.add(illness);
			}
			preparation.setIllness(illnessList);
			preparations.add(preparation);
		}
		return preparations;
	}

	public List<Genus> searchGenus(String genus) {
		List<Genus> values = new ArrayList<Genus>();

		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
		RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Genus")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual genusIndiv = (OWLIndividual) jt.next();
						String genusValue = genusIndiv.getPropertyValue(datatypeProperty_Genus).toString();
						if (genusValue.toLowerCase().contains(genus.toLowerCase())) {
							Genus genusClass = new Genus(genusValue);
							try {
								OWLIndividual familyIndiv = (OWLIndividual) genusIndiv
										.getPropertyValue(belongsToFamily);
								String familyValue = familyIndiv.getPropertyValue(datatypeProperty_Family).toString();
								Family familyClass = new Family(familyValue);
								genusClass.setFamily(familyClass);
							} catch (Exception e) {
							}
							values.add(genusClass);
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return values;
	}

	public List<Family> searchFamily(String family) {
		List<Family> values = new ArrayList<Family>();

		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
		RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Family")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual familyIndiv = (OWLIndividual) jt.next();
						String familyValue = familyIndiv.getPropertyValue(datatypeProperty_Family).toString();
						if (familyValue.toLowerCase().contains(family.toLowerCase())) {
							Family familyClass = new Family(familyValue);
							values.add(familyClass);
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return values;
	}

	public String getMedPlantIndivName(String medPlantName) throws SQWRLException {
		RDFProperty datatypeProperty_MedicinalPlant = owlModel.getRDFProperty("datatypeProperty_MedicinalPlant");
		String medPlantIndivName = null;
//		System.out.println("Entered Get All PlantNames");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MedicinalPlant")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						if (medPlantName.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_MedicinalPlant)
								.toString().toLowerCase())) {
							medPlantIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return medPlantIndivName;
	}

	public String getFamilyIndivName(String familyName) throws SQWRLException {
		RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
		String familyIndivName = null;
//		System.out.println("Entered Get All PlantNames");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Family")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						if (familyName
								.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Family).toString())) {
							familyIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return familyIndivName;
	}

	public String getGenusIndivName(String genusName) throws SQWRLException {
		RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
		String genusIndivName = null;
//		System.out.println("Entered Get All PlantNames");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Genus")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						if (genusName.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Genus).toString().toLowerCase())) {
							genusIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return genusIndivName;
	}

	public String getSpeciesIndivName(String sciName) throws SQWRLException {
		RDFProperty datatypeProperty_Synonym = owlModel.getRDFProperty("datatypeProperty_Synonym");
		String sciNameIndivName = null;
//		System.out.println("Entered Get All PlantNames");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Species")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						if (sciName.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Synonym).toString().toLowerCase())) {
							sciNameIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return sciNameIndivName;
	}

	public String getLocIndivName(String location) throws SQWRLException {
		RDFProperty datatypeProperty_Location = owlModel.getRDFProperty("datatypeProperty_Location");
		String locIndivName = null;

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Location")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						if (location.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Location).toString().toLowerCase())) {
							locIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return locIndivName;
	}

	public String getPrepIndivName(String prep) throws SQWRLException {
		RDFProperty datatypeProperty_Preparation = owlModel.getRDFProperty("datatypeProperty_Preparation");
		String prepIndivName = null;

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Preparation")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {

					OWLIndividual individual = (OWLIndividual) jt.next();
					try {
						if (prep.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Preparation).toString().toLowerCase())) {
							prepIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return prepIndivName;
	}

	public String getPlantPartIndivName(String plantPart) throws SQWRLException {
		RDFProperty datatypeProperty_PlantPart = owlModel.getRDFProperty("datatypeProperty_PlantPart");
		String plantPartIndivName = null;

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("PlantPart")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {

					OWLIndividual individual = (OWLIndividual) jt.next();
					try {
						if (plantPart.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_PlantPart).toString().toLowerCase())) {
							plantPartIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
//						System.out.println("Exception here");
					}
				}
			}

		}
		return plantPartIndivName;
	}

	public String getIllnessIndivName(String illness) throws SQWRLException {
		RDFProperty datatypeProperty_Illness = owlModel.getRDFProperty("datatypeProperty_Illness");
		String illIndivName = null;

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Illness")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {

					OWLIndividual individual = (OWLIndividual) jt.next();
					try {
						if (illness.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Illness).toString().toLowerCase())) {
							illIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
						if (illness.equalsIgnoreCase(individual.getBrowserText().replaceAll("_", " "))) {
							illIndivName = individual.getBrowserText();
						}
//						System.out.println("Exception here");
					}
				}
			}

		}
		return illIndivName;
	}

	public String getSpeciesPartIndivName(String speciesPart) throws SQWRLException {
		String SpeciesPartIndivName = null;

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("SpeciesPart")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					OWLIndividual individual = (OWLIndividual) jt.next();
					if (speciesPart.equalsIgnoreCase(individual.getBrowserText())) {
						SpeciesPartIndivName = individual.getBrowserText();
					}
				}
			}

		}
		return SpeciesPartIndivName;
	}

	public String getCompoundIndivName(String compound) throws SQWRLException {
		RDFProperty datatypeProperty_Compound = owlModel.getRDFProperty("datatypeProperty_Compound");
		String compoundIndivName = null;

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("Compound")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {

					OWLIndividual individual = (OWLIndividual) jt.next();
					try {
						if (compound.equalsIgnoreCase(
								individual.getPropertyValue(datatypeProperty_Compound).toString().toLowerCase())) {
							compoundIndivName = individual.getBrowserText();
						}
					} catch (Exception e) {
						if (compound.equalsIgnoreCase(individual.getBrowserText().replaceAll("_", " "))) {
							compoundIndivName = individual.getBrowserText();
						}
//						System.out.println("Exception here");
					}
				}
			}

		}
		return compoundIndivName;
	}

}
