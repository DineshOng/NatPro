package preprocessing;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Coref {
	private String text;

	public Coref(String text) {
		this.text = text;
	}
	
	public String findPronoun(String str, String lastEnt) {
		//System.out.println(str);
		
		String[] words = str.split(" ");
		for(String word: words) {
			if(word.endsWith("_PRP")) {
				//System.err.println("changed");
				str = str.replaceAll(word, lastEnt);
				//System.err.println("changed " + lastEnt);
				//System.err.println(str.trim().replaceAll("_[A-Z$,.]+", ""));
			}
		}
		
		return str;
	}
	
	public String run2() throws IOException {
		MaxentTagger mt = new MaxentTagger("english-left3words-distsim.tagger");
		String subject = "";
		String corefText = "";
		
		String[] sentences = text.split("\n\n");
		
		for(String sentence: sentences) {
			Pattern p = Pattern.compile("<([a-z]+)>([^<]+)<\\/[a-z]+>");
			Matcher m = p.matcher(sentence);
			
			sentence = mt.tagTokenizedString(sentence.trim());
			
			if(!sentence.contains("_PRP")) {
				while(m.find()) {
					if(m.group(1).equals("compound") || m.group(1).equals("plant") || m.group(1).equals("aka")) {
						subject = m.group();
						
						//System.err.println(subject);
						break;
					} 
					
				}
				corefText += sentence.trim().replaceAll("_[A-Z$,.]{1,4}", "") + "\n\n";
			} else {
				corefText += findPronoun(sentence, subject).trim().replaceAll("_[A-Z$,.]{1,4}", "") + "\n\n";
			}
			
			
			
			/*
				text += findPronoun(sentence, subject).trim().replaceAll("_[A-Z]+", "") + "\n\n";
			else
				text += sentence.trim().replaceAll("_[A-Z]+", "") + "\n\n";
				*/
		}
		
		return corefText;
	}

	public String run() throws IOException {
		String[] sentences = text.split("\n\n");
		String subject = "";
		
		for(int i=0; i<sentences.length; i++) {
			//String sentence = s.trim();
			//System.out.println(sentence);
			
			Pattern p = Pattern.compile("<([a-z]+)>([^<]+)<\\/[a-z]+>");
			Matcher m = p.matcher(sentences[i]);
			
			sentences[i] = sentences[i].trim();
			
			if(!sentences[i].contains("It")) {
				while(m.find()) {
					if(m.group(1).equals("compound") || m.group(1).equals("plant")) {
						subject = m.group();
						
						//System.err.println(subject);
						break;
					} 
					
				}
			} else {
				
					sentences[i] = sentences[i].replaceAll("It", subject);
				
			}
			//System.out.println(sentences[i].trim());
		}
		
		
		
		return String.join("\n\n", sentences);
	}
}
