import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import uk.ac.cam.ch.wwmm.oscar.Oscar;
import uk.ac.cam.ch.wwmm.oscar.document.NamedEntity;

public class CompoundTagger extends LookUpEntityTagger {
	private List<String> compounds;
	private Map<String, Integer> map;
	private Oscar oscar;
	private HashSet<String> words;
	//private List<String> final_compounds;
	
	public CompoundTagger(String tag, String text, String filename, String commonWords) throws IOException {
		super(tag, text, filename);
        
        map = new HashMap<>();
        compounds = new ArrayList<String>();
        words = new HashSet<String>();
        oscar = new Oscar();
        
        BufferedReader reader;

        reader = new BufferedReader(new FileReader(commonWords));
        String line = reader.readLine();
        while (line != null) {

            if(!line.equals("")) {
            	words.add(line);
            }

            line = reader.readLine();
        }

        reader.close();
        //findEntities();
        
        //sortEntities();
	}
	
	public String run() throws IOException {
		hideTaggedEntities();
		
		sortLexiconFile();
		readLexiconFile();
		
		//setPrefix_regex("\\(?\\b[A-Za-z0-9\\[\\(\\]\\),':-]");
		//setSuffix_regex("\\b");
		
		//compilePatterns();
		
		for(String s: lexicon) {
			patterns.add(Pattern.compile("\\b([A-Za-z0-9\\[\\(\\]\\),':-]+" + s + ")\\b", Pattern.CASE_INSENSITIVE));
		}
		
		findCompounds();
		sortEntities();
		
		for(int i=0; i<compounds.size(); i++) {
        	getFound_entities().add(compounds.get(i));
        }
        
        tagEntities();
        resolveHiddenEntities();
        
        return text;
	}
	
	public CompoundTagger findCompounds() throws IOException {
		//List<String> suffixs = new ReadLexiconFile("compound-suffix.txt").getContents();
		//List<Pattern> patterns = new ArrayList<Pattern>();
		
		//for(String s: suffixs) {
		//	patterns.add(Pattern.compile("\\b([A-Za-z0-9\\[\\(\\]\\),':-]+" + s + ")\\b"));
		//}
		
		for(Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(text);
            while(matcher.find()) {
            	String compound = matcher.group().replaceAll("^and ", "").replaceAll("^a ", "").replaceAll("^[0-9]+ ", "").trim();
            	if(!compounds.contains(compound)) {
            		compounds.add(compound);
            	}
                Integer n = map.get(compound);
                n = (n == null) ? 1 : ++n;
                map.put(compound, n);
            }
        }
		String d = "";
		int c = 0;
		
		for(String i : map.keySet()) {
			if(oscar.findNamedEntities(i).size()!=0 || !words.contains(i.toLowerCase()) ) {
				if(oscar.findNamedEntities(i).size()!=0) {
					if(!words.contains(i.toLowerCase())) {
						//System.err.print("TRUE ");
						System.err.print("value: " + map.get(i) + "\tkey: " + i + "\n");
						c++;
					} else {
						compounds.remove(i);
					}
				} else if(!words.contains(i.toLowerCase())) {
					//System.err.print("TRUE ");
					System.err.print("value: " + map.get(i) + "\tkey: " + i + "\n");
					c++;
				} else {
					compounds.remove(i);
				}
				
				
				
			} else {
				System.out.println("value: " + map.get(i) + "\tkey: " + i);
				compounds.remove(i);
			}
			//d += i + " ";
		}
		
		System.out.println(c);
		
		System.out.println(map.size());
		
		/*Iterator<String> i = compounds.iterator();
		
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
		System.out.println(compounds.size());*/
		
		return this;
	}
	
	public void sortEntities() throws IOException {
		String[] words = compounds.toArray(new String[0]);
		
		Arrays.sort(words, (x,y) -> Integer.compare(y.length(), x.length()));
        
		compounds = Arrays.asList(words);
		
	}
	
	public void Oscar() {
		Oscar oscar = new Oscar();
		List<NamedEntity> entities = oscar.findNamedEntities(text);
		TreeSet<String> e = new TreeSet<>();
		for (NamedEntity ne : entities) {
			if(ne.getSurface().length()>3 && !ne.getSurface().matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$") && !ne.getSurface().matches("^[A-Z0-9]+$")) {
				if(!compounds.contains(ne.getSurface().toLowerCase()))
					e.add(ne.getSurface().toLowerCase());
			}
		}
		
		for(String ra : e){
		    System.out.println(ra);
		}
		System.out.println("size: " + e.size());
	}
}
