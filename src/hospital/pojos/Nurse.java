package hospital.pojos;

import java.util.Objects;


public class Nurse {
	private Integer nurseID;
	private String nurseName;
	public Boolean nurseAvailability;
	
	public Nurse() {
		super();
	}
	
	public Nurse(Integer nurseID, String nurseName, Boolean nurseAvailability) {
		super();
		this.nurseID = nurseID;
		this.nurseName = nurseName;
		this.nurseAvailability = nurseAvailability;	
	}
	
	@Override
	public String toString() {
		return 
				"Nurse [id=" + nurseID + ", name=" + nurseName + ", available=" + nurseAvailability + "]";
	}

	public Integer getNurseID() {
		return nurseID;
	}

	public void setNurseId(Integer nurseID) {
		this.nurseID = nurseID;
	}
	
	public String getNurseName() {
		return nurseName;
	}

	public void setId(String nurseName) {
		this.nurseName = nurseName;
	}
	
	public Boolean getNurseAvailability() {
		return nurseAvailability;
	}

	public void setNurseAvailability(Boolean nurseAvailability) {
		this.nurseAvailability = nurseAvailability;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nurseID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nurse other = (Nurse) obj;
		return Objects.equals(nurseID, other.nurseID);
	}
}
