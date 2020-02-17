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

public class CompoundTagger {
	private String text;
	private String tag_name;
	private MaxentTagger mt;
	private List<String> compounds;
	private Map<String, Integer> map;
	private Oscar oscar;
	private HashSet<String> words;
	//private List<String> final_compounds;
	
	public CompoundTagger(String text, String tag_name) throws IOException {
		this.text = text;
		this.tag_name = tag_name;
		
		long startTime, endTime;
        startTime = System.nanoTime();
        
        map = new HashMap<>();
        compounds = new ArrayList<String>();
        words = new HashSet<String>();
        oscar = new Oscar();
        
        BufferedReader reader;

        reader = new BufferedReader(new FileReader("20k.txt"));
        String line = reader.readLine();
        while (line != null) {

            if(!line.equals("")) {
            	words.add(line);
            }

            line = reader.readLine();
        }

        reader.close();
		
        //mt = new MaxentTagger("english-left3words-distsim.tagger");
        
        findEntities();
        
        sortEntities();
		
		endTime = System.nanoTime();
        System.err.println("Oscar Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        System.err.println("Oscar Duration: "+ ((double)(endTime - startTime)) / 1000000000 + " s");
	}
	
	public CompoundTagger() throws IOException {
        map = new HashMap<>();
        compounds = new ArrayList<String>();
        words = new HashSet<String>();
        oscar = new Oscar();
        
        BufferedReader reader;

        reader = new BufferedReader(new FileReader("20k.txt"));
        String line = reader.readLine();
        while (line != null) {

            if(!line.equals("")) {
            	words.add(line);
            }

            line = reader.readLine();
        }

        reader.close();
	}
	
	public CompoundTagger findEntities() throws IOException {
		List<String> suffixs = new ReadLexiconFile("compound-suffix.txt").getContents();
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		for(String s: suffixs) {
			patterns.add(Pattern.compile("\\b([A-Za-z0-9\\[\\(\\]\\),':-]+" + s + ")\\b"));
		}
		
		for(Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(text);
            while(matcher.find()) {
            	String compound = matcher.group().toLowerCase().replaceAll("^and ", "").replaceAll("^a ", "").replaceAll("^[0-9]+ ", "").trim();
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
			if(oscar.findNamedEntities(i).size()!=0 || !words.contains(i)) {
				if(oscar.findNamedEntities(i).size()!=0) {
					if(!words.contains(i)) {
						//System.err.print("TRUE ");
						System.err.print("value: " + map.get(i) + "\tkey: " + i + "\n");
						c++;
					} else {
						compounds.remove(i);
					}
				} else if(!words.contains(i)) {
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
		
		Iterator<String> i = compounds.iterator();
		
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
		System.out.println(compounds.size());
		return this;
	}
	
	public CompoundTagger sortEntities() throws IOException {
		String[] words = compounds.toArray(new String[0]);
		
		Arrays.sort(words, (x,y) -> Integer.compare(y.length(), x.length()));
        
		compounds = Arrays.asList(words);
		
		/*
		Iterator<String> i = compounds.iterator();
		
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
		System.out.println(compounds.size());
		
		for(String w: words) {
			text = text.replaceAll(w, "<" + tag_name + ">" + w + "</" + tag_name + ">");
		}
		*/
		
		return this;
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

	public String getText() {
		return text;
	}

	public CompoundTagger setText(String text) {
		this.text = text;
		
		return this;
	}

	public String getTag_name() {
		return tag_name;
	}

	public CompoundTagger setTag_name(String tag_name) {
		this.tag_name = tag_name;
		
		return this;
	}

	public MaxentTagger getMt() {
		return mt;
	}

	public void setMt(MaxentTagger mt) {
		this.mt = mt;
	}

	public List<String> getCompounds() {
		return compounds;
	}

	public void setCompounds(List<String> compounds) {
		this.compounds = compounds;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public Oscar getOscar() {
		return oscar;
	}

	public void setOscar(Oscar oscar) {
		this.oscar = oscar;
	}

	public HashSet<String> getWords() {
		return words;
	}

	public void setWords(HashSet<String> words) {
		this.words = words;
	}
	
	
}
