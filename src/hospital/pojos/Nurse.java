package hospital.pojos;

import java.io.Serializable;
import java.util.Objects;


public class Nurse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer nurseId;
	private String nurseName;
	private String nurseSurname;
	private String email;
	
	public Nurse() {
		super();
	}
	
	public Nurse(Integer nurseId, String nurseSurname, String nurseName, String email) {
		super();
		this.nurseId = nurseId;
		this.nurseName = nurseName;
		this.nurseSurname = nurseSurname;
		this.email = email;
	}
	
	public Nurse(String name, String surname,String email){
		super();
		this.nurseName= name;
		this.nurseSurname= surname;
		this.email= email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}

	@Override
	public String toString() {
		return 
				"Nurse [id=" + nurseId + ", name=" + nurseName + ", surname=" + nurseSurname + "]";
	}

	public String getNurseSurname() {
		return nurseSurname;
	}

	public void setNurseSurname(String nurseSurname) {
		this.nurseSurname = nurseSurname;
	}

	public Integer getNurseId() {
		return nurseId;
	}

	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}
	
	public String getNurseName() {
		return nurseName;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nurseId);
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
		return Objects.equals(nurseId, other.nurseId);
	}
}
