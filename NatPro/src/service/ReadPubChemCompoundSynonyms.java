package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadPubChemCompoundSynonyms {
	private String compound;
	private URL url;
	private List<String> synonyms;
	
	public ReadPubChemCompoundSynonyms(String compound) throws IOException {
		this.compound = compound;
		
		long startTime, endTime;
	    startTime = System.nanoTime ();
		
		url = new URL("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" + compound + "/synonyms/txt");
		
		synonyms = new ArrayList<String>();
		
		 // read text returned by server
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
         
        String line;
        while ((line = in.readLine()) != null) {
        	synonyms.add(line.trim());
            System.out.println(line);
        }
        
        in.close();
        
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

	public List<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}
	
	

}
