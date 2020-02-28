import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Coref {
	private String text;

	public Coref(String text) {
		this.text = text;
	}

	public String run() throws ParserConfigurationException, SAXException, IOException {
		String[] sentences = text.split("\n\n");
		String subject = "";
		
		for(int i=0; i<sentences.length; i++) {
			//String sentence = s.trim();
			//System.out.println(sentence);
			
			Pattern p = Pattern.compile("<([a-z]+)>([^<]+)<\\/[a-z]+>");
			Matcher m = p.matcher(sentences[i]);
			
			if(!sentences[i].contains("It")) {
				while(m.find()) {
					if(m.group(1).equals("compound") || m.group(1).equals("plant")) {
						subject = m.group();
						
						System.err.println(subject);
						break;
					} 
					
				}
			} else {
				sentences[i] = sentences[i].replaceAll("It", subject);
			}
			System.out.println(sentences[i].trim());
		}
		
		
		
		return String.join("\n\n", sentences);
	}
}
