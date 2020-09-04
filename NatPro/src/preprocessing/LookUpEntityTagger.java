package preprocessing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LookUpEntityTagger extends EntityTagger {
	protected String filename;
	protected List<String> lexicon;
	protected List<Pattern> patterns;
	protected String prefix_regex;
	protected String suffix_regex;
	
	public LookUpEntityTagger(String tag, String text, String filename) {
		super(tag, text);
	
		this.filename = filename;
		
		lexicon = new ArrayList<String>();
		patterns = new ArrayList<Pattern>();
		prefix_regex = "";
		suffix_regex = "";
	}
	
	/*public LookUpEntityTagger run() throws IOException {
		hideTaggedEntities();
		findEntities();
		tagEntities();
		resolveHiddenEntities();
		removeOverlappingTags();
		
		return this;
	}*/
	
	public LookUpEntityTagger readLexiconFile() throws IOException {
		lexicon = new ReadLexiconFile(filename).getContents();
		
		return this;
	}
	
	/*
	public LookUpEntityTagger readLexiconFile(String filename) throws IOException {
		this.filename = filename;
		lexicon = new ReadLexiconFile(filename).getContents();
		
		return this;
	}*/
	
	public LookUpEntityTagger sortLexiconFile() throws IOException {
		new SortbyStringLength(filename);
		
		return this;
	}
	
	
	
	public LookUpEntityTagger findEntities() {
		for(Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(text);
            while(matcher.find()) {
            	if(!found_entities.contains(matcher.group().trim())) {
            		found_entities.add(matcher.group().trim());
            	}
                Integer n = map.get(matcher.group().trim());
                n = (n == null) ? 1 : ++n;
                map.put(matcher.group().trim(), n);
            }
        }
		
		return this;
	}
	
	public LookUpEntityTagger findEntities_Insensitive() {
		for(Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(text);
            while(matcher.find()) {
            	if(!found_entities.contains(matcher.group().toLowerCase().trim())) {
            		found_entities.add(matcher.group().toLowerCase().trim());
            	}
                Integer n = map.get(matcher.group().toLowerCase().trim());
                n = (n == null) ? 1 : ++n;
                map.put(matcher.group().trim().toLowerCase(), n);
            }
        }
		
		return this;
	}
	
	
	
	public LookUpEntityTagger compilePatterns() throws IOException {
        System.out.println("Compiling " + filename + " Patterns...");
        long startTime, endTime;
        startTime = System.nanoTime();

        patterns = new ArrayList<>();

        for(String d : lexicon) {
            patterns.add(Pattern.compile(prefix_regex + d + suffix_regex));
        }

        endTime = System.nanoTime();
        System.err.println("[Compiled "+filename+" Patterns] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        
        return this;
    }

    public LookUpEntityTagger compilePatternsInsensitive() throws IOException {
        System.out.println("Compiling " + filename + " Patterns...");
        long startTime, endTime;
        startTime = System.nanoTime();

        patterns = new ArrayList<>();

        for(String d : lexicon) {
            patterns.add(Pattern.compile(prefix_regex + d + suffix_regex, Pattern.CASE_INSENSITIVE));
        }

        endTime = System.nanoTime();
        System.err.println("[Compiled "+filename+" Patterns] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        
        return this;
    }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<String> getLexicon() {
		return lexicon;
	}

	public void setLexicon(List<String> lexicon) {
		this.lexicon = lexicon;
	}

	public List<Pattern> getPatterns() {
		return patterns;
	}

	public void setPatterns(List<Pattern> patterns) {
		this.patterns = patterns;
	}

	public String getPrefix_regex() {
		return prefix_regex;
	}

	public void setPrefix_regex(String prefix_regex) {
		this.prefix_regex = prefix_regex;
	}

	public String getSuffix_regex() {
		return suffix_regex;
	}

	public void setSuffix_regex(String suffix_regex) {
		this.suffix_regex = suffix_regex;
	}
    
    
}
