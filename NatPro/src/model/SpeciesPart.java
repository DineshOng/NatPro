package model;

import java.util.ArrayList;

public class SpeciesPart {
	String plantPart;
	ArrayList<Compound> compounds;

	public SpeciesPart(String plantPart) {
		super();
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

}
