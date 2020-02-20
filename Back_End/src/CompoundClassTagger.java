import java.io.IOException;

public class CompoundClassTagger extends LookUpEntityTagger {

	public CompoundClassTagger(String tag, String text, String filename) {
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
