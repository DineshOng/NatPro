import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.list.PointerTargetTree;
import net.didion.jwnl.dictionary.Dictionary;

public class cff {

	public static void main(String[] args) throws FileNotFoundException, JWNLException {
		// TODO Auto-generated method stub
	
		
		try {
            // initialize JWNL (this must be done before JWNL can be used)
            JWNL.initialize(new FileInputStream("file_properties.xml"));
            IndexWord word = Dictionary.getInstance().lookupIndexWord(POS.VERB, "acquire");

            Synset synset[] = word.getSenses();
            for(int i=0;i<synset.length;i++){
                System.out.println(synset[i].toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
		
		
    }

}
