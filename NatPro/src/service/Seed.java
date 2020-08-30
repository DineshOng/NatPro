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
//		
//		genMPWithLoc(q);
//		genMPWithGenus(q);
//		genMPWithFamily(q);
//		genMPWithPart(q);
//		genMPWithComp(q);
//		
//		genSynWithLoc(q);
//		genSynWithGenus(q);
//		genSynWithFamily(q);
//		genSynWithPart(q);
//		genSynWithComp(q);
//
//		genGenusWithFamily(q);	
//		
		genPrepWithIllness(q);
		genPrepWithPlantPart(q);

		genPartWithIllness(q);
	}

//	LEGEND:
//	aka - Synonym/Species (Scientific Name)
//	plant - MedicinalPlant (Common Name)
//	genus - Genus
//	family - Family
//	loc - Location
//	plantpart - PlantPart
//	prep - Preparation
//	illness - Illness
//	compound - Compound
//	bioact - BiologicalActivity
//	cell - CellLine
//	bodypart - BodyPart

	public void genMPWithSyn(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Synonyms = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/aka-plant.txt");
			writer.print("");
			writer.print("e1:aka\r\n");
			writer.print("e2:plant\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/aka-loc.txt");
			writer.print("");
			writer.print("e1:aka\r\n");
			writer.print("e2:loc\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/aka-genus.txt");
			writer.print("");
			writer.print("e1:aka\r\n");
			writer.print("e2:genus\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/aka-family.txt");
			writer.print("");
			writer.print("e1:aka\r\n");
			writer.print("e2:family\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/aka-plantpart.txt");
			writer.print("");
			writer.print("e1:aka\r\n");
			writer.print("e2:plantpart\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/aka-compound.txt");
			writer.print("");
			writer.print("e1:aka\r\n");
			writer.print("e2:compound\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				CompoundsList = q.getCompounds(MedPlantNames.get(i));
				for (int j = 0; j < CompoundsList.size(); j++) {
					writer.write(MedPlantNames.get(i) + ";" + CompoundsList.get(j));
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
			PrintWriter writer = new PrintWriter("./seeds/plant-loc.txt");
			writer.print("");
			writer.print("e1:plant\r\n");
			writer.print("e2:loc\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/plant-genus.txt");
			writer.print("");
			writer.print("e1:plant\r\n");
			writer.print("e2:genus\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/plant-family.txt");
			writer.print("");
			writer.print("e1:plant\r\n");
			writer.print("e2:family\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/plant-plantpart.txt");
			writer.print("");
			writer.print("e1:plant\r\n");
			writer.print("e2:plantpart\r\n");
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
			PrintWriter writer = new PrintWriter("./seeds/plant-compound.txt");
			writer.print("");
			writer.print("e1:plant\r\n");
			writer.print("e2:compound\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Synonyms.size(); i++) {
				CompoundsList = q.getSynCompounds(Synonyms.get(i));
				for (int j = 0; j < CompoundsList.size(); j++) {
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
		List<String> Genus = q.getAllGenus();
		List<String> Family = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/genus-family.txt");
			writer.print("");
			writer.print("e1:genus\r\n");
			writer.print("e2:family\r\n");
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

	public void genPrepWithIllness(OntoQuery q) throws SQWRLException {
		List<String> Prep = q.getAllPreparations();
		List<String> Illness = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/prep-illness.txt");
			writer.print("");
			writer.print("e1:prep\r\n");
			writer.print("e2:illness\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Prep.size(); i++) {
				Illness = q.getPreparationIllness(Prep.get(i));
				for (int j = 0; j < Illness.size(); j++) {
					String prep = removePar(Prep.get(i)).replaceAll("\\.", "");
					String ill = removePar(Illness.get(j)).replaceAll("\\.", "");
					writer.write(prep + ";" + ill);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void genPrepWithPlantPart(OntoQuery q) throws SQWRLException {
		List<String> Prep = q.getAllPreparations();
		List<String> PlantPart = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/prep-plantpart.txt");
			writer.print("");
			writer.print("e1:prep\r\n");
			writer.print("e2:plantpart\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Prep.size(); i++) {
				PlantPart = q.getPreparationPlantPart(Prep.get(i));
				for (int j = 0; j < PlantPart.size(); j++) {
					String prep = removePar(Prep.get(i)).replaceAll("\\.", "");
					String part = removePar(PlantPart.get(j)).replaceAll("\\.", "");
					writer.write(prep + ";" + part);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void genPartWithIllness(OntoQuery q) throws SQWRLException {
		List<String> Prep = q.getAllPreparations();
		List<String> PlantPart = new ArrayList<String>();
		List<String> Illness = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./seeds/plantpart-illness.txt");
			writer.print("");
			writer.print("e1:plantpart\r\n");
			writer.print("e2:illness\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Prep.size(); i++) {
				PlantPart = q.getPreparationPlantPart(Prep.get(i));
				Illness = q.getPreparationIllness(Prep.get(i));
				for (int k = 0; k < Illness.size(); k++) {
					for (int j = 0; j < PlantPart.size(); j++) {
						String part = removePar(PlantPart.get(j)).replaceAll("\\.", "");
						String ill = removePar(Illness.get(k)).replaceAll("\\.", "");
						writer.write(part + ";" + ill);
						writer.write("\r\n"); // write new line
					}
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
