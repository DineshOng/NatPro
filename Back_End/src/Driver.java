import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.CoreMap;
import net.didion.jwnl.JWNLException;

public class Driver {

	public static void main(String[] args) throws NoSuchAlgorithmException, ClassCastException, ClassNotFoundException, IOException, JWNLException {
		// TODO Auto-generated method stub
		long startTime, endTime;
        startTime = System.nanoTime();
		
        //new EntityTagger("de.pdf");
		new EntityTagger("am.pdf");
		//new EntityTagger("ap.pdf");
		//new EntityTagger("hm.pdf");
        
		endTime = System.nanoTime();
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000000 + " s");
        
        
	}

}
