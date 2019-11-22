import edu.stanford.nlp.pipeline.*;
import java.util.Properties;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.XMLOutputter;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.CoreMap;

public class SentenceSplitter {
    private String text = "";
    private String sstext = "";

    public SentenceSplitter(String text) {
        System.out.println("Splitting Sentences...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        this.text = text;

        Properties props=new Properties();
        props.put("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = null;

        annotation = new Annotation(text);
        pipeline.annotate(annotation);

        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            sstext += "<sentence>" + sentence + "</sentence>\n\n";
        }

        endTime = System.nanoTime ();
        System.out.println("[Sentence Splitter] Duration: "+ ((double)(endTime - startTime)) / 10000000 + " ms");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSentenceSplitText() {
        return sstext;
    }

    public String getSstext() {
        return sstext;
    }

    public void setSstext(String sstext) {
        this.sstext = sstext;
    }
}
