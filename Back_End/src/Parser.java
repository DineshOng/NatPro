import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.LabeledWord;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.TregexPattern;

class Parser {

    private final static String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";        

    private final TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");

    private final LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);

    public Tree parse(String str) {                
        List<CoreLabel> tokens = tokenize(str);
        Tree tree = parser.apply(tokens);
        return tree;
    }

    private List<CoreLabel> tokenize(String str) {
        Tokenizer<CoreLabel> tokenizer =
            tokenizerFactory.getTokenizer(
                new StringReader(str));    
        return tokenizer.tokenize();
    }

    public static void main(String[] args) { 
        String str = "Chemical investigation of the dichloromethane extract from the leaves of Hoya meliflua afforded squalene and mixtures of β-amyrin (1a), α-amyrin (1b) and lupeol (1c) in about 1:1:0.25 ratio; oleanone (2a), ursenone (2b) and lupenone (2c) in about 1:1:0.3 ratio; β-amyrin cinnamate (3a), α-amyrin cinnamate (3b) and lupenyl cinnamate (3c) in about 0.5:0.3:1 ratio; and β-sitosterol and stigmasterol in about 5:1 ratio.";
        Parser parser = new Parser(); 
        Tree tree = parser.parse(str);  

        getNounPhrases(tree);
        
        System.out.println(tree);
        
        List<Tree> leaves = tree.getLeaves();
        // Print words and Pos Tags
        for (Tree leaf : leaves) { 
            Tree parent = leaf.parent(tree);
            System.out.print(leaf.label().value() + "-" + parent.label().value() + " ");
        }
        System.out.println();               
    }
    
    private static List<String> getNounPhrases(Tree parse) {
        List<String> result = new ArrayList<>();
        TregexPattern pattern = TregexPattern.compile("@NP");
        TregexMatcher matcher = pattern.matcher(parse);
        while (matcher.find()) {
            Tree match = matcher.getMatch();
            List<Tree> leaves = match.getLeaves();
            System.out.println(leaves);
            // Some Guava magic.
            String nounPhrase = Joiner.on(' ').join(Lists.transform(leaves, Functions.toStringFunction()));
            result.add(nounPhrase);
            //List<LabeledWord> labeledYield = match.labeledYield();
            //System.out.println("labeledYield: " + labeledYield);
        }
        return result;
    }
}