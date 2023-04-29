package hospital.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Surgeon implements Serializable{
	private static final long serialVersionUID = 5459002349058385563L;
	
	private Integer surgeonId;
	private String email;
	private String name;
	private String surname;
	private Boolean chief;
	private List<Boolean> hourAvailability;
	
	public Surgeon() {
		super();
	}
	
	public List<Boolean> getHourAvailability() {
		return hourAvailability;
	}

	public void setHourAvailability(List<Boolean> hourAvailability) {
		this.hourAvailability = hourAvailability;
	}

	public void setSurgeonId(Integer surgeonId) {
		this.surgeonId = surgeonId;
	}

	public void setChief(Boolean chief) {
		this.chief = chief;
	}

	public Surgeon(String name, String surname, String email, boolean chief){
		super();
		this.name= name;
		this.surname= surname;
		this.email= email;
		this.chief= chief;
	}
	
	public Surgeon(String name, Integer id){
		super();
		this.name= name;
		this.surgeonId= id;
	}
	
	public void setSurgeonId(int surgeonId) {
		this.surgeonId = surgeonId;
	}

	public Surgeon(int surgeonId, String name, String surname, String email, boolean chief){
		this.surgeonId= surgeonId;
		this.name= name;
		this.email= email;
		this.chief= chief;
	}
		

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, surgeonId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Surgeon other = (Surgeon) obj;
		return Objects.equals(email, other.email) && surgeonId == other.surgeonId;
	}

	@Override
	public String toString() {
		return "Surgeon [id= " + surgeonId + ", name= " + name + ", surname= " + surname + ", email= " + email +
				", chief= " + chief + "]";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	
	public int getSurgeonId() {
		return surgeonId;
	}
	
	public boolean getChief() {
		return chief;
	}
	
	public void setChief(boolean chief) {
		this.chief= chief;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
