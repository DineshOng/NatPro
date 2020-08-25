package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Compound;

public class ReadPubChemCompoundCSV {
	private URL url;
	private Compound compound;
	
	public static void main(String[] args) throws IOException {
		new ReadPubChemCompoundCSV("cocaine");
	}
	
	public ReadPubChemCompoundCSV(String query) throws IOException {
		long startTime, endTime;
	    startTime = System.nanoTime ();
	    
		url = new URL("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" + query + "/property/MolecularFormula,MolecularWeight,IsomericSMILES,InChI,InChIKey,IUPACName,XLogP,ExactMass,TPSA,Complexity,Charge,HBondDonorCount,HBondAcceptorCount,RotatableBondCount/CSV");
		
		String csv = "";
		
        BufferedReader in;
        
        try {
	        in = new BufferedReader(new InputStreamReader(url.openStream()));
	        
	        in.readLine();
	        csv = in.readLine();
	        /*while ((line = in.readLine()) != null) {
	        	//synonyms.add(line.trim());
	            System.out.println(line);
	            csv = line;
	        }*/
	        
	        in.close();
	        
	        String regex = ",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))";
	        Pattern p = Pattern.compile(regex);
	        String[] split = p.split(csv);
	        
	        /*
	        for(String s:split) {
	            System.out.println(s);
	        }
	        */
	        
	        compound = new Compound();
	        
	        compound.setPubCID(split[0]);
	        compound.setMolForm(split[1].replaceAll("\"", ""));
	        compound.setCanSMILES(split[3].replaceAll("\"", ""));
	        compound.setInchi(split[4].replaceAll("\"", ""));
	        compound.setInchikey(split[5].replaceAll("\"", ""));
	        compound.setIupac(split[6].replaceAll("\"", ""));
	        
	        compound.setMolWeight(split[2]);
	        compound.setXlogp(split[7]);
	        compound.setMass(split[8]);
	        compound.setTpsa(split[9]);
	        compound.setComplexity(split[10]);
	        
	        compound.setCharge(split[11]);
	        compound.setHBondDonor(split[12]);
	        compound.setHBondAcceptor(split[13]);
	        compound.setRotBondCount(split[14]);
	        
        } catch (Exception e) {
        	
        }
	        
        endTime = System.nanoTime ();
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000000 + " s");
	}

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
}
