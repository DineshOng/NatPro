package preprocessing;
import java.util.HashSet;
import java.util.List;

public class Compound {
	private String compoundName;
	private HashSet<BioAct> bioActs;
	private String pubCID;
	private String molForm;
	private String molWeight;
	private String canSMILES;
	private String inchi;
	private String inchikey;
	private String iupac;
	private String xlogp;
	private String mass;
	private String tpsa;
	private String complexity;
	private String charge;
	private String hBondDonor;
	private String hBondAcceptor;
	private String rotBondCount;
	private List<String> synonyms;
	private String imgSVG;
	
	public Compound(String compoundName) {
		this.compoundName = compoundName;
	}

	public String getCompoundName() {
		return compoundName;
	}

	public void setCompoundName(String compoundName) {
		this.compoundName = compoundName;
	}

	public HashSet<BioAct> getBioActs() {
		return bioActs;
	}

	public void setBioActs(HashSet<BioAct> bioActs) {
		this.bioActs = bioActs;
	}
	
}
