import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityTagger {
	protected String tag;
	protected String text;
	protected List<String> found_entities;
	protected Map<String, Integer> map;
	protected HashMap<String, String> prev_ent;
	private int idx;
	
	public EntityTagger(String tag, String text) {
		this.tag = tag;
		this.text = text;
		
		found_entities = new ArrayList<String>();
		map = new HashMap<>();
		prev_ent = new HashMap<String, String>();
		idx = 0;
	}
	
	public EntityTagger hideTaggedEntities() {
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
	
	public EntityTagger tagEntities() {
		for(String e : found_entities) {
            //text = text.replaceAll(e, "<" + tag_name + ">" + e + "</" + tag_name + ">");
			//prev_ent.add(tag_name + " " + e + " " + idx);
			e = e.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
            e = e.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
			prev_ent.put(tag + " " + idx, e);
			text = text.replaceAll(e, "<<" + tag + "@" + idx + ">>");
			idx++;
        }
		
		return this;
	}
	
	
	
	public EntityTagger tagEntities_Insensitive() {
		for(String e : found_entities) {
            //text = text.replaceAll(e, "<" + tag_name + ">" + e + "</" + tag_name + ">");
			//prev_ent.add(tag_name + " " + e + " " + idx);
			e = e.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
            e = e.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
			prev_ent.put(tag + " " + idx, e.toLowerCase());
			text = text.replaceAll("(?i)"+e, "<<" + tag + "@" + idx + ">>");
			idx++;
        }
		
		return this;
	}
	
	public void printEntityFrequencyCount() {
		for(String i : map.keySet()) {
			System.out.println("value: " + map.get(i) + "\tkey: " + i);
	     }
	}
	
	public EntityTagger resolveHiddenEntities() {
		for (String e : prev_ent.keySet()) {
            String []tag = e.split(" ");
            text = text.replaceAll("<<"+ tag[0] + "@" + tag[1] + ">>", "<"+tag[0]+">"+prev_ent.get(e)+"</"+tag[0]+">");
        }
		
		return this;
	}
	
	public EntityTagger removeOverlappingTags() {
    	// Remove inside tags - Left hand side
        Pattern pattern = Pattern.compile("([-\\w\\d\\.]+)<[\\w\\d]+>([\\w\\d\\s]+)<\\/[\\w\\d]+>");
	    Matcher matcher = pattern.matcher(text);
	    while(matcher.find()) {
	    	text = text.replaceAll(matcher.group(), matcher.group(1) + matcher.group(2));
        }
	    
	    // Remove inside tags - Right hand side
        pattern = Pattern.compile("<[\\w\\d]+>([\\w\\d\\s]+)<\\/[\\w\\d]+>([-\\w\\d]+)");
	    matcher = pattern.matcher(text);
	    while(matcher.find()) {
	    	text = text.replaceAll(matcher.group(), matcher.group(1) + matcher.group(2));
        }
        
	    
	    // Remove reference ex. </tag>23
	    pattern = Pattern.compile("(<\\/\\w+>)\\d+");
	    matcher = pattern.matcher(text);
	    while(matcher.find()) {
	    	text = text.replaceAll(matcher.group(), matcher.group(1));
        }
	    
	    // Remove reference ex. </tag>,2,3
	    pattern = Pattern.compile("(<\\/\\w+>,)(\\d+)+");
	    matcher = pattern.matcher(text);
	    while(matcher.find()) {
	    	text = text.replaceAll(matcher.group(), matcher.group(1));
        }
	    
	    return this;
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	
}
