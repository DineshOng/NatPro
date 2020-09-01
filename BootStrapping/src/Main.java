import com.sun.deploy.util.ArrayUtil;
import edu.stanford.nlp.io.EncodingPrintWriter;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.util.ArrayUtils;
import jdk.internal.org.xml.sax.SAXException;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class Main {

    public static void main(String[] args) {

        File Folder = new File("TaggedSample/");
        File[] listFiles = Folder.listFiles();
        Reader fileReader = null;

        MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");
        String V = "(.*)_VB[A-Z]*(.*)";
        String W = "(.*)_[DJNPR][NJBTR]P*(.*)";
        String P = "(.*)_[IRT][NPO](.*)";




        for(File xmlFile : listFiles) {

            File seedFolder = new File("SeedsPossible/");
            File[] seedList = seedFolder.listFiles();
            for(File seeds: seedList) {

                String hashxml = xmlFile.getName().replaceAll(".xml","");


                String e1=null; String e2=null;
                TreeSet<String> relation = new TreeSet<String>();
                TreeSet<String> e1Name = new TreeSet<String>();
                TreeSet<String> e2Name = new TreeSet<String>();

                String seedData = readFile(seeds, fileReader).toString();
                String[] seedLines = seedData.split("\\r?\\n");
                ArrayList<String> seedEntity = new ArrayList<String>();
                ArrayList<String> lines = new ArrayList<String>();
                for(String sling: seedLines){
                    seedEntity.add(sling);
                    //System.out.println(sling);
                }

                //entity retrieval
                e1 = getTag(seedEntity.get(0));
                e2 = getTag(seedEntity.get(1));

                //System.out.println("Seeds retrieved: "+e1+" "+e2);

                ArrayList<String> seedMap1 = new ArrayList<String>();
                ArrayList<String> seedMap2 = new ArrayList<String>();
                for(int i=3; i<seedEntity.size(); i++){
                    addMap(seedMap1,seedMap2,seedEntity,i);
                }



                String xml2String = readFile(xmlFile, fileReader).toString();
                String[] line = xml2String.split("\\r?\\n");
                Collections.addAll(lines,line);



                for (String pLine : lines) {
                    pLine = pLine.toLowerCase();
                    // System.out.println(pLine+"/n");
                    addPreprocessing(relation,pLine,e1,e2,lines);
                    //System.out.println("Preprocessing finished");

                }//end of pLine loop of preprocessing


                //System.out.println("Started Running");
                //for(String class1 : seedMap.keySet())
                for(int i = 0; i< seedMap1.size();i++){
                    String class1 = seedMap1.get(i);
                    String class2 = seedMap2.get(i);
                    class1 = class1.toLowerCase();
                    class2 = class2.toLowerCase();
                    //System.out.println("Classes: "+ class1+" "+class2);


                    //System.out.println(e1+": "+class1+";"+e2+": "+class2);



                    for (String pLine : lines) {
                        pLine = pLine.toLowerCase();
                        addRelation(relation,pLine,class1,class2,e1,e2,e1Name,e2Name);

                        TreeSet<String> relationTemp = new TreeSet<String>();
                        addRelation(relationTemp,pLine,class1,class2,e1,e2,e1Name,e2Name);
                        //System.out.println("Relation finished");


                        TreeSet<String> ValidationRelation = new TreeSet<String>();
                        TreeSet<String> Verbmatches = new TreeSet<String>();
                        TreeSet<String> Pronounmatches = new TreeSet<String>();
                        TreeSet<String> Wordmatches = new TreeSet<String>();
                        TreeSet<String> validation = new TreeSet<String>();

                        for(String rTLine: relationTemp){
                            //System.out.println("relation: "+rLine);
                            POSTagger(ValidationRelation,rTLine,V,P,W,tagger);
                        }

                        for(String seedWord: ValidationRelation){
                            //System.out.println(seedWord+" is seedword");
                            try {
                                String tagged = tagger.tagString(seedWord);
                                //String temp = tagged.substring(tagged.indexOf("_")+1);
                                if(tagged.matches(V)){
                                    Verbmatches.add(seedWord);
                                }else if(tagged.matches(P)){
                                    Pronounmatches.add(seedWord);
                                }else if(tagged.matches(W)){
                                    Wordmatches.add(seedWord);
                                }
                                //matches.clear();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //System.out.println(seedWord);
                        }//End of wordnet loop


                        if(Wordmatches.isEmpty() ){
                            if(Pronounmatches.isEmpty()){
                                for(String p: Verbmatches){
                                    validation.add(p);
                                }
                            }
                            else{
                                for(String p: Verbmatches){
                                    for(String j: Pronounmatches){
                                        validation.add(p+" "+j);
                                        //deleteTest = i+" " +j;//ADD THESE FOR THE DELETE FUNCTION
                                    }
                                }
                            }

                        }
                        else{
                            for(String p: Verbmatches){
                                for(String j: Wordmatches) {
                                    for (String k : Pronounmatches) {
                                        validation.add(p + " " + j+ " "+ k);
                                    }
                                }
                            }
                        }




                        TreeSet<String> validationTemp = validation;
                        class1 = class1.replace("\\","");
                        class2 = class2.replace("\\","");
                        File validationOutput = new File("validation/"+hashxml+"-"+class1+"-"+class2+".xml");
                        if(validationOutput.exists()){

                            DocumentBuilderFactory Vfactory=  DocumentBuilderFactory.newInstance();
                            try {
                                DocumentBuilder builder =  Vfactory.newDocumentBuilder();
                                Document doc = builder.parse("validation/"+hashxml+"-"+class1+"-"+class2+".xml");
                                doc.getDocumentElement().normalize();
                                //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                                NodeList nodeList = doc.getElementsByTagName("Seed");
                                for(int h = 0; h< nodeList.getLength();h++) {
                                    Node nNode = nodeList.item(h);
                                    //System.out.println("Node Name: " + nNode.getNodeName());
                                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                        //System.out.println("I am here");
                                        Element eElement = (Element) nNode;
                                        int eCount= eElement.getElementsByTagName("Pattern").getLength();
                                        int nameCount = eElement.getElementsByTagName("Name").getLength();

                                        for(int j=0; j<eCount;j++){
                                            //System.out.println(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                            validationTemp.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                        }

                                    }
                                }
                            } catch (ParserConfigurationException | IOException e) {
                                e.printStackTrace();
                            } catch (org.xml.sax.SAXException e) {
                                e.printStackTrace();
                            }
                        }//End of Validation Retrieval

                        if(!validationTemp.isEmpty()){


                            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
                            try {
                                DocumentBuilder documentBuilder = docBuildFactory.newDocumentBuilder();
                                Document document = documentBuilder.newDocument();


                                Element element = document.createElement("Seed");
                                document.appendChild(element);

                                Element category = document.createElement("Category");
                                addCategory(e1,e2,category,document);
                                element.appendChild(category);

                                Element A = document.createElement("Tag1");
                                A.appendChild(document.createTextNode(e1));
                                element.appendChild(A);

                                //Element AValues = document.createElement("Name");
                                Element AValues = document.createElement("Name");
                                AValues.appendChild(document.createTextNode(class1));
                                A.appendChild(AValues);

                                 Element B = document.createElement("Tag2");
                                 B.appendChild(document.createTextNode(e2));
                                 element.appendChild(B);

                                 Element BValues = document.createElement("Name");
                                 BValues.appendChild(document.createTextNode(class2));
                                 B.appendChild(BValues);


                                 Element C = document.createElement("Relation");
                                 //C.appendChild(document.createTextNode(ms));
                                element.appendChild(C);
                                for(String ms: validationTemp) {
                                    Element D = document.createElement("Pattern");
                                    D.appendChild(document.createTextNode(ms));
                                    C.appendChild(D);
                                }


                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                Transformer transformer = transformerFactory.newTransformer();
                                DOMSource source = new DOMSource(document);


                                StreamResult streamResult = new StreamResult("validation/"+hashxml+"-"+class1+"-"+class2+".xml");
                                transformer.transform(source,streamResult);

                            } catch (ParserConfigurationException | TransformerException e) {
                                e.printStackTrace();
                            }
                        }

                    }//end of pLine loop of addrelations
                }//end of class1 loop
                //System.out.println("finished reading XML");

                /*for(String test: relation){
                    System.out.println("relations are: "+test);
                }*/

                TreeSet<String> seedPattern = new TreeSet<String>();
                //System.out.println(e1Name.isEmpty());
            /*for(String test: e1Name)
                System.out.println("e1 is: "+test);
            for(String test:e2Name)
                System.out.println("e2 is: "+test);*/

                // POS TAGGER
                for(String rLine: relation){
                    //System.out.println("relation: "+rLine);
                    POSTagger(seedPattern,rLine,V,P,W,tagger);
                }

                ArrayList<String> seedAL = new ArrayList<String>();
                for(String temp: seedPattern){
                    seedAL.add(temp);
                }



                String POSrelationship = "";
            /*for(int i = seedAL.size()-1;i >= 0;i--){
                if(POSrelationship.compareTo("") == 0)
                    POSrelationship= POSrelationship.concat(seedAL.get(i));
                else
                    POSrelationship= POSrelationship.concat(" "+seedAL.get(i));
            }*/





                TreeSet<String> Vmatches = new TreeSet<String>();

                TreeSet<String> Pmatches = new TreeSet<String>();

                TreeSet<String> Wmatches = new TreeSet<String>();




                for(String seedWord: seedPattern){
                    //System.out.println(seedWord+" is seedword");
                    try {
                        //MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");
                        String tagged = tagger.tagString(seedWord);
                        String temp = tagged.substring(tagged.indexOf("_")+1);

                        //System.out.println("Temp: "+temp);

                        // initialize JWNL (this must be done before JWNL can be used)
                        JWNL.initialize(new FileInputStream("file_properties.xml"));
                        IndexWord word = null;
                        if(tagged.matches(V)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.VERB, seedWord);
                            Vmatches.add(seedWord);
                        }else if(tagged.matches(P)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.ADVERB, seedWord);
                            Pmatches.add(seedWord);
                        }else if(tagged.matches(W)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.ADJECTIVE, seedWord);
                            Wmatches.add(seedWord);
                        }
                        if(word != null){
                            Synset synset[] = word.getSenses();

                            for(int i=0;i<synset.length;i++){
                                //System.out.println(word.getSenses());
                                for(Word synonym : synset[i].getWords())
                                {
                                    if(tagged.matches(V)){
                                        Vmatches.add(synonym.getLemma());



                                    }else if(tagged.matches(P)){
                                        Pmatches.add(synonym.getLemma());


                                    }else if(tagged.matches(W)){
                                        Wmatches.add(synonym.getLemma());


                                    }
                                    //matches.add(synonym.getLemma());
                                    //System.out.println(synonym.getLemma());
                                }
                                //System.out.println(" ");
                            }
                        }



                        //matches.clear();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //System.out.println(seedWord);
                }//End of wordnet loop

            /*for(String temp: Verbmatches){
                //System.out.println(temp);
            }

            System.out.println();
            for(String temp: Pronounmatches){
                //System.out.println(temp);
            }

            for(String temp: Wordmatches){
                //System.out.println(temp);
            }*/





                TreeSet<String> matches = new TreeSet<String>();
               //TreeSet<String> validation = new TreeSet<String>();

                //CHECKS IF THE GENERATED XML FILE EXISTS
                File seedOutput = new File("seedOutput/"+e1+"-"+e2+".xml");
                if(seedOutput.exists()){
                    DocumentBuilderFactory factory=  DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder builder =  factory.newDocumentBuilder();
                /*=======================================================================================================
                =======================================================================================================
                                                NOTE: CHECK IF FOLDER EXISTS
                =======================================================================================================
                 =======================================================================================================*/
                        Document doc = builder.parse("seedOutput/"+e1+"-"+e2+".xml");
                        doc.getDocumentElement().normalize();
                        //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                        NodeList nodeList = doc.getElementsByTagName("Seed");

                        for(int i = 0; i< nodeList.getLength();i++) {
                            //System.out.println("I am looping");
                            Node nNode = nodeList.item(i);
                            //System.out.println("Node Name: " + nNode.getNodeName());
                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                //System.out.println("I am here");
                                Element eElement = (Element) nNode;
                                int eCount= eElement.getElementsByTagName("Pattern").getLength();
                                for(int j=0; j<eCount;j++){
                                    //System.out.println(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                    matches.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                }

                            }
                        }
                    } catch (ParserConfigurationException | IOException e) {
                        e.printStackTrace();
                    } catch (org.xml.sax.SAXException e) {
                        e.printStackTrace();
                    }
                }



                /*File validationOutput = new File("validation/"+hashxml+"-"+e1+"-"+e2+".xml");
                if(validationOutput.exists()){
                    //====================================================================================================================
                    //For validation XML
                    //====================================================================================================================
                    DocumentBuilderFactory Vfactory=  DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder builder =  Vfactory.newDocumentBuilder();
                        Document doc = builder.parse("validation/"+hashxml+"-"+e1+"-"+e2+".xml");
                        doc.getDocumentElement().normalize();
                        //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                        NodeList nodeList = doc.getElementsByTagName("Seed");
                        for(int i = 0; i< nodeList.getLength();i++) {
                            Node nNode = nodeList.item(i);
                            //System.out.println("Node Name: " + nNode.getNodeName());
                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                //System.out.println("I am here");
                                Element eElement = (Element) nNode;
                                int eCount= eElement.getElementsByTagName("Pattern").getLength();
                                int nameCount = eElement.getElementsByTagName("Name").getLength();
                                for(int k=0; k<nameCount; k++){
                                    if((eElement.getElementsByTagName("Name").item(k).getParentNode().getNodeName().equals("Tag1"))){

                                        e1Name.add(eElement.getElementsByTagName("Name").item(k).getTextContent());
                                    }
                                    else{

                                        e2Name.add(eElement.getElementsByTagName("Name").item(k).getTextContent());
                                    }

                                }
                                for(int j=0; j<eCount;j++){
                                    //System.out.println(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                    validation.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                }

                            }
                        }
                    } catch (ParserConfigurationException | IOException e) {
                        e.printStackTrace();
                    } catch (org.xml.sax.SAXException e) {
                        e.printStackTrace();
                    }
                }*/





                //matches.add(POSrelationship);//adding the POS Tagged relationship to the generated seed patterns
                //System.out.println("WordMatches: "+ Pmatches);
                if(Wmatches .isEmpty() ){
                    if(Pmatches.isEmpty()){
                        for(String p: Vmatches){
                            matches.add(p);
                        }
                    }
                    else{
                        for(String p: Vmatches){
                            for(String j: Pmatches){
                                matches.add(p+" "+j);
                                //deleteTest = i+" " +j;//ADD THESE FOR THE DELETE FUNCTION
                            }
                        }
                    }
                }
                else{
                    for(String i: Vmatches){
                        for(String j: Wmatches) {
                            for (String k : Pmatches) {
                                matches.add(i + " " + j+ " "+ k);
                            }
                        }
                    }
                }
                //String deleteTest = "";//ADD THESE FOR THE DELETE FUNCTION







            /*====================================================================================================================
            ====================================================================================================================
            ====================================================================================================================
                                            DELETE SPECIFIC ITEMS IN XML FILE
                                            CHECK LINE 289 AND 295 FOR TESTING
            ====================================================================================================================
             ====================================================================================================================
             ====================================================================================================================*/
           /* DocumentBuilderFactory Deletefactory=  DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder =  Deletefactory.newDocumentBuilder();
                Document doc = builder.parse("validation/"+e1+"-"+e2+".xml");
                doc.getDocumentElement().normalize();
                //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                NodeList nodeList = doc.getElementsByTagName("Seed");
                for(int i = 0; i< nodeList.getLength();i++) {
                    Node nNode = nodeList.item(i);
                    //System.out.println("Node Name: " + nNode.getNodeName());
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        //System.out.println("I am here");
                        Element eElement = (Element) nNode;
                        int eCount= eElement.getElementsByTagName("Pattern").getLength();
                        for(int j=0; j<eCount;j++){
                            //System.out.println("Delete Test:"+eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                            //validation.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                            if(eElement.getElementsByTagName("Pattern").item(j).getTextContent().equals(deleteTest)){
                                eElement.getElementsByTagName("Pattern").item(j).getParentNode().removeChild(eElement.getElementsByTagName("Pattern").item(j));
                                //System.out.println(eElement.getElementsByTagName("Pattern").item(j).getTextContent()+ " is Deleted Successfully");
                            }
                        }

                    }
                }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);

                StreamResult streamResult = new StreamResult("validation/"+e1+"-"+e2+".xml");
                transformer.transform(source,streamResult);

            } catch (ParserConfigurationException | IOException e) {
                e.printStackTrace();
            } catch (org.xml.sax.SAXException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }*/


                for(String temp: matches){
                    //System.out.println(temp);
                }

                if(!matches.isEmpty()){
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                        Document document = documentBuilder.newDocument();


                        Element element = document.createElement("Seed");
                        document.appendChild(element);

                        Element A = document.createElement("Tag1");
                        A.appendChild(document.createTextNode(e1));
                        element.appendChild(A);

                        Element B = document.createElement("Tag2");
                        B.appendChild(document.createTextNode(e2));
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

                        StreamResult streamResult = new StreamResult("seedOutput/"+e1+"-"+e2+".xml");
                        transformer.transform(source,streamResult);

                    } catch (ParserConfigurationException | TransformerException e) {
                        e.printStackTrace();
                    }
                }


                //System.out.println(validation.isEmpty());

                /*if(!validation.isEmpty()){

                    DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder documentBuilder = docBuildFactory.newDocumentBuilder();
                        Document document = documentBuilder.newDocument();


                        Element element = document.createElement("Seed");
                        document.appendChild(element);

                        Element category = document.createElement("Category");
                        addCategory(e1,e2,category,document);
                        element.appendChild(category);

                        Element A = document.createElement("Tag1");
                        A.appendChild(document.createTextNode(e1));
                        element.appendChild(A);

                        //Element AValues = document.createElement("Name");
                        for(String e1Value: e1Name) {
                            Element AValues = document.createElement("Name");
                            AValues.appendChild(document.createTextNode(e1Value));
                            A.appendChild(AValues);
                        }

                        Element B = document.createElement("Tag2");
                        B.appendChild(document.createTextNode(e2));
                        element.appendChild(B);

                        for(String e2Value: e2Name) {
                            Element BValues = document.createElement("Name");
                            if(!e1Name.contains(e2Value)){
                                BValues.appendChild(document.createTextNode(e2Value));
                                B.appendChild(BValues);
                            }
                        }

                        Element C = document.createElement("Relation");
                        //C.appendChild(document.createTextNode(ms));
                        element.appendChild(C);
                        for(String ms: validation) {
                            Element D = document.createElement("Pattern");
                            D.appendChild(document.createTextNode(ms));
                            C.appendChild(D);
                        }


                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(document);

                        StreamResult streamResult = new StreamResult("validation/"+hashxml+"-"+e1+"-"+e2+".xml");
                        transformer.transform(source,streamResult);

                    } catch (ParserConfigurationException | TransformerException e) {
                        e.printStackTrace();
                    }
                }*/





                System.out.println("Finish Reading seeds : seedOutput/"+e1+"-"+e2+".xml");

            }//end of seeds loop


        }//End of file[] Loop
    }

    /*=============================
     File Reader for Tagged Documents
    ==============================*/
    public static StringBuilder readFile(File xmlFile, Reader fileReader){
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
        return(sb);
    }

    /*=============================
    Retrieve Entities on txt files
    ==============================*/
    public static String getTag(String tag){
        tag = tag.substring(tag.indexOf(":")+1);
        return(tag);
    }

    /*=============================
    Store Pair on Map
    ==============================*/
    public static void addMap(ArrayList seedMap1,ArrayList<String>seedMap2,ArrayList<String> seedEntity, int i){
        String[] classes = seedEntity.get(i).split(";", 2);
        if(classes.length >=2){
            String class1 = classes[0];
            String class2 = classes[1];
            seedMap1.add(class1);
            seedMap2.add(class2);
        }else{
            System.out.println("ignoring line: " + i);
        }
    }

    /*=============================
    Add founded relation to TreeSet
    ==============================*/
    public static void addRelation(
            TreeSet<String> relation, String pLine,
            String class1, String class2,
            String e1, String e2,
            TreeSet<String> e1Name, TreeSet<String> e2Name){
        e1= e1.toLowerCase();
        e2 = e2.toLowerCase();
        //System.out.println("running Relation");
        //System.out.println(e1+": "+class1+";"+e2+": "+class2);
        if(pLine.contains("<"+e1+">"+class1+"</"+e1+">") && pLine.contains("<"+e2+">"+class2+"</"+e2+">") ){
            if(e2.contains("pound")){
                //.println(e1+": "+class1+";"+e2+": "+class2);
            }
            //System.out.println(e1+": "+class1+";"+e2+": "+class2);
            Pattern p = Pattern.compile("<\\/["+e1+"]+>.+<["+e2+"]+>");
            Matcher m = p.matcher(pLine);
            //.println(e1+": "+class1+";"+e2+": "+class2);
            while(m.find()) {

                String temp = m.group();
                temp = temp.replaceAll("<\\/?[a-z]+>", "");
                relation.add(temp);
                e1Name.add(class1);
                e2Name.add(class2);
                //System.out.println(temp);
                //System.out.println("This is "+ e1Name+" and "+e2Name);
                //System.out.println(relation.size());
                //System.out.println(pLine);
            } // end of matcher while
        }// end of if pLine
    }



    /*=============================
    Use the generated seeds to look for patterns
    ==============================*/
    public static void addPreprocessing(
            TreeSet<String> relation, String pLine,
            String e1, String e2,
            ArrayList<String> lines){
        e1 = e1.toLowerCase();
        e2 = e2.toLowerCase();
        TreeSet<String> List = new TreeSet<String>();
        File seedOutput = new File("seedOutput/"+e1+"-"+e2+".xml");
        //System.out.println(e1+": "+";"+e2+": ");
        if(seedOutput.exists()){
            readXML(e1,e2,List);
            //System.out.println("I am not empty");
            for(String l: List){
                if (pLine.contains("<"+e1+">") && pLine.contains("<"+e2+">") && pLine.contains(l)) {
                    Pattern p = Pattern.compile("<\\/[" + e1 + "]+>.+<[" + e2 + "]+>");
                    Matcher m = p.matcher(pLine);
                    //System.out.println(e1+": "+class1+";"+e2+": "+class2);
                    while (m.find()) {
                        String temp = m.group();
                        temp = temp.replaceAll("<\\/?[a-z]+>", "");
                        relation.add(temp);
                        for (String delete : lines) {
                            String store = delete.toLowerCase();
                            if (pLine.equals(store)) {
                                int index = lines.indexOf(delete);
                                lines.set(index, "");
                            }
                        }
                        //System.out.println(temp);
                        //System.out.println("This is "+ e1Name+" and "+e2Name);
                        //System.out.println(relation.size());
                        //System.out.println(pLine);
                    } // end of matcher while
                }// end of if pLine
            }

        }







    }


    public static void readXML(String e1, String e2, TreeSet<String> matches){
        //CHECKS IF THE GENERATED XML FILE EXISTS
        DocumentBuilderFactory factory=  DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder =  factory.newDocumentBuilder();
                /*=======================================================================================================
                =======================================================================================================
                                                NOTE: CHECK IF FOLDER EXISTS
                =======================================================================================================
                 =======================================================================================================*/
            Document doc = builder.parse("seedOutput/"+e1+"-"+e2+".xml");
            doc.getDocumentElement().normalize();
            //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Seed");
            for(int i = 0; i< nodeList.getLength();i++) {
                Node nNode = nodeList.item(i);
                //System.out.println("Node Name: " + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    //System.out.println("I am here");
                    Element eElement = (Element) nNode;
                    int eCount= eElement.getElementsByTagName("Pattern").getLength();
                    for(int j=0; j<eCount;j++){
                        //System.out.println(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                        matches.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                    }

                }
            }
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }



    /*=============================
    Add Category
    ==============================*/
    public static void addCategory(String e1, String e2, Element category, Document document){
        e1 = e1.toLowerCase();
        e2 = e2.toLowerCase();
        String entities = e1+"+"+e2;
        switch(entities){
            case "Synonym+MedicinalPlant":
                category.appendChild(document.createTextNode("Common Name"));
                break;
            case "MedicinalPlant+PlantPart":
            case "Synonym+PlantPart":
            case "Preparation+PlantPart":
                category.appendChild(document.createTextNode("Plant Part"));
                break;
            case "MedicinalPlant+Location":
            case "Synonym+Location":
                category.appendChild(document.createTextNode("Location"));
                break;

            case "MedicinalPlant+Compound":
            case "Synonym+Compound":
            case "PlantPart+Compound":
                category.appendChild(document.createTextNode("Compound"));
                break;
            case "MedicinalPlant+Family":
            case "Genus+Family":
            case "Synonym+Family":
                category.appendChild(document.createTextNode("Family"));
                break;
            case "MedicinalPlant+Genus":
            case "Synonym+Genus":
                category.appendChild(document.createTextNode("Genus"));
                break;
            case "MedicinalPlant+Synonym":
                category.appendChild(document.createTextNode("Plant"));
                break;
            case "MedicinalPlant+Preparation":
                category.appendChild(document.createTextNode("Preparation"));
                break;
            case "BioAct+CellLine":
                category.appendChild(document.createTextNode("Cell"));
                break;
            case "Compound+BioAct":
                category.appendChild(document.createTextNode("Bio-Activity"));
                break;
            case "PlantPart+Illness":
            case "Preparation+Illness":
                category.appendChild(document.createTextNode("Illness"));
                break;


        }
    }



    /*=============================
    POS Tagger
    ==============================*/
    public static void POSTagger(TreeSet<String> seedPattern, String rLine, String V, String P, String W, MaxentTagger tagger){
        //MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");
        String tagged = tagger.tagString(rLine);
        //System.out.println(tagged);
        String[] tLines= tagged.split(" ");
        for(int i=0; i<tLines.length;i++){
            //System.out.println(tLines[i]);
            if(tLines[i].matches(V)){
                String temp = tLines[i].substring(0,tLines[i].indexOf("_"));
                seedPattern.add(temp);
                if(i+1 <tLines.length){
                    if(tLines[i+1].matches(W)){
                        temp = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                        seedPattern.add(temp);
                        if(i+2 < tLines.length){
                            if(tLines[i+2].matches(P)){
                                temp = tLines[i+2].substring(0,tLines[i+2].indexOf("_"));
                                seedPattern.add(temp);
                            }
                        }

                    }
                    else if(tLines[i+1].matches(P)){
                        temp = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                        seedPattern.add(temp);
                    }
                }

                //seedPattern.add(temp);
            }
        }
    }
}
