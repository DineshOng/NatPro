import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class EntityTagger {
	public static boolean checkDocument(String uniqueID, String filename) throws IOException {
    	File dir = new File("texts\\");
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(uniqueID) && name.endsWith(".pdf"));
        if(files.length == 0) {
        	new SaveFile(new File(filename), new File("texts\\"+uniqueID+".pdf"));
        	return true;
        }
        return false;
    }
	
	public EntityTagger(String filename) throws IOException, NoSuchAlgorithmException {
		String uniqueID = new GenUniqueDocID2(filename).getUniqueID();
        
        if(checkDocument(uniqueID, filename) || true) {
        	String text = new PDFtoTXT(filename).convertedText();
            String cleanTxt = new TextCleaner(text).cleanText().getText();
            String txt = new SentenceSplitter(cleanTxt).getSentenceSplitText();
		
            LookUpEntityTagger tagger;
            
            tagger = new LookUpEntityTagger()
        			.setText(txt)
        			.setTag_name("bioact")
        			.readLexiconFile("bioact.txt")
        			.sortLexiconFile()
        			.compilePatternsInsensitive()
        			.run();
            
            tagger.printEntityFrequencyCount();

            tagger = new LookUpEntityTagger()
            		.setText(tagger.getText())
            		.setTag_name("family")
            		.readLexiconFile("family.txt")
            		.compilePatternsInsensitive()
            		.run();
            
            tagger.printEntityFrequencyCount();
            
            tagger = new LookUpEntityTagger()
            		.setText(tagger.getText())
            		.setTag_name("species")
            		.readLexiconFile("genus.txt")
            		.setSuffix_regex("\\s(\\b[a-z]+)")
            		.compilePatterns()
            		.addPattern("[A-Z]\\.\\s([a-z])+")
            		.addPattern("\\s([A-Z]{2})\\s")
            		.hideTaggedEntities()
            		.findEntities();
            
            for (String i : tagger.getMap().keySet()) {
	            System.out.println("value: " + tagger.getMap().get(i) + "\tkey: " + i);
	            if(tagger.getMap().get(i)<5 && i.length()==2) {
	            	tagger.getFound_entities().remove(i);
	                System.out.println("Removed: "+ i);
	            } else if(i.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$") && i.length()==2) {
	            	tagger.getFound_entities().remove(i);
		            System.out.println("Removed: "+ i);
	            }
	        }
            
            tagger.tagEntities();
            tagger.resolveHiddenEntities();
            
            tagger = new LookUpEntityTagger()
            		.setText(tagger.getText())
            		.setTag_name("orgpart")
            		.readLexiconFile("orgpart.txt")
            		.sortLexiconFile()
            		.compilePatternsInsensitive()
            		.run();
            
            tagger.printEntityFrequencyCount();
            
            tagger = new LookUpEntityTagger()
            		.setText(tagger.getText())
            		.setTag_name("cell")
            		.readLexiconFile("cell-lines.txt")
            		.sortLexiconFile()
            		.compilePatterns()
            		.run();
            
            tagger.printEntityFrequencyCount();
            
            tagger = new LookUpEntityTagger()
            		.setText(tagger.getText())
            		.setTag_name("class")
            		.readLexiconFile("compound-class.txt")
            		.sortLexiconFile()
            		.compilePatternsInsensitive()
            		.run();
            
            tagger.printEntityFrequencyCount();
            
            tagger = new LookUpEntityTagger()
            		.setText(tagger.getText())
            		.setTag_name("bodypart")
            		.readLexiconFile("bodypart.txt")
            		.sortLexiconFile()
            		.compilePatternsInsensitive()
            		.run();
            
            tagger.printEntityFrequencyCount();
            
            tagger = new LookUpEntityTagger()
            		.setText(tagger.getText())
            		.setTag_name("prep")
            		.readLexiconFile("prep.txt")
            		.sortLexiconFile()
            		.compilePatternsInsensitive()
            		.run();
            
            tagger.printEntityFrequencyCount();
            
            tagger = new LookUpEntityTagger()
            		.setText(tagger.getText())
            		.setTag_name("illness")
            		.readLexiconFile("illness.txt")
            		.sortLexiconFile()
            		.compilePatternsInsensitive()
            		.run();
            
            tagger.printEntityFrequencyCount();
            
            
            java.io.FileWriter fw = new java.io.FileWriter("abc.xml");
	        fw.write(tagger.getText());
	        fw.close();	   
        }
	}
}
