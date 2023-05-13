package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.Surgeon;
import hospital.pojos.SurgeonVacation;

public interface SurgeonVacationManager {
	List<SurgeonVacation> getSurgeonReservedVacation (int id);
	void addVacation(SurgeonVacation sV);
	List<Surgeon> getSurgeonsOnVacation(java.sql.Date start, java.sql.Date end);
	void modifySurgeonVacation(int vacationId, java.sql.Date starts, java.sql.Date ends);
	void deleteSurgeonVacationById(int vacationId);
	List<SurgeonVacation> getAllVacations();
	List<Surgeon> getSurgeonsOnVacation(Date date);
	List<SurgeonVacation> getMyVacationsSurgeon(int id);
	int countSurgeonVacations(int id, int year);
}
