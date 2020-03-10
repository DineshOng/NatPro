import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {

        File Folder = new File("TaggedSample/");
        File[] listFiles = Folder.listFiles();
        Reader fileReader = null;
        String e1,e2;
        TreeSet<String> relation = new TreeSet<String>();
        String V = "(.*)_VB[A-Z]*(.*)";
        String W = "(.*)_[DJNPR][NJBTR]P*(.*)";
        String P = "(.*)_[IRT][NPO](.*)";



        for(File xmlFile : listFiles) {

            File seedFolder = new File("SeedsPossible/");
            File[] seedList = seedFolder.listFiles();
            for(File seeds: seedList) {

                String seedData = readFile(seeds, fileReader).toString();
                String[] seedLines = seedData.split("\\r?\\n");
                ArrayList<String> seedEntity = new ArrayList<String>();
                for(String sling: seedLines){
                    seedEntity.add(sling);
                }

                //entity retrieval
                e1 = getTag(seedEntity.get(0));
                e2 = getTag(seedEntity.get(1));

                HashMap<String, String> seedMap = new HashMap<String, String>();
                for(int i=3; i<seedEntity.size(); i++){
                    addMap(seedMap,seedEntity,i);
                }

                String xml2String = readFile(xmlFile, fileReader).toString();
                String[] lines = xml2String.split("\\r?\\n");


                for(String class1 : seedMap.keySet()){
                    String class2 = seedMap.get(class1);
                    class1 = class1.toLowerCase();
                    class2 = class2.toLowerCase();
                    for (String pLine : lines) {
                        pLine = pLine.toLowerCase();
                        addRelation(relation,pLine,class1,class2,e1,e2);
                    }//end of pLine loop
                }//end of class1 loop

                /*for(String test: relation){
                    System.out.println(test);
                }*/



            }//end of seeds loop

            TreeSet<String> seedPattern = new TreeSet<String>();

            // POS TAGGER
            for(String rLine: relation){
                //System.out.println(rLine);
                POSTagger(seedPattern,rLine,V,P,W);
            }
            for(String test: seedPattern){
                System.out.println(test);
            }

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
    public static void addMap(HashMap seedMap,ArrayList<String> seedEntity, int i){
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
            String e1, String e2){
        if(pLine.contains(class1) && pLine.contains(class2)){
            Pattern p = Pattern.compile("<\\/["+e1+"]+>.+<["+e2+"]+>");
            Matcher m = p.matcher(pLine);
            while(m.find()) {
                String temp = m.group();
                temp = temp.replaceAll("<\\/?[a-z]+>", "");
                relation.add(temp);
                //System.out.println(relation.size());
                //System.out.println(pLine);
            } // end of matcher while
        }// end of if pLine
    }

    /*=============================
    POS Tagger
    ==============================*/
    public static void POSTagger(TreeSet<String> seedPattern, String rLine, String V, String P, String W){
        MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");
        String tagged = tagger.tagString(rLine);
        System.out.println(tagged);
        String[] tLines= tagged.split(" ");
        for(int i=0; i<tLines.length;i++){
            //System.out.println(tLines[i]);
            if(tLines[i].matches(V)){
                String temp = tLines[i].substring(0,tLines[i].indexOf("_"));
                if(tLines[i+1].matches(P)){
                    temp = temp+" "+tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                }
                seedPattern.add(temp);
            }
        }
    }
}
