package service;

import java.io.IOException;

import org.openbabel.OBConversion;
import org.openbabel.OBMol;

public class GenerateCompoundSVG {
	public static void main(String[] args) throws IOException {
		new GenerateCompoundSVG("CN1C2CCC1C(C(C2)OC(=O)C3=CC=CC=C3)C(=O)OC");
	}
	
	public GenerateCompoundSVG(String cansmiles) throws IOException {
		//System.out.println(System.getProperty("java.library.path"));
		
		System.loadLibrary("openbabel_java");
				
		OBConversion conv = new OBConversion();
		OBMol mol = new OBMol();
		conv.SetInAndOutFormats("can", "svg");
		
		conv.ReadString(mol, cansmiles);
		
		//System.out.println(conv.WriteString(mol));
		
		java.io.FileWriter fw2 = new java.io.FileWriter("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\CompoundIMG\\" + cansmiles + ".svg");
        fw2.write(conv.WriteString(mol));
        fw2.close();
	}
}
