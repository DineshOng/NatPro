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
	//change with your file path of directory.properties
	public static final String filname = "C:\\\\Users\\\\Unknown\\\\eclipse-workspace-jee\\\\NatPro\\\\directory.properties";
	public HashMap<String, String> props;

	public HashMap<String, String> getProps() {
		return props;
	}

	public void setProps(HashMap<String, String> props) {
		this.props = props;
	}

	public NatProDirectory() {

		try (InputStream input = new FileInputStream(filname)) {

			Properties prop = new Properties();
			prop.load(input);
			props = new HashMap<String, String>();
			prop.forEach((key, value) -> props.put(key + "", value + ""));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
