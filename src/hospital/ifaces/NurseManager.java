package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.Nurse;

public interface NurseManager {
	
	public void addNurse(Nurse nurse);
	public List<Nurse> getListOfNurses();
	//public void assign(int nurseID, int surgeonID); //Assign a nurse to a surgeon
	public void deleteNurseByID (int ID);
	int getIdByEmail(String email);
	public String getNameById(int id);
	public Nurse getNurseById(int nurseId);
	public List<Nurse> getNursesAssignedThisDay(Date date);

}
