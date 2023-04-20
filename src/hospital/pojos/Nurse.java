package hospital.pojos;

import java.io.Serializable;
import java.util.Objects;


public class Nurse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer nurseID;
	private String nurseName;
	private Boolean nurseAvailability;
	private String email;
	
	public Nurse() {
		super();
	}
	
	public Nurse(Integer nurseID, String nurseName, Boolean nurseAvailability) {
		super();
		this.nurseID = nurseID;
		this.nurseName = nurseName;
		this.nurseAvailability = nurseAvailability;	
	}
	
	public Nurse(Integer nurseID, String nurseName, String email, Boolean nurseAvailability) {
		super();
		this.nurseID = nurseID;
		this.nurseName = nurseName;
		this.nurseAvailability = nurseAvailability;	
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNurseID(Integer nurseID) {
		this.nurseID = nurseID;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
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
