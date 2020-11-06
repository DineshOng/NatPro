package model;

import java.util.ArrayList;

public class MedicinalPlant {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicinalPlant == null) ? 0 : medicinalPlant.hashCode());
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
		MedicinalPlant other = (MedicinalPlant) obj;
		if (medicinalPlant == null) {
			if (other.medicinalPlant != null)
				return false;
		} else if (!medicinalPlant.equals(other.medicinalPlant))
			return false;
		return true;
	}

}
