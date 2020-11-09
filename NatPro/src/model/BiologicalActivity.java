package model;

public class BiologicalActivity {
	
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
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((biologicalActivity == null) ? 0 : biologicalActivity.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BiologicalActivity other = (BiologicalActivity) obj;
//		if (biologicalActivity == null) {
//			if (other.biologicalActivity != null)
//				return false;
//		} else if (!biologicalActivity.equals(other.biologicalActivity))
//			return false;
//		return true;
//	}
}
