import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Driver {

	public static void main(String[] args) throws NoSuchAlgorithmException, ClassCastException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		new EntityTagger("am.pdf");
		new EntityTagger("ap.pdf");
		new EntityTagger("hm.pdf");
	}

}
