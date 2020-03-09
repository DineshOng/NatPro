package model;

import java.util.ArrayList;

public class Species {
	String specie;
	ArrayList<SpeciesPart> speciesParts;

	public Species(String specie) {
		super();
		this.specie = specie;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public ArrayList<SpeciesPart> getSpeciesParts() {
		return speciesParts;
	}

	public void setSpeciesParts(ArrayList<SpeciesPart> speciesParts) {
		this.speciesParts = speciesParts;
	}
}
