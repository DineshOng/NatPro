package servlet;

import java.io.IOException;
import java.util.HashSet;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.Compound;
import service.OntoMngr;
import service.OntoQuery;

public class asdasdad {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		try {
			OntoMngr ontoMngr = new OntoMngr();
			OntoQuery ontoQry = new OntoQuery();
			
			String oldCompoundName = "Alstonerine";
			
			//ontoMngr.addIndiv_Compound(Compound.toOWLString(oldCompoundName));
			
			Compound compound = ontoQry.getCompound(oldCompoundName);
			
			String newVal = "69.42";
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), Compound.toOWLIndivString(oldCompoundName), Compound.DP_MolWeight, compound.getMolWeight()+"", newVal);
			
			newVal = "NIX69";
			ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), Compound.toOWLIndivString(oldCompoundName), Compound.DP_MolForm, compound.getMolForm()+"", newVal);
			
			String newCompoundName = "xiongdi";
		
			
			
			
			if(!oldCompoundName.equals(newCompoundName) && !ontoQry.getAllCompoundNames().contains(newCompoundName)) {
				ontoMngr.changeDataProperty(ontoMngr.getCompoundClass(), Compound.toOWLIndivString(oldCompoundName), Compound.DP_Compound, compound.getCompoundName()+"", newCompoundName);
				ontoMngr.changeNameIndividual(oldCompoundName, newCompoundName);
				System.out.println("hi");
			}
			
		} catch (OWLOntologyCreationException | OWLOntologyStorageException | OntologyLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
