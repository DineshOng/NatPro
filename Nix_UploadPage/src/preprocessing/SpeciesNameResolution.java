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
	private String text;
	private String mainGenus;
	
	public SpeciesNameResolution(String text) {
		this.text = text;
	}
	
	public String run() {
		Pattern p = Pattern.compile("<plant>(([A-Z][a-z]+) [a-z-]+[^<]+)<\\/[a-z]+>");
		Matcher m = p.matcher(text);
		
		while(m.find()) {
			mainGenus = m.group(2);
			break;
		}
		
		
		p = Pattern.compile("<(plant)>([^<]+)<\\/[a-z]+>");
		m = p.matcher(text);
		
		while(m.find()) {
			//System.out.println(m.group(2));
			
			if(!m.group(2).matches("^[A-Z]{2}$") && !m.group(2).matches("^[A-Z]\\. [a-z]+$")) {
				String str = m.group(2);
				String []strr = str.split(" ");
				text = text.replaceAll("<plant>" + strr[0].toUpperCase().charAt(0) + strr[1].toUpperCase().charAt(0) + "</plant>", "<plant>" + str + "</plant>");
				text = text.replaceAll("<plant>" + strr[0].toUpperCase().charAt(0) + ". " + strr[1] + "</plant>", "<plant>" + str + "</plant>");
			}
		}
		
		p = Pattern.compile("<plant>([A-Z]{2})<\\/plant>");
		m = p.matcher(text);
		
		while(m.find()) {
			text = text.replaceAll(m.group(), m.group(1));
		}
		
		p = Pattern.compile("<plant>(([A-Z]\\.) ([a-z-]+[^<]+))<\\/[a-z]+>");
		m = p.matcher(text);
		System.out.println(mainGenus);
		while(m.find()) {
			if(m.group(2).toUpperCase().charAt(0) == mainGenus.toUpperCase().charAt(0)) {
				text = text.replaceAll(m.group(), "<plant>" + mainGenus + " " + m.group(3) + "</plant>");
			}
		}
		
		return text;
	}
}
