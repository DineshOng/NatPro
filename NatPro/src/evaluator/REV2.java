package evaluator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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

public class REV2 {
	//C:\Users\Unknown\Documents\GitHub\natpro-ann\all
	
//	//1st run
//	private static String annFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\Tagged Documents\\Tagged 1st\\";
//	private static String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\Tagged Documents\\Validations\\validation 1st\\";
	
//	// 2nd run
//	private static String annFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\Tagged Documents\\Tagged 2nd\\";
//	private static String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\Tagged Documents\\Validations\\validation 2nd\\";
	
	// 3rd run
	private static String annFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\Tagged Documents\\Tagged 3rd\\";
	private static String validationFolder = "C:\\Users\\Unknown\\Documents\\GitHub\\NatPro\\BootStrapping\\Tagged Documents\\Validations\\validation 3rd\\";
		
	public static HashSet<String> gold_rel_set;
	private static HashMap<String, String> gold_entityType_map;
	private static HashMap<String, String> gol_relType_map;
	
	private static HashMap<String, Integer> gold_rel_map;
	
	private static HashMap<String, Double> eval_acc_map;
	private static HashMap<String, Double> eval_pre_map;
	private static HashMap<String, Double> eval_rec_map;
	private static HashMap<String, Double> eval_fme_map;
	
	public static HashSet<String> res_rel_tp_set;
	public static HashSet<String> res_rel_set;
	private static HashMap<String, String> res_entityType_map;
	private static HashMap<String, String> res_relType_map;
	
	private static HashMap<String, Integer> res_rel_tp_map;
	private static HashMap<String, Integer> res_rel_map;
	
	private static int ctr = 0;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Main...");
        long startTime, endTime;
        startTime = System.nanoTime();
        
        gold_rel_map = new HashMap<String, Integer>();
		initMap0(gold_rel_map);
		
		res_rel_tp_map = new HashMap<String, Integer>();
		initMap0(res_rel_tp_map);
		
		res_rel_map = new HashMap<String, Integer>();
		initMap0(res_rel_map);
		
		eval_acc_map = new HashMap<String, Double>();
		eval_pre_map = new HashMap<String, Double>();
		eval_rec_map = new HashMap<String, Double>();
		eval_fme_map = new HashMap<String, Double>();
		
		ReadGoldAnn();
		ReadXMLFiles();
		
		//Evaluate();
		
		printResult();
		
		endTime = System.nanoTime();
        System.err.println("Main Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void Evaluate() {
//		if(gold_rel_set.contains(ent1 + " : " + ent2)) {
//			res_rel_tp_set.add(ent1 + " : " + ent2);
//			//System.out.println(ent1 + " : " + ent2 + " " + gol_relType_map.get(ent1 + " : " + ent2));
//			eval(res_rel_tp_map, gol_relType_map.get(ent1 + " : " + ent2), gold_entityType_map.get(ent1));
//		}
		int ctr = 0;
		for(String goldPair : gold_rel_set) {
			if(res_rel_set.contains(goldPair)) {
				//System.out.println(goldPair);
				ctr++;
				res_rel_tp_set.add(goldPair);
				//eval(res_rel_tp_map, gol_relType_map.get(goldPair), gold_entityType_map.get(ent1));
			}
		}
		System.out.println(ctr);
	}
	
	public static void ReadGoldAnn() throws IOException {
		System.out.println("ReadGoldAnn...");
        long startTime, endTime;
        startTime = System.nanoTime();
		
        gold_rel_set = new HashSet<String>();
        gold_entityType_map = new HashMap<String, String>();
        gol_relType_map = new HashMap<String, String>();
        
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
		        	text += line + "\n";
		            line = reader.readLine();
		        }
		        
		        text = text.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		        text = text.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		        
		        Pattern pattern1 = Pattern.compile("(T\\d+)\\t(\\w+)\\s\\d+\\s\\d+\\t(.*)");
		        Matcher matcher1 = pattern1.matcher(text);
		        
		        while(matcher1.find()) {
		        	Ts.put(matcher1.group(1), matcher1.group(3).toLowerCase());
		        	gold_entityType_map.put(matcher1.group(3).toLowerCase(), matcher1.group(2));
		        }
		        
		        Pattern pattern2 = Pattern.compile("(R\\d+)\\t(\\w+)\\sArg1:(T\\d+)\\sArg2:(T\\d+)");
		        Matcher matcher2 = pattern2.matcher(text);
		        
