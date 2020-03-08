import net.didion.jwnl.JWNL;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {

        File Folder = new File("Tagged/");
        File[] listFiles = Folder.listFiles();
        Reader fileReader = null;

        for(File xmlFile : listFiles) {
            /*=============================
            File Reader
            ==============================*/
            try {

                fileReader = new FileReader(xmlFile);
            } catch (
                    FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader bufReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                line = bufReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (line != null) {
                sb.append(line).append("\n");
                try {
                    line = bufReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String xml2String = sb.toString();
            String[] lines = xml2String.split("\\r?\\n");

            SearchSeeds sSeed = new SearchSeeds(lines);
            for(String ling : lines){
                //sSeed.findPair(ling,);
            }



        }//End of file[] Loop
    }
}
