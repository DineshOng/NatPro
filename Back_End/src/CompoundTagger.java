import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import uk.ac.cam.ch.wwmm.oscar.Oscar;
import uk.ac.cam.ch.wwmm.oscar.document.NamedEntity;

public class CompoundTagger {
	private String text;
	private String tag_name;
	private MaxentTagger mt;
	private List<String> compounds;
	private Map<String, Integer> map;
	private Oscar oscar;
	
	public CompoundTagger(String text, String tag_name) throws IOException {
		this.text = text;
		this.tag_name = tag_name;
		
		long startTime, endTime;
        startTime = System.nanoTime();
        
        map = new HashMap<>();
        compounds = new ArrayList<String>();
        oscar = new Oscar();
		
        //mt = new MaxentTagger("english-left3words-distsim.tagger");
        
        suffix();
		
		endTime = System.nanoTime();
        System.err.println("Oscar Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        System.err.println("Oscar Duration: "+ ((double)(endTime - startTime)) / 1000000000 + " s");
	}
	
	public void suffix() throws IOException {
		List<String> suffixs = new ReadLexiconFile("compound-suffix.txt").getContents();
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		for(String s: suffixs) {
			patterns.add(Pattern.compile("\\b([A-Za-z0-9\\[\\(\\]\\),':-]+" + s + ")\\b"));
		}
		
		for(Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(text);
            while(matcher.find()) {
            	if(!compounds.contains(matcher.group().trim().toLowerCase())) {
            		compounds.add(matcher.group().trim().toLowerCase());
            	}
                Integer n = map.get(matcher.group().trim().toLowerCase());
                n = (n == null) ? 1 : ++n;
                map.put(matcher.group().trim().toLowerCase(), n);
            }
        }
		String d = "";
		int c = 0;
		
		for(String i : map.keySet()) {
			if(oscar.findNamedEntities(i).size()!=0) {
				System.err.print("TRUE ");
				System.out.print("value: " + map.get(i) + "\tkey: " + i + "\n");
				
				c++;
			} else {
				System.out.println("value: " + map.get(i) + "\tkey: " + i);
			}
			//d += i + " ";
		}
		
		System.out.println(c);
		
		System.out.println(map.size());
		
		//System.out.println(d);
		
		//Oscar(d.trim());
	}
	
	public void Oscar1(String text) {
		//Oscar oscar = new Oscar();
		List<NamedEntity> entities = oscar.findNamedEntities(text);
		TreeSet<String> e = new TreeSet<>();
		for (NamedEntity ne : entities) {
			if(ne.getSurface().length()>3 && !ne.getSurface().matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$") && !ne.getSurface().matches("^[A-Z0-9]+$")) {
				e.add(ne.getSurface().toLowerCase());
			}
		}
		
		for(String ra : e){
		    System.out.println(ra);
		}
		System.out.println("size: " + e.size());
	}
	
	public void Oscar(String text) {
		Oscar oscar = new Oscar();
		List<NamedEntity> entities = oscar.findNamedEntities(text);
		TreeSet<String> e = new TreeSet<>();
		for (NamedEntity ne : entities) {
			if(ne.getSurface().length()>3 && !ne.getSurface().matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$") && !ne.getSurface().matches("^[A-Z0-9]+$")) {
				e.add(ne.getSurface().toLowerCase());
			}
		}
		
		for(String ra : e){
		    System.out.println(ra);
		}
		System.out.println("size: " + e.size());
	}
}
