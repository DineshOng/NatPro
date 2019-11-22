import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenUniqueDocID {
    private String id = "";

    public GenUniqueDocID(String filename) throws IOException, NoSuchAlgorithmException {
        System.out.println("Generating Unique Doc ID...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        InputStream inputStream = new FileInputStream(filename);
        DigestInputStream digestInputStream = new DigestInputStream(inputStream, MessageDigest.getInstance("md5"));

        //String digestBeforeRead = getMessageDigest(digestInputStream);
        while (digestInputStream.read() != -1) {
        }
        id = getMessageDigest(digestInputStream);
        //System.out.println("digestBeforeRead : " + digestBeforeRead);
        digestInputStream.close();

        endTime = System.nanoTime ();
        System.out.println("[Generate Unique Doc ID] Duration: "+ ((double)(endTime - startTime)) / 10000000 + " ms");
        System.out.println("id: " + id);
    }

    private static String getHexaString(byte[] data) {
        String result = new BigInteger(1, data).toString(16);
        return result;
    }

    private static String getMessageDigest(DigestInputStream digestInputStream) {
        MessageDigest digest = digestInputStream.getMessageDigest();
        byte[] digestBytes = digest.digest();
        String digestStr = getHexaString(digestBytes);
        return digestStr;
    }

    public String getUniqueID() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
