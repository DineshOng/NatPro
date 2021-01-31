package service;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public final class NatProDirectory {
	private String resources;
	private String preprocessed;
	private String tagged;
	private String taggedBootstrap;
	private String seedsPossible;
	private String seedOutput;
	private String validation;
	
	private String ontology_file;
	private String jwnl_props_file;
	private String pre_text_file;

	public HashMap<String, String> props;
	
	public static void main(String[] args) {
		System.out.println(new NatProDirectory().getProps().get("file.ontology"));
	}
	
	public HashMap<String, String> getProps() {
		return props;
	}

	public void setProps(HashMap<String, String> props) {
		this.props = props;
	}

	public NatProDirectory() {
		

        try (InputStream input = new FileInputStream("C:\\\\Users\\\\Unknown\\\\eclipse-workspace-jee\\\\NatPro\\\\directory.properties")) {

            Properties prop = new Properties();

          

            prop.load(input);
            props = new HashMap<String, String>();
            prop.forEach((key, value) -> props.put(key+"", value+""));
            System.out.println(props);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
	
	}
}
