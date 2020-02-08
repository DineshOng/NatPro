import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiPatter4 {
    private static int MIN_FREQ = 5;
    private static int NAME_MIN_FREQ = 1;
    private static int NAME_MAX_FREQ = 4;

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
        BufferedReader reader;
        String filename = "c:\\am.pdf";

        long startTime, endTime;
        startTime = System.nanoTime ();

        //String uniqueID = new GenUniqueDocID(filename).getUniqueID();
        String uniqueID1 = new GenUniqueDocID2(filename).getUniqueID();
        String text = new PDFtoTXT(filename).convertedText();
        String cleanTxt = new TextCleaner(text).cleanText().getText();
        String txt = new SentenceSplitter(cleanTxt).getSentenceSplitText();



        FileWriter fw = new FileWriter("my-file.xml");
        fw.write(txt);
        fw.close();

        endTime = System.nanoTime ();
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");

        //([A-Za-z]+_(NNP|VBZ)\s?((,_,\s|\.?))?)+
        //(from|in)_IN\s(([A-Za-z]+_(NNP|VBZ)\s?((,_,\s|\.?))?)+)
        //(from|in)_IN\s(([A-Za-z]+_(NNP|VBZ)\s?((,_,\s|\.?))?)+)
        //(of|from|in)_IN\s(([A-Za-z]+_(NNP|VBZ)\s?((,_,\s|\.?))?)+)(in)? g2
        //(of|from|in|to)_(IN|TO)\s(([A-Za-z]+_(NNP|VBZ)\s?((,_,\s|\.?))?)+((and_CC)\s[A-Za-z]+_(NNP|VBZ)\s?)?)(in)? g3
        //((([A-Za-z0-9-]+)\s?(\([A-Za-z0-9.%]+\)),?\s?(and)?\s?){2,})

    }
}
