package brat.ann;

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
		
		run("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Preprocessed\\"+"Alstonia_macrophylla"+" tag.txt");
	}
	
	public static void run(String filename) throws IOException {
		FileInputStream inputStream = new FileInputStream(filename);
		String text = "";
		try {
		    text = IOUtils.toString(inputStream);
		} finally {
		    inputStream.close();
		}
		
		Pattern pattern = Pattern.compile("<([A-Za-z]+)>([^>]+)<\\/[A-Za-z]+>");
		Matcher matcher = pattern.matcher(text);
		
		System.out.println(text);
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
