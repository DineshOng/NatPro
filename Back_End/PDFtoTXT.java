import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;

public class PDFtoTXT {
    private String text = "";

    public PDFtoTXT(String filename){
        System.out.println("Converting PDT to TEXT...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        try{
            PDDocument document = null;
            document = PDDocument.load(new File(filename));
            document.getClass();
            if(!document.isEncrypted()){
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                text = Tstripper.getText(document);
                //System.out.println("Text:" + text);
            }
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        endTime = System.nanoTime ();
        System.out.println("[PDT to TXT Converter] Duration: "+ ((double)(endTime - startTime)) / 10000000 + " ms");
    }

    public String convertedText() {
        return text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
