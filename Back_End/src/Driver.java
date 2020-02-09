import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Driver {

	public static void main(String[] args) throws NoSuchAlgorithmException, ClassCastException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		long startTime, endTime;
        startTime = System.nanoTime();
		
		new EntityTagger("am.pdf");
		new EntityTagger("ap.pdf");
		new EntityTagger("hm.pdf");
		
		endTime = System.nanoTime();
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        System.err.println("Duration: "+ ((double)(endTime - startTime)) / 1000000000 + " s");
	}

}
