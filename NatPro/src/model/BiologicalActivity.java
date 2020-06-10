package model;

public class BiologicalActivity {
	public static String CLASS_BioAct = "BiologicalActivity";
	
	String biologicalActivity;
	CellLine cellLine;
	
	public BiologicalActivity(String biologicalActivity) {
		this.biologicalActivity = biologicalActivity;
	}

	public String getBiologicalActivity() {
		return biologicalActivity;
	}

	public void setBiologicalActivity(String biologicalActivity) {
		this.biologicalActivity = biologicalActivity;
	}

	public CellLine getCellLine() {
		return cellLine;
	}

	public void setCellLine(CellLine cellLine) {
		this.cellLine = cellLine;
	}
	
	
}
