import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        text = text.replaceAll("[^\\x00-\\x7F]", "");
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
        text = text.replaceAll("\\p{C}", "");

        text = text.replaceAll("\\s{2,}", " ");

        text = text.replaceAll("(References?|REFERENCES?).*", "");

        Pattern p = Pattern.compile("(\\.[0-9]+(,[0-9]+)?) ([A-Z]+)");
        Matcher m = p.matcher(text);
        while(m.find()) {
            text = text.replace(m.group(), ". " + m.group(3));
        }
        
        p = Pattern.compile("([A-Za-z]{2,}\\.\\s[a-z])");
        m = p.matcher(text);
        while(m.find()) {
            System.err.println(m.group());
            text = text.replace(m.group(), m.group().replaceAll("\\.", ""));
        }

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
