import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

import model.MedicinalPlant;
import service.OntoQuery;

public class Driver {

	public static void main(String[] args) throws OntologyLoadException, SQWRLException {
		// TODO Auto-generated method stub
		OntoQuery q = new OntoQuery("C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\Ontology\\OntoNatPro.owl");
//		Seed s = new Seed();
//		s.generateSeed(q);
		List<MedicinalPlant> medPlants = q.searchMedicinalPlant("bat");

		for (int i = 0; i < medPlants.size(); i++) {
			for (int j = 0; j < medPlants.get(i).getSpecies().size(); j++) {
				System.out.print(
						medPlants.get(i).getMedicinalPlant() + " > " + medPlants.get(i).getSpecies().get(j).getSpecie());
				if (medPlants.get(i).getSpecies().get(j).getSpeciesParts() != null) {
					System.out.print(" > ");
					for (int k = 0; k < medPlants.get(i).getSpecies().get(j).getSpeciesParts().size(); k++) {
						System.out.print(medPlants.get(i).getSpecies().get(j).getSpeciesParts().get(k).getPlantPart()+" | ");
						for (int l = 0; l < medPlants.get(i).getSpecies().get(j).getSpeciesParts().get(k).getCompounds().size(); l++) {
							System.out.print(medPlants.get(i).getSpecies().get(j).getSpeciesParts().get(k).getCompounds().get(l).getCompound()+" | ");
						}
						System.out.println();
					}
					
				}
				System.out.println();
			}
		}
	}

}
