package hospital.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Patient implements Serializable {

	private static final long serialVersionUID = -9167697616498813358L;

	private int patientId;
	private int phoneNumber;
	private String patientName;
	private String patientSurname;
	private byte[] photo;

	public Patient(String name, String surname, int phoneNumber) throws phoneException {
		super();
		this.patientName = name;
		this.patientSurname = surname;
		if (Integer.toString(phoneNumber).length() != 9) {
			throw new phoneException();
		}
		this.phoneNumber = phoneNumber;
	}
	
	public Patient(String name, String surname, int phoneNumber, byte[] photo) throws phoneException {
		super();
		this.patientName = name;
		this.patientSurname = surname;
		if (Integer.toString(phoneNumber).length() != 9) {
			throw new phoneException();
		}
		this.phoneNumber = phoneNumber;
		this.photo= photo;
	}
	
	public Patient(int id, String name, String surname, int phoneNumber, byte[] photo) throws phoneException {
		super();
		this.patientId= id;
		this.patientName = name;
		this.patientSurname = surname;
		if (Integer.toString(phoneNumber).length() != 9) {
			throw new phoneException();
		}
		this.phoneNumber = phoneNumber;
		this.photo= photo;
	}

	public Patient(int patientId, String name, String surname, int phoneNumber) throws phoneException {
		super();
		this.patientId = patientId;
		this.patientName = name;
		this.patientSurname = surname;
		if (Integer.toString(phoneNumber).length() != 9) {
			throw new phoneException();
		}
		this.phoneNumber = phoneNumber;
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
		return patientName + " " + patientSurname + " Phone Number: " + phoneNumber + "  Id: " + patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String name) {
		this.patientName = name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int number) throws phoneException {
		if (Integer.toString(number).length() != 9) {
			throw new phoneException();
		}
		this.phoneNumber = number;
	}

	public boolean checkPhoneNumber(int number) throws phoneException {
		if (Integer.toString(number).length() != 9) {
			throw new phoneException();
		} else {
			return true;
		}
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientSurname() {
		return patientSurname;
	}

	public void setPatientSurname(String patientSurname) {
		this.patientSurname = patientSurname;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
