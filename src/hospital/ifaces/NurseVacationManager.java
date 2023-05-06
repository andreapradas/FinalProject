package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.Nurse;
import hospital.pojos.NurseVacation;

public interface NurseVacationManager {
	List<NurseVacation> getNurseReservedVacation (int id);
	void addVacation(NurseVacation nV);
	List<Nurse> getNursesOnVacation(java.sql.Date start, java.sql.Date end);
	void modifyNurseVacation(int vacationId, java.sql.Date starts, java.sql.Date ends);
//	int getNurseVacationId(int vacationId, java.sql.Date start, java.sql.Date end);
	void deleteNurseVacationById(int vacationId);
	List<NurseVacation> getAllVacations();
	int countNurseVacations(int id);
	List<Nurse> getNursesOnVacation(Date date);
	List<NurseVacation> getMyVacationsNurse(int id);
	void deleteNurseVacations(int nId);
}