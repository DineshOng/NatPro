import java.io.OutputStream;
import java.io.InputStream;
import javax.servlet.http.Part;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.file.Paths;
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

@WebServlet({ "/registerSERVLET" })
@MultipartConfig(maxFileSize = 16177215L)
public class registerSERVLET extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("2upload.html");
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String description = request.getParameter("description");
        final Part filePart = request.getPart("file-upload");
        final String fileName = Paths.get(filePart.getSubmittedFileName(), new String[0]).getFileName().toString();
        final InputStream fileContent = filePart.getInputStream();
        final byte[] buffer = new byte[fileContent.available()];
        fileContent.read(buffer);
        final File targetFile = new File(fileName);
        final OutputStream outStream = new FileOutputStream("C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\PDF\\"+targetFile);
        outStream.write(buffer);
    }
}