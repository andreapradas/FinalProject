package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.Surgeon;

public interface SurgeonManager {
	String getNameById(int id);
	void addSurgeon(Surgeon surg);
	List<Surgeon> getListOfSurgeons();
	Surgeon getChiefSurgeon();
	void changeChief(int id);
	void deleteChief();
	int getIdByEmail(String email);
	String getEmailById(int id);
	Surgeon getSurgeonById(int Id);
	List<Surgeon> getSurgeonsAssignedThisDay(Date date);
}
