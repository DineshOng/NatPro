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
	
	public SpeciesNameResolution(String text) {
		this.text = text;
	}
	
	public String run() {
		Pattern p = Pattern.compile("<(plant)>([^<]+)<\\/[a-z]+>");
		Matcher m = p.matcher(text);
		
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
		
		return text;
	}
}
