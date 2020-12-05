package bratAnn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

public class ResultsEvaluation {
	private static String annFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\all\\";
	//String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\validation\\";
	//private static String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\validation (first run)\\";
	private static String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\validation(second run)\\";
	private static SetMultimap<String, String> GoldRelationships;
	private static HashMap<String, String> entityType;
	private static HashMap<String, String> relationshipType;
	private static HashSet<String> pairTruePositive;
	private static HashSet<String> pairFalsePositive;
	private static HashMap<String, Integer> goldRelTP;
	private static HashMap<String, Integer> relTP;
	private static HashMap<String, Integer> relFalseTP;
	private static int count;

	public static void main(String[] args) throws IOException {
		System.out.println("Main...");
        long startTime, endTime;
        startTime = System.nanoTime();
        
        entityType = new HashMap<String, String>();
        relationshipType = new HashMap<String, String>();
        pairTruePositive = new HashSet<String>();
        pairFalsePositive = new HashSet<String>();        
        goldRelTP = new HashMap<String, Integer>();
        relTP = new HashMap<String, Integer>();
        relFalseTP = new HashMap<String, Integer>();
        
        goldRelTP.put("hasBiologicalActivity", 0);
        goldRelTP.put("hasCompoundP", 0);
        goldRelTP.put("hasCompoundS", 0);
        goldRelTP.put("treats", 0);
        goldRelTP.put("hasSynonymPlantPart", 0);
        goldRelTP.put("affects", 0);
        goldRelTP.put("belongsToFamily", 0);
        goldRelTP.put("isLocatedIn", 0);
        goldRelTP.put("belongsToGenus", 0);
        
        relTP.put("hasBiologicalActivity", 0);
        relTP.put("hasCompoundP", 0);
        relTP.put("hasCompoundS", 0);
        relTP.put("treats", 0);
        relTP.put("hasSynonymPlantPart", 0);
        relTP.put("affects", 0);
        relTP.put("belongsToFamily", 0);
        relTP.put("isLocatedIn", 0);
        relTP.put("belongsToGenus", 0);
        
        relFalseTP.put("hasBiologicalActivity", 0);
        relFalseTP.put("hasCompoundP", 0);
        relFalseTP.put("hasCompoundS", 0);
        relFalseTP.put("treats", 0);
        relFalseTP.put("hasSynonymPlantPart", 0);
        relFalseTP.put("affects", 0);
        relFalseTP.put("belongsToFamily", 0);
        relFalseTP.put("isLocatedIn", 0);
        relFalseTP.put("belongsToGenus", 0);
        
        count = 0;
        
		ReadGoldAnn();
		ReadXMLFiles();
		
		//System.out.println("True Positive: " + count);
		System.out.println("" + pairTruePositive.size());
		System.out.println("" + pairFalsePositive.size());
		System.out.println("" + (pairTruePositive.size() + pairFalsePositive.size()));
		
		System.out.println("" + relTP.get("hasBiologicalActivity") +"//" + goldRelTP.get("hasBiologicalActivity"));
		System.out.println("" + relTP.get("hasCompoundP") +"//" + goldRelTP.get("hasCompoundP"));
		System.out.println("" + relTP.get("hasCompoundS") +"//" + goldRelTP.get("hasCompoundS"));
		System.out.println("" + relTP.get("treats") +"//" + goldRelTP.get("treats"));
		System.out.println("" + relTP.get("hasSynonymPlantPart") +"//" + goldRelTP.get("hasSynonymPlantPart"));
		System.out.println("" + relTP.get("affects") +"//" + goldRelTP.get("affects"));
		System.out.println("" + relTP.get("belongsToFamily") +"//" + goldRelTP.get("belongsToFamily"));
		System.out.println("" + relTP.get("isLocatedIn") +"//" + goldRelTP.get("isLocatedIn"));
		System.out.println("" + relTP.get("belongsToGenus") +"//" + goldRelTP.get("belongsToGenus"));
		
		/*System.out.println("\nhasBiologicalActivity: " + String. format("%.2f", (double) relTP.get("hasBiologicalActivity")/goldRelTP.get("hasBiologicalActivity")));
		System.out.println("hasCompound (PlantPart-Compound): " + String. format("%.2f", (double) relTP.get("hasCompoundP")/goldRelTP.get("hasCompoundP")));
		System.out.println("hasCompound (Synonym-Compound): " +  String. format("%.2f", (double) relTP.get("hasCompoundS")/goldRelTP.get("hasCompoundS")));
		System.out.println("treats: " +  String. format("%.2f", (double) relTP.get("treats")/goldRelTP.get("treats")));
		System.out.println("hasSynonymPlantPart: " +  String. format("%.2f", (double) relTP.get("hasSynonymPlantPart")/goldRelTP.get("hasSynonymPlantPart")));
		System.out.println("affects: " + String. format("%.2f", (double) relTP.get("affects")/goldRelTP.get("affects")));
		System.out.println("belongsToFamily: " + String. format("%.2f", (double) relTP.get("belongsToFamily")/goldRelTP.get("belongsToFamily")));
		System.out.println("isLocatedIn: " +  String. format("%.2f", (double) relTP.get("isLocatedIn")/goldRelTP.get("isLocatedIn")));
		System.out.println("belongsToGenus: " +  String. format("%.2f", (double) relTP.get("belongsToGenus")/goldRelTP.get("belongsToGenus")));
		
		
		System.out.println("True Positive: " + pairTruePositive.size());
		System.out.println("\nhasBiologicalActivity: " + relTP.get("hasBiologicalActivity") +"/" + goldRelTP.get("hasBiologicalActivity"));
		System.out.println("hasCompound (PlantPart-Compound): " + relTP.get("hasCompoundP") +"/" + goldRelTP.get("hasCompoundP"));
		System.out.println("hasCompound (Synonym-Compound): " + relTP.get("hasCompoundS") +"/" + goldRelTP.get("hasCompoundS"));
		System.out.println("treats: " + relTP.get("treats") +"/" + goldRelTP.get("treats"));
		System.out.println("hasSynonymPlantPart: " + relTP.get("hasSynonymPlantPart") +"/" + goldRelTP.get("hasSynonymPlantPart"));
		System.out.println("affects: " + relTP.get("affects") +"/" + goldRelTP.get("affects"));
		System.out.println("belongsToFamily: " + relTP.get("belongsToFamily") +"/" + goldRelTP.get("belongsToFamily"));
		System.out.println("isLocatedIn: " + relTP.get("isLocatedIn") +"/" + goldRelTP.get("isLocatedIn"));
		System.out.println("belongsToGenus: " + relTP.get("belongsToGenus") +"/" + goldRelTP.get("belongsToGenus"));
		 */
		
		//System.out.println("Count: " + count);
		
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
		        	entityType.put(matcher1.group(3).toLowerCase(), matcher1.group(2));
		        }
		        
