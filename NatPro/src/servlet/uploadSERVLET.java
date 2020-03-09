package servlet;
import java.io.OutputStream;
import java.io.InputStream;
import javax.servlet.http.Part;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.xml.sax.SAXException;
import preprocessing.Tagger;
import service.GenUniqueDocID2;
import service.SaveFile;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
    	//List<String> files = new ArrayList<String>();
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
					//files.add(filePart.getName() + fileName);
					System.out.println("file duplicate: "+fileName);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    //for(String e :files ) {
	    //	System.out.println("hi   " + e);
	    //}
	    
	    //request.setAttribute("invalidScript", "bootbox.alert('Successfully Registered!', function(){});");
		//request.setAttribute("invalidText", "Successfully Registered!");
	    //request.setAttribute("duplicateDoc", files);
	    
		//request.getRequestDispatcher("2acomplete.jsp").forward(request, response);
		response.sendRedirect("2acomplete.jsp");
		
		
		
		File folder = new File("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\UploadedDocuments\\");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    	String name = listOfFiles[i].getName().replaceAll(".pdf", "").replaceAll(".txt", "");
		    	if(!getTaggedDocumentsNames().contains(name)) {
		    		int num = i;
		    		new Thread(() -> {
		    			try {
							new Tagger(folder+ "\\"+listOfFiles[num].getName(), name);
						} catch (NoSuchAlgorithmException | ClassCastException | ClassNotFoundException | IOException
								| ParserConfigurationException | SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}).start();
		    	}
		    	else {
		    		System.out.println(name + " already tagged");
		    	}
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
    }
    
    public Set<String> getTaggedDocumentsNames() throws IOException {
    	Set<String> tagged = new TreeSet<String>();
    	File dir = new File("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Tagged\\");
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".xml"));
        for(File file: files) {
        	tagged.add(file.getName().replaceAll(".xml", ""));
        }
        
        /*for(String s : tagged) {
        	System.out.println(s);
        }*/
        
        return tagged;
        /*
        if(files.length == 0) {
        	return true;
        }
        return false;*/
    }
    
    public boolean checkDocument(String uniqueID) throws IOException {
    	File dir = new File("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\UploadedDocuments\\");
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(uniqueID) && (name.endsWith(".pdf") || name.endsWith(".txt")));
        if(files.length == 0) {
        	return true;
        }
        return false;
    }
}