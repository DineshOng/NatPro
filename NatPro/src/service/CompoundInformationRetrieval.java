package service;

import java.io.IOException;
import java.util.List;

import model.Compound;

public class CompoundInformationRetrieval {
	private Compound compound;
	
	public static void main(String[] args) throws Exception {
		System.out.println(new CompoundInformationRetrieval("aspirin").isCompound());
	}
	
	public CompoundInformationRetrieval(String query) throws Exception {
		//compound = new ReadPubChemCompoundCSV(query).getCompound();
		
		compound = new Compound();
		
		List<String> synonyms = new ReadPubChemCompoundSynonyms(query).getCompoundSynonyms();
		
		if(synonyms!=null) {
			compound = new ReadPubChemCompoundCSV(query).getCompound();
			compound.setCompoundName(query);
			compound.setCompoundSynonyms(synonyms);
			//new GenerateCompoundSVG(compound.getCanSMILES());
		}
		
		//System.out.println(compound.getCompoundSynonyms().toString());
	}
	
	public boolean isCompound() {
		if(compound.getCompoundName()!=null) {
			return true;
		}
		return false;
	}

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}
}
