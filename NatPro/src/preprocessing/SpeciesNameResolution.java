package preprocessing;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpeciesNameResolution {
	private String tag;
	private String text;
	private String mainGenus;
	
	public SpeciesNameResolution(String tag, String text) {
		this.tag = tag;
		this.text = text;
	}
	
	public String run() {
		Pattern p = Pattern.compile("<"+tag+">(([A-Z][a-z]+) [a-z-]+[^<]+)<\\/"+tag+">");
		Matcher m = p.matcher(text);
		
		while(m.find()) {
			mainGenus = m.group(2);
			break;
		}
		
		
		p = Pattern.compile("<("+tag+")>([^<]+)<\\/"+tag+">");
		m = p.matcher(text);
		
		while(m.find()) {
			//System.out.println(m.group(2));
			
			if(!m.group(2).matches("^[A-Z]{2}$") && !m.group(2).matches("^[A-Z]\\. [a-z]+$")) {
				String str = m.group(2);
				String []strr = str.split(" ");
				text = text.replaceAll("<"+tag+">" + strr[0].toUpperCase().charAt(0) + strr[1].toUpperCase().charAt(0) + "</"+tag+">", "<"+tag+">" + str + "</"+tag+">");
				text = text.replaceAll("<"+tag+">" + strr[0].toUpperCase().charAt(0) + ". " + strr[1] + "</"+tag+">", "<"+tag+">" + str + "</"+tag+">");
			}
		}
		
		p = Pattern.compile("<"+tag+">([A-Z]{2})<\\/"+tag+">");
		m = p.matcher(text);
		
		while(m.find()) {
			text = text.replaceAll(m.group(), m.group(1));
		}
		
		p = Pattern.compile("<"+tag+">(([A-Z]\\.) ([a-z-]+[^<]+))<\\/"+tag+">");
		m = p.matcher(text);
		System.out.println(mainGenus);
		while(m.find()) {
			try {
				if(m.group(2).toUpperCase().charAt(0) == mainGenus.toUpperCase().charAt(0)) {
					text = text.replaceAll(m.group(), "<"+tag+">" + mainGenus + " " + m.group(3) + "</"+tag+">");
				}
			} catch (Exception e) {}
		}
		
		return text;
	}
}
