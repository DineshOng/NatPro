package bratAnn;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import preprocessing.Tagger;

public class TagToAnn {
	static Map<Integer, String> stars;
	
	public static void main(String[] args) throws IOException {
//		try {
//			new TagToAnn().etag();
//		} catch (NoSuchAlgorithmException | ClassCastException | ClassNotFoundException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		long startTime, endTime;
        startTime = System.nanoTime ();
		
		String star;
		stars = new HashMap<Integer, String>();
		for(int i=1; i<251; i++) {
			star = "";
			for(int j=0; j<i; j++) {
				star += "*";
			}
			//System.out.println(i);
			stars.put(i, star);
		}
		System.out.println(stars.size());
		
		String[] names = {"Artocarpus_ovatus", "Brassica_oleracea_varieties", "Cycas_edentata", "Duranta_erecta", "Dysoxylum_gaudichaudianum", "Eucalyptus_deglupta", "Garcinia_mangostana",
						  "Aleurites_moluccana", "Cordia_dichotoma", "Cycas_lacrimans", "Cycas_sancti-lasallei", "Dioscorea_luzonensis", "Hoya_meliflua",
						  "Alstonia_macrophylla", "Andrographis_paniculata", "Ficus_pseudopalma_ulmifolia", "Macromitrium_orthostichum", "Pleurotus_eryngii_Flammulina_velutipes",
						  "Alstonia_scholaris", "Aphanamixis_polystachya", "Cycas_vespertilio", "Cymodocea_rotundata", "Dracontomelon_dao", "Macaranga_tanarius", "Samanea_saman"};
		
		for(String name : names)
			run("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Preprocessed\\" + name + " tag.txt", name);
		
		endTime = System.nanoTime ();
	    System.err.println("[PDF to TXT Converter] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void run(String filename, String name) throws IOException {
		String annTxt = "", notag = "";
		int ctr = 1;
		
		FileInputStream inputStream = new FileInputStream(filename);
		String text = "";
		try {
		    text = IOUtils.toString(inputStream);
		} finally {
		    inputStream.close();
		}
		
		Pattern pattern = Pattern.compile("<([A-Za-z]+)>([^>]+)(<\\/[A-Za-z]+>)");
		Matcher matcher = pattern.matcher(text);
		
		notag = text.replaceAll("<\\/?\\w+>", "");
		text = notag;
		
		while(matcher.find()) {
			int len = matcher.group(2).length();
			
			Pattern pattern2 = Pattern.compile(matcher.group(2).replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)").replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]"));
			Matcher matcher2 = pattern2.matcher(text);
			
			if(matcher2.find()) {
				text = text.replaceFirst(matcher2.group().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)").replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]"), stars.get(len));
				System.out.println("T"+ctr+"\t"+matcher.group(1)+"\t"+matcher2.start()+"\t"+matcher2.end()+"\t"+matcher.group(2));
				annTxt += "T"+ctr+"\t"+matcher.group(1)+" "+matcher2.start()+" "+matcher2.end()+"\t"+matcher.group(2)+"\n";
				ctr++;
			}
        }
		
		java.io.FileWriter fw2 = new java.io.FileWriter("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\all\\" + name + "_notag.txt");
        fw2.write(notag.trim());
        fw2.close();
		
		java.io.FileWriter fw3 = new java.io.FileWriter("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\all\\" + name + "_notag.ann");
        fw3.write(annTxt.trim());
        fw3.close();	
	}
	
	public void etag() throws NoSuchAlgorithmException, ClassCastException, ClassNotFoundException, IOException {
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\dd\\Artocarpus_ovatus.pdf", "Artocarpus_ovatus");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\dd\\Brassica_oleracea_varieties.pdf", "Brassica_oleracea_varieties");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\dd\\Cycas_edentata.pdf", "Cycas_edentata");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\dd\\Duranta_erecta.pdf", "Duranta_erecta");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\dd\\Dysoxylum_gaudichaudianum.pdf", "Dysoxylum_gaudichaudianum");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\dd\\Eucalyptus_deglupta.pdf", "Eucalyptus_deglupta");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\dd\\Garcinia_mangostana.pdf", "Garcinia_mangostana");
		
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\ee\\Aleurites_moluccana.pdf", "Aleurites_moluccana");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\ee\\Cordia_dichotoma.pdf", "Cordia_dichotoma");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\ee\\Cycas_lacrimans.pdf", "Cycas_lacrimans");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\ee\\Cycas_sancti-lasallei.pdf", "Cycas_sancti-lasallei");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\ee\\Dioscorea_luzonensis.pdf", "Dioscorea_luzonensis");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\ee\\Hoya_meliflua.pdf", "Hoya_meliflua");
		
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\no\\Alstonia_macrophylla.pdf", "Alstonia_macrophylla");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\no\\Andrographis_paniculata.pdf", "Andrographis_paniculata");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\no\\Ficus_pseudopalma_ulmifolia.pdf", "Ficus_pseudopalma_ulmifolia");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\no\\Macromitrium_orthostichum.pdf", "Macromitrium_orthostichum");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\no\\Pleurotus_eryngii_Flammulina_velutipes.pdf", "Pleurotus_eryngii_Flammulina_velutipes");
		
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\pa\\Alstonia_scholaris.pdf", "Alstonia_scholaris");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\pa\\Aphanamixis_polystachya.pdf", "Aphanamixis_polystachya");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\pa\\Cycas_vespertilio.pdf", "Cycas_vespertilio");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\pa\\Cymodocea_rotundata.pdf", "Cymodocea_rotundata");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\pa\\Dracontomelon_dao.pdf", "Dracontomelon_dao");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\pa\\Macaranga_tanarius.pdf", "Macaranga_tanarius");
		new Tagger("C:\\Users\\Unknown\\Documents\\GitHub\\natpro-ann\\pa\\Samanea_saman.pdf", "Samanea_saman");
	}
}