		        Pattern pattern2 = Pattern.compile("(R\\d+)\\t(\\w+)\\sArg1:(T\\d+)\\sArg2:(T\\d+)");
		        Matcher matcher2 = pattern2.matcher(text);
		        
		        while(matcher2.find()) {
		        	//System.out.println(matcher2.group(3) + " " + matcher2.group(4));
		        	GoldRelationships.put(Ts.get(matcher2.group(3)), Ts.get(matcher2.group(4)));
		        	//GoldRelationships.put(Ts.get(matcher2.group(4)), Ts.get(matcher2.group(3)));
		        	relationshipType.put(Ts.get(matcher2.group(3)) + " : " + Ts.get(matcher2.group(4)), matcher2.group(2));
		        	
		        	//System.out.println(Ts.get(matcher2.group(3)) + " : " + Ts.get(matcher2.group(4)));
		        	
		        	eval(goldRelTP, matcher2.group(2), entityType.get(Ts.get(matcher2.group(3))));
		        	
		        	/*if(matcher2.group(2).equals("hasBiologicalActivity")) {
						int count = goldRelTP.get("hasBiologicalActivity");
						goldRelTP.remove("hasBiologicalActivity");
						count++;
						goldRelTP.put("hasBiologicalActivity", count);
					} else if(matcher2.group(2).equals("hasCompound")) {
						if(entityType.get(Ts.get(matcher2.group(3))).equals("PlantPart")) {
							int count = goldRelTP.get("hasCompoundP");
							goldRelTP.remove("hasCompoundP");
							count++;
							goldRelTP.put("hasCompoundP", count);
						} else if(entityType.get(Ts.get(matcher2.group(3))).equals("Synonym")) {
							int count = goldRelTP.get("hasCompoundS");
							goldRelTP.remove("hasCompoundS");
							count++;
							goldRelTP.put("hasCompoundS", count);
						}
					} else if(matcher2.group(2).equals("treats")) {
						int count = goldRelTP.get("treats");
						goldRelTP.remove("treats");
						count++;
						goldRelTP.put("treats", count);
					} else if(matcher2.group(2).equals("hasSynonymPlantPart")) {
						int count = goldRelTP.get("hasSynonymPlantPart");
						goldRelTP.remove("hasSynonymPlantPart");
						count++;
						goldRelTP.put("hasSynonymPlantPart", count);
					} else if(matcher2.group(2).equals("affects")) {
						int count = goldRelTP.get("affects");
						goldRelTP.remove("affects");
						count++;
						goldRelTP.put("affects", count);
					} else if(matcher2.group(2).equals("belongsToFamily")) {
						int count = goldRelTP.get("belongsToFamily");
						goldRelTP.remove("belongsToFamily");
						count++;
						goldRelTP.put("belongsToFamily", count);
					} else if(matcher2.group(2).equals("isLocatedIn")) {
						int count = goldRelTP.get("isLocatedIn");
						goldRelTP.remove("isLocatedIn");
						count++;
						goldRelTP.put("isLocatedIn", count);
					} else if(matcher2.group(2).equals("belongsToGenus")) {
						int count = goldRelTP.get("belongsToGenus");
						goldRelTP.remove("belongsToGenus");
						count++;
						goldRelTP.put("belongsToGenus", count);
					}*/
		        }
		   
