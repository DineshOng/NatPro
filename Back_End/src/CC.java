import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.*;

import java.util.*;

public class CC {

  public static void main(String[] args) {
    // set up pipeline properties
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
    // use faster shift reduce parser
    props.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
    props.setProperty("parse.maxlen", "100");
    // set up Stanford CoreNLP pipeline
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    // build annotation for a review
    Annotation annotation =
        new Annotation("Chemical investigation of the dichloromethane extract from the leaves of Hoya meliflua afforded squalene and mixtures of beta-amyrin (1a), alpha-amyrin (1b) and lupeol (1c) in about 1:1:0.25 ratio; oleanone (2a), ursenone (2b) and lupenone (2c) in about 1:1:0.3 ratio; beta-amyrin cinnamate (3a), alpha-amyrin cinnamate (3b) and lupenyl cinnamate (3c) in about 0.5:0.3:1 ratio; beta-sitosterol and stigmasterol in about 5:1 ratio.");
    // annotate
    pipeline.annotate(annotation);
    // get tree
    Tree tree =
        annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);
    System.out.println(tree);
    Set<Constituent> treeConstituents = tree.constituents(new LabeledScoredConstituentFactory());
    for (Constituent constituent : treeConstituents) {
      if (constituent.label() != null &&
          (constituent.label().toString().equals("NP"))) {
        //System.err.println("found constituent: "+constituent.toString());
        System.out.println(tree.getLeaves().subList(constituent.start(), constituent.end()+1));
      }
    }
  }
}