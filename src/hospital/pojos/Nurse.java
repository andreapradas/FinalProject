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
	private String email;
	
	public Nurse() {
		super();
	}
	
	public Nurse(Integer nurseID, String nurseName, String email) {
		super();
		this.nurseID = nurseID;
		this.nurseName = nurseName;
		this.email = email;
	}
	
	public Nurse(String name, String email){
		super();
		this.nurseName= name;
		this.email= email;
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
				"Nurse [id=" + nurseID + ", name=" + nurseName + "]";
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
