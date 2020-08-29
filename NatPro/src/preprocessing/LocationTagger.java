package preprocessing;
import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;

public class LocationTagger extends EntityTagger {
	public LocationTagger(String tag, String text) {
		super(tag, text);
	}
	
	public String run() throws ClassCastException, ClassNotFoundException, IOException {
		hideTaggedEntities();
		
//		String serializedClassifier = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\classifiers\\english.all.3class.distsim.crf.ser.gz";
		String serializedClassifier = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\classifiers\\english.all.3class.distsim.crf.ser.gz";

        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);
        List<Triple<String,Integer,Integer>> triples = classifier.classifyToCharacterOffsets(text);
        
        for (Triple<String,Integer,Integer> trip : triples) {
            if(trip.first.equals("LOCATION")) {
                //System.out.println(trip.first + " - " + txt.substring(trip.second, trip.third));
            	
            	found_entities.add(text.substring(trip.second, trip.third));
            	
                Integer n = map.get(text.substring(trip.second, trip.third));
                n = (n == null) ? 1 : ++n;
                map.put(text.substring(trip.second, trip.third), n);
                
            } else if(trip.first.equals("PERSON")) {
            	//System.err.println(text.substring(trip.second, trip.third));
            }
        }
        
        tagEntities();
        resolveHiddenEntities();
        removeOverlappingTags();
        
        printEntityFrequencyCount();
        
        return text;
	}
}
