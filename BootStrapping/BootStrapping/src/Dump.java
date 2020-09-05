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
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
public class Dump {

    /*============================================================
                            String Builder
         =============================================================

    File xmlFile = new File("376734970d0a87a0c6077dc10de28ceb.xml");
    Reader fileReader = null;
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


        System.out.println(tagFile.length());
    String[] temp = new String[(int)tagFile.length()];
        try {
        Scanner scan = new Scanner(tagFile);
        while(scan.hasNextLine()){
            temp[tcnt] = scan.nextLine();

            tcnt++;
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }



    String tag1 = temp[0].substring(temp[0].indexOf(":")+1);
    String tag2 = temp[1].substring(temp[1].indexOf(":")+1);

    //System.out.println(temp[3]);
    HashMap<String, String> map = new HashMap<String, String>();

    int tempctr = 3;
        while(temp[tempctr] != null){
        //System.out.println(temp[tempctr]);
        String[] classes = temp[tempctr].split(";", 2);
        if(classes.length >=2){
            String class1 = classes[0];
            String class2 = classes[1];
            map.put(class1,class2);
        }else{
            System.out.println("ignoring line: " + tempctr);
        }
        tempctr++;
    }

    String relation = "";
    String[] lines = xml2String.split("\\r?\\n");

        for(String class1 : map.keySet()){
        String class2 = map.get(class1);
        class1 = class1.toLowerCase();
        class2 = class2.toLowerCase();
        //System.out.println(class1 + ":" + class2);
        //System.err.println("title");
        for(String ling: lines){
            ling= ling.toLowerCase();
            //System.out.println("body");
            count++;
            if(ling.contains(class1) && ling.contains(class2)) {
                //System.out.println(class1 + ":" + class2);
                //System.out.println("line " + count + " : " + ling);
                //String[] phrase = ling.split("<\\/location>.+<species>");
                //System.out.println(phrase[0]);
                Pattern p = Pattern.compile("<\\/["+tag1+"*"+tag2+"]+>.+<["+tag1+"*"+tag2+"]+>");
                Matcher m = p.matcher(ling);
                while(m.find()) {
                    //System.out.println(m.group());
                    relation= m.group();
                }

                //System.out.println(relation);

            }
        }
    }

    relation = relation.replaceAll("<\\/?[a-z]+>", "");
        System.out.println(relation);
    //String class1 = temp[0];
    //String class2 = temp[2];







    TreeSet<String> matches = new TreeSet<String>();

    //brute force searching
    File verbFile = new File("verbs.txt");
        try {
        Scanner scan = new Scanner(verbFile);
        while(scan.hasNextLine()){
            String currLine = scan.nextLine();
            if(relation.matches("(.*)"+currLine+"(.*)")) {
                relation = currLine;
                matches.add(relation);
                System.out.println(relation);
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
        System.out.println(relation);
    //ArrayList<String> matches = new ArrayList<String>();

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
                //System.out.println(synonym.getLemma());
            }
            //System.out.println(" ");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    //===========================

    //remove duplicates
    //for(String Test : unique)
    //  System.out.println(Test);



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

        Element A = document.createElement("Tag1");
        A.appendChild(document.createTextNode(tag1));
        element.appendChild(A);

        Element B = document.createElement("Tag2");
        B.appendChild(document.createTextNode(tag2));
        element.appendChild(B);

        Element C = document.createElement("Relation");
        //C.appendChild(document.createTextNode(ms));
        element.appendChild(C);
        for(String ms: matches) {
            Element D = document.createElement("Pattern");
            D.appendChild(document.createTextNode(ms));
            C.appendChild(D);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult streamResult = new StreamResult("outputDump.xml");
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
        }


}*/