		        reader.close();
			}
		}
		
//		for(String i : GoldRelationships.keySet()) {
//			System.out.println("value: " + GoldRelationships.get(i) + "\tkey: " + i);
//		}
		//System.out.println(GoldRelationships);
		System.out.println("Gold Pair Size: " + GoldRelationships.size());
		System.out.println("Gold Pair Size: " + relationshipType.size());
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
		
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
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
							//if(pairTruePositive.contains(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent()))
							//	count++;
							//else {
								pairTruePositive.add(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent());
								//System.out.println("Type: " + relationshipType.get(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent()) + "\t\t\t" + nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent());
								//System.out.format("Type: %21s Pair: %40s (%14s) %54s (%16s)\n", relationshipType.get(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent()), nameElementTag1.item(0).getTextContent(), entityType.get(nameElementTag1.item(0).getTextContent()), nameElementTag2.item(j).getTextContent(), entityType.get(nameElementTag2.item(j).getTextContent()));
								//pairTruePositive.add(nameElementTag2.item(j).getTextContent()  + " : " + nameElementTag1.item(0).getTextContent() );
								
								eval(relTP, relationshipType.get(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent()), entityType.get(nameElementTag1.item(0).getTextContent()));
								
						} else {
							pairFalsePositive.add(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent());
							//System.out.println(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent());
							//eval(relFalseTP, relationshipType.get(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent()), entityType.get(nameElementTag1.item(0).getTextContent()));
							//eval(relFalseTP, relationshipType.get(nameElementTag1.item(0).getTextContent() + " : " + nameElementTag2.item(j).getTextContent()), entityType.get(nameElementTag1.item(0).getTextContent()));
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
	
	
	public static void eval(HashMap<String, Integer> hm, String relType, String entType) {
		if(relType.equals("hasBiologicalActivity")) {
			int count = hm.get("hasBiologicalActivity");
			hm.remove("hasBiologicalActivity");
			count++;
			hm.put("hasBiologicalActivity", count);
		} else if(relType.equals("hasCompound")) {
			if(entType.equals("PlantPart")) {
				int count = hm.get("hasCompoundP");
				hm.remove("hasCompoundP");
				count++;
				hm.put("hasCompoundP", count);
			} else if(entType.equals("Synonym")) {
				int count = hm.get("hasCompoundS");
				hm.remove("hasCompoundS");
				count++;
				hm.put("hasCompoundS", count);
			}
		} else if(relType.equals("treats")) {
			int count = hm.get("treats");
			hm.remove("treats");
			count++;
			hm.put("treats", count);
		} else if(relType.equals("hasSynonymPlantPart")) {
			int count = hm.get("hasSynonymPlantPart");
			hm.remove("hasSynonymPlantPart");
			count++;
			hm.put("hasSynonymPlantPart", count);
		} else if(relType.equals("affects")) {
			int count = hm.get("affects");
			hm.remove("affects");
			count++;
			hm.put("affects", count);
		} else if(relType.equals("belongsToFamily")) {
			int count = hm.get("belongsToFamily");
			hm.remove("belongsToFamily");
			count++;
			hm.put("belongsToFamily", count);
		} else if(relType.equals("isLocatedIn")) {
			int count = hm.get("isLocatedIn");
			hm.remove("isLocatedIn");
			count++;
			hm.put("isLocatedIn", count);
		} else if(relType.equals("belongsToGenus")) {
			int count = hm.get("belongsToGenus");
			hm.remove("belongsToGenus");
			count++;
			hm.put("belongsToGenus", count);
		}
	}
}
