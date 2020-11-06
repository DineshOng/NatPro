package model;

import java.util.ArrayList;

public class SpeciesPart {
	
	String plantPart;
	ArrayList<Compound> compounds;

	public SpeciesPart(String plantPart) {
		this.plantPart = plantPart;
	}

	public String getPlantPart() {
		return plantPart;
	}

	public void setPlantPart(String plantPart) {
		this.plantPart = plantPart;
	}

	public ArrayList<Compound> getCompounds() {
		return compounds;
	}

	public void setCompounds(ArrayList<Compound> compounds) {
		this.compounds = compounds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plantPart == null) ? 0 : plantPart.hashCode());
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
		SpeciesPart other = (SpeciesPart) obj;
		if (plantPart == null) {
			if (other.plantPart != null)
				return false;
		} else if (!plantPart.equals(other.plantPart))
			return false;
		return true;
	}
}
