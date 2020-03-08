import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchSeeds {
	private String []text;
	private Set<String> seeds;
	private Set<String> e1;
	private Set<String> e2;
	private Set<String> e3;
	
	public void findPair(String s, String s1, String s2) {
		e1 = new TreeSet<String>();
		e2 = new TreeSet<String>();
		
		Pattern p = Pattern.compile("<([a-z]+)>([^<]+)<\\/[a-z]+>");
		Matcher m = p.matcher(s);
		
		while(m.find()) {
			if(m.group(1).equals(s1)) {
				e1.add(m.group().toLowerCase());
			} else if(m.group(1).equals(s2)) {
				e2.add(m.group().toLowerCase());
			}
		}
		
		for(String e : e1) {
			for(String ee : e2) {
				if(seeds.contains(e+ee)) {
					//System.out.println(s);
					//System.out.println(e+ee);
					//processSentence()
					
					p = Pattern.compile("(.*)("+e+")(.+)("+ee+")(.*)", Pattern.CASE_INSENSITIVE);
					m = p.matcher(s);
					while(m.find()) {
						System.out.println("BEF:\t"+m.group(1));
						System.out.println("ENT1:\t"+e);
						System.out.println("BET:\t"+m.group(3));
						System.out.println("ENT2:\t"+ee);
						System.out.println("AFT:\t"+m.group(5));
						System.out.println("\n");
					}
				}
			}
		}
	}
	
	public SearchSeeds(String []text) {
		this.text = text;
		//this.seeds = seeds;
		
		String allTag = "(.*)<[a-z]+>[^<]+<\\/[a-z]+>(.*)";
		String plant = "(.*)<plant>[^<]+<\\/plant>(.*)";
		String location = "(.*)<loc>[^<]+<\\/loc>(.*)";
		String aka = "(.*)<aka>[^<]+<\\/aka>(.*)";
		String compound = "(.*)<compound>[^<]+<\\/compound>(.*)";
		
		e1 = new TreeSet<String>();
		e2 = new TreeSet<String>();
		e3 = new TreeSet<String>();
		
		for(String s : text) {
			if(s.matches(allTag)) {
				if(s.matches(plant)) {
					if(s.matches(location)) {
						findPair(s, "plant", "loc");
					}
					if(s.matches(aka)) {
						findPair(s, "plant", "aka");
					}
					if(s.matches(compound)) {
						findPair(s, "plant", "compound");
					}
				}
			}
		}
		
		
	}
}
