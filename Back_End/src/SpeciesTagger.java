import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class SpeciesTagger extends LookUpEntityTagger {
	private Set<String> words;
	
	public SpeciesTagger(String tag, String text, String filename) throws IOException {
		super(tag, text, filename);
		
		words = new ReadLexiconFile("google-10k.txt").getContentsInHash();
	}
	
	public String run() throws IOException {
		HashSet<String> stopwords = new HashSet<>();
        stopwords.add("in");
        stopwords.add("and");
        stopwords.add("from");
		
		readLexiconFile();
		
		// Adds the contents of the lexicon with this suffix ~ Alstonia ......., Andrographis .........
		setSuffix_regex("\\s(\\b[a-z]+)");
		
		compilePatterns();
		
		// Gets species with pattern A. macrophylla, A. paniculata
		patterns.add(Pattern.compile("[A-Z]\\.\\s([a-z]){4,}"));
		
		// Gets species with pattern AM, AP, HM 
		patterns.add(Pattern.compile("\\s([A-Z]{2})\\s"));
		
		hideTaggedEntities();
		findEntities();
		
		for (String i : map.keySet()) {
            //System.out.println("value: " + map.get(i) + "\tkey: " + i);
            if(i.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$") && i.length()==2) { // Removes Roman Numeral found as species
            	getFound_entities().remove(i);
	            System.out.println("Removed: "+ i);
            } else if(i.split(" ").length==2 && (stopwords.contains(i.split(" ")[1]) || words.contains(i.split(" ")[1]))) {
            	getFound_entities().remove(i);
	            System.out.println("Removed: "+ i);
            }
        }
		
		tagEntities();
        resolveHiddenEntities();
        
        printEntityFrequencyCount();
        
        return text;
	}
}
