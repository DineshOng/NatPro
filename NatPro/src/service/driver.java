package service;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

public class driver {
	private static final String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro.owl";
	public static void main(String[] args) throws OntologyLoadException, SQWRLException {
		// TODO Auto-generated method stub
		OntoQuery q = new OntoQuery(owlPath);
		Seed s = new Seed();
		s.generateSeed(q);
//		System.out.println(q.searchMedicinalPlant("akapulko"));
	}

}
