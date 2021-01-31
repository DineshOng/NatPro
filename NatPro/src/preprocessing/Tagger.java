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

public class Tagger {
	
	public Tagger(String filename, String uniqueID) throws IOException, NoSuchAlgorithmException, ClassCastException, ClassNotFoundException{
			//file paths
			String preprocessedDocumentsFolderPath = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Preprocessed\\";
			String taggedDocumentsFolderPath = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Tagged\\";
			String taggedBootstrapFolderPath = "C:\\\\Users\\\\Unknown\\\\eclipse-workspace-jee\\\\NatPro\\\\Documents\\\\TaggedBootstrap\\";
			
			String genusTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\genus.txt";
			String bioActTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\bioact.txt";
			String familyTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\family.txt";
			String orgPartTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\orgpart.txt";
			String clTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\cell-lines.txt";
			String compoundClassTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\compound-class.txt";
			String bodyPartTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\bodypart.txt";
			String prepTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\prep.txt";
			String illnessTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\illness.txt";
			String compoundSuffixTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\compound-suffix.txt";
			
			String twentyKTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\20k.txt";
			String googleTenKTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\google-10k.txt";
			
			String englishTaggerFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\english-left3words-distsim.tagger";
			String serializedClassifier = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\classifiers\\english.all.3class.distsim.crf.ser.gz";
			
//			String preprocessedDocumentsFolderPath = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\Preprocessed\\";
//			String taggedDocumentsFolderPath = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\Tagged\\";
//			String taggedBootstrapFolderPath = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\TaggedBootstrap\\";
//			
//			String genusTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\genus.txt";
//			String bioActTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\bioact.txt";
//			String familyTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\family.txt";
//			String orgPartTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\orgpart.txt";
//			String clTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\cell-lines.txt";
//			String compoundClassTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\compound-class.txt";
//			String bodyPartTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\bodypart.txt";
//			String prepTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\prep.txt";
//			String illnessTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\illness.txt";
//			String compoundSuffixTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\compound-suffix.txt";
//			
//			String twentyKTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\20k.txt";
//			String googleTenKTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\google-10k.txt";
//			
//			String englishTaggerFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\english-left3words-distsim.tagger";
//			String serializedClassifier = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\classifiers\\english.all.3class.distsim.crf.ser.gz";
			
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
	        
            txt = new SpeciesTagger("Synonym", txt, genusTxtFile, googleTenKTxtFile).run();
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
