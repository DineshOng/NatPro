package service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLEntityRenamer;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class OntoMngr {
	public static String CLASS_MedPlant = "MedicinalPlant";
	public static String CLASS_BioAct = "BiologicalActivity";
	public static String CLASS_CellLine = "CellLine";
	public static String CLASS_Compound = "Compound";
	public static String CLASS_Family = "Family";
	public static String CLASS_Genus = "Genus";
	public static String CLASS_Species = "Species";
	public static String CLASS_SpeciesPart = "SpeciesPart";

	/* Compound Data properties */
	public static String DP_Compound = "datatypeProperty_Compound";
	public static String DP_Synonym = "datatypeProperty_CompoundSynonym";

	public static String DP_PubCID = "datatypeProperty_PubCID";
	public static String DP_MolForm = "datatypeProperty_MolForm";
	public static String DP_CanSMILES = "datatypeProperty_CanSMILES";
	public static String DP_InChI = "datatypeProperty_InChI";
	public static String DP_InChIkey = "datatypeProperty_InChIkey";
	public static String DP_IUPACName = "datatypeProperty_IUPACName";

	public static String DP_MolWeight = "datatypeProperty_MolWeight";
	public static String DP_XLogP = "datatypeProperty_XLogP";
	public static String DP_Mass = "datatypeProperty_Mass";
	public static String DP_TPSA = "datatypeProperty_TPSA";
	public static String DP_Complexity = "datatypeProperty_Complexity";

	public static String DP_Charge = "datatypeProperty_Charge";
	public static String DP_HBondDonor = "datatypeProperty_HBondDonorCount";
	public static String DP_HBondAcceptor = "datatypeProperty_HBondAcceptorCount";
	public static String DP_RotatableBond = "datatypeProperty_RotatableBondCount";

	/* Object Properties */
	public static String OP_hasBiologicalActivity = "hasBiologicalActivity";
	public static String OP_affects = "affects";

	private OWLOntology owlOntology;
	private OWLOntologyManager owlManager;
	private OWLDataFactory owlFact;
	private PrefixManager pm;
	private File owlFile;
	private String base = "http://www.owl-ontologies.com/PMPlants.owl";
	private IRI owlIRI;

	private OWLDatatype datatypeString;

	private OWLDataProperty dataProp;

	private OWLClassAssertionAxiom classAssertion;
	private OWLDataPropertyAssertionAxiom dataPropAssertion;
	private OWLObjectPropertyAssertionAxiom objectAssertion;

	private OWLDataPropertyRangeAxiom rangeAxiom;
	private OWLAxiom axiomDataProp;
	private AddAxiom axiomObjectProp;

	// OWL Classes
	private OWLClass medPlantClass, locationClass, speciesClass, genusClass, familyClass, speciesPartClass,
			plantPartClass, compoundClass, biologicalActivityClass, cellLineClass, preparationClass, illnessClass, documentClass;

	// OWL Individual
	private OWLNamedIndividual medPlantIndiv, locationIndiv, speciesIndiv, genusIndiv, familyIndiv, speciesPartIndiv,
			plantPartIndiv, compoundIndiv, biologicalActivityIndiv, cellLineIndiv, preparationIndiv, illnessIndiv, documentIndiv;

	// OWLObjectProperty
	OWLObjectProperty isLocatedIn, hasScientificName, belongsToGenus, belongsToFamily, hasChildPlantPart, hasPlantPart,
			hasCompound, hasBiologicalActivity, affects, hasPreparation, treats, utilizedPart, foundFromDocument;

	private String owlPath;

	public OntoMngr(String owlPath) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// loadOntology();
		owlManager = OWLManager.createOWLOntologyManager();

//		owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro2.1.owl";
//		owlFile = new File("C:\\Users\\eduar\\Desktop\\OntoNatPro2.1.owl"); // user defined

//		owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro.owl";
//		owlFile = new File("C:\\Users\\eduar\\Desktop\\OntoNatPro.owl"); // user defined

		
//		owlPath = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Ontology\\OntoNatPro.owl";
//		owlFile = new File("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Ontology\\OntoNatPro.owl");

		owlFile = new File("C:\\\\Users\\\\Unknown\\\\eclipse-workspace-jee\\\\NatPro\\\\Ontology\\\\OntoNatPro.owl");
		// load the ontology
		owlOntology = owlManager.loadOntologyFromOntologyDocument(owlFile);
		pm = new DefaultPrefixManager(base);
		owlIRI = owlManager.getOntologyDocumentIRI(owlOntology);
		owlFact = owlManager.getOWLDataFactory();
		datatypeString = owlFact.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());

		initializeClasses();

		// addIndiv_Compound("nixon");
		// addDataPropCompound("nixon");

		// changeDataProperty(Compound.DP_Compound, "hi", "nic");
		// removeDataPropertyValue(Compound.DP_Compound, "nixon");

	}

	public static String cleanString(String str) {

		str = str.toLowerCase();
		str = str.replaceAll("\\s+", "_");
		str = str.replaceAll(",+", ".");
		str = str.replaceAll("&#945;", "alpha");
		str = str.replaceAll("&#946;", "beta");
		str = str.replaceAll("&#947;", "gamma");
		str = str.trim();

		return str;
	}

	public void initializeClasses() {
		medPlantClass = owlFact.getOWLClass("#" + CLASS_MedPlant, pm);
		locationClass = owlFact.getOWLClass("#Location", pm);
		speciesClass = owlFact.getOWLClass("#" + CLASS_Species, pm);
		genusClass = owlFact.getOWLClass("#" + CLASS_Genus, pm);
		familyClass = owlFact.getOWLClass("#" + CLASS_Family, pm);
		speciesPartClass = owlFact.getOWLClass("#" + CLASS_SpeciesPart, pm);
		plantPartClass = owlFact.getOWLClass("#PlantPart", pm);
		compoundClass = owlFact.getOWLClass("#" + CLASS_Compound, pm);
		biologicalActivityClass = owlFact.getOWLClass("#" + CLASS_BioAct, pm);
		cellLineClass = owlFact.getOWLClass("#" + CLASS_CellLine, pm);
		preparationClass = owlFact.getOWLClass("#Preparation", pm);
		illnessClass = owlFact.getOWLClass("#Illness", pm);
	}

	public void changeNameIndividual(String oldVal, String newVal) throws IOException {
		Path path = Paths.get(owlPath);
		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(path), charset);

		/*
		 * str1 =
		 * "<datatypeProperty_Compound rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">"
		 * +oldVal+"</datatypeProperty_Compound>"; str2 =
		 * "<datatypeProperty_Compound rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">"
		 * +newVal+"</datatypeProperty_Compound>"; content = content.replaceAll(str1,
		 * str2);
		 * 
		 * str1 = "<datatypeProperty_Compound>"+oldVal+"</datatypeProperty_Compound>";
		 * str2 = "<datatypeProperty_Compound>"+newVal+"</datatypeProperty_Compound>";
		 * content = content.replaceAll(str1, str2);
		 */

		// Compound
		String str1 = "<hasCompound rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(oldVal)
				+ "\"/>";
		String str2 = "<hasCompound rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(newVal)
				+ "\"/>";
		content = content.replaceAll(str1, str2);

		// Genus
		str1 = "<belongsToGenus rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(oldVal)
				+ "\"/>";
		str2 = "<belongsToGenus rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(newVal)
				+ "\"/>";
		content = content.replaceAll(str1, str2);

		// Family
		str1 = "<belongsToFamily rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(oldVal)
				+ "\"/>";
		str2 = "<belongsToFamily rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(newVal)
				+ "\"/>";
		content = content.replaceAll(str1, str2);

		// Scientific Name
		str1 = "<hasScientificName rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(oldVal)
				+ "\"/>";
		str2 = "<hasScientificName rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(newVal)
				+ "\"/>";
		content = content.replaceAll(str1, str2);

		str1 = "<owl:NamedIndividual rdf:about=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(oldVal)
				+ "\">";
		str2 = "<owl:NamedIndividual rdf:about=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(newVal)
				+ "\">";
		content = content.replaceAll(str1, str2);

		Files.write(path, content.getBytes(charset));
	}

	public void changeSpeciePartNameIndividual(String oldVal, String newVal) throws IOException {
		Path path = Paths.get(owlPath);
		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(path), charset);

		// Specie Part Indiv Name
		String str1 = "<owl:NamedIndividual rdf:about=\"http://www.owl-ontologies.com/PMPlants.owl#"
				+ cleanString(oldVal) + "_";
		String str2 = "<owl:NamedIndividual rdf:about=\"http://www.owl-ontologies.com/PMPlants.owl#"
				+ cleanString(newVal) + "_";
		content = content.replaceAll(str1, str2);

		// Specie Part
		str1 = "<hasChildPlantPart rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(oldVal);
		str2 = "<hasChildPlantPart rdf:resource=\"http://www.owl-ontologies.com/PMPlants.owl#" + cleanString(newVal);
		content = content.replaceAll(str1, str2);

		Files.write(path, content.getBytes(charset));
	}

	public void removeObjectPropertyValue(String indivName, String objPropName, String oldVal)
			throws OWLOntologyStorageException {
		if (oldVal != null) {
			OWLIndividual indiv = owlFact.getOWLNamedIndividual("#" + indivName.trim(), pm);
			OWLIndividual oldIndiv = owlFact.getOWLNamedIndividual("#" + oldVal.trim(), pm);
			// OWLObjectProperty objProp = owlFact.getOWLObjectProperty("#" + objPropName,
			// pm);

			OWLObjectPropertyExpression objProp = owlFact.getOWLObjectProperty("#" + objPropName, pm);

			OWLObjectPropertyAssertionAxiom opa = owlFact.getOWLObjectPropertyAssertionAxiom(objProp, indiv, oldIndiv);
			RemoveAxiom r = new RemoveAxiom(owlOntology, opa);
			owlManager.applyChange(r);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void removeDataPropertyValue(String indivName, String datapropName, String oldVal)
			throws OWLOntologyStorageException {
		if (oldVal != null) {
			OWLIndividual indiv = owlFact.getOWLNamedIndividual("#" + indivName.trim(), pm);
			OWLDataProperty dataprop = owlFact.getOWLDataProperty("#" + datapropName, pm);

			OWLDataPropertyAssertionAxiom dpa = owlFact.getOWLDataPropertyAssertionAxiom(dataprop, indiv,
					oldVal.trim());
			RemoveAxiom r = new RemoveAxiom(owlOntology, dpa);
			owlManager.applyChange(r);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void changeDataProperty(OWLClass owlClass, String indivName, String datapropName, String oldVal,
			String newVal) throws OWLOntologyStorageException {
		if (oldVal != null) {
			OWLIndividual indiv = owlFact.getOWLNamedIndividual("#" + indivName.trim(), pm);
			OWLDataProperty dataprop = owlFact.getOWLDataProperty("#" + datapropName, pm);

			if (newVal.equals("") || newVal == null) {
				removeDataPropertyValue(indivName, datapropName, oldVal);
			} else {
				OWLDataPropertyAssertionAxiom dpa = owlFact.getOWLDataPropertyAssertionAxiom(dataprop, indiv,
						oldVal.trim());
				RemoveAxiom r = new RemoveAxiom(owlOntology, dpa);
				owlManager.applyChange(r);

				OWLDataPropertyRangeAxiom rangeAx = owlFact.getOWLDataPropertyRangeAxiom(dataprop, datatypeString);
				owlManager.addAxiom(owlOntology, rangeAx);

				dpa = owlFact.getOWLDataPropertyAssertionAxiom(dataprop, indiv, newVal.trim());
				OWLAxiom aDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataprop, owlClass);
				owlManager.addAxiom(owlOntology, aDataProp);

				owlManager.addAxiom(owlOntology, dpa);
				owlManager.saveOntology(owlOntology, IRI.create(owlFile));
			}
		}
	}

	public void deleteMedicinalPlant(String oldMedPlant) throws OWLOntologyStorageException {
		// Remove old plant
		medPlantClass = owlFact.getOWLClass("#MedicinalPlant", pm);
		medPlantIndiv = owlFact.getOWLNamedIndividual("#" + oldMedPlant, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(medPlantClass, medPlantIndiv);

		OWLEntityRemover remove = new OWLEntityRemover(owlManager, Collections.singleton(owlOntology));
		medPlantIndiv.accept(remove);
		owlManager.applyChanges(remove.getChanges());
		owlManager.saveOntology(owlOntology);
	}

	public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
//		OntoMngr m = new OntoMngr();
	}

	// ADD INDIVIDUALS
	public void addIndiv_MedPlant(String MedicinalPlant) throws OWLOntologyStorageException {
		// Creating Individual
		medPlantClass = owlFact.getOWLClass("#MedicinalPlant", pm);
		medPlantIndiv = owlFact.getOWLNamedIndividual("#" + MedicinalPlant.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(medPlantClass, medPlantIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Location(String location) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		locationClass = owlFact.getOWLClass("#Location", pm);
		locationIndiv = owlFact.getOWLNamedIndividual("#" + location.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(locationClass, locationIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Species(String species) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		speciesClass = owlFact.getOWLClass("#Species", pm);
		speciesIndiv = owlFact.getOWLNamedIndividual("#" + species.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(speciesClass, speciesIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Genus(String genus) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		genusClass = owlFact.getOWLClass("#Genus", pm);
		genusIndiv = owlFact.getOWLNamedIndividual("#" + genus.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(genusClass, genusIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Family(String family) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		familyClass = owlFact.getOWLClass("#Family", pm);
		familyIndiv = owlFact.getOWLNamedIndividual("#" + family.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(familyClass, familyIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_SpeciesPart(String speciesPart)
			throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		speciesPartClass = owlFact.getOWLClass("#SpeciesPart", pm);
		speciesPartIndiv = owlFact.getOWLNamedIndividual("#" + speciesPart.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(speciesPartClass, speciesPartIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_PlantPart(String plantPart) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		plantPartClass = owlFact.getOWLClass("#PlantPart", pm);
		plantPartIndiv = owlFact.getOWLNamedIndividual("#" + plantPart.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(plantPartClass, plantPartIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Compound(String compound) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		compoundClass = owlFact.getOWLClass("#Compound", pm);
		compoundIndiv = owlFact.getOWLNamedIndividual("#" + compound.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(compoundClass, compoundIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_BiologicalActivity(String bioAct)
			throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		biologicalActivityClass = owlFact.getOWLClass("#BiologicalActivity", pm);
		biologicalActivityIndiv = owlFact.getOWLNamedIndividual("#" + bioAct.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(biologicalActivityClass, biologicalActivityIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_CellLine(String cellLine) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		cellLineClass = owlFact.getOWLClass("#CellLine", pm);
		cellLineIndiv = owlFact.getOWLNamedIndividual("#" + cellLine.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(cellLineClass, cellLineIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Preparation(String preparation)
			throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		preparationClass = owlFact.getOWLClass("#Preparation", pm);
		preparationIndiv = owlFact.getOWLNamedIndividual("#" + preparation.trim(), pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(preparationClass, preparationIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Illness(String illness) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		illnessClass = owlFact.getOWLClass("#Illness", pm);
		illnessIndiv = owlFact.getOWLNamedIndividual("#" + illness, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(illnessClass, illnessIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Document(String document) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		documentClass = owlFact.getOWLClass("#Document", pm);
		documentIndiv = owlFact.getOWLNamedIndividual("#" + document, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(documentClass, documentIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	// ADD DATATYPE PROPERTY
	public void addDataPropMedPlant(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_MedicinalPlant", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, medPlantIndiv, Value);
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, medPlantClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropLocation(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Location", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, locationIndiv, Value);
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, locationClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropSpecies(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Synonym", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, speciesIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, speciesClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropGenus(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Genus", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, genusIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, genusClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropFamily(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Family", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, familyIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, familyClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropPlantPart(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_PlantPart", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, plantPartIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, plantPartClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_Compound, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_Synonym(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_Synonym, pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_PubCID(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_PubCID, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_MolForm(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_MolForm, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_MolWeight(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_MolWeight, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_CanSMILES(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_CanSMILES, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_InChI(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_InChI, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_InChIkey(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_InChIkey, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_IUPACName(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_IUPACName, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_XLogP(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_XLogP, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_Mass(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_Mass, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_TPSA(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_TPSA, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_Complexity(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_Complexity, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_Charge(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_Charge, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_HBondDonorCount(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_HBondDonor, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_HBondAcceptorCount(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_HBondAcceptor, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCompound_RotatableBondCount(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			dataProp = owlFact.getOWLDataProperty("#" + DP_RotatableBond, pm);

			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropBiologicalActivity(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_BiologicalActivity", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, biologicalActivityIndiv,
					Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, biologicalActivityClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropCellLine(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_CellLine", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, cellLineIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, cellLineClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropPreparation(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Preparation", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, preparationIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, preparationClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropIllness(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Illness", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, illnessIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, illnessClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	public void addDataPropDocument(String Value) throws OWLOntologyStorageException {
		if (!Value.equals("")) {
			// Creating Data Property, Range, and Value
			dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Document", pm);
			rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
			owlManager.addAxiom(owlOntology, rangeAxiom);
			dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, documentIndiv, Value.trim());
			axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, documentClass);
			owlManager.addAxiom(owlOntology, axiomDataProp);

			owlManager.addAxiom(owlOntology, dataPropAssertion);
			owlManager.saveOntology(owlOntology, IRI.create(owlFile));
		}
	}

	// ADD OBJECT PROPERTY
	public void addObjectIsLocatedIn(String medicPlant, String location) throws OWLOntologyStorageException {
		medPlantIndiv = owlFact.getOWLNamedIndividual("#" + medicPlant, pm);
		locationIndiv = owlFact.getOWLNamedIndividual("#" + location, pm);
		isLocatedIn = owlFact.getOWLObjectProperty("#isLocatedIn", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(isLocatedIn, medPlantIndiv, locationIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectHasScientificName(String medicPlant, String species) throws OWLOntologyStorageException {
		medPlantIndiv = owlFact.getOWLNamedIndividual("#" + medicPlant, pm);
		speciesIndiv = owlFact.getOWLNamedIndividual("#" + species, pm);
		hasScientificName = owlFact.getOWLObjectProperty("#hasScientificName", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(hasScientificName, medPlantIndiv, speciesIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectBelongsToGenus(String species, String genus) throws OWLOntologyStorageException {
		speciesIndiv = owlFact.getOWLNamedIndividual("#" + species, pm);
		genusIndiv = owlFact.getOWLNamedIndividual("#" + genus, pm);
		belongsToGenus = owlFact.getOWLObjectProperty("#belongsToGenus", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(belongsToGenus, speciesIndiv, genusIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectBelongsToFamily(String genus, String family) throws OWLOntologyStorageException {
		genusIndiv = owlFact.getOWLNamedIndividual("#" + genus, pm);
		familyIndiv = owlFact.getOWLNamedIndividual("#" + family, pm);
		belongsToFamily = owlFact.getOWLObjectProperty("#belongsToFamily", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(belongsToFamily, genusIndiv, familyIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectHasChildPlantPart(String species, String speciesPart) throws OWLOntologyStorageException {
		speciesIndiv = owlFact.getOWLNamedIndividual("#" + species, pm);
		speciesPartIndiv = owlFact.getOWLNamedIndividual("#" + speciesPart, pm);
		hasChildPlantPart = owlFact.getOWLObjectProperty("#hasChildPlantPart", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(hasChildPlantPart, speciesIndiv, speciesPartIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectHasPlantPart(String speciesPart, String plantPart) throws OWLOntologyStorageException {
		speciesPartIndiv = owlFact.getOWLNamedIndividual("#" + speciesPart, pm);
		plantPartIndiv = owlFact.getOWLNamedIndividual("#" + plantPart, pm);
		hasPlantPart = owlFact.getOWLObjectProperty("#hasPlantPart", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(hasPlantPart, speciesPartIndiv, plantPartIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectHasCompound(String speciesPart, String compound) throws OWLOntologyStorageException {
		speciesPartIndiv = owlFact.getOWLNamedIndividual("#" + speciesPart, pm);
		compoundIndiv = owlFact.getOWLNamedIndividual("#" + compound, pm);
		hasCompound = owlFact.getOWLObjectProperty("#hasCompound", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(hasCompound, speciesPartIndiv, compoundIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectHasBiologicalActivity(String compound, String bioAct) throws OWLOntologyStorageException {
		compoundIndiv = owlFact.getOWLNamedIndividual("#" + compound, pm);
		biologicalActivityIndiv = owlFact.getOWLNamedIndividual("#" + bioAct, pm);
		hasBiologicalActivity = owlFact.getOWLObjectProperty("#hasBiologicalActivity", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(hasBiologicalActivity, compoundIndiv,
				biologicalActivityIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectAffects(String bioAct, String cellLine) throws OWLOntologyStorageException {
		biologicalActivityIndiv = owlFact.getOWLNamedIndividual("#" + bioAct, pm);
		cellLineIndiv = owlFact.getOWLNamedIndividual("#" + cellLine, pm);
		affects = owlFact.getOWLObjectProperty("#affects", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(affects, biologicalActivityIndiv, cellLineIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectHasPreparation(String medicPlant, String preparation) throws OWLOntologyStorageException {
		medPlantIndiv = owlFact.getOWLNamedIndividual("#" + medicPlant, pm);
		preparationIndiv = owlFact.getOWLNamedIndividual("#" + preparation, pm);
		hasPreparation = owlFact.getOWLObjectProperty("#hasPreparation", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(hasPreparation, medPlantIndiv, preparationIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectTreats(String preparation, String illness) throws OWLOntologyStorageException {
		preparationIndiv = owlFact.getOWLNamedIndividual("#" + preparation, pm);
		illnessIndiv = owlFact.getOWLNamedIndividual("#" + illness, pm);
		treats = owlFact.getOWLObjectProperty("#treats", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(treats, preparationIndiv, illnessIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addObjectUtilizedPart(String preparation, String plantPart) throws OWLOntologyStorageException {
		preparationIndiv = owlFact.getOWLNamedIndividual("#" + preparation, pm);
		plantPartIndiv = owlFact.getOWLNamedIndividual("#" + plantPart, pm);
		utilizedPart = owlFact.getOWLObjectProperty("#utilizedPart", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(utilizedPart, preparationIndiv, plantPartIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	public void addObjectFoundFromDocument(String species, String document) throws OWLOntologyStorageException {
		speciesIndiv = owlFact.getOWLNamedIndividual("#" + species, pm);
		documentIndiv = owlFact.getOWLNamedIndividual("#" + document, pm);
		foundFromDocument = owlFact.getOWLObjectProperty("#foundFromDocument", pm);
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(foundFromDocument, speciesIndiv, documentIndiv);
		axiomObjectProp = new AddAxiom(owlOntology, objectAssertion);
		owlManager.applyChange(axiomObjectProp);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public OWLClass getMedPlantClass() {
		return medPlantClass;
	}

	public OWLClass getLocationClass() {
		return locationClass;
	}

	public OWLClass getSpeciesClass() {
		return speciesClass;
	}

	public OWLClass getGenusClass() {
		return genusClass;
	}

	public OWLClass getFamilyClass() {
		return familyClass;
	}

	public OWLClass getSpeciesPartClass() {
		return speciesPartClass;
	}

	public OWLClass getPlantPartClass() {
		return plantPartClass;
	}

	public OWLClass getCompoundClass() {
		return compoundClass;
	}

	public OWLClass getBiologicalActivityClass() {
		return biologicalActivityClass;
	}

	public OWLClass getCellLineClass() {
		return cellLineClass;
	}

	public OWLClass getPreparationClass() {
		return preparationClass;
	}

	public OWLClass getIllnessClass() {
		return illnessClass;
	}

	public void setMedPlantIndiv(String medPlantIndiv) {
		this.medPlantIndiv = owlFact.getOWLNamedIndividual("#" + medPlantIndiv, pm);
	}

	public void setLocationIndiv(String locationIndiv) {
		this.locationIndiv = owlFact.getOWLNamedIndividual("#" + locationIndiv, pm);
	}

	public void setSpeciesIndiv(String speciesIndiv) {
		this.speciesIndiv = owlFact.getOWLNamedIndividual("#" + speciesIndiv, pm);
	}

	public void setGenusIndiv(String genusIndiv) {
		this.genusIndiv = owlFact.getOWLNamedIndividual("#" + genusIndiv, pm);
	}

	public void setFamilyIndiv(String familyIndiv) {
		this.familyIndiv = owlFact.getOWLNamedIndividual("#" + familyIndiv, pm);
	}

	public void setSpeciesPartIndiv(String speciesPartIndiv) {
		this.speciesPartIndiv = owlFact.getOWLNamedIndividual("#" + speciesPartIndiv, pm);
	}

	public void setPlantPartIndiv(String plantPartIndiv) {
		this.plantPartIndiv = owlFact.getOWLNamedIndividual("#" + plantPartIndiv, pm);
	}

	public void setCompoundIndiv(String compoundIndiv) {
		this.compoundIndiv = owlFact.getOWLNamedIndividual("#" + compoundIndiv, pm);
	}

	public void setBiologicalActivityIndiv(String biologicalActivityIndiv) {
		this.biologicalActivityIndiv = owlFact.getOWLNamedIndividual("#" + biologicalActivityIndiv, pm);
	}

	public void setCellLineIndiv(String cellLineIndiv) {
		this.cellLineIndiv = owlFact.getOWLNamedIndividual("#" + cellLineIndiv, pm);
	}

	public void setPreparationIndiv(String preparationIndiv) {
		this.preparationIndiv = owlFact.getOWLNamedIndividual("#" + preparationIndiv, pm);
	}

	public void setIllnessIndiv(String illnessIndiv) {
		this.illnessIndiv = owlFact.getOWLNamedIndividual("#" + illnessIndiv, pm);
	}

}
