package model;

import java.util.ArrayList;

public class Compound {
	String compound;
	ArrayList<String> compoundSynonyms;
	ArrayList<BiologicalActivity> biologicalActivities;

	public Compound(String compound) {
		super();
		this.compound = compound;
	}

	public String getCompound() {
		return compound;
	}

	public void setCompound(String compound) {
		this.compound = compound;
	}

	public ArrayList<String> getCompoundSynonyms() {
		return compoundSynonyms;
	}

	public void setCompoundSynonyms(ArrayList<String> compoundSynonyms) {
		this.compoundSynonyms = compoundSynonyms;
	}

	public ArrayList<BiologicalActivity> getBiologicalActivities() {
		return biologicalActivities;
	}

	public void setBiologicalActivities(ArrayList<BiologicalActivity> biologicalActivities) {
		this.biologicalActivities = biologicalActivities;
	}
}
