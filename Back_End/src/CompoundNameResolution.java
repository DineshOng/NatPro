import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompoundNameResolution {
	private String text;
	private String tag;
	
	private HashMap<String, String> compoundsByName;
	private HashMap<String, String> compoundsByCode;
	
	private Map<String, Integer> map;
	
	private Pattern pattern;
	private Matcher matcher;
	
	public CompoundNameResolution(String tag, String text) {
		this.tag = tag;
		this.text = text;
		
		compoundsByName = new HashMap<String, String>();
		compoundsByCode = new HashMap<String, String>();
		
		map = new HashMap<String, Integer>();
	}
	
	public String run() {
		pattern = Pattern.compile("(<compound>([A-Za-z0-9\\[\\(\\]\\),':-]+)(( ([A-Za-z0-9\\[\\(\\]\\),':-]+))+)?<\\/compound>) ?(\\((([1-9][a-z])|([XIV]+)|([1-9]))\\))");
		matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			String compound = "";
			if(matcher.group(3)!=null) {
				//compound = matcher.group(7)+":\t"+matcher.group(2)+" "+matcher.group(3);
				compound = matcher.group(2).trim()+" "+matcher.group(3).trim();
				System.out.println(matcher.group(7)+":\t"+compound);
				compoundsByName.put(compound, matcher.group(7));
				compoundsByCode.put(matcher.group(7), compound);
				
				Integer n = map.get(matcher.group(7).trim());
                n = (n == null) ? 1 : ++n;
                map.put(matcher.group(7).trim(), n);
                
                //text = text.replaceAll(matcher.group(6).trim(), "");
                //System.out.println(matcher.group(6).trim());
			} else {
				//compound = matcher.group(7)+":\t"+matcher.group(2);
				compound = matcher.group(2);
				System.out.println(matcher.group(7)+":\t"+compound);
				compoundsByName.put(compound, matcher.group(7));
				compoundsByCode.put(matcher.group(7), compound);
				
				Integer n = map.get(matcher.group(7).trim());
                n = (n == null) ? 1 : ++n;
                map.put(matcher.group(7).trim(), n);
                
                //text = text.replaceAll(matcher.group(6).trim(), "");
                //System.out.println(matcher.group(6).trim());
			}
		}
		
		text = text.replaceAll("\\((([1-9][a-z])|([XIV]+)|([1-9]))\\)", "");
		
		for(String i : map.keySet()) {
			System.out.println("value: " + map.get(i) + "\tkey: " + i);
			
			text = text.replaceAll("[^->'%,:]\\b" + i + "\\b[^->'%:]", " <" + tag + ">" + compoundsByCode.get(i) + "</" + tag + "> ");
		}
		
		text = text.replaceAll(" {2}", " ");
		
		return text;
	}

}
