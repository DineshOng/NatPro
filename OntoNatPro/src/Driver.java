import java.util.List;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

import model.MedicinalPlant;
import service.OntoMngr;
import service.OntoQuery;

public class Driver {

	public static void main(String[] args) throws OntologyLoadException, SQWRLException, OWLOntologyStorageException, OWLOntologyCreationException {
		// TODO Auto-generated method stub
//		OntoQuery q = new OntoQuery("C:\\Users\\eduar\\Desktop\\OntoNatPro2.owl");
//		System.out.println(q.getAllMedPlantNames());
		OntoMngr m = new OntoMngr();
		m.addIndiv_MedPlant("batinoob");
		m.addDataPropMedPlant("Batinoob");
		m.addIndividual_Location("pateros");
		m.addDataPropLocation("Pateros");
		m.addObjectIsLocatedIn("batinoob", "pateros");
//		Seed s = new Seed();
//		s.generateSeed(q);
//		List<MedicinalPlant> medPlants = q.searchMedicinalPlant("bat");
//
//		for (int i = 0; i < medPlants.size(); i++) {
//			for (int j = 0; j < medPlants.get(i).getSpecies().size(); j++) {
//				System.out.print(
//						medPlants.get(i).getMedicinalPlant() + " > " + medPlants.get(i).getSpecies().get(j).getSpecie());
//				if (medPlants.get(i).getSpecies().get(j).getSpeciesParts() != null) {
//					System.out.print(" > ");
//					for (int k = 0; k < medPlants.get(i).getSpecies().get(j).getSpeciesParts().size(); k++) {
//						System.out.print(medPlants.get(i).getSpecies().get(j).getSpeciesParts().get(k).getPlantPart()+" | ");
//						for (int l = 0; l < medPlants.get(i).getSpecies().get(j).getSpeciesParts().get(k).getCompounds().size(); l++) {
//							System.out.print(medPlants.get(i).getSpecies().get(j).getSpeciesParts().get(k).getCompounds().get(l).getCompound()+" | ");
//						}
//						System.out.println();
//					}
//					
//				}
//				System.out.println();
//			}
//		}
		
	}

}
