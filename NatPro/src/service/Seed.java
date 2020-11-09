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
//		genMPWithPrep(q);
//		
//		genSynWithLoc(q);
//		genSynWithGenus(q);
//		genSynWithFamily(q);
//		genSynWithPart(q);
//		genSynWithComp(q);
//
//		genGenusWithFamily(q);	
//		
//		genPrepWithIllness(q);
//		genPrepWithPlantPart(q);
//
//		genPartWithIllness(q);
//		genPartWithCompound(q);
//
//		genCompWithBioAct(q);
//		genBioActWithCell(q);
//		genCompWithCellLine(q);

	}

//	LEGEND:
//	Synonym - Synonym/Species (Scientific Name)
//	MedicinalPlant - MedicinalPlant (Common Name)
//	Genus - Genus
//	Family - Family
//	Location - Location
//	PlantPart - PlantPart
//	Preparation - Preparation
//	Illness - Illness
//	Compound - Compound
//	BioAct - BiologicalActivity
//	CellLine - CellLine
//	BodyPart - BodyPart

	public void genMPWithSyn(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Synonyms = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/MedicinalPlant-Synonym.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Synonym\r\n");
			writer.write("\r\n"); // write new line
			
//			PrintWriter writer2 = new PrintWriter("./Documents/SeedsPossible/Synonym-MedicinalPlant.txt");
//			writer2.print("");
//			writer2.print("e1:Synonym\r\n");
//			writer2.print("e2:MedicinalPlant\r\n");
//			writer2.write("\r\n"); // write new line
			
			for (int i = 0; i < MedPlantNames.size(); i++) {
				Synonyms = q.getSynonyms(MedPlantNames.get(i));
				for (int j = 0; j < Synonyms.size(); j++) {
					String medPlant = removePar(MedPlantNames.get(i));
					String synonym = removePar(Synonyms.get(j));
					writer.write(medPlant + ";" + synonym);
					writer.write("\r\n"); // write new line
//					writer2.write(synonym + ";" + medPlant);
//					writer2.write("\r\n"); // write new line
				}
			}
			writer.close();
//			writer2.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void genMPWithLoc(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Locs = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/MedicinalPlant-Location.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Location\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				Locs = q.getLocations(MedPlantNames.get(i));
				for (int j = 0; j < Locs.size(); j++) {
					String medPlant = removePar(MedPlantNames.get(i));
					String Location = removePar(Locs.get(j));
					writer.write(medPlant + ";" + Location);
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/MedicinalPlant-Genus.txt");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/MedicinalPlant-Family.txt");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/MedicinalPlant-PlantPart.txt");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/MedicinalPlant-Compound.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Compound\r\n");
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

	public void genMPWithPrep(OntoQuery q) throws SQWRLException {
		List<String> MedPlantNames = q.getAllMedPlantNames();
		List<String> Prep = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/MedicinalPlant-Preparation.txt");
			writer.print("");
			writer.print("e1:MedicinalPlant\r\n");
			writer.print("e2:Preparation\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < MedPlantNames.size(); i++) {
				Prep = q.getMPPreparation(MedPlantNames.get(i));
				for (int j = 0; j < Prep.size(); j++) {
					String MedicinalPlant = removePar(MedPlantNames.get(i));
					String prep = removePar(Prep.get(j)).replaceAll("\\.", "");
					writer.write(MedicinalPlant + ";" + prep);
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Synonym-Location.txt");
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
						String Location = removePar(Locs.get(k));
						writer.write(synonym + ";" + Location);
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Synonym-Genus.txt");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Synonym-Family.txt");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Synonym-PlantPart.txt");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Synonym-Compound.txt");
			writer.print("");
			writer.print("e1:Synonym\r\n");
			writer.print("e2:Compound\r\n");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Genus-Family.txt");
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

	public void genPrepWithIllness(OntoQuery q) throws SQWRLException {
		List<String> Prep = q.getAllPreparations();
		List<String> Illness = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Preparation-Illness.txt");
			writer.print("");
			writer.print("e1:Preparation\r\n");
			writer.print("e2:Illness\r\n");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Preparation-PlantPart.txt");
			writer.print("");
			writer.print("e1:Preparation\r\n");
			writer.print("e2:PlantPart\r\n");
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
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/PlantPart-Illness.txt");
			writer.print("");
			writer.print("e1:PlantPart\r\n");
			writer.print("e2:Illness\r\n");
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

	public void genPartWithCompound(OntoQuery q) throws SQWRLException {
		List<String> PlantPart = q.getAllPlantParts();
		List<String> Compound = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/PlantPart-Compound.txt");
			writer.print("");
			writer.print("e1:PlantPart\r\n");
			writer.print("e2:Compound\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < PlantPart.size(); i++) {
				Compound = q.getPlantPartCompound(PlantPart.get(i));
				for (int j = 0; j < Compound.size(); j++) {
					String part = removePar(PlantPart.get(i));
					String comp = removePar(Compound.get(j));
					writer.write(part + ";" + comp);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genCompWithBioAct(OntoQuery q) throws SQWRLException {
		List<String> Compound = q.getAllCompounds();
		List<String> BioAct = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Compound-BioAct.txt");
			writer.print("");
			writer.print("e1:Compound\r\n");
			writer.print("e2:BioAct\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Compound.size(); i++) {
				BioAct = q.getCompoundBioAct(Compound.get(i));
				for (int j = 0; j < BioAct.size(); j++) {
					String comp = removePar(Compound.get(i));
					String bioact = removePar(BioAct.get(j));
					writer.write(comp + ";" + bioact);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genBioActWithCell(OntoQuery q) throws SQWRLException {
		List<String> BioAct = q.getAllBioAct();
		List<String> Cell = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/BioAct-CellLine.txt");
			writer.print("");
			writer.print("e1:BioAct\r\n");
			writer.print("e2:CellLine\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < BioAct.size(); i++) {
				Cell = q.getBioActCell(BioAct.get(i));
				for (int j = 0; j < Cell.size(); j++) {
					String bioact = removePar(BioAct.get(i));
					String cell = removePar(Cell.get(j));
					writer.write(bioact + ";" + cell);
					writer.write("\r\n"); // write new line
				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	
	public void genCompWithCellLine(OntoQuery q) throws SQWRLException {
		List<String> Compound = q.getAllCompounds();
		List<String> CellLine = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("./Documents/SeedsPossible/Compound-CellLine.txt");
			writer.print("");
			writer.print("e1:Compound\r\n");
			writer.print("e2:CellLine\r\n");
			writer.write("\r\n"); // write new line
			for (int i = 0; i < Compound.size(); i++) {
				CellLine = q.getCompoundCell(Compound.get(i));
				for (int j = 0; j < CellLine.size(); j++) {
					String comp = removePar(Compound.get(i));
					String cell = removePar(CellLine.get(j));
					writer.write(comp + ";" + cell);
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
