package hospital.ifaces;

import java.io.File;

import hospital.pojos.Nurse;
import hospital.pojos.Patient;

public interface XMLManager {

	public void nurse2xml(Integer id);
	public void patient2xml(Patient p);
	public Patient xml2Patient(File xml);
	Nurse xml2Nurse(File xml);
}
