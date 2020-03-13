package service;

import org.openbabel.OBConversion;
import org.openbabel.OBMol;

public class GenerateCompoundSVG {
	public static void main(String[] args) {
		new GenerateCompoundSVG("CN1C2CCC1C(C(C2)OC(=O)C3=CC=CC=C3)C(=O)OC");
	}
	
	public GenerateCompoundSVG(String cansmiles) {
		//System.out.println(System.getProperty("java.library.path"));
		
		System.loadLibrary("openbabel_java");
				
		OBConversion conv = new OBConversion();
		OBMol mol = new OBMol();
		conv.SetInAndOutFormats("can", "svg");
		
		conv.ReadString(mol, cansmiles);
		
		System.out.println(conv.WriteString(mol));
		
	}
}
