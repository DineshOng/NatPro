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
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {

        /*============================================================
                            String Builder
         =============================================================*/

        File xmlFile = new File("e71bd7f8bf891b0d55b5183fae88e027.xml");
        Reader fileReader = null;
        try {
            fileReader = new FileReader(xmlFile);
        } catch (FileNotFoundException e) {
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
        while( line != null){
            sb.append(line).append("\n");
            try {
                line = bufReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String xml2String = sb.toString();
        int count=1;
        int tcnt=0;
        String[] temp = new String[4];
        File tagFile = new File("sample.txt");
        try {
            Scanner scan = new Scanner(tagFile);
            while(scan.hasNextLine()){
                temp[tcnt] = scan.nextLine();
                tcnt++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String class1 = temp[0];
        String class2 = temp[2];
        String tag1 = "</"+temp[1]+">";
        String tag2 = "<"+temp[3]+">";
        String relation = "";
        String[] lines = xml2String.split("\\r?\\n");
        for(String ling: lines){
            count++;
            if(ling.contains(class1) && ling.contains(class2)) {
                System.out.println("line " + count + " : " + ling);
                relation = ling.substring(ling.indexOf(tag1) + tag1.length(), ling.indexOf(tag2));
                System.out.println(relation);
            }
        }

        File verbFile = new File("verbs.txt");
        try {
            Scanner scan = new Scanner(verbFile);
            while(scan.hasNextLine()){
                String currLine = scan.nextLine();
                if(relation.contains(currLine)) {
                    relation = currLine;
                    System.out.println(relation);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> matches = new ArrayList<String>();
        //==========================
        try {
            // initialize JWNL (this must be done before JWNL can be used)
            JWNL.initialize(new FileInputStream("file_properties.xml"));
            IndexWord word = Dictionary.getInstance().lookupIndexWord(POS.VERB, relation);

            Synset synset[] = word.getSenses();

            for(int i=0;i<synset.length;i++){
                //System.out.println(word.getSenses());
                for(Word synonym : synset[i].getWords())
                {
                    matches.add(synonym.getLemma());
                }
                //System.out.println(" ");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //===========================

        try {
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();


                Element element = document.createElement("Seed");
                document.appendChild(element);
            for(String ms: matches) {
                Element A = document.createElement("Tag1");
                A.appendChild(document.createTextNode(temp[1]));
                element.appendChild(A);

                Element B = document.createElement("Tag2");
                B.appendChild(document.createTextNode(temp[3]));
                element.appendChild(B);

                Element C = document.createElement("Relation");
                C.appendChild(document.createTextNode(ms));
                element.appendChild(C);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult("output.xml");
            transformer.transform(source,streamResult);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }




        /*============================================================
                            Docu Builder
         =============================================================*/
        /*DocumentBuilderFactory factory=  DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder =  factory.newDocumentBuilder();
            Document doc = builder.parse("e71bd7f8bf891b0d55b5183fae88e027.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }*/



    }
}
