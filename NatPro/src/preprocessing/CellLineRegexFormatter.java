package preprocessing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CellLineRegexFormatter {
    public static void main(String[] args) throws IOException{
        BufferedReader reader;
//        String cellLinesTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\cell-lines.txt";
//        String clTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Resources\\cl.txt";
        
        String cellLinesTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\cell-lines.txt";
        String clTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Resources\\cl.txt";
        try {
            reader = new BufferedReader(new FileReader(cellLinesTxtFile));
            String line = reader.readLine();
            String str = "";
            while (line != null) {
                //System.out.println(line);
                String cell = line.replaceAll("\\([0-9 A-Za-z]+\\)", "").trim();
                
                //String s = "";
                str += cell + "\n";
                if(cell.contains("-"))
                	str += cell.replaceAll("-", "") + "\n";
                
                if(cell.contains("cell line")) {
                	str += cell.replaceAll("cell line", "") + "\n";
                	if(cell.contains("-"))
                		str += cell.replaceAll("-", "").replaceAll("cell line", "") + "\n";
                }
                System.out.println(cell);
                // read next line
                line = reader.readLine();
            }
            reader.close();
            
            java.io.FileWriter fw2 = new java.io.FileWriter(clTxtFile);
	        fw2.write(str);
	        fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        new SortbyStringLength(clTxtFile);
    }
}
