package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.SurgeonVacation;

public interface SurgeonVacationManager {
	List<SurgeonVacation> getSurgeonReservedVacation (int id);
	void addVacation(SurgeonVacation sV);
	List<SurgeonVacation> getSurgeonsOnVacation(Date start, Date end);
	void modifySurgeonVacation(int vacationId, Date starts, Date ends);
	int getSurgeonVacationId(int vacationId, Date start, Date end);
	public void deleteSurgeonVacationById(int vacationId);
}
