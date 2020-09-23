package model;

import java.util.HashSet;

public class Validation {

	String pdfFileName;
	HashSet<MedicinalPlant> medPlants;
	HashSet<String> MedicinalPlants;
	HashSet<String> Synonyms;
	HashSet<String> PlantParts;
	HashSet<String> Compounds;
	HashSet<String> BioAct;
	HashSet<String> CellLine;
	HashSet<String> Family;
	HashSet<String> Genus;
	HashSet<String> Location;
	HashSet<String> Preparation;
	HashSet<String> Illness;
	
	
	public Validation(String pdfFileName) {
		// TODO Auto-generated constructor stub
		this.medPlants = new HashSet<MedicinalPlant>();
		this.MedicinalPlants = new HashSet<String>();
		this.PlantParts = new HashSet<String>();
		this.Synonyms = new HashSet<String>();
		this.Compounds = new HashSet<String>();
		this.BioAct = new HashSet<String>();
		this.CellLine = new HashSet<String>();
		this.Family = new HashSet<String>();
		this.Genus = new HashSet<String>();
		this.Location = new HashSet<String>();
		this.Preparation = new HashSet<String>();
		this.Illness = new HashSet<String>();
		this.pdfFileName = pdfFileName;
	}


	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFilePath) {
		this.pdfFileName = pdfFilePath;
	}
	
	public void addMedicinalPlants(String medicinalPlant) {
		MedicinalPlants.add(medicinalPlant);
	}
	
	public void addPlantParts(String plantPart) {
		PlantParts.add(plantPart);
	}
	
	public void addCompounds(String compound) {
		Compounds.add(compound);
	}
	
	public void addSynonyms(String synonym) {
		Synonyms.add(synonym);
	}
	
	public void addBioActs(String bioact) {
		BioAct.add(bioact);
	}
	
	public void addCellLine(String cellline) {
		CellLine.add(cellline);
	}
	
	public void addFamily(String family) {
		Family.add(family);
	}
	
	public void addGenus(String genus) {
		Genus.add(genus);
	}
	
	public void addPreparation(String preparation) {
		Preparation.add(preparation);
	}

	public void addLocation(String location) {
		Location.add(location);
	}
	
	public HashSet<String> getMedicinalPlants() {
		return MedicinalPlants;
	}

	public HashSet<String> getSynonyms() {
		return Synonyms;
	}


	public HashSet<String> getPlantParts() {
		return PlantParts;
	}	
	
	public HashSet<String> getCompounds() {
		return Compounds;
	}


	public HashSet<String> getBioAct() {
		return BioAct;
	}


	public HashSet<String> getCellLine() {
		return CellLine;
	}


	public HashSet<String> getFamily() {
		return Family;
	}


	public HashSet<String> getGenus() {
		return Genus;
	}


	public HashSet<String> getPreparation() {
		return Preparation;
	}


	public HashSet<String> getIllness() {
		return Illness;
	}
	
	public HashSet<String> getLocation() {
		return Location;
	}


	public void addIllness(String illness) {
		Illness.add(illness);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pdfFileName == null) ? 0 : pdfFileName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Validation other = (Validation) obj;
		if (pdfFileName == null) {
			if (other.pdfFileName != null)
				return false;
		} else if (!pdfFileName.equals(other.pdfFileName))
			return false;
		return true;
	}
	

}
