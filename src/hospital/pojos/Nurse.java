package hospital.pojos;

import java.io.Serializable;
import java.util.Objects;


public class Nurse implements Serializable{
	private static final long serialVersionUID = 1L;
	private int nurseId;
	private String nurseName;
	private String nurseSurname;
	private String email;
	
	public Nurse() {
		super();
	}
	
	public Nurse(int nurseId, String nurseName, String nurseSurname, String email) {
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

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
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
