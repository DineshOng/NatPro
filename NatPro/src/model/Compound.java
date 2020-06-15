package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import service.OntoMngr;

public class Compound {
	
	private String compoundName;
	private HashSet<String> compoundSynonyms;
	
	private HashSet<BiologicalActivity> bioActs;
	private HashSet<CompoundClass> compoundClasses;
	
	private HashSet<String> compounds;
	
	private String pubCID;
	private String molForm;
	private String canSMILES;
	private String inchi;
	private String inchikey;
	private String iupac;
	
	private String molWeight;
	private String xlogp;
	private String mass;
	private String tpsa;
	private String complexity;
	
	private String charge;
	private String hBondDonor;
	private String hBondAcceptor;
	private String rotBondCount;
	
	private boolean isMixture;
	
	public Compound() {
		
	}
	
	public Compound(String compoundName) {
		this.compoundName = compoundName;
		
		compoundSynonyms = new HashSet<String>();
		bioActs = new HashSet<BiologicalActivity>();
		compoundClasses = new HashSet<CompoundClass>();
		
		if(compoundName.toLowerCase().contains("(i?)mixture")) {
			isMixture = true;
			compounds = new HashSet<String>();
		}
		
	}
	
	public HashSet<String> getCompoundsHashSet() {
		HashSet<String> set = new HashSet<String>();
		set.addAll(compounds);
		
		return set;
	}

	public boolean isMixture() {
		return isMixture;
	}

	public void setMixture(boolean isMixture) {
		this.isMixture = isMixture;
	}

	
	public String getCompoundNameHTML() {
		String str = compoundName.replaceAll("(?i)alpha", "&alpha;").replaceAll("(?i)beta", "&beta;").replaceAll("(?i)gamma", "&gamma;");
		if(str.toLowerCase().contains("mixture")) {
			str = str.replaceAll("\\.", ", ");
		} else {
			str = str.replaceAll("\\.", ",");
		}
		return str;
	}
	
	public String getMolFormHTML() {
		String str = molForm;
		
		try {
			
			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(str);
			
			while(matcher.find()) {
				str = str.replace(matcher.group(), "<sub>"+matcher.group()+"</sub>");
			}
		
		
			str = str.replaceAll("(<sub>)+", "<sub>").replaceAll("(</sub>)+", "</sub>");
		
		} catch(Exception e) {
			
		}
		
		return str;
	}

	public String getCompoundName() {
		return compoundName;
	}

	public void setCompoundName(String compoundName) {
		this.compoundName = compoundName;
	}

	public HashSet<String> getCompoundSynonyms() {
		return compoundSynonyms;
	}
	
	public String getAllCompoundSynonymsHTML() {
		return String.join("\n", compoundSynonyms).trim();
	}

	public void setCompoundSynonyms(HashSet<String> compoundSynonyms) {
		this.compoundSynonyms = compoundSynonyms;
	}

	public HashSet<BiologicalActivity> getBioActs() {
		return bioActs;
	}

	public void setBioActs(HashSet<BiologicalActivity> bioActs) {
		this.bioActs = bioActs;
	}

	public HashSet<CompoundClass> getCompoundClasses() {
		return compoundClasses;
	}

	public void setCompoundClasses(HashSet<CompoundClass> compoundClasses) {
		this.compoundClasses = compoundClasses;
	}

	public HashSet<String> getCompounds() {
		return compounds;
	}

	public void setCompounds(HashSet<String> compounds) {
		this.compounds = compounds;
	}

	public String getPubCID() {
		return pubCID;
	}

	public void setPubCID(String pubCID) {
		this.pubCID = pubCID;
	}

	public String getMolForm() {
		return molForm;
	}

	public void setMolForm(String molForm) {
		this.molForm = molForm;
	}

	public String getCanSMILES() {
		return canSMILES;
	}

	public void setCanSMILES(String canSMILES) {
		this.canSMILES = canSMILES;
	}

	public String getInchi() {
		return inchi;
	}

	public void setInchi(String inchi) {
		this.inchi = inchi;
	}

	public String getInchikey() {
		return inchikey;
	}

	public void setInchikey(String inchikey) {
		this.inchikey = inchikey;
	}

	public String getIupac() {
		return iupac;
	}

	public void setIupac(String iupac) {
		this.iupac = iupac;
	}

	public String getMolWeight() {
		return molWeight;
	}

	public void setMolWeight(String molWeight) {
		this.molWeight = molWeight;
	}

	public String getXlogp() {
		return xlogp;
	}

	public void setXlogp(String xlogp) {
		this.xlogp = xlogp;
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public String getTpsa() {
		return tpsa;
	}

	public void setTpsa(String tpsa) {
		this.tpsa = tpsa;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getHBondDonor() {
		return hBondDonor;
	}

	public void setHBondDonor(String hBondDonor) {
		this.hBondDonor = hBondDonor;
	}

	public String getHBondAcceptor() {
		return hBondAcceptor;
	}

	public void setHBondAcceptor(String hBondAcceptor) {
		this.hBondAcceptor = hBondAcceptor;
	}

	public String getRotBondCount() {
		return rotBondCount;
	}

	public void setRotBondCount(String rotBondCount) {
		this.rotBondCount = rotBondCount;
	}

	public String getCompoundOWL() {
		// TODO Auto-generated method stub
		return OntoMngr.cleanString(compoundName);
	}

	
}
