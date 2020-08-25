package service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.MedicinalPlant;

public class Seed {

	public void generateSeed(OntoQuery q) throws OntologyLoadException, SQWRLException {
//		genMPWithSyn(q);
//		genMPWithLoc(q);
//		genSynWithLoc(q);
		genMPWithComp(q);
		genSynWithComp(q);
//		genMPWithPart(q);
//		genMPWithGenus(q);
//		genMPWithFamily(q);
//		genSynWithPart(q);
//		genSynWithGenus(q);
//		genGenusWithFamily(q);

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
					writer.write(medPlant + ";" + loc);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genMPWithGenus(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Genus = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/MedicinalPlant-Genus.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Genus\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				Genus = q.getMPGenus(MedPlantNames.get(i));
				for (int j = 0; j < Genus.size(); j++) {
					String medPlant = removePar(MedPlantNames.get(i));
					String genus = removePar(Genus.get(j));
					writer.write(medPlant + ";" + genus);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genMPWithFamily(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Family = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/MedicinalPlant-Family.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Family\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				Family = q.getMPFamily(MedPlantNames.get(i));
				for (int j = 0; j < Family.size(); j++) {
					String medPlant = removePar(MedPlantNames.get(i));
					String family = removePar(Family.get(j));
					writer.write(medPlant + ";" + family);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genMPWithPart(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> PlantParts = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/MedicinalPlant-PlantParts.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:PlantPart\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				PlantParts = q.getMPPlantParts(MedPlantNames.get(i));
				for (int j = 0; j < PlantParts.size(); j++) {
					String medPlant = removePar(MedPlantNames.get(i));
					String part = removePar(PlantParts.get(j));
					writer.write(medPlant + ";" + part);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genMPWithComp(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> CompoundsList = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/MedicinalPlant-Compound.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Compound\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				CompoundsList = q.getCompounds(MedPlantNames.get(i));
				for(int j = 0; j<CompoundsList.size(); j++) {
					writer.write(MedPlantNames.get(i) + ";" +CompoundsList.get(j));
					writer.write("\r\n"); // write new line
				}
				
			}
			writer.close();
		} catch (

		IOException e) {
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
						String loc = removePar(Locs.get(k));
						writer.write(synonym + ";" + loc);
						writer.write("\r\n"); // write new line
					}
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genSynWithGenus(OntoQuery q) throws SQWRLException {
		List<String> Synonyms = q.getAllSynonyms();
		List<String> Genus = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/Synonym-Genus.txt");
			writer.print("");
			writer.print("e1:Synonym\r\n");
			writer.print("e2:Genus\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Synonyms.size(); i++) {
				Genus = q.getSynonymGenus(Synonyms.get(i));
				for (int j = 0; j < Genus.size(); j++) {
					String synonym = removePar(Synonyms.get(i));
					String genus = removePar(Genus.get(j));
					writer.write(synonym + ";" + genus);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genSynWithFamily(OntoQuery q) throws SQWRLException {
		List<String> Synonyms = q.getAllSynonyms();
		List<String> Family = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/Synonym-Family.txt");
			writer.print("");
			writer.print("e1:Synonym\r\n");
			writer.print("e2:Family\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Synonyms.size(); i++) {
				Family = q.getSynonymFamily(Synonyms.get(i));
				for (int j = 0; j < Family.size(); j++) {
					String synonym = removePar(Synonyms.get(i));
					String family = removePar(Family.get(j));
					writer.write(synonym + ";" + family);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
		
	public void genSynWithPart(OntoQuery q) throws SQWRLException {
		List<String> Synonyms = q.getAllSynonyms();
		List<String> PlantParts = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/Synonym-PlantParts.txt");
			writer.print("");
			writer.print("e1:Synonym\r\n");
			writer.print("e2:PlantPart\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Synonyms.size(); i++) {
				PlantParts = q.getSynonymPlantParts(Synonyms.get(i));
				for (int j = 0; j < PlantParts.size(); j++) {
					String syn = removePar(Synonyms.get(i));
					String part = removePar(PlantParts.get(j));
					writer.write(syn + ";" + part);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void genSynWithComp(OntoQuery q) throws SQWRLException {
		List<String> Synonyms = q.getAllSynonyms();
		List<String> CompoundsList = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/Synonym-Compound.txt");
			writer.print("");
			writer.print("e1:Synonym\r\n");
			writer.print("e2:Compound\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Synonyms.size(); i++) {
				CompoundsList = q.getSynCompounds(Synonyms.get(i));
				for(int j = 0; j<CompoundsList.size(); j++) {
					String syn = removePar(Synonyms.get(i));
					String compound = removePar(CompoundsList.get(j));
					writer.write(syn + ";" + compound);
					writer.write("\r\n"); // write new line
				}
				
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void genGenusWithFamily(OntoQuery q) throws SQWRLException {
		List<String> Genus= q.getAllGenus();
		List<String> Family = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/Genus-Family.txt");
			writer.print("");
			writer.print("e1:Genus\r\n");
			writer.print("e2:Family\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Genus.size(); i++) {
				Family = q.getGenusFamily(Genus.get(i));
				for (int j = 0; j < Family.size(); j++) {
					String genus = removePar(Genus.get(i));
					String family = removePar(Family.get(j));
					writer.write(genus + ";" + family);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	
	public String removePar(String string) {
		String newString = string;
		newString = newString.replaceAll("\\(.+\\)", "");
		return newString;
	}
}
