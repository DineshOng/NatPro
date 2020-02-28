import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SSplit {
	private String text;
    private String sstext;
    
	public SSplit(String text) throws FileNotFoundException {
		this.text = text;
		InputStream modelIn = new FileInputStream("en-sent.bin");

		try {
		  SentenceModel model = new SentenceModel(modelIn);
		  SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		  String sentences[] = sentenceDetector.sentDetect(text);
		  
		  for(String s: sentences) {
			  sstext += s + "\n\n";
		  }
		  
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		
		
		// TODO Auto-generated constructor stub
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
