package hospital.ifaces;

import java.util.List;
import hospital.pojos.Patient;


public interface PatientManager {

	public void addPatient(Patient p) throws Exception;
	public void updatePhoneNumber(int pat_id, int phoneNumber) throws Exception;
	public List<Patient> getListOfPatients();
	public List<Patient> getListPatientByName(String patientName); //List of Patients with the same name
	public Patient getPatientByName(String patientName);
	Patient getPatientById(int id);
	String getPatCompleteNametById(int id);
	void addPatient(Patient p, String fileName) throws Exception;
}