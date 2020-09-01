package model;

public class Validation {

	String Category;
	String entity1;
	String entity2;
	String pdfFilePath;
	
	public Validation(String pdfFilePath) {
		// TODO Auto-generated constructor stub
		this.pdfFilePath = pdfFilePath;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getEntity1() {
		return entity1;
	}

	public void setEntity1(String entity1) {
		this.entity1 = entity1;
	}

	public String getEntity2() {
		return entity2;
	}

	public void setEntity2(String entity2) {
		this.entity2 = entity2;
	}

	public String getPdfFilePath() {
		return pdfFilePath;
	}

	public void setPdfFilePath(String pdfFilePath) {
		this.pdfFilePath = pdfFilePath;
	}

}
