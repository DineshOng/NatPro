package preprocessing;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math.util.OpenIntToDoubleHashMap.Iterator;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.Triple;

public class Tagger {
	
	public Tagger(String filename, String uniqueID) throws IOException, NoSuchAlgorithmException, ClassCastException, ClassNotFoundException{
		
        	String text = new PDFtoTXT(filename).convertedText();
            String cleanTxt = new TextCleaner(text).cleanText().getText();
            String txt = new SentenceSplitter(cleanTxt).getSentenceSplitText();
            
            java.io.FileWriter fw = new java.io.FileWriter("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Preprocessed\\" + uniqueID + " txt.txt");
	        fw.write(text);
	        fw.close();
	        
	        java.io.FileWriter fw1 = new java.io.FileWriter("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Preprocessed\\" + uniqueID + " clean.txt");
	        fw1.write(cleanTxt);
	        fw1.close();
	        
	        java.io.FileWriter fw2 = new java.io.FileWriter("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Preprocessed\\" + uniqueID + " ssplit.txt");
	        fw2.write(txt);
	        fw2.close();
           
            
            txt = new CommonNameTagger("aka", txt).run();
            
            txt = new SpeciesTagger("plant", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\genus.txt").run();
            txt = new SpeciesNameResolution(txt).run();
            
            txt = new LocationTagger("loc", txt).run();
            txt = new BioActivityTagger("bioact", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\bioact.txt").run();
            txt = new SpeciesFamilyTagger("family", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\family.txt").run();
            txt = new OrgPartTagger("plantpart", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\orgpart.txt").run();
            txt = new CellLineTagger("cell", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\cl.txt").run();
            txt = new CompoundClassTagger("class", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\compound-class.txt").run();
            txt = new BodyPartTagger("bodypart", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\bodypart.txt").run();
            txt = new PreparationTagger("prep", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\prep.txt").run();
            txt = new IllnessTagger("illness", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\illness.txt").run();
            
            txt = new CompoundTagger("compound", txt, "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\compound-suffix.txt", "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\20k.txt").run();
            txt = new CompoundNameExpander("compound", txt).run();
            txt = new CompoundNameResolution("compound", txt).run();
            
            txt = new Coref(txt).run();
            
            String notag = txt.replaceAll("<\\/?[a-z]+>", "");
            
            java.io.FileWriter fw3 = new java.io.FileWriter("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Preprocessed\\" + uniqueID + " notag.txt");
	        fw3.write(notag);
	        fw3.close();	
            
	        java.io.FileWriter fw4 = new java.io.FileWriter("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Tagged\\" + uniqueID + ".xml");
	        fw4.write(txt);
	        fw4.close();	
        
	}
}
