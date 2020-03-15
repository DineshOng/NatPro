package service;

import java.io.*;

import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;

public class SaveAsJPEG {

    public SaveAsJPEG(String canSMILES, String svg) throws Exception {

    	Reader reader = new BufferedReader(new StringReader(svg));
        
        // Create a JPEG transcoder
        JPEGTranscoder t = new JPEGTranscoder();

        // Set the transcoding hints.
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                   new Float(.8));

        // Create the transcoder input.
        String svgURI = svg;
        TranscoderInput input = new TranscoderInput(reader);

        // Create the transcoder output.
        OutputStream ostream = new FileOutputStream("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\WebContent\\media\\compound\\"+canSMILES+".jpg");
        TranscoderOutput output = new TranscoderOutput(ostream);

        // Save the image.
        t.transcode(input, output);

        // Flush and close the stream.
        ostream.flush();
        ostream.close();
        System.exit(0);
    }
}