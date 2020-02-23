import java.util.HashSet;

public class Compound {
	private String compoundName;
	private HashSet<BioAct> bioActs;
	
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
