package hospital.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient implements Serializable {

	private static final long serialVersionUID = -9167697616498813358L;

	private int patientId;
	private int phoneNumber;
	private String patientName;
	private String patientSurname;
	private List<Surgery> surgeries;

	public Patient() {
		super();
		surgeries = new ArrayList<Surgery>();
	}

	public Patient(String name, String surname, int phoneNumber) throws phoneException {
		super();
		this.patientName = name;
		this.patientSurname = surname;
		if (Integer.toString(phoneNumber).length() != 9) {
			throw new phoneException();
		}
		this.phoneNumber = phoneNumber;
		surgeries = new ArrayList<Surgery>();
	}

	public Patient(String name, String surname) throws phoneException {
		super();
		this.patientName = name;
		this.patientSurname = surname;
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
		surgeries = new ArrayList<Surgery>();
	}

	public Patient(String name, int phoneNumber) throws phoneException {
		super();
		this.patientName = name;
		if (Integer.toString(phoneNumber).length() != 8) {
			throw new phoneException();
		}
		this.phoneNumber = phoneNumber;
		surgeries = new ArrayList<Surgery>();
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

	public List<Surgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(List<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

}
