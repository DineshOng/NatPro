package model;

import java.util.ArrayList;

public class Preparation {
	String preparation;
	String utilizedPlantPart;
	ArrayList<String> illness;
	
	public Preparation(String preparation) {
		super();
		this.preparation = preparation;
	}
	
	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public String getUtilizedPlantPart() {
		return utilizedPlantPart;
	}
	public void setUtilizedPlantPart(String utilizedPlantPart) {
		this.utilizedPlantPart = utilizedPlantPart;
	}
	public ArrayList<String> getIllness() {
		return illness;
	}
	public void setIllness(ArrayList<String> illness) {
		this.illness = illness;
	}
	
}
