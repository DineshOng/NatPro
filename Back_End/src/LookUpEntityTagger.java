import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LookUpEntityTagger {
	private List<String> lexicon;
	private String filename;
	private List<Pattern> patterns;
	private String text;
	private List<String> found_entities;
	private Map<String, Integer> map;
	HashMap<String, String> prev_ent;
	private String prefix_regex;
	private String suffix_regex;
	private String tag_name;
	private int idx;

	public LookUpEntityTagger (String tag_name, String filename, String text) {
		this.tag_name = tag_name;
		this.filename = filename;
		this.text = text;
		map = new HashMap<>();
		prev_ent = new HashMap<String, String>();
		patterns = new ArrayList<Pattern>();
		found_entities = new ArrayList<String>();
		lexicon = new ArrayList<String>();
		prefix_regex = "";
		suffix_regex = "";
		idx = 0;
	}
	
	public LookUpEntityTagger() {
		this.tag_name = tag_name;
		this.filename = filename;
		this.text = text;
		map = new HashMap<>();
		prev_ent = new HashMap<String, String>();
		patterns = new ArrayList<Pattern>();
		found_entities = new ArrayList<String>();
		lexicon = new ArrayList<String>();
		prefix_regex = "";
		suffix_regex = "";
		idx = 0;
	}
	
	public LookUpEntityTagger run() throws IOException {
		hideTaggedEntities();
		findEntities();
		tagEntities();
		resolveHiddenEntities();
		
		return this;
	}
	
	/*public LookUpEntityTagger readLexiconFile() throws IOException {
		lexicon = new ReadLexiconFile(filename).getContents();
		
		return this;
	}*/
	
	public LookUpEntityTagger readLexiconFile(String filename) throws IOException {
		this.filename = filename;
		lexicon = new ReadLexiconFile(filename).getContents();
		
		return this;
	}
	
	public LookUpEntityTagger sortLexiconFile() throws IOException {
		new SortbyStringLength(filename);
		
		return this;
	}
	
	public LookUpEntityTagger hideTaggedEntities() {
		Pattern pattern = Pattern.compile("<([a-z]+)>([a-zA-Z\\s.-]+)<\\/[a-z]+>");
		Matcher matcher = pattern.matcher(text);
		idx = 0;
		while(matcher.find()) {
        	prev_ent.put(matcher.group(1) + " " + idx, matcher.group(2));
        	text = text.replaceAll(matcher.group(), "<<" + matcher.group(1) + "@" + idx + ">>");
        	//text = text.replaceAll(matcher.group(), "hi");
        	idx++;
        }
		
		//System.out.println(text);
		
		return this;
	}
	
	public LookUpEntityTagger resolveHiddenEntities() {
		for (String e : prev_ent.keySet()) {
            String []tag = e.split(" ");
            text = text.replaceAll("<<"+ tag[0] + "@" + tag[1] + ">>", "<"+tag[0]+">"+prev_ent.get(e)+"</"+tag[0]+">");
        }
		
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
	
	public LookUpEntityTagger tagEntities() {
		for(String e : found_entities) {
            //text = text.replaceAll(e, "<" + tag_name + ">" + e + "</" + tag_name + ">");
			//prev_ent.add(tag_name + " " + e + " " + idx);
			prev_ent.put(tag_name + " " + idx, e);
			text = text.replaceAll(e, "<<" + tag_name + "@" + idx + ">>");
			idx++;
        }
		
		return this;
	}
	
	public void printEntityFrequencyCount() {
		for(String i : map.keySet()) {
			System.out.println("value: " + map.get(i) + "\tkey: " + i);
	     }
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

	public List<String> getLexicon() {
		return lexicon;
	}

	public void setLexicon(List<String> lexicon) {
		this.lexicon = lexicon;
	}
	
	public LookUpEntityTagger addLexicon(String s) {
		lexicon.add(s);
		
		return this;
	}

	public String getFilename() {
		return filename;
	}

	public LookUpEntityTagger setFilename(String filename) {
		this.filename = filename;
		
		return this;
	}

	public List<Pattern> getPatterns() {
		return patterns;
	}
	
	public LookUpEntityTagger addPattern(String s) {
		patterns.add(Pattern.compile(s));
		
		return this;
	}

	public void setPatterns(List<Pattern> patterns) {
		this.patterns = patterns;
	}

	public String getText() {
		return text;
	}

	public LookUpEntityTagger setText(String text) {
		this.text = text;
		
		return this;
	}

	public List<String> getFound_entities() {
		return found_entities;
	}

	public void setFound_entities(List<String> found_entities) {
		this.found_entities = found_entities;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public HashMap<String, String> getPrev_ent() {
		return prev_ent;
	}

	public void setPrev_ent(HashMap<String, String> prev_ent) {
		this.prev_ent = prev_ent;
	}

	public String getPrefix_regex() {
		return prefix_regex;
	}

	public LookUpEntityTagger setPrefix_regex(String prefix_regex) {
		this.prefix_regex = prefix_regex;
		
		return this;
	}

	public String getSuffix_regex() {
		return suffix_regex;
	}

	public LookUpEntityTagger setSuffix_regex(String suffix_regex) {
		this.suffix_regex = suffix_regex;
		
		return this;
	}

	public String getTag_name() {
		return tag_name;
	}

	public LookUpEntityTagger setTag_name(String tag_name) {
		this.tag_name = tag_name;
		
		return this;
	}
    
    
}
