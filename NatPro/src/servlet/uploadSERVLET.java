package servlet;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStream;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

//import javax.xml.parsers.ParserConfigurationException;
//
//import org.xml.sax.SAXException;
import preprocessing.Tagger;
import service.GenUniqueDocID;
import service.SaveFile;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

@WebServlet({ "/UploadServlet" })
@MultipartConfig(maxFileSize = 16177215L)
public class uploadSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private	String uploadedDocumentsFolderPath = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\UploadedDocuments\\";
//	private String taggedDocumentsFolderPath = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\Tagged\\";
//	private String processingTxtFile = "C:\\Users\\Unknown\\eclipse-workspace-jee\\NatPro\\Documents\\processing.txt";

	private static final String taggedFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\TaggedBootstrap\\";
	private static String uploadedDocumentsFolderPath = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\UploadedDocuments\\";
	private static String taggedDocumentsFolderPath = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\Tagged\\";
	private static String processingTxtFile = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\processing.txt";

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("2upload.html");
	}

	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		removeFinishedFiles();
		List<Part> fileParts = request.getParts().stream()
				.filter(part -> "file-upload".equals(part.getName()) && part.getSize() > 0)
				.collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
		// List<String> files = new ArrayList<String>();

		if (fileParts.size() > 1) {
			request.setAttribute("numdocsplural", "s");
		} else {
			request.setAttribute("numdocsplural", "");
		}

		// Create and check if document unique
		List<String> fileNameList = new ArrayList<String>();
		for (Part filePart : fileParts) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

			InputStream fileContent = filePart.getInputStream();
			InputStream fileContent1 = filePart.getInputStream();
			// File targetFile = new File(fileName);
			// OutputStream outStream;

			try {
				String uniqueID = new GenUniqueDocID(fileContent).getUniqueID();
				if (checkDocument(uniqueID)) {
					System.out.println(fileName);
					if (fileName.endsWith(".pdf")) {
						new SaveFile(fileContent1, new File(uploadedDocumentsFolderPath + uniqueID + ".pdf"));
					} else if (fileName.endsWith(".txt")) {
						new SaveFile(fileContent1, new File(uploadedDocumentsFolderPath + uniqueID + ".txt"));
					}
				} else {
					// files.add(filePart.getName() + fileName);
					System.out.println("file duplicate: " + fileName);
					request.getRequestDispatcher("duplicate_upload.jsp").forward(request, response);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileNameList.add(fileName);
		}
		request.setAttribute("filenamelist", fileNameList);

		// Tag Uploaded Document
		File folder = new File(uploadedDocumentsFolderPath);
		File[] listOfFiles = folder.listFiles();

		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String name = listOfFiles[i].getName().replaceAll(".pdf", "").replaceAll(".txt", "");
				deleteProcessUntagged();
				if (checkIfProcessing(name)) {
					if (!getTaggedDocumentsNames().contains(name)) {
						int num = i;
						System.err.println("bef:>>>>>>>>>>>>>>>>>>>>>>>>>>> " + Thread.activeCount());
						Thread upThread = new Thread(() -> {
							try {
								writeUniqueID(name);
								new Tagger(folder + "\\" + listOfFiles[num].getName(), name);

								System.err.println("aft:>>>>>>>>>>>>>>>>>>>>>>>>>>> " + Thread.activeCount());
							} catch (NoSuchAlgorithmException | ClassCastException | ClassNotFoundException
									| IOException e) {
								e.printStackTrace();
								try {
									deleteUniqueID(name);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} finally {
								try {
									deleteUniqueID(name);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});

						upThread.start();
						threads.add(upThread);

					} else {
						System.out.println(name + " already tagged");
					}
				} else {

					System.out.println(name + " preprocessing ongoing");
				}
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

		request.setAttribute("threads", threads);
		request.getRequestDispatcher("/BootstrapServlet").forward(request, response);

	}
	
	public void removeFinishedFiles() {
		File Folder = new File(taggedFolder);
		File[] listFiles = Folder.listFiles();
		for (File xmlFile : listFiles) {
			if (xmlFile.delete()) {
				System.out.println("Deleted the file: " + xmlFile.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
		}
	}

	public Set<String> getTaggedDocumentsNames() throws IOException {
		Set<String> tagged = new TreeSet<String>();
		File dir = new File(taggedDocumentsFolderPath);
		File[] files = dir.listFiles((dir1, name) -> name.endsWith(".xml"));
		for (File file : files) {
			tagged.add(file.getName().replaceAll(".xml", ""));
		}

		/*
		 * for(String s : tagged) { System.out.println(s); }
		 */

		return tagged;
		/*
		 * if(files.length == 0) { return true; } return false;
		 */
	}

	public void deleteProcessUntagged() throws IOException {
		Set<String> tagged = getTaggedDocumentsNames();
		String content = FileUtils.readFileToString(new File(processingTxtFile), "UTF-8");

		for (String s : tagged) {
			content = content.replaceAll(s, "");
		}

		File tempFile = new File(processingTxtFile);
		FileUtils.writeStringToFile(tempFile, content, "UTF-8");
	}

	public void deleteUniqueID(String uniqueID) throws IOException {
		String content = FileUtils.readFileToString(new File(processingTxtFile), "UTF-8");
		content = content.replaceAll(uniqueID, "");
		File tempFile = new File(processingTxtFile);
		FileUtils.writeStringToFile(tempFile, content, "UTF-8");
	}

	public void writeUniqueID(String uniqueID) throws IOException {
		String filename = processingTxtFile;
		FileWriter fw = new FileWriter(filename, true); // the true will append the new data
		fw.write("\n" + uniqueID);// appends the string to the file
		fw.close();
	}

	public boolean checkIfProcessing(String uniqueID) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(processingTxtFile));
		String line = reader.readLine();
		while (line != null) {
			if (!line.equals("")) {
				if (line.trim().equals(uniqueID)) {
					return false;
				}
			}

			line = reader.readLine();
		}

		reader.close();

		return true;
	}

	public boolean checkDocument(String uniqueID) throws IOException {
		File dir = new File(uploadedDocumentsFolderPath);
		File[] files = dir.listFiles(
				(dir1, name) -> name.startsWith(uniqueID) && (name.endsWith(".pdf") || name.endsWith(".txt")));
		if (files.length == 0) {
			return true;
		}
		return false;
	}
}