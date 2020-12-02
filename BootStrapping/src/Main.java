import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.sun.deploy.util.ArrayUtil;
import edu.stanford.nlp.io.EncodingPrintWriter;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.util.ArrayUtils;
import edu.stanford.nlp.util.StringUtils;
import jdk.internal.org.xml.sax.SAXException;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
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
import javax.xml.transform.OutputKeys;
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
        try {
            JWNL.initialize(new FileInputStream("file_properties.xml"));
        } catch (JWNLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //V = Verb, Particule, Adverb
        //W = Noun, Adjective,Adverb, Pronoun, Determiner
        //P = Prepostion(ADP), Particle
        String V = "(.*)_[VTN][BON][PNDZG]*(.*)"; //Verb --V
        String V2 = "(.*)_[V]([BP][DNGPZ]*)*(.*)"; //Verb --V
        String AV = "(.*)_[WRV][RBP][RSBG]*(.*)"; //Adverb --VW
        String W = "(.*)_[NSV][BYNP][GMPS]*(.*)"; //Noun --W
        //String W2 = "(.*)_(SYM)*(VBG)*(.*)"; //Noun --W
        String P = "(.*)_[PRT][ORP][ST]*(.*)"; //Particule --VP
        //String P2 = "(.*)_(POS)*(PRT)*(TO)*(.*)"; //Particule --VP
        String ADJ = "(.*)_[JRV][BJB][RSG]*(JR)*(.*)"; //Adjective --W
        String PN = "(.*)_[WPV][BPR][$P]*(.*)"; //Pronoun --W
        //String PN2 = "(.*)_(VBP)*(.*)"; //Pronoun --W
        String DET = "(.*)_[DEWP][TXD][T]*(.*)"; //Determiner --W
        String ADP = "(.*)_[IR][NP](.*)"; //ADPronoun --P





        for(File xmlFile : listFiles) {

            File seedFolder = new File("SeedsPossible/");
            File[] seedList = seedFolder.listFiles();
            for(File seeds: seedList) {

                String hashxml = xmlFile.getName().replaceAll(".xml","");

                TreeSet<String> ValidationRelation = new TreeSet<String>();
                TreeSet<String> Verbmatches = new TreeSet<String>();
                TreeSet<String> Pronounmatches = new TreeSet<String>();
                TreeSet<String> Wordmatches = new TreeSet<String>();
                TreeSet<String> validation = new TreeSet<String>();



                String e1=null; String e2=null;
                TreeSet<String> relation = new TreeSet<String>();
                TreeSet<String> e1Name = new TreeSet<String>();
                TreeSet<String> e2Name = new TreeSet<String>();
                TreeSet<String> list = new TreeSet<String>();

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
                File seedExists = new File("seedOutput/"+e1+"-"+e2+".xml");
                if(seedExists.exists()){
                    readXML(e1,e2,list);
                }


                //System.out.println("Seeds retrieved: "+e1+" "+e2);

                //ArrayList<String> seedMap1 = new ArrayList<String>();//change to MultiMap
                //ArrayList<String> seedMap2 = new ArrayList<String>();//change to MultiMap
                Multimap<String,String> seedMap = HashMultimap.create();
                Multimap<String,String> ValidationMap = HashMultimap.create();
                for(int i=3; i<seedEntity.size(); i++){
                    //System.out.println("I went here");
                    addMap(seedMap,seedEntity,i);
                }



                String xml2String = readFile(xmlFile, fileReader).toString();
                String[] line = xml2String.split("\\r?\\n");
                Collections.addAll(lines,line);



                long startTime = System.nanoTime();
                for (String pLine : lines) {
                    //System.out.println("I went here");
                    pLine = pLine.toLowerCase();
                    // System.out.println(pLine+"/n");
                    addPreprocessing(relation,pLine,e1,e2,lines,seedMap,ValidationMap,list);
                    //System.out.println("Preprocessing finished");

                }//end of pLine loop of preprocessing
                long endTime = System.nanoTime();
                long time = endTime-startTime;
                System.out.println("Time Elapsed: "+ time);


                //System.out.println("Started Running");
                //for(String class1 : seedMap.keySet())
                //System.out.println(seedMap.isEmpty());
                for(String key : seedMap.keySet()){
                    //System.out.println(key);
                    String class1 = key;
                    for(String class2 : seedMap.get(key)){
                        class1 = class1.toLowerCase();
                        class2 = class2.toLowerCase();
                        //System.out.println("I went here");
                        //System.out.println("Classes: "+ class1+" "+class2);
                        //System.out.println(e1+": "+class1+";"+e2+": "+class2);
                        for (String pLine : lines) {
                            pLine = pLine.toLowerCase();
                            addRelation(relation,pLine,class1,class2,e1,e2,ValidationMap,validation);
                            //System.out.println("Relation finished");


                        }//end of pLine loop of addrelations
                    }

                }//end of class1 loop
                //System.out.println("finished reading XML");

                /*for(String test: relation){
                    System.out.println("relations are: "+test);
                }*/

                TreeSet<String> seedPatternV = new TreeSet<String>();
                TreeSet<String> seedPatternW = new TreeSet<String>();
                TreeSet<String> seedPatternP = new TreeSet<String>();
                //System.out.println(e1Name.isEmpty());
            /*for(String test: e1Name)
                System.out.println("e1 is: "+test);
            for(String test:e2Name)
                System.out.println("e2 is: "+test);*/

                // POS TAGGER
                String EntityCheck = e1+"+"+e2;
                EntityCheck = EntityCheck.toLowerCase();
                for(String rLine: relation){
                    //System.out.println("I went here");
                    //if(rLine.contains("suppress"))
                        //System.out.println("relation: "+rLine+"located in: "+hashxml);
                        //System.out.println("I went here");
                    if(EntityCheck.contains("compound") || EntityCheck.contains("synonym+location") || EntityCheck.contains("cellline")){
                        POSTagger(seedPatternV,seedPatternW,seedPatternP,rLine,V2,P,W,tagger,AV,ADJ,PN,DET,ADP,validation);
                    }//else if(EntityCheck.contains("cellline")){
                       // POSTagger(seedPatternV,seedPatternW,seedPatternP,rLine,V,P,W2,tagger,AV,ADJ,PN2,DET,ADP,validation);
                   // }
                    else{
                        POSTagger(seedPatternV,seedPatternW,seedPatternP,rLine,V,P,W,tagger,AV,ADJ,PN,DET,ADP,validation);
                    }

                }

                /*ArrayList<String> seedAL = new ArrayList<String>();
                for(String temp: seedPattern){
                    seedAL.add(temp);
                }*/



                //String POSrelationship = "";
            /*for(int i = seedAL.size()-1;i >= 0;i--){
                if(POSrelationship.compareTo("") == 0)
                    POSrelationship= POSrelationship.concat(seedAL.get(i));
                else
                    POSrelationship= POSrelationship.concat(" "+seedAL.get(i));
            }*/





                TreeSet<String> Vmatches = new TreeSet<String>();

                TreeSet<String> Pmatches = new TreeSet<String>();

                TreeSet<String> Wmatches = new TreeSet<String>();




                /*
                ========================================================================================================
                                            CREATE 3 TIMES
                ========================================================================================================
                 */


                for(String seedWordV: seedPatternV){
                    try {
                        //MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");
                        String tagged = tagger.tagString(seedWordV);
                        String temp = tagged.substring(tagged.indexOf("_")+1);
                        //System.out.println("Temp: "+temp);
                        // initialize JWNL (this must be done before JWNL can be used)
                        IndexWord word = null;
                        if(tagged.matches(V)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.VERB, seedWordV);
                            Vmatches.add(seedWordV);
                        }
                        if(word != null){
                            Synset synset[] = word.getSenses();

                            //System.out.println(synset[0].getWord(0).toString());
                            Vmatches.add(synset[0].getWord(0).getLemma());
                            /*for(int i=0;i<synset.length;i++){
                                //System.out.println(word.getSenses());
                                for(Word synonym : synset[i].getWords())
                                {
                                    if(tagged.matches(V) ){
                                        Vmatches.add(synonym.getLemma());
                                    }


                                    //matches.add(synonym.getLemma());
                                    //System.out.println(synonym.getLemma());
                                }
                                if(i==0)
                                    break;
                                //System.out.println(" ");
                            }*/
                        }

                        //matches.clear();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                for(String seedWordW: seedPatternW){
                    try {
                        //MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");
                        String tagged = tagger.tagString(seedWordW);
                        String temp = tagged.substring(tagged.indexOf("_")+1);
                        //System.out.println("Temp: "+temp);
                        IndexWord word = null;
                        if(tagged.matches(W) || tagged.matches(PN)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.NOUN, seedWordW );
                            Wmatches.add(seedWordW);
                        }
                        if(word != null){
                            Synset synset[] = word.getSenses();

                            for(int i=0;i<synset.length;i++){
                                //System.out.println(word.getSenses());
                                for(Word synonym : synset[i].getWords())
                                {
                                    if( tagged.matches(ADJ)  || tagged.matches(DET)){
                                        Wmatches.add(synonym.getLemma());
                                    }else if(tagged.matches(W) || tagged.matches(PN)){
                                        Wmatches.add(synonym.getLemma());
                                    }

                                    //matches.add(synonym.getLemma());
                                    //System.out.println(synonym.getLemma());
                                }
                                //System.out.println(" ");
                                if(i == 0)
                                    break;
                            }
                        }

                        //matches.clear();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                for(String seedWordP: seedPatternP){
                    //System.out.println(seedWord+" is seedword");
                    try {
                        //MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");
                        String tagged = tagger.tagString(seedWordP);
                        String temp = tagged.substring(tagged.indexOf("_")+1);
                        //System.out.println("Temp: "+temp);
                        IndexWord word = null;
                        if(tagged.matches(P) || tagged.matches(ADP) || tagged.matches(AV)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.ADVERB, seedWordP);
                            Pmatches.add(seedWordP);
                        }
                        if(word != null){
                            Synset synset[] = word.getSenses();
                            for(int i=0;i<synset.length;i++){
                                //System.out.println(word.getSenses());
                                for(Word synonym : synset[i].getWords())
                                {
                                    if(tagged.matches(P) || tagged.matches(ADP) || tagged.matches(AV)){
                                        Pmatches.add(synonym.getLemma());
                                    }

                                    //matches.add(synonym.getLemma());
                                    //System.out.println(synonym.getLemma());
                                }
                                //System.out.println(" ");
                                if(i == 0)
                                    break;
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

                matches.addAll(list);
                //CHECKS IF THE GENERATED XML FILE EXISTS
                /*File seedOutput = new File("seedOutput/"+e1+"-"+e2+".xml");
                long readStart = System.nanoTime();
                if(seedOutput.exists()){
                    DocumentBuilderFactory factory=  DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder builder =  factory.newDocumentBuilder();*/
                /*=======================================================================================================
                =======================================================================================================
                                                NOTE: CHECK IF FOLDER EXISTS
                =======================================================================================================
                 =======================================================================================================*/
                       /* Document doc = builder.parse("seedOutput/"+e1+"-"+e2+".xml");
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
                                    //matches.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                    matches.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                }
                            }
                        }
                        doc.getDocumentElement().setTextContent("");
                    } catch (ParserConfigurationException | IOException e) {
                        e.printStackTrace();
                    } catch (org.xml.sax.SAXException e) {
                        e.printStackTrace();
                    }
                }
                long readEnd = System.nanoTime();
                long readTime = readEnd-readStart;
                System.out.println("Reading Time Elapsed: "+readTime);*/



                for(String key : ValidationMap.keySet()) {
                    String class1 = key;
                    File validationOutput = new File("validation/"+hashxml+"-"+class1+"-"+e2+".xml");
                    if(validationOutput.exists()){
                    //====================================================================================================================
                    //For validation XML
                    //====================================================================================================================
                        DocumentBuilderFactory Vfactory=  DocumentBuilderFactory.newInstance();
                        try {
                            DocumentBuilder builder =  Vfactory.newDocumentBuilder();
                            Document doc = builder.parse("validation/"+hashxml+"-"+class1+"-"+e2+".xml");
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
                                    for(String e1Value : e1Name){
                                        for(String e2Value : e2Name){
                                            ValidationMap.put(e1Value,e2Value);
                                        }
                                    }
                                    for(int j=0; j<eCount;j++){
                                        //System.out.println(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                        validation.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                    }

                                }
                            }
                            doc.getDocumentElement().setTextContent("");
                        } catch (ParserConfigurationException | IOException e) {
                            e.printStackTrace();
                        } catch (org.xml.sax.SAXException e) {
                            e.printStackTrace();
                        }
                    }
                }





                //matches.add(POSrelationship);//adding the POS Tagged relationship to the generated seed patterns
                //System.out.println("WordMatches: "+ Pmatches);
                /*======================================================================================================
                                                    CHANGE UNDERSCORE TO SPACES
                 ======================================================================================================*/
                for(String p: Vmatches){
                    p = p.replaceAll("_"," ");
                    p = p.replaceAll("(.*)-[A-Z]*-(.*)","");
                    matches.add(p);
                }
                if(!Pmatches.isEmpty()){
                    for(String p: Vmatches){
                        for(String j: Pmatches){
                            p = p.replaceAll("_"," ");
                            p = p.replaceAll("(.*)-[A-Z]*-(.*)","");
                            j = j.replaceAll("_"," ");
                            j = j.replaceAll("(.*)-[A-Z]*-(.*)","");
                            matches.add(p+" "+j);
                            //deleteTest = i+" " +j;//ADD THESE FOR THE DELETE FUNCTION
                        }
                    }
                }
                if(!Wmatches.isEmpty()){
                    for(String i: Vmatches){
                        for(String j: Wmatches) {
                            for (String k : Pmatches) {
                                i = i.replaceAll("_"," ");
                                i = i.replaceAll("(.*)-[A-Z]*-(.*)","");
                                j = j.replaceAll("_"," ");
                                j = j.replaceAll("(.*)-[A-Z]*-(.*)","");
                                k = k.replaceAll("_"," ");
                                k = k.replaceAll("(.*)-[A-Z]*-(.*)","");
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

           //System.out.println(matches.isEmpty());
           //System.out.println(validation.isEmpty());
                //if(!matches.isEmpty()){
                if(!matches.isEmpty()){
                    //System.out.println(validation);
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
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                        DOMSource source = new DOMSource(document);

                        StreamResult streamResult = new StreamResult("seedOutput/"+e1+"-"+e2+".xml");
                        transformer.transform(source,streamResult);

                    } catch (ParserConfigurationException | TransformerException e) {
                        e.printStackTrace();
                    }
                }


                //System.out.println(validation.isEmpty());

                if(!validation.isEmpty()){
                    //System.out.println("validationmap is "+ValidationMap.isEmpty());
                    for(String key : ValidationMap.keySet()) {
                        String class1 = key;
                        /*if(class1.contains("Ehrlich")){
                            System.out.println(validation);
                        }*/

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


                            for(String class2 : ValidationMap.get(key)) {
                                Element BValues = document.createElement("Name");
                                BValues.appendChild(document.createTextNode(class2));
                                B.appendChild(BValues);
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
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                            DOMSource source = new DOMSource(document);
                            class1 = class1.replaceAll("<\\/?[a-z]+>", "");
                            if(e1.contains("Preparation")){
                                StreamResult streamResult = new StreamResult("validation/"+hashxml+"-"+"Preparation"+"-"+e2+".xml");
                                transformer.transform(source,streamResult);
                            }
                            else{
                                StreamResult streamResult = new StreamResult("validation/"+hashxml+"-"+class1+"-"+e2+".xml");
                                transformer.transform(source,streamResult);
                            }


                        } catch (ParserConfigurationException | TransformerException e) {
                            e.printStackTrace();
                        }
                    }
                }
                seedMap.clear();
                ValidationMap.clear();






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
    public static void addMap(Multimap<String,String> seedMap,ArrayList<String> seedEntity, int i){
        String[] classes = seedEntity.get(i).split(";", 2);
        if(classes.length >=2){
            String class1 = classes[0];
            String class2 = classes[1];
            seedMap.put(class1,class2);
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
            String e1, String e2,Multimap<String,String> ValidationMap,TreeSet<String> validation){
        e1= e1.toLowerCase();
        e2 = e2.toLowerCase();
        //System.out.println("running Relation");
        //System.out.println(e1+": "+class1+";"+e2+": "+class2);
        String linetemp = pLine;
        if(linetemp.contains("preparation")){
            linetemp = linetemp.replaceAll("<\\/?[a-z]+>","");
            if(e1.contains("preparation") ||  e2.contains("preparation") ){
                if(e1.contains("preparation") && pLine.contains("<"+e2+">"+class2+"</"+e2+">")) {
                    ValidationMap.put(class2, linetemp);
                    validation.add(linetemp);
                }
                else if(pLine.contains("<"+e1+">"+class1+"</"+e1+">") && e2.contains("preparation")){
                    ValidationMap.put(class1, linetemp);
                    validation.add(linetemp);
                }

            }
        }
        else if(pLine.contains("<"+e1+">"+class1+"</"+e1+">") && pLine.contains("<"+e2+">"+class2+"</"+e2+">") ){
            //if(pLine.contains("form")){
             //   System.out.println(e1+": "+class1+";"+e2+": "+class2);
                //System.out.println(pLine);
            //}

            ValidationMap.put(class1,class2);


            //System.out.println(e1+": "+class1+";"+e2+": "+class2);
            Pattern p = Pattern.compile("<\\/["+e1+"]+>.+<["+e2+"]+>");
            Matcher m = p.matcher(pLine);
            Pattern pRev = Pattern.compile("<\\/["+e2+"]+>.+<["+e1+"]+>");
            Matcher mRev = pRev.matcher(pLine);
            //.println(e1+": "+class1+";"+e2+": "+class2);
            while(m.find()) {
                String temp = m.group();
                //System.out.println("I went here");
                temp = temp.replaceAll("<\\/?[a-z]+>", "");
                relation.add(temp);

                //System.out.println(temp);
                //System.out.println("This is "+ e1Name+" and "+e2Name);
                //System.out.println(relation.size());
                //System.out.println(pLine);
            } // end of m while
            while(mRev.find()) {
                String temp = mRev.group();
                //System.out.println("I went here");
                temp = temp.replaceAll("<\\/?[a-z]+>", "");
                relation.add(temp);

                //System.out.println(temp);
                //System.out.println("This is "+ e1Name+" and "+e2Name);
                //System.out.println(relation.size());
                //System.out.println(pLine);
            } // end of m while
        }// end of if pLine
    }



    /*=============================
    Use the generated seeds to look for patterns
    ==============================*/
    public static void addPreprocessing(
            TreeSet<String> relation, String pLine,
            String e1, String e2,
            ArrayList<String> lines,Multimap<String,String> seedMap,Multimap<String,String> ValidationMap,TreeSet<String> list){
        e1 = e1.toLowerCase();
        e2 = e2.toLowerCase();
        TreeSet<String> List = new TreeSet<String>();
        //File seedOutput = new File("seedOutput/"+e1+"-"+e2+".xml");
        //System.out.println(e1+": "+";"+e2+": ");
        if(!list.isEmpty()){

            //System.out.println("I am not empty");
            //String finalE = e1;
            //String finalE1 = e2;
            for (String l : list) {//System.out.println("I run here");
                if (pLine.contains("<" + e1 + ">") && pLine.contains("<" + e2 + ">") && pLine.contains(l)) {
                    String class1 = null;
                    String class2 = null;
                    Pattern pc1 = Pattern.compile("<[" + e1 + "]+>[^<>]*<\\/[" + e1 + "]+>");
                    Matcher mc1 = pc1.matcher(pLine);
                    Pattern pc2 = Pattern.compile("<[" +e2 + "]+>[^<>]*<\\/[" + e2 + "]+>");
                    Matcher mc2 = pc2.matcher(pLine);
                    while (mc1.find()) {
                        class1 = mc1.group();
                        while (mc2.find()) {
                            //System.out.println("I got here");
                            class2 = mc2.group();
                            class1 = class1.replaceAll("<\\/?[a-z]+>", "");
                            class2 = class2.replaceAll("<\\/?[a-z]+>", "");
                            seedMap.put(class1, class2);
                            ValidationMap.put(class1, class2);
                        }
                    }
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
            //for(String l: List){

            //}

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
            doc.getDocumentElement().setTextContent("");
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
    //V = Verb, Particule, Adverb
    //W = Noun, Adjective,Adverb, Pronoun, Determiner
    //P = Prepostion(ADP), Particle
    /*String V = "(.*)_[V][BP][A-Z]*[|]*[VB]*[NT]*[O]*(.*)"; //Verb --V
    String AV = "(.*)_[V][BP][A-Z]*[|]*[VB]*[NT]*[O]*(.*)"; //Adverb --VW
    String W = "(.*)_[N][NP][PS]*[|NSYMVBG]*(.*)"; //Noun --W
    String P = "(.*)_[RTP][NPOR][ST]*(.*)"; //Particule --VP
    String ADJ = "(.*)_[J][J][|SRJVBG]*(.*)"; //Adjective --W
    String PN = "(.*)_[WP][RP][|P$]*(VBP)*(.*)"; //Pronoun --W
    String DET = "(.*)_[PDEW][DTX][T]*(.*)"; //Determiner --W
    String ADP = "(.*)_[I][N][|RP]*(.*)"; //ADPronoun --P*/
    public static void POSTagger(TreeSet<String> seedPatternV,TreeSet<String> seedPatternW,TreeSet<String> seedPatternP,
                                 String rLine, String V, String P, String W, MaxentTagger tagger, String AV,String ADJ,
                                 String PN, String DET, String ADP, TreeSet<String> validation){
        //MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");
        String tagged = tagger.tagString(rLine);
        if(tagged.contains("also known as")){
            System.out.println(tagged);
        }

        String[] tLines= tagged.split(" ");
        //System.out.println("I went here");
        for(int i=0; i<tLines.length;i++){

            //System.out.println(tLines[i]);
            if(tLines[i].matches(V)){
                //System.out.println("I went here");
                String temp = tLines[i].substring(0,tLines[i].indexOf("_"));
                //System.out.println("V: "+temp);
                seedPatternV.add(temp);
                if(i+1 <tLines.length){
                    if(tLines[i+1].matches(W) || tLines[i+1].matches(ADJ) || tLines[i+1].matches(AV) || tLines[i+1].matches(PN) || tLines[i+1].matches(DET) ){
                        temp = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                        seedPatternW.add(temp);
                        if(i+2 < tLines.length){
                            if(tLines[i+2].matches(P) || tLines[i+2].matches(ADP)){
                                temp = tLines[i+2].substring(0,tLines[i+2].indexOf("_"));
                                seedPatternP.add(temp);
                                String TempSeedPatternV = tLines[i].substring(0,tLines[i].indexOf("_"));
                                TempSeedPatternV = TempSeedPatternV.replaceAll("(.*)-[A-Z]*-(.*)","");
                                String TempSeedPatternW = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                                TempSeedPatternW = TempSeedPatternW.replaceAll("(.*)-[A-Z]*-(.*)","");
                                String TempSeedPatternP = tLines[i+2].substring(0,tLines[i+2].indexOf("_"));
                                TempSeedPatternP = TempSeedPatternP.replaceAll("(.*)-[A-Z]*-(.*)","");
                                //System.out.println("Temp Seed: "+TempSeedPatternV+" "+TempSeedPatternW+" "+TempSeedPatternP);
                                validation.add(TempSeedPatternV+" "+TempSeedPatternW+" "+TempSeedPatternP);
                            }
                        }
                    }
                    else if(tLines[i+1].matches(P)|| tLines[i+1].matches(ADP)){
                        temp = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                        seedPatternP.add(temp);
                        String TempSeedPatternV = tLines[i].substring(0,tLines[i].indexOf("_"));
                        TempSeedPatternV = TempSeedPatternV.replaceAll("(.*)-[A-Z]*-(.*)","");
                        String TempSeedPatternP = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                        TempSeedPatternP = TempSeedPatternP.replaceAll("(.*)-[A-Z]*-(.*)","");
                        validation.add(TempSeedPatternV+" "+TempSeedPatternP);
                        //System.out.println(TempSeedPatternV+" "+TempSeedPatternP);
                    }else{
                        String TempSeedPatternV = tLines[i].substring(0,tLines[i].indexOf("_"));
                        TempSeedPatternV = TempSeedPatternV.replaceAll("(.*)-[A-Z]*-(.*)","");
                        validation.add(TempSeedPatternV);
                        //System.out.println(TempSeedPatternV);
                    }
                }else{
                    String TempSeedPatternV = tLines[i].substring(0,tLines[i].indexOf("_"));
                    TempSeedPatternV = TempSeedPatternV.replaceAll("(.*)-[A-Z]*-(.*)","");
                    validation.add(TempSeedPatternV);
                    //System.out.println(TempSeedPatternV);
                }

                //seedPattern.add(temp);
            }
        }
    }
}
