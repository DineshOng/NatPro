package preprocessing;

public class BioAct {
	private String bioActName;
	private String cellLine;
	
	public BioAct(String bioActName) {
		this.bioActName = bioActName;
	}
	
	public BioAct(String bioActName, String cellLine) {
		this.bioActName = bioActName;
		this.cellLine = cellLine;
	}

	public String getBioActName() {
		return bioActName;
	}

	public void setBioActName(String bioActName) {
		this.bioActName = bioActName;
	}

	public String getCellLine() {
		return cellLine;
	}

	public void setCellLine(String cellLine) {
		this.cellLine = cellLine;
	}
	
	
}