		        while(matcher2.find()) {
		        	String ent1 = Ts.get(matcher2.group(3));
		        	String ent2 = Ts.get(matcher2.group(4));
		        	
		        	if(!gold_rel_set.contains(ent1 + " : " + ent2)) {
		        		gold_rel_set.add(ent1 + " : " + ent2);
		        		gol_relType_map.put(ent1 + " : " + ent2, matcher2.group(2));
		        		eval(gold_rel_map, matcher2.group(2), gold_entityType_map.get(ent1));
		        	} else {
		        		//System.out.println(ent1 + " : " + ent2);
		        	}
		        	//System.out.println(ent1 + " : " + ent2);
		        }
		   
		        reader.close();
			}
		}
		
		endTime = System.nanoTime();
        System.err.println("ReadGoldAnn Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void readXML(File xmlFile) {
		
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
					
					String relType = eElement.getElementsByTagName("Category").item(0).getTextContent();
					
					String entType1 = tag1.replace(nameElementTag1.item(0).getTextContent(), "").trim();
					String entType2 = tag2.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
					entType2 = entType2.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
					entType2 = entType2.replaceAll("\\s+", "").replaceAll("\n", "");
							
					String txt = "";
					for(int j = 0; j < nameElementTag2.getLength(); j++) {
						txt += nameElementTag2.item(j).getTextContent() + "";
					}
					
					txt = txt.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
					txt = txt.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
					txt = txt.replaceAll("\\s+", "").replaceAll("\n", "");
					
					//System.out.println("txt: " + txt);
					
					entType2 = entType2.replace(txt, "");
					//System.out.println(entType2.equals(txt));
					//System.out.println("entType1: " + entType1 + "\t\t" + "entType2: " + entType2.trim());
					
					for(int j = 0; j < nameElementTag2.getLength(); j++) {
						String ent1 = nameElementTag1.item(0).getTextContent().toLowerCase();
			        	String ent2 = nameElementTag2.item(j).getTextContent().toLowerCase();
			        	
						if(gold_rel_set.contains(ent1 + " : " + ent2)) {
							if(!res_rel_tp_set.contains(ent1 + " : " + ent2)) {
								res_rel_tp_set.add(ent1 + " : " + ent2);
								//System.out.println(ent1 + " : " + ent2 + " " + gol_relType_map.get(ent1 + " : " + ent2));
								if(res_rel_tp_set.contains(ent1 + " : " + ent2)) {
									eval(res_rel_tp_map, gol_relType_map.get(ent1 + " : " + ent2), gold_entityType_map.get(ent1));
								}
							}
						}
						if(!res_rel_set.contains(ent1 + " : " + ent2)) {
							res_rel_set.add(ent1 + " : " + ent2);
							res_relType_map.put(ent1 + " : " + ent2, relType);
							
							if(res_rel_set.contains(ent1 + " : " + ent2)) {
								//System.out.println(ent1 + " : " + ent2);
								eval(res_rel_map, relType, entType1);
							}
			        		
							//ctr++;
						}
						//System.out.println(ent1 + " : " + ent2);
						//eval(res_rel_map, gol_relType_map.get(ent1 + " : " + ent2), gold_entityType_map.get(ent1));
						
					}
				}
			}
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		}
	}
	
	public static void ReadXMLFiles() {
		
		System.out.println("ReadXMLFiles...");
        long startTime, endTime;
        startTime = System.nanoTime();
        
        res_rel_tp_set = new HashSet<String>();
        res_rel_set = new HashSet<String>();
    	res_entityType_map = new HashMap<String, String>();
    	res_relType_map = new HashMap<String, String>();
		
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		for (File xmlFile : listFiles) {
			//System.out.println(xmlFile.getName());
			readXML(xmlFile);
		}
		
		endTime = System.nanoTime();
        System.err.println("ReadXMLFiles Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void initMap0(HashMap<String, Integer> hm) {
		hm.put("hasBiologicalActivity", 0);
		hm.put("hasCompoundP", 0);
		hm.put("hasCompoundS", 0);
		hm.put("hasCompoundM", 0);
		hm.put("treats", 0);
		hm.put("hasSynonymPlantPart", 0);
		hm.put("affects", 0);
		hm.put("belongsToFamily", 0);
		hm.put("isLocatedIn", 0);
		hm.put("belongsToGenus", 0);
		
		hm.put("hasPreparation", 0);
		hm.put("hasSynonym", 0);
		hm.put("hasMedicinalPlantPart", 0);
		hm.put("appliedTo", 0);
		hm.put("utilizePart", 0);
		hm.put("hasSynonymParent", 0);
		hm.put("belongsToClass", 0);
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
			} else if(entType.equals("MedicinalPlant")) {
				int count = hm.get("hasCompoundM");
				hm.remove("hasCompoundM");
				count++;
				hm.put("hasCompoundM", count);
			} else {
				System.out.println(entType + " " + relType);
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
		} else if(relType.equals("hasPreparation")) {
			int count = hm.get("hasPreparation");
			hm.remove("hasPreparation");
			count++;
			hm.put("hasPreparation", count);
		} else if(relType.equals("hasSynonym")) {
			int count = hm.get("hasSynonym");
			hm.remove("hasSynonym");
			count++;
			hm.put("hasSynonym", count);
		} else if(relType.equals("hasMedicinalPlantPart")) {
			int count = hm.get("hasMedicinalPlantPart");
			hm.remove("hasMedicinalPlantPart");
			count++;
			hm.put("hasMedicinalPlantPart", count);
		} else if(relType.equals("appliedTo")) {
			int count = hm.get("appliedTo");
			hm.remove("appliedTo");
			count++;
			hm.put("appliedTo", count);
		} else if(relType.equals("utilizePart")) {
			int count = hm.get("utilizePart");
			hm.remove("utilizePart");
			count++;
			hm.put("utilizePart", count);
		} else if(relType.equals("hasSynonymParent")) {
			int count = hm.get("hasSynonymParent");
			hm.remove("hasSynonymParent");
			count++;
			hm.put("hasSynonymParent", count);
		} else if(relType.equals("belongsToClass")) {
			int count = hm.get("belongsToClass");
			hm.remove("belongsToClass");
			count++;
			hm.put("belongsToClass", count);
		} else {
			//System.out.println("entType: " + entType + "\trelType: " + relType);
		}
	}
	
	public static String getScore_Accuracy(String relType) {
		return String.format("%.2f", (double) res_rel_tp_map.get(relType)/(res_rel_tp_map.get(relType)+(res_rel_map.get(relType)-res_rel_tp_map.get(relType))+(gold_rel_map.get(relType)-res_rel_tp_map.get(relType)))*100);
	}
	
	public static String getScore_Precision(String relType) {
		return String.format("%.2f", (double) res_rel_tp_map.get(relType)/(res_rel_tp_map.get(relType)+(res_rel_map.get(relType)-res_rel_tp_map.get(relType)))*100);
	}
	
	public static String getScore_Recall(String relType) {
		return String.format("%.2f", (double) res_rel_tp_map.get(relType)/(res_rel_tp_map.get(relType)+(gold_rel_map.get(relType)-res_rel_tp_map.get(relType)))*100);
	}
	
	public static String getScore_Fmeasure(String relType) {
		Double prec = Double.parseDouble(getScore_Precision(relType));
		Double rec = Double.parseDouble(getScore_Recall(relType));
		return String.format("%.2f", (double) (2*prec*rec)/(prec+rec));
	}
	
	public static int getTP(String relType) {
		return res_rel_tp_map.get(relType);
	}
	
	public static int getFP(String relType) {
		return res_rel_map.get(relType)-res_rel_tp_map.get(relType);
	}
	
	public static int getFN(String relType) {
		return gold_rel_map.get(relType)-res_rel_tp_map.get(relType);
	}
	
	public static int getGold(String relType) {
		return gold_rel_map.get(relType);
	}
	
	public static int getRet(String relType) {
		return res_rel_map.get(relType);
	}
	
	public static void printResult() {
		System.out.println("Gold Pair Size: " + gold_rel_set.size());
		System.out.println("Result Pair Size: " + res_rel_set.size());
		System.out.println("True Positive: " + res_rel_tp_set.size());
		System.out.println("False Positive: " + (res_rel_set.size()-res_rel_tp_set.size()));
		System.out.println("False Negative: " + (gold_rel_set.size()-res_rel_tp_set.size()));
		int tp = res_rel_tp_set.size();
		int fp = res_rel_set.size()-res_rel_tp_set.size();
		int fn = gold_rel_set.size()-res_rel_tp_set.size();
		
		double acc = (double) tp/(tp+fp+fn)*100;
		double prec = (double) tp/(tp+fp)*100;
		double rec = (double) tp/(tp+fn)*100;
		double fm = (double) (2*prec*rec)/(prec+rec);
		
		//double x = (double) res_rel_tp_map.get("hasBiologicalActivity")/(res_rel_tp_map.get("hasBiologicalActivity")+(res_rel_map.get("hasBiologicalActivity")-res_rel_tp_map.get("hasBiologicalActivity"))+(gold_rel_map.get("hasBiologicalActivity")-res_rel_tp_map.get("hasBiologicalActivity")))*100;
		//eval_acc_map.put("hasBiologicalActivity", (double) x);
		System.out.println("\n" + getScore_Accuracy("hasBiologicalActivity"));
		System.out.println(getScore_Precision("hasBiologicalActivity"));
		System.out.println(getScore_Recall("hasBiologicalActivity"));
		System.out.println(getScore_Fmeasure("hasBiologicalActivity"));
		System.out.println(getTP("hasBiologicalActivity") + " " + getFP("hasBiologicalActivity") + " " + getFN("hasBiologicalActivity") + " " + getGold("hasBiologicalActivity") + " " + getRet("hasBiologicalActivity"));
		
		System.out.println("\nResults: " + "\nAccuracy: " + String.format("%.2f", acc) + "\nPrecision: " + String.format("%.2f", prec) + "\nRecall: " + String.format("%.2f", rec) + "\nf-measure: " + String.format("%.2f", fm));
		
		//HashMap<String, Integer> relTP = new HashMap<String, Integer>();
		System.out.println("\n\n" + res_rel_tp_map.get("hasBiologicalActivity") +"//" + gold_rel_map.get("hasBiologicalActivity"));
		System.out.println("" + res_rel_tp_map.get("hasCompoundP") +"//" + gold_rel_map.get("hasCompoundP"));
		System.out.println("" + res_rel_tp_map.get("hasCompoundS") +"//" + gold_rel_map.get("hasCompoundS"));
		System.out.println("" + res_rel_tp_map.get("hasCompoundM") +"//" + gold_rel_map.get("hasCompoundM"));
		System.out.println("" + res_rel_tp_map.get("treats") +"//" + gold_rel_map.get("treats"));
		System.out.println("" + res_rel_tp_map.get("hasSynonymPlantPart") +"//" + gold_rel_map.get("hasSynonymPlantPart"));
		System.out.println("" + res_rel_tp_map.get("affects") +"//" + gold_rel_map.get("affects"));
		System.out.println("" + res_rel_tp_map.get("belongsToFamily") +"//" + gold_rel_map.get("belongsToFamily"));
		System.out.println("" + res_rel_tp_map.get("isLocatedIn") +"//" + gold_rel_map.get("isLocatedIn"));
		System.out.println("" + res_rel_tp_map.get("belongsToGenus") +"//" + gold_rel_map.get("belongsToGenus"));
		System.out.println("" + res_rel_tp_map.get("hasPreparation") +"//" + gold_rel_map.get("hasPreparation"));
		System.out.println("" + res_rel_tp_map.get("hasSynonym") +"//" + gold_rel_map.get("hasSynonym"));
		System.out.println("" + res_rel_tp_map.get("hasMedicinalPlantPart") +"//" + gold_rel_map.get("hasMedicinalPlantPart"));
		System.out.println("" + res_rel_tp_map.get("appliedTo") +"//" + gold_rel_map.get("appliedTo"));
		System.out.println("" + res_rel_tp_map.get("utilizePart") +"//" + gold_rel_map.get("utilizePart"));
		System.out.println("" + res_rel_tp_map.get("hasSynonymParent") +"//" + gold_rel_map.get("hasSynonymParent"));
		System.out.println("" + res_rel_tp_map.get("belongsToClass") +"//" + gold_rel_map.get("belongsToClass"));
		
		System.out.println("\n\n" + res_rel_map.get("hasBiologicalActivity"));
		System.out.println("" + res_rel_map.get("hasCompoundP"));
		System.out.println("" + res_rel_map.get("hasCompoundS"));
		System.out.println("" + res_rel_map.get("hasCompoundM"));
		System.out.println("" + res_rel_map.get("treats"));
		System.out.println("" + res_rel_map.get("hasSynonymPlantPart"));
		System.out.println("" + res_rel_map.get("affects"));
		System.out.println("" + res_rel_map.get("belongsToFamily"));
		System.out.println("" + res_rel_map.get("isLocatedIn"));
		System.out.println("" + res_rel_map.get("belongsToGenus"));
		System.out.println("" + res_rel_map.get("hasPreparation"));
		System.out.println("" + res_rel_map.get("hasSynonym"));
		System.out.println("" + res_rel_map.get("hasMedicinalPlantPart"));
		System.out.println("" + res_rel_map.get("appliedTo"));
		System.out.println("" + res_rel_map.get("utilizePart"));
		System.out.println("" + res_rel_map.get("hasSynonymParent"));
		System.out.println("" + res_rel_map.get("belongsToClass"));
	}
}
