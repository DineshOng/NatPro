package bratAnn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.WordUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;

import model.BiologicalActivity;
import model.CellLine;
import model.Compound;
import model.MedicinalPlant;
import model.Species;
import model.SpeciesPart;
import model.Validation;

public class ResultsEvaluation {
	private static String annFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\all\\";
	private static SetMultimap<String, String> GoldRelationships;
	private static int count;

	public static void main(String[] args) throws IOException {
		System.out.println("Main...");
        long startTime, endTime;
        startTime = System.nanoTime();
        
        count = 0;
        
		ReadGoldAnn();
		ReadXMLFiles();
		
		System.out.println("True Positive: " + count);
		
		endTime = System.nanoTime();
        System.err.println("Main Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void ReadGoldAnn() throws IOException {
		System.out.println("ReadGoldAnn...");
        long startTime, endTime;
        startTime = System.nanoTime();
		
        GoldRelationships = HashMultimap.create();
        
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
		        	//GoldRelationships.put(Ts.get(matcher2.group(4)), Ts.get(matcher2.group(3)));
		        }
		   
		        reader.close();
			}
		}
		
//		for(String i : GoldRelationships.keySet()) {
//			System.out.println("value: " + GoldRelationships.get(i) + "\tkey: " + i);
//		}
		System.out.println(GoldRelationships);
		System.out.println("Size: " + GoldRelationships.size());
		//System.out.println("Size: " + GoldRelationships.keySet().size());
		//System.out.println("Actual Size: " + GoldRelationships.size()*GoldRelationships.keySet().size());
		//System.out.println(GoldRelationships.containsEntry("alstonia macrophylla", "alstonerine"));
		
		endTime = System.nanoTime();
        //System.err.println("ReadGoldAnn Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void ReadXMLFiles() {
		System.out.println("ReadXMLFiles...");
        long startTime, endTime;
        startTime = System.nanoTime();
        
		String taggedFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\TaggedBootstrap\\";
		String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\validation\\";
		//String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\validation\\";
		
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		Reader fileReader = null;
		String pdfFileName = null;
		HashSet<Validation> validations = new HashSet<Validation>();
		for (File xmlFile : listFiles) {
			//System.out.println(xmlFile.getName());
			readXML(xmlFile);
		}
		
		endTime = System.nanoTime();
        //System.err.println("ReadXMLFiles Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void readXML(File xmlFile) {
		// CHECKS IF THE GENERATED XML FILE EXISTS
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile.getAbsoluteFile());
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Seed");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String tag1 = eElement.getElementsByTagName("Tag1").item(0).getTextContent();
					String tag2 = eElement.getElementsByTagName("Tag2").item(0).getTextContent();

					Element elementTag1 = (Element) eElement.getElementsByTagName("Tag1").item(0);
					Element elementTag2 = (Element) eElement.getElementsByTagName("Tag2").item(0);

					NodeList nameElementTag1 = elementTag1.getElementsByTagName("Name");
					NodeList nameElementTag2 = elementTag2.getElementsByTagName("Name");
					
					//System.out.println(nameElementTag1.item(0).getTextContent());
					for(int j = 0; j < nameElementTag2.getLength(); j++) {
						//System.out.println(nameElementTag2.item(j).getTextContent());
						if(GoldRelationships.containsEntry(nameElementTag1.item(0).getTextContent().toLowerCase(), nameElementTag2.item(j).getTextContent().toLowerCase())) {
						//if(GoldRelationships.containsEntry(nameElementTag2.item(j).getTextContent().toLowerCase(), nameElementTag1.item(0).getTextContent().toLowerCase())) {
							//System.out.println(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent());
							count++;
						}
					}
				}
			}
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		}
	}
}
