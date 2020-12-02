package bratAnn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import model.Validation;

public class ResultsEvaluation {
	private static String annFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\all\\";
	private static Multimap<String, String> GoldRelationships;

	public static void main(String[] args) throws IOException {
		ReadGoldAnn();
	}
	
	public static void ReadGoldAnn() throws IOException {
		System.out.println("ReadGoldAnn...");
        long startTime, endTime;
        startTime = System.nanoTime();
		
        GoldRelationships = ArrayListMultimap.create();
        
		File Folder = new File(annFolder);
		File[] listFiles = Folder.listFiles();
		
		for (File file : listFiles) {
			if(file.getName().matches("(.*)\\.ann")) {
				//System.out.println(file.getName());
				HashMap<String, String> Ts = new HashMap<String, String>();
				
				String text = "";
				
				BufferedReader reader;
		        reader = new BufferedReader(new FileReader(file));
		        String line = reader.readLine();
		        while (line != null) {
		            //System.out.println(line);
		        	text += line + "\n";
		        	
		            line = reader.readLine();
		        }
		        
		        text = text.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		        text = text.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		        
		        Pattern pattern1 = Pattern.compile("(T\\d+)\\t(\\w+)\\s\\d+\\s\\d+\\t(.*)");
		        Matcher matcher1 = pattern1.matcher(text);
		        
		        while(matcher1.find()) {
		        	//System.out.println(matcher.group(1) + " " + matcher.group(3));
		        	Ts.put(matcher1.group(1), matcher1.group(3).toLowerCase());
		        }
		        
		        Pattern pattern2 = Pattern.compile("(R\\d+)\\t(\\w+)\\sArg1:(T\\d+)\\sArg2:(T\\d+)");
		        Matcher matcher2 = pattern2.matcher(text);
		        
		        while(matcher2.find()) {
		        	//System.out.println(matcher2.group(3) + " " + matcher2.group(4));
		        	GoldRelationships.put(Ts.get(matcher2.group(3)), Ts.get(matcher2.group(4)));
		        	GoldRelationships.put(Ts.get(matcher2.group(4)), Ts.get(matcher2.group(3)));
		        }
		   
		        reader.close();
			}
		}
		
//		for(String i : GoldRelationships.keySet()) {
//			System.out.println("value: " + GoldRelationships.get(i) + "\tkey: " + i);
//		}
		System.out.println(GoldRelationships);
		System.out.println("Size: " + GoldRelationships.size());
		System.out.println(GoldRelationships.containsEntry("alstonia macrophylla", "alstonerine"));
		
		endTime = System.nanoTime();
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void ReadXMLFiles() {
		// TODO Auto-generated method stub
		String taggedFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\TaggedBootstrap\\";
		String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\validation\\";
		
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		Reader fileReader = null;
		String pdfFileName = null;
		HashSet<Validation> validations = new HashSet<Validation>();
		for (File xmlFile : listFiles) {
			System.out.println(xmlFile.getName());
		}
	}
}
