package service;

import java.util.HashMap;

import edu.stanford.smi.protege.exception.OntologyLoadException;

public class AdvancedMedPlantSearch extends AdvancedSearch {

	public AdvancedMedPlantSearch(HashMap<String, String> props)  throws OntologyLoadException {
		super(props);
	}
	
	public AdvancedMedPlantSearch search() {
		
		
		startSearch();
		
		return this;
	}

}
