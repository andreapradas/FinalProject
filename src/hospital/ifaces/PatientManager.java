package hospital.ifaces;

import java.util.List;

import hospital.pojos.Patient;


public interface PatientManager {

	void addPatient(Patient p);
	void updatePhoneNumber(int pat_id, int phoneNumber);
	List<Patient> getListOfPatients();
	
}