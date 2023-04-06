package hospital.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = -9167697616498813358L;
	
	private int patientId;
	private int phoneNumber;
	private String name;
	
	public Patient(int patientId, int phoneNumber, String name) throws phoneException {
		this.patientId= patientId;
		this.name= name;
		if(Integer.toString(phoneNumber).length()!= 9){
			throw new phoneException();			
		}
		this.phoneNumber= phoneNumber;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.patientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(this.patientId, other.patientId);
	}

	@Override
	public String toString() {
		return "Patient [id= " + patientId + ", name= " + name + ", phone number= " + phoneNumber + "]";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int number) throws phoneException {
		if(Integer.toString(phoneNumber).length()!= 9){
			throw new phoneException();			
		}
		this.phoneNumber= number;
	}
}
