package model;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

public class Species {
	
	String specie;
	ArrayList<SpeciesPart> speciesParts;
	String genus;
	String family;
	Genus genusObj;

	public Species(String specie) {
		this.specie = StringUtils.capitalize(specie);
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

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}
	
	public Genus getGenusObj() {
		return genusObj;
	}
	
	public void setGenus(Genus genusObj) {
		this.genusObj = genusObj;
	}
}
