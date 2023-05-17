package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.Surgeon;
import hospital.pojos.SurgeonVacation;

public interface SurgeonVacationManager {
	List<SurgeonVacation> getSurgeonReservedVacation (int id);
	void addVacation(SurgeonVacation sV);
	List<Surgeon> getSurgeonsOnVacation(Date start, Date end);
	void modifySurgeonVacation(int vacationId, Date starts, Date ends);
	void deleteSurgeonVacationById(int vacationId);
	List<SurgeonVacation> getAllVacations();
	List<Surgeon> getSurgeonsOnVacation(Date date);
	List<SurgeonVacation> getMyVacationsSurgeon(int id);
	int countSurgeonVacations(int id, int year);
}
