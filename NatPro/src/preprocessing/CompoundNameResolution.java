package preprocessing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
			
			text = text.replaceAll("[^->'%:]\\b" + i + "\\b[^->'%:]", " <" + tag + ">" + compoundsByCode.get(i) + "</" + tag + ">, ");
		}
		
		pattern = Pattern.compile("\\b(([1-9][a-z])|([XIV]+)|([1-9]))-(([1-9][a-z])|([XIV]+)|([1-9]))\\b");
		matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			String start = matcher.group(1);
			String end = matcher.group(5);
			
			List<String> range = new ArrayList<String>();
			String compounds = "";
			
			if(start.matches("^[1-9]$")) {
				int s;
				int e;
				try {
					s = Integer.parseInt(start);
					e = Integer.parseInt(end);
				} catch (Exception eee) {
					break;
				}
				
				for(int i=s; i<e; i++) {
					range.add(i+"");
				}
				
				System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>> " + start + " " + end);
			} else if(start.matches("^[1-9][a-z]$")) {
				try {
					int sn = Integer.parseInt(start.charAt(0)+"");
					char sl = (char)start.charAt(1);
			
					int en = Integer.parseInt(end.charAt(0)+"");
					char el = (char)end.charAt(1);
					
					//sn = 1; sl = 'c';
					//en = 2; el = 'b';
					
					int diff = el - sl;
					
					int flag = 0;
					
					char letter = sl;
					
					String code = sn+""+sl;
					
					for(int i=sn; i<=en; i++) {
						flag = 0;
						for(int j=letter; flag == 0 && (code.charAt(0)!=en && code.charAt(1)!=el); j++) {
							code =  i+""+(char)j;
							System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> " + code);
							System.out.println(compoundsByCode.get(code));
							if(compoundsByCode.get(code)!=null) {
								range.add(code);
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> " + code);
							} else {
								flag = 1;
								letter = 97;
							}
							
						}
					}
				}catch (Exception e) {}
			}
			
			
			
			for(String cod: range) {
				compounds += "<" + tag + ">" + compoundsByCode.get(cod) + "</" + tag + ">, ";
			}
			
			text = text.replaceAll(matcher.group(), compounds);
		}
		
		text = text.replaceAll(" {2}", " ");
		
		return text;
	}

}
