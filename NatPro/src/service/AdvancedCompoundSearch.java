package service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.BiologicalActivity;
import model.Compound;
import model.MedicinalPlant;
import model.Preparation;
import model.Species;
import model.SpeciesPart;

public class AdvancedCompoundSearch extends AdvancedSearch {
	
	public AdvancedCompoundSearch(HashMap<String, String> props) throws OntologyLoadException {
		super(props);
	}
	
	public AdvancedCompoundSearch search() {
		if(medPlantName == null && location == null && family == null && genus == null && species == null && plantPart == null && illness == null && bioAct == null && cellLine == null) {
			if(compoundName != null) {
				System.out.println("here");
				//System.println(q.searchCompound(compoundName).get(0).getCompoundName());
				compoundSearch(q.searchCompound(compoundName), true, null);
			} else {
				compoundSearch(q.searchCompound(""), true, null);
			}
			return this;
		}
		
		startSearch();
		
		return this;
	}
	
	
}
