package hospital.ifaces;

import java.util.List;
import hospital.pojos.Patient;


public interface PatientManager {

	public void addPatient(Patient p);
	public void updatePhoneNumber(int pat_id, int phoneNumber);
	public List<Patient> getListOfPatients();
	//List of Patients with the same name
	public List<Patient> getListPatientByName(String patientName);
	//
	public Patient getPatientByName(String patientName);
	public List<Patient> getPatientBySurname(String patientSurname, String patientName);
	Patient getPatientById(int id);

	
}