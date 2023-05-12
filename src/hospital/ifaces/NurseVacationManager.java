package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.Nurse;
import hospital.pojos.NurseVacation;

public interface NurseVacationManager {
	List<NurseVacation> getNurseReservedVacation (int id);
	void addVacation(NurseVacation nV);
	List<Nurse> getNursesOnVacation(Date start, Date end);
	void modifyNurseVacation(int vacationId, Date starts, Date ends);
	void deleteNurseVacationById(int vacationId);
	List<NurseVacation> getAllVacations();
	int countNurseVacations(int id, int year);
	List<Nurse> getNursesOnVacation(Date date);
	List<NurseVacation> getMyVacationsNurse(int id);
}