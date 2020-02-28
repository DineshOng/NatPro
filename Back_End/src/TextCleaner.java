import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.Word;

public class TextCleaner {
    String text = "";

    public TextCleaner(String text) {
        this.text = text;
    }

    public TextCleaner cleanText() {
        System.out.println("Cleaning TEXT...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        //text = text.replaceAll("[A-Za-z]+\n", "").replaceAll("[0-9]+\n", "");
        text = text.replaceAll("\\s\n"," ");
        text = text.replaceAll("-\\s", "");
        //text = text.replaceAll("-@-@","");
        //text = text.replaceAll("@-@"," ");

        
        
        //text = text.replaceAll("-\\s","");
        //text = text.replaceAll("\\s\n"," ");
        text = text.replaceAll("α","alpha");
        text = text.replaceAll("β","beta");
        text = text.replaceAll("γ","gamma");
        
        text = text.replaceAll("Ñ","N");
        text = text.replaceAll("ñ","n");
        
        text = text.replaceAll("′","'");
        
        text = text.replaceAll("–","-");
        text = text.replaceAll("−","-");
        
        text = text.replaceAll("”","");
        text = text.replaceAll("“","");
        
        text = text.replaceAll("_+", " ");
        
        text = text.replaceAll("INTRODUCTION", ".\n");
        text = text.replaceAll("Introduction", ".\n");
        text = text.replaceAll("RESUTS AND DISCUSSION", ".\n");
        text = text.replaceAll("RESULTS AND DISCUSSION", ".\n");
        text = text.replaceAll("Results and Discussion", ".\n");
        text = text.replaceAll("CONCLUSION", ".\n");
        text = text.replaceAll("Conclusion", ".\n");
        text = text.replaceAll("ACKNOWLEDGEMENT", ".\n");
        text = text.replaceAll("Acknowledgement", ".\n");
        
        text = text.replaceAll("(References?|REFERENCES?).*", "");
        
        text = text.replaceAll("[^\\x00-\\x7F]", "");
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
        text = text.replaceAll("\\p{C}", "");
        
        
        
        // Remove references in brackets hello[20,22].
        text = text.replaceAll("\\[[0-9,]+\\]", "");

        

        //Pattern p = Pattern.compile("(\\.[0-9]+(,[0-9]+)?) ([A-Z]+)");
        Pattern p = Pattern.compile("(\\.[0-9]+(,[0-9]+)?)\\s?([A-Z]+)");
        Matcher m = p.matcher(text);
        while(m.find()) {
            text = text.replace(m.group(), ". " + m.group(3));
        }
        
        p = Pattern.compile("([A-Za-z]{2,}\\.\\s[a-z])");
        m = p.matcher(text);
        while(m.find()) {
            //System.err.println(m.group());
            text = text.replace(m.group(), m.group().replaceAll("\\.", " "));
        }
        
        p = Pattern.compile("([A-Za-z]+\\.)[0-9]+\\s?([A-Za-z-]+)");
        m = p.matcher(text);
        while(m.find()) {
            //System.err.println(m.group());
        	String str = m.group(2);
        	String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
        	
            text = text.replace(m.group(), m.group(1) + " " + cap + " ");
        }
        
        // Remove references ex. (Hauert et al., 1974), (Naeem, 2017)
        p = Pattern.compile("\\([A-Z][a-z]+\\s?(et\\s?al\\.)?,\\s?[0-9]{4,}\\)");
        m = p.matcher(text);
        while(m.find()) {
            //System.err.println(m.group());
            text = text.replace(m.group(), "");
        }
        
        /*
        
        // Remove reference referencing ex. anti-microbial3,3
        p = Pattern.compile("([A-Za-z]+)(,[\\d]+)+");
        m = p.matcher(text);
        while(m.find()) {
        	//System.err.println(m.group());
            text = text.replace(m.group(), m.group(1)+",");
        }
        
        */
        
        p = Pattern.compile("([A-Za-z]+)(,[\\d]+)+\\s");
        m = p.matcher(text);
        while(m.find()) {
        	//System.err.println(m.group());
            text = text.replace(m.group(), m.group(1)+", ");
        }
        
        p = Pattern.compile("([A-Za-z]{4,})[0-9]+");
        m = p.matcher(text);
        while(m.find()) {
        	//System.err.println(m.group());
            text = text.replace(m.group(), m.group(1));
        }
        
        p = Pattern.compile("\\(([A-Za-z.]{4,})\\)(\\s[A-Z])");
        m = p.matcher(text);
        while(m.find()) {
        	//System.err.println(m.group());
            text = text.replace(m.group(), m.group(1).replaceAll("\\.", "") + m.group(2));
        }
        
        // word. 28
        p = Pattern.compile("([a-z\\)]+\\.) ?[0-9]+");
        m = p.matcher(text);
        while(m.find()) {
        	//System.err.println(m.group());
            text = text.replace(m.group(), m.group(1)+" ");
        }
        
        // Replaces double or more spaces into single space
        text = text.replaceAll("\\s{2,}", " ");
        
        endTime = System.nanoTime ();
        System.err.println("[Text Cleaner] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");

        return this;
    }

    public String getText() {
        return text.trim();
    }

    public void setText(String text) {
        this.text = text;
    }
}
