import java.io.OutputStream;
import java.io.InputStream;
import javax.servlet.http.Part;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

// 
// Decompiled by Procyon v0.5.36
// 

@WebServlet({ "/uploadSERVLET" })
@MultipartConfig(maxFileSize = 16177215L)
public class uploadSERVLET extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("2upload.html");
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    	List<Part> fileParts = request.getParts().stream().filter(part -> "file-upload".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">

	    for (Part filePart : fileParts) {
	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	        InputStream fileContent = filePart.getInputStream();
	        InputStream fileContent1 = filePart.getInputStream();
	        //File targetFile = new File(fileName);
	        //OutputStream outStream;
	        
	        try {
				String uniqueID = new GenUniqueDocID2(fileContent).getUniqueID();
				if(checkDocument(uniqueID)) {
					System.out.println(fileName);
					if(fileName.endsWith(".pdf")) {
						new SaveFile(fileContent1, new File("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\UploadedDocuments\\"+uniqueID+".pdf"));
					} else if(fileName.endsWith(".txt")) {
						new SaveFile(fileContent1, new File("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\UploadedDocuments\\"+uniqueID+".txt"));
					}
				} else {
					System.out.println("file duplicate: "+fileName);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    //request.setAttribute("invalidScript", "bootbox.alert('Successfully Registered!', function(){});");
		//request.setAttribute("invalidText", "Successfully Registered!");
		//request.getRequestDispatcher("2acomplete.html").forward(request, response);
		response.sendRedirect("2acomplete.html");
    }
    
    public static boolean checkDocument(String uniqueID) throws IOException {
    	File dir = new File("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\UploadedDocuments\\");
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(uniqueID) && name.endsWith(".pdf"));
        if(files.length == 0) {
        	return true;
        }
        return false;
    }
}