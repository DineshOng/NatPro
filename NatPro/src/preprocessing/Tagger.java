package preprocessing;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.math.util.OpenIntToDoubleHashMap.Iterator;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.Triple;
import service.NatProDirectory;

public class Tagger {
	
	public Tagger(String filename, String uniqueID) throws IOException, NoSuchAlgorithmException, ClassCastException, ClassNotFoundException{
			//file paths
			String preprocessedDocumentsFolderPath =  new NatProDirectory().getProps().get("dir.pre.Preprocessed");
			String taggedDocumentsFolderPath =  new NatProDirectory().getProps().get("dir.pre.Tagged");
			String taggedBootstrapFolderPath =  new NatProDirectory().getProps().get("dir.boot.TaggedBootstrap");
			
			String genusTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\genus.txt";
			String bioActTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\bioact.txt";
			String familyTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\family.txt";
			String orgPartTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\orgpart.txt";
			String clTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\cell-lines.txt";
			String compoundClassTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\compound-class.txt";
			String bodyPartTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\bodypart.txt";
			String prepTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\prep.txt";
			String illnessTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\illness.txt";
			String compoundSuffixTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\compound-suffix.txt";
			
			String twentyKTxtFile = new NatProDirectory().getProps().get("dir.resources")+"\\google-20k.txt";
			
			String englishTaggerFile = new NatProDirectory().getProps().get("dir.resources")+"\\english-left3words-distsim.tagger";
			String serializedClassifier = new NatProDirectory().getProps().get("dir.resources")+"\\english.all.3class.distsim.crf.ser.gz";
			
        	String text = new PDFtoTXT(filename).getConvertedText();
            String cleanTxt = new TextCleaner(text).cleanText().getText();
            String txt = new SentenceSplitter(cleanTxt).getSentenceSplitText();
            
            java.io.FileWriter fw = new java.io.FileWriter(preprocessedDocumentsFolderPath + uniqueID + " txt.txt");
	        fw.write(text);
	        fw.close();
	        
	        java.io.FileWriter fw1 = new java.io.FileWriter(preprocessedDocumentsFolderPath + uniqueID + " clean.txt");
	        fw1.write(cleanTxt);
	        fw1.close();
	        
	        java.io.FileWriter fw2 = new java.io.FileWriter(preprocessedDocumentsFolderPath + uniqueID + " ssplit.txt");
	        fw2.write(txt);
	        fw2.close();
           
	        //Note:
            //Name of tag should not be a substring of other tag
	        
	        //MedicinalPlant : PlantPart => OK
	        //Compound: CompoundClass => NOT OK
	        
            txt = new SpeciesTagger("Synonym", txt, genusTxtFile, twentyKTxtFile).run();
            txt = new SpeciesNameResolution("Synonym", txt).run();
            
            txt = new CommonNameTagger("MedicinalPlant", txt, englishTaggerFile).run();
            
            txt = new LocationTagger("Location", txt, serializedClassifier).run();
            txt = new BioActivityTagger("BioAct", txt, bioActTxtFile).run();
            txt = new SpeciesFamilyTagger("Family", txt, familyTxtFile).run();
            txt = new CellLineTagger("CellLine", txt, clTxtFile).run();
            txt = new CompoundClassTagger("ChemicalClass", txt, compoundClassTxtFile).run();
            txt = new PreparationTagger("Preparation", txt, prepTxtFile).run();
            txt = new IllnessTagger("Illness", txt, illnessTxtFile).run();
            txt = new BodyPartTagger("BodyPart", txt, bodyPartTxtFile).run();
            txt = new OrgPartTagger("PlantPart", txt, orgPartTxtFile).run();
            
            txt = new CompoundTagger("Compound", txt, compoundSuffixTxtFile, twentyKTxtFile).run();
            txt = new CompoundNameExpander("Compound", txt).run();
            txt = new CompoundNameResolution("Compound", txt).run();
            
            txt = new Coref(txt).run();
            
//            Pattern pattern = Pattern.compile("(<Compound>[^<]+)<Compound>([^<]+)<\\/Compound>([^<]+<\\/Compound>)");
//    		Matcher matcher = pattern.matcher(text);
//    		while(matcher.find()) {
//    			String temp = matcher.group().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
//    			temp = temp.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
//    	    	text = text.replaceAll(temp, matcher.group(1).trim() + compoundsByName.get(matcher.group(2)) + matcher.group(3).trim());
//            }
            
            String notag = txt.replaceAll("<\\/?\\w+>", "");
            
            java.io.FileWriter fw3 = new java.io.FileWriter(preprocessedDocumentsFolderPath + uniqueID + " notag.txt");
	        fw3.write(notag);
	        fw3.close();	
            
	        java.io.FileWriter fw4 = new java.io.FileWriter(taggedDocumentsFolderPath + uniqueID + ".xml");
	        fw4.write(txt);
	        fw4.close();
	        
	        java.io.FileWriter fw5 = new java.io.FileWriter(taggedBootstrapFolderPath + uniqueID + ".xml");
	        fw5.write(txt);
	        fw5.close();	
        
	}
}
