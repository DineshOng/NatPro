package Servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class servlet_docUpload
 */
@WebServlet("/servlet_docUpload")
public class servlet_docUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static String serverPath = "/tmp/jcg";
	
//	//code based from https://www.tutorialspoint.com/servlets/servlets-file-uploading.htm
//	private boolean isMultipart;
//	private String filePath;
//	private int maxFileSize = 50 * 1024;
//	private int maxMemSize = 4 * 1024;
//	private File file;
//	
//	public void init() {
//		// Get the file location where it would be stored
//		filePath = getServletContext().getInitParameter("file-upload");
//	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet_docUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		throw new ServletException("GET method used with " + getClass().getName()+": POST method required.") ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		// https://examples.javacodegeeks.com/enterprise-java/servlet/java-servlet-file-upload-example/
		response.setContentType("text/html;charset=UTF-8");
		
		final Part filePart = request.getPart("file");
		String fileName = getFileName(filePart);
		
		OutputStream out = null;
		InputStream filecontent = null;
		final PrintWriter writer = response.getWriter();
		
		try {
			out = new FileOutputStream(new File(serverPath + File.separator + fileName));
			filecontent = filePart.getInputStream();
 
			int read = 0;
			final byte[] bytes = new byte[1024];
 
			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			writer.println("New file " + fileName + " created at " + serverPath);
 
		} catch (FileNotFoundException fne) {
			writer.println("Missing file or no insufficient permissions.");
			writer.println(" ERROR: " + fne.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
			if (writer != null) {
				writer.close();
			}
		}
			
		
		
		
//		// Check that we have a file upload request
//		isMultipart = ServletFileUpload.isMultipartContent(request);
//		response.setContentType("text/html");
//		java.io.PrintWriter out = response.getWriter();
//		
//		if (!isMultipart) {
//			out.println("<html>");
//			out.println("<head>");
//			out.println("<title>Servlet upload</title>");
//			out.println("</head>");
//			out.println("<body>");
//			out.println("<p>No file uploaded</p>");
//			out.println("</body>");
//			out.println("</html>");
		}
		
	private String getFileName(Part filePart) {
		String header = filePart.getHeader("content-disposition");
		String name = header.substring(header.indexOf("filename=\"")+10);
		return name.substring(0, name.indexOf("\""));
	}

}
