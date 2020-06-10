package model;

import java.util.ArrayList;

public class MedicinalPlant {
	public static String CLASS_MedPlant = "MedicinalPlant";
	
	String medicinalPlant;
	ArrayList<Species> species;
	ArrayList<String> locations;
	ArrayList<Preparation> preparations;

	public MedicinalPlant(String medicinalPlant) {
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

	public ArrayList<String> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<String> locations) {
		this.locations = locations;
	}

	public ArrayList<Preparation> getPreparations() {
		return preparations;
	}

	public void setPreparations(ArrayList<Preparation> preparations) {
		this.preparations = preparations;
	}
	
	

	

}
