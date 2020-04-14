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
	private OWLClass medPlantClass, locationClass;

	// OWL Individual
	private OWLNamedIndividual medPlantIndiv, locationIndiv;

	// OWLObjectProperty
	OWLObjectProperty isLocatedIn;

	public OntoMngr() throws OWLOntologyCreationException, OWLOntologyStorageException {
		loadOntology();
	}

	public void loadOntology() throws OWLOntologyCreationException, OWLOntologyStorageException {
		owlManager = OWLManager.createOWLOntologyManager();
		owlFile = new File("C:\\Users\\eduar\\Desktop\\OntoNatPro2.owl"); // user defined

		// load the ontology
		owlOntology = owlManager.loadOntologyFromOntologyDocument(owlFile);
		pm = new DefaultPrefixManager(base);
		owlIRI = owlManager.getOntologyDocumentIRI(owlOntology);
		owlFact = owlManager.getOWLDataFactory();
		datatypeString = owlFact.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());

		// owlManager.saveOntology(owlOntology, new SystemOutDocumentTarget());
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

	public void addIndividual_Location(String location)
			throws OWLOntologyCreationException, OWLOntologyStorageException {
		// Creating Individual
		locationClass = owlFact.getOWLClass("#Location", pm);
		locationIndiv = owlFact.getOWLNamedIndividual("#" + location, pm);
		classAssertion = owlFact.getOWLClassAssertionAxiom(locationClass, locationIndiv);
		owlManager.addAxiom(owlOntology, classAssertion);
		owlManager.saveOntology(owlOntology, IRI.create(owlFile));
	}

	// ADD DATATYPE PROPERTY
	public void addDataPropMedPlant(String medPlantValue) throws OWLOntologyStorageException {
		//medPlantValue = medPlantValue.replaceAll("\\n+", " ").replaceAll("\\s+", " ").toLowerCase();
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
		//locationValue = locationValue.replaceAll("\\n+", " ").replaceAll("\\s+", " ").toLowerCase();
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
}
