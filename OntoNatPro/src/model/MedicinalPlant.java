package model;

import java.util.ArrayList;

public class MedicinalPlant {
	String medicinalPlant;
	ArrayList<Species> species;
	public MedicinalPlant(String medicinalPlant) {
		super();
		this.medicinalPlant = medicinalPlant;
	}
	
	public String getMedicinalPlant() {
		return medicinalPlant;
	}
	public void setMedicinalPlant(String medicinalPlant) {
		this.medicinalPlant = medicinalPlant;
	}

	public ArrayList<Species> getSpecies() {
		return species;
	}

	public void setSpecies(ArrayList<Species> species) {
		this.species = species;
	}
	

}
