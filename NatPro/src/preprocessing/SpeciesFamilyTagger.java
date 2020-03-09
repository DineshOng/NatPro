package preprocessing;
import java.io.IOException;

public class SpeciesFamilyTagger extends LookUpEntityTagger {
	
	public SpeciesFamilyTagger(String tag, String text, String filename) throws IOException {
		super(tag, text, filename);
	}
	
	public String run() throws IOException {
		hideTaggedEntities();
		
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
