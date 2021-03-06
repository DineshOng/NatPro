package preprocessing;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompoundNameExpander {
	private String text;
	private String tag;
	private Pattern pattern;
	private Matcher matcher; 

	public CompoundNameExpander (String tag, String text) {
		this.tag = tag;
		this.text = text;
	}
	
	public String run() {
		pattern = Pattern.compile("(<"+tag+">([A-Za-z0-9\\[\\(\\]\\),':-]+)<\\/"+tag+">) (([XIV]{1,3},? ?)+(and [XIV]{1,3})?)\\b[^-]");
		matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			System.out.println(matcher.group()+">>"+matcher.group(2)+">>"+matcher.group(3));
			String []list = matcher.group(3).replaceAll("and", " ").replaceAll(",", " ").replaceAll(" +", " ").split(" ");
			System.out.println(Arrays.deepToString(list));
			
			String out = "";
			
			for(int i=0; i<list.length; i++) {
				if(i!=list.length-1 && list.length!=2)
					out += "<" + tag + ">" + matcher.group(2) + " " + list[i] + "</" + tag + ">, ";
				else
					out += "<" + tag + ">" + matcher.group(2) + " " + list[i] + "</" + tag + ">";
				if(i==list.length-2)
					out += " and ";
			}
			
			System.err.println(out);
			
			String temp = matcher.group();
			
			if(temp.charAt(temp.length() - 1) == ',') {
				out += ", ";
			}
			
			text = text.replaceAll(temp, out);
		}
		
		pattern = Pattern.compile("(<"+tag+">([A-Za-z0-9\\[\\(\\]\\),':-]+)<\\/"+tag+">) (([A-Da-d],? ?)+(and [A-Da-d])?)\\b[^-]");
		matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			System.out.println(matcher.group()+">>"+matcher.group(2)+">>"+matcher.group(3));
			String []list = matcher.group(3).replaceAll("and", " ").replaceAll(",", " ").replaceAll(" +", " ").split(" ");
			System.out.println(Arrays.deepToString(list));
			
			String out = "";
			
			for(int i=0; i<list.length; i++) {
				if(i!=list.length-1 && list.length!=2)
					out += "<" + tag + ">" + matcher.group(2) + " " + list[i] + "</" + tag + ">, ";
				else
					out += "<" + tag + ">" + matcher.group(2) + " " + list[i] + "</" + tag + ">";
				if(i==list.length-2)
					out += " and ";
			}
			
			System.err.println(out);
			
			String temp = matcher.group();
			
			if(temp.charAt(temp.length() - 1) == ',') {
				out += ", ";
			}
			
			text = text.replaceAll(temp, out);
		}
		
		pattern = Pattern.compile("(<"+tag+">([A-Za-z0-9\\[\\(\\]\\),':-]+)<\\/"+tag+">) ([A-Z]-[A-Z])\\b");
		matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			System.out.println(matcher.group()+">>"+matcher.group(2)+">>"+matcher.group(3));
			String []letters = matcher.group(3).split("-");
			char start = (char)letters[0].charAt(0);
			char end = (char)letters[1].charAt(0);
			
			int diff = end-start;
			
			for(int i=0; i<=diff; i++) {
				System.out.println(matcher.group(2) + " " + (char)(start+i));
			}
			
			String out = "";
			
			for(int i=0; i<=diff; i++) {
				if(i!=diff)
					out += "<" + tag + ">" + matcher.group(2) + " " + (char)(start+i) + "</" + tag + ">, ";
				else
					out += "<" + tag + ">" + matcher.group(2) + " " + (char)(start+i) + "</" + tag + ">";
				if(i==diff-1)
					out += " and ";
			}
			
			System.err.println(out);
			
			String temp = matcher.group();
			
			text = text.replaceAll(temp, out);
		}
		
		return text;
	}
}
