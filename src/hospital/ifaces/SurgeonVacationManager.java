package hospital.ifaces;

import java.util.List;

import hospital.pojos.SurgeonVacation;

public interface SurgeonVacationManager {
	List<SurgeonVacation> getSurgeonReservedVacation (int id);
	void addVacation(SurgeonVacation sV);
}
