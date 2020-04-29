package service;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class OntoMngr {

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
	private OWLClass medPlantClass, locationClass, speciesClass, genusClass, familyClass, speciesPartClass, plantPartClass, compoundClass, biologicalActivityClass, cellLineClass;

	// OWL Individual
	private OWLNamedIndividual medPlantIndiv, locationIndiv, speciesIndiv, genusIndiv, familyIndiv, speciesPartIndiv, plantPartIndiv, compoundIndiv, biologicalActivityIndiv, cellLineIndiv;

	// OWLObjectProperty
	OWLObjectProperty isLocatedIn, hasScientificName, belongsToGenus, belongsToFamily, hasChildPlantPart, hasPlantPart, hasCompound, hasBiologicalActivity, affects;

	public OntoMngr() throws OWLOntologyCreationException, OWLOntologyStorageException {
		// loadOntology();
		owlManager = OWLManager.createOWLOntologyManager();
		owlFile = new File("C:\\Users\\eduar\\Desktop\\OntoNatPro2.owl"); // user defined

		// load the ontology
		owlOntology = owlManager.loadOntologyFromOntologyDocument(owlFile);
		pm = new DefaultPrefixManager(base);
		owlIRI = owlManager.getOntologyDocumentIRI(owlOntology);
		owlFact = owlManager.getOWLDataFactory();
		datatypeString = owlFact.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
	}

	// ADD INDIVIDUALS
	public void addIndiv_MedPlant(String MedicinalPlant) throws OWLOntologyStorageException {
		// Creating Individual
		medPlantClass = owlFact.getOWLClass("#MedicinalPlant", pm);
		medPlantIndiv = owlFact.getOWLNamedIndividual("#" + MedicinalPlant, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(medPlantClass, medPlantIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Location(String location) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		locationClass = owlFact.getOWLClass("#Location", pm);
		locationIndiv = owlFact.getOWLNamedIndividual("#" + location, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(locationClass, locationIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Species(String species) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		speciesClass = owlFact.getOWLClass("#Species", pm);
		speciesIndiv = owlFact.getOWLNamedIndividual("#" + species, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(speciesClass, speciesIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Genus(String genus) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		genusClass = owlFact.getOWLClass("#Genus", pm);
		genusIndiv = owlFact.getOWLNamedIndividual("#" + genus, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(genusClass, genusIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_Family(String family) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		familyClass = owlFact.getOWLClass("#Family", pm);
		familyIndiv = owlFact.getOWLNamedIndividual("#" + family, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(familyClass, familyIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	public void addIndiv_SpeciesPart(String speciesPart) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		speciesPartClass = owlFact.getOWLClass("#SpeciesPart", pm);
		speciesPartIndiv = owlFact.getOWLNamedIndividual("#" + speciesPart, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(speciesPartClass, speciesPartIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	public void addIndiv_PlantPart(String plantPart) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		plantPartClass = owlFact.getOWLClass("#PlantPart", pm);
		plantPartIndiv = owlFact.getOWLNamedIndividual("#" + plantPart, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(plantPartClass, plantPartIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	public void addIndiv_Compound(String compound) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		compoundClass = owlFact.getOWLClass("#Compound", pm);
		compoundIndiv = owlFact.getOWLNamedIndividual("#" + compound, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(compoundClass, compoundIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addIndiv_BiologicalActivity(String bioAct) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		biologicalActivityClass = owlFact.getOWLClass("#BiologicalActivity", pm);
		biologicalActivityIndiv = owlFact.getOWLNamedIndividual("#" + bioAct, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(biologicalActivityClass, biologicalActivityIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	public void addIndiv_CellLine(String cellLine) throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		cellLineClass = owlFact.getOWLClass("#CellLine", pm);
		cellLineIndiv = owlFact.getOWLNamedIndividual("#" + cellLine, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(cellLineClass, cellLineIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	// ADD DATATYPE PROPERTY
	public void addDataPropMedPlant(String medPlantValue) throws OWLOntologyStorageException {
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_MedicinalPlant", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, medPlantIndiv, medPlantValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, medPlantClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addDataPropLocation(String locationValue) throws OWLOntologyStorageException {
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Location", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, locationIndiv, locationValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, locationClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addDataPropSpecies(String speciesValue) throws OWLOntologyStorageException {
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Synoynm", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, speciesIndiv, speciesValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, speciesClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addDataPropGenus(String genusValue) throws OWLOntologyStorageException {
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Genus", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, genusIndiv, genusValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, genusClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	public void addDataPropFamily(String familyValue) throws OWLOntologyStorageException {
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_Family", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, familyIndiv, familyValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, familyClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	public void addDataPropPlantPart(String plantPartValue) throws OWLOntologyStorageException {
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_PlantPart", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, plantPartIndiv, plantPartValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, plantPartClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	

	public void addDataPropCompound(String compoundValue) throws OWLOntologyStorageException {
		// locationValue = locationValue.replaceAll("\\n+", " ").replaceAll("\\s+", "
		// ").toLowerCase();
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_CompoundSynonym", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, compoundIndiv, compoundValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, compoundClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	public void addDataPropBiologicalActivity(String bioActValue) throws OWLOntologyStorageException {
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_BiologicalActivity", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, biologicalActivityIndiv, bioActValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, biologicalActivityClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}
	
	public void addDataPropCellLine(String cellLineValue) throws OWLOntologyStorageException {
		// Creating Data Property, Range, and Value
		dataProp = owlFact.getOWLDataProperty("#datatypeProperty_CellLine", pm);
		rangeAxiom = owlFact.getOWLDataPropertyRangeAxiom(dataProp, datatypeString);
		owlManager.addAxiom(owlOntology, rangeAxiom);
		dataPropAssertion = owlFact.getOWLDataPropertyAssertionAxiom(dataProp, cellLineIndiv, cellLineValue);
		axiomDataProp = owlFact.getOWLDataPropertyDomainAxiom(dataProp, cellLineClass);
		owlManager.addAxiom(owlOntology, axiomDataProp);

		owlManager.addAxiom(owlOntology, dataPropAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
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
		objectAssertion = owlFact.getOWLObjectPropertyAssertionAxiom(hasBiologicalActivity, compoundIndiv, biologicalActivityIndiv);
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
	
	
}
