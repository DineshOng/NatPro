package service;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

public class Seed {

	public void generateSeed(OntoQuery q) throws OntologyLoadException, SQWRLException {		
		genMPWithSyn(q);
		genMPWithLoc(q);
		genSynWithLoc(q);
	}

	public void genMPWithSyn(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Synonyms = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/MedicinalPlant-Synonyms.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Synonym\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				Synonyms = q.getSynonyms(MedPlantNames.get(i));
				for (int j = 0; j < Synonyms.size(); j++) {
					String medPlant = removePar(MedPlantNames.get(i));
					String synonym = removePar(Synonyms.get(j));
						writer.write(medPlant + ";" + synonym);
						writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void genMPWithLoc(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Locs = new ArrayList<String>();
		List<String> Synonyms = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/MedicinalPlant-Locations.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Location\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				Locs = q.getLocations(MedPlantNames.get(i));
				for (int j = 0; j < Locs.size(); j++) {
					String medPlant = removePar(MedPlantNames.get(i));
					String loc = removePar(Locs.get(j));
						writer.write(removePar(MedPlantNames.get(i)) + ";" + removePar(Locs.get(j)));
						writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genSynWithLoc(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Locs = new ArrayList<String>();
		List<String> Synonyms = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/Synonym-Locations.txt");
			writer.print("");
			writer.print("e1:Synonym\r\n");
			writer.print("e2:Location\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				Locs = q.getLocations(MedPlantNames.get(i));
				Synonyms = q.getSynonyms(MedPlantNames.get(i));
				for (int j = 0; j < Synonyms.size(); j++) {
					for (int k = 0; k < Locs.size(); k++) {
						String synonym = removePar(Synonyms.get(j));
						String loc = removePar(Synonyms.get(j));
							writer.write(removePar(Synonyms.get(j)) + ";" + removePar(Locs.get(k)));
							writer.write("\r\n"); // write new line
					}
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String removePar(String string) {
		String newString = string;
		newString = newString.replaceAll("\\(.+\\)", "");
		return newString;
	}
}
