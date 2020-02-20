import java.io.IOException;

public class BioActivityTagger extends LookUpEntityTagger {
	public BioActivityTagger(String tag, String text, String filename) throws IOException {
		super(tag, text, filename);
	}
	
	public String run() throws IOException {
		hideTaggedEntities();
		
		sortLexiconFile();
		readLexiconFile();
		
		compilePatternsInsensitive();
		
		findEntities();
		tagEntities();
		
		resolveHiddenEntities();
		
		removeOverlappingTags();
		
		printEntityFrequencyCount();
		
		return text;
	}
}
