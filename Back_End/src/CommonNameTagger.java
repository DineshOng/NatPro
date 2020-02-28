import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class CommonNameTagger extends EntityTagger {
	private TreeSet<String> phrases;
	private MaxentTagger mt;
	
	public CommonNameTagger(String tag, String text) {
		super(tag, text);
		
		mt = new MaxentTagger("english-left3words-distsim.tagger");
		phrases = new TreeSet<String>();
	}
	
	public void find(String sentence) {
		String[] words = sentence.split(" ");
		
		String phrase = "";
		for(String word: words) {
			//System.out.println(word);
        	if(word.endsWith("VBN") && (word.contains("known") || word.contains("called"))) {
        		phrase += " ";
        	} else if(phrase.length()!=0  && word.endsWith("JJ") && (phrase.trim().endsWith("NN") || phrase.trim().endsWith("NNS"))) {
        		continue;
        	} else if(phrase.length()!=0 && (word.endsWith("JJ") || word.endsWith("NN") || word.endsWith("NNS") || word.endsWith("CC"))) {
        		//System.out.println(word);
        		phrase += word + " ";
        	} else if(phrase.length()!=0 && word.endsWith("IN")) {
        		continue;
        	} else {
        		if(phrase.split(" ").length>1)
        			phrases.add(phrase.trim().replaceAll("_[A-Z]+", ""));
        		phrase = "";
        	}
        }
	}
	
	public String[] getCommonNames() {
		return phrases.toArray(new String[phrases.size()]);
	}
	
	public String run() {
		String[] sentences = text.split("\n\n");
		
		for(String sentence: sentences) {
			find(mt.tagString(sentence));
		}
		
		for(String p: phrases) {
			String ps[] = p.split("(and)|(or)");
			for(String s : ps)
				text = text.replaceAll(s.trim(), "<" + tag + ">" + s.trim() + "</" + tag + ">");
		}
		
		return text;
	}

	public TreeSet<String> getPhrases() {
		return phrases;
	}

	public void setPhrases(TreeSet<String> phrases) {
		this.phrases = phrases;
	}

	public String getText() {
		return text;
	}
}
