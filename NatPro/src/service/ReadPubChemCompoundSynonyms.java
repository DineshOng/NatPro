package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadPubChemCompoundSynonyms {
	private URL url;
	private List<String> compoundSynonyms;
	
	public ReadPubChemCompoundSynonyms(String query) throws IOException {
		
		long startTime, endTime;
	    startTime = System.nanoTime ();
		
		url = new URL("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" + query + "/synonyms/txt");
		
		
		
		 // read text returned by server
        BufferedReader in;
        
        try {
        	in = new BufferedReader(new InputStreamReader(url.openStream()));
        	
        	compoundSynonyms = new ArrayList<String>();
        	
        	String line;
        	while ((line = in.readLine()) != null) {
        		compoundSynonyms.add(line.trim());
        		//System.out.println(line);
        		
        	}
             
            in.close();
        } catch (Exception e) {
        	
        }
        
        endTime = System.nanoTime ();
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000000 + " s");
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public List<String> getCompoundSynonyms() {
		return compoundSynonyms;
	}

	public void setCompoundSynonyms(List<String> compoundSynonyms) {
		this.compoundSynonyms = compoundSynonyms;
	}
}
