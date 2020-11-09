package model;

import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;

public class Species {
	
	String specie;
	ArrayList<SpeciesPart> speciesParts;
	String genus;
	String family;
	Genus genusObj;

	public Species(String specie) {
		this.specie = StringUtils.capitalize(specie);
		speciesParts = new ArrayList<SpeciesPart>();
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((specie == null) ? 0 : specie.hashCode());
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
		Species other = (Species) obj;
		if (specie == null) {
			if (other.specie != null)
				return false;
		} else if (!specie.equals(other.specie))
			return false;
		return true;
	}
}
