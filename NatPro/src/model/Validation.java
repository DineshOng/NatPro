package model;

import java.util.HashSet;

public class Validation {

	String pdfFileName;
	HashSet<MedicinalPlant> MedicinalPlants;
	HashSet<Species> Synonyms;
	HashSet<SpeciesPart> PlantParts;
	HashSet<Compound> Compounds;
	HashSet<BiologicalActivity> BioAct;
	HashSet<Preparation> Preparation;
	
	
	public Validation(String pdfFileName) {
		// TODO Auto-generated constructor stub
		this.MedicinalPlants = new HashSet<MedicinalPlant>();
		this.Synonyms = new HashSet<Species>();		
		this.PlantParts = new HashSet<SpeciesPart>();		
		this.Compounds = new HashSet<Compound>();
		this.BioAct = new HashSet<BiologicalActivity>();
		this.Preparation = new HashSet<Preparation>();
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
			
	public void addPreparation(Preparation preparation) {
		Preparation.add(preparation);
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

	public HashSet<Preparation> getPreparation() {
		return Preparation;
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
