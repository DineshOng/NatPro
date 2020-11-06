package model;

import java.util.HashSet;

public class Validation {

	String pdfFileName;
	HashSet<MedicinalPlant> MedicinalPlants;
	HashSet<Species> Synonyms;
	HashSet<SpeciesPart> PlantParts;
	HashSet<Compound> Compounds;
	HashSet<BiologicalActivity> BioAct;
	HashSet<CellLine> CellLine;
	HashSet<String> Family;
	HashSet<String> Genus;
	HashSet<String> Location;
	HashSet<Preparation> Preparation;
	HashSet<String> Illness;
	
	
	public Validation(String pdfFileName) {
		// TODO Auto-generated constructor stub
		this.MedicinalPlants = new HashSet<MedicinalPlant>();
		this.Synonyms = new HashSet<Species>();		
		this.PlantParts = new HashSet<SpeciesPart>();		
		this.Compounds = new HashSet<Compound>();
		this.BioAct = new HashSet<BiologicalActivity>();
		this.CellLine = new HashSet<CellLine>();
		this.Family = new HashSet<String>();
		this.Genus = new HashSet<String>();
		this.Location = new HashSet<String>();
		this.Preparation = new HashSet<Preparation>();
		this.Illness = new HashSet<String>();
		this.pdfFileName = pdfFileName;
	}


	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFilePath) {
		this.pdfFileName = pdfFilePath;
	}
	
	public void addMedicinalPlants(MedicinalPlant medicinalPlant) {
		MedicinalPlants.add(medicinalPlant);
	}
	
	public void addPlantParts(SpeciesPart plantPart) {
		PlantParts.add(plantPart);
	}
	
	public void addCompounds(Compound compound) {
		Compounds.add(compound);
	}
	
	public void addSynonyms(Species synonym) {
		Synonyms.add(synonym);
	}
	
	public void addBioActs(BiologicalActivity bioact) {
		BioAct.add(bioact);
	}
	
	public void addCellLine(CellLine cellline) {
		CellLine.add(cellline);
	}
	
	public void addFamily(String family) {
		Family.add(family);
	}
	
	public void addGenus(String genus) {
		Genus.add(genus);
	}
	
	public void addPreparation(Preparation preparation) {
		Preparation.add(preparation);
	}

	public void addLocation(String location) {
		Location.add(location);
	}
	
	public HashSet<MedicinalPlant> getMedicinalPlants() {
		return MedicinalPlants;
	}

	public HashSet<Species> getSynonyms() {
		return Synonyms;
	}


	public HashSet<SpeciesPart> getPlantParts() {
		return PlantParts;
	}	
	
	public HashSet<Compound> getCompounds() {
		return Compounds;
	}

	public HashSet<BiologicalActivity> getBioAct() {
		return BioAct;
	}


	public HashSet<CellLine> getCellLine() {
		return CellLine;
	}


	public HashSet<String> getFamily() {
		return Family;
	}


	public HashSet<String> getGenus() {
		return Genus;
	}


	public HashSet<Preparation> getPreparation() {
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
