package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Compound {
	private String compoundName;
	private HashSet<BiologicalActivity> bioActs;
	private int pubCID;
	private String molForm;
	private double molWeight;
	private String canSMILES;
	private String inchi;
	private String inchikey;
	private String iupac;
	private double xlogp;
	private double mass;
	private double tpsa;
	private double complexity;
	private int charge;
	private int hBondDonor;
	private int hBondAcceptor;
	private int rotBondCount;
	private List<String> compoundSynonyms;
	private String imgSVG;
	
	public Compound() {
		compoundSynonyms = new ArrayList<String>();
	}
	
	public Compound(String compoundName) {
		this.compoundName = compoundName;
	}

	public String getCompoundName() {
		return compoundName;
	}

	public void setCompoundName(String compoundName) {
		this.compoundName = compoundName;
	}

	public HashSet<BiologicalActivity> getBioActs() {
		return bioActs;
	}

	public void setBioActs(HashSet<BiologicalActivity> bioActs) {
		this.bioActs = bioActs;
	}

	public int getPubCID() {
		return pubCID;
	}

	public void setPubCID(int pubCID) {
		this.pubCID = pubCID;
	}

	public String getMolForm() {
		return molForm;
	}

	public void setMolForm(String molForm) {
		this.molForm = molForm;
	}

	public double getMolWeight() {
		return molWeight;
	}

	public void setMolWeight(double molWeight) {
		this.molWeight = molWeight;
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

	public double getXlogp() {
		return xlogp;
	}

	public void setXlogp(double xlogp) {
		this.xlogp = xlogp;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getTpsa() {
		return tpsa;
	}

	public void setTpsa(double tpsa) {
		this.tpsa = tpsa;
	}

	public double getComplexity() {
		return complexity;
	}

	public void setComplexity(double complexity) {
		this.complexity = complexity;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public int gethBondDonor() {
		return hBondDonor;
	}

	public void sethBondDonor(int hBondDonor) {
		this.hBondDonor = hBondDonor;
	}

	public int gethBondAcceptor() {
		return hBondAcceptor;
	}

	public void sethBondAcceptor(int hBondAcceptor) {
		this.hBondAcceptor = hBondAcceptor;
	}

	public int getRotBondCount() {
		return rotBondCount;
	}

	public void setRotBondCount(int rotBondCount) {
		this.rotBondCount = rotBondCount;
	}

	public List<String> getCompoundSynonyms() {
		return compoundSynonyms;
	}

	public void setCompoundSynonyms(List<String> compoundSynonyms) {
		this.compoundSynonyms = compoundSynonyms;
	}

	public String getImgSVG() {
		return imgSVG;
	}

	public void setImgSVG(String imgSVG) {
		this.imgSVG = imgSVG;
	}
	
	
	
}
