package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReadPubChemCompoundCSV {
	private String compound;
	private URL url;
	
	public ReadPubChemCompoundCSV(String compound) throws IOException {
		this.compound = compound;
		
		long startTime, endTime;
	    startTime = System.nanoTime ();
	    
		url = new URL("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" + compound + "/property/MolecularFormula,MolecularWeight,CanonicalSMILES,InChI,InChIKey,IUPACName,XLogP,ExactMass,TPSA,Complexity,Charge,HBondDonorCount,HBondAcceptorCount,RotatableBondCount/CSV");
		
		String csv = "";
		
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        
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
        
        for(String s:split) {
            System.out.println(s);
        }
        
        endTime = System.nanoTime ();
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000000 + " s");
	}

	public String getCompound() {
		return compound;
	}

	public void setCompound(String compound) {
		this.compound = compound;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
}
