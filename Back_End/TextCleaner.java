import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCleaner {
    String text = "";

    public TextCleaner(String text) {
        System.out.println("Cleaning TEXT...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        this.text = text;

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

        endTime = System.nanoTime ();
        System.out.println("[Text Cleaner] Duration: "+ ((double)(endTime - startTime)) / 10000000 + " ms");
    }

    public String cleanedText() {
        return text.trim();
    }

    public String getText() {
        return text.trim();
    }

    public void setText(String text) {
        this.text = text;
    }
}
