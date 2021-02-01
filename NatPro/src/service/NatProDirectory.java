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
	// change with the file path of directory.properties in your workspace/machine
	public static final String filname = "C:\\\\Users\\\\eduar\\\\Documents\\\\GitHub\\\\NatPro\\\\NatPro\\\\directory.properties";
	
//	public HashMap<String, String> props;
//	
//	public HashMap<String, String> getProps() {
//		return props;
//	}
//
//	public void setProps(HashMap<String, String> props) {
//		this.props = props;
//	}
	
	public String projectDir;
	public String getProjectDir() {
		return projectDir;
	}

	public void setProjectDir(String projectDir) {
		this.projectDir = projectDir;
	}

	public NatProDirectory() {

//		try (InputStream input = new FileInputStream(filname)) {
//
//			Properties prop = new Properties();
//			prop.load(input);
//			props = new HashMap<String, String>();
//			prop.forEach((key, value) -> props.put(key + "", value + ""));
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}

		try (InputStream input = new FileInputStream(filname)) {
			Properties prop = new Properties();
			prop.load(input);
			projectDir = prop.getProperty("dir.project");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


}
