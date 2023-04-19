package hospital.ifaces;

import java.util.List;

import hospital.pojos.NurseVacation;

public interface NurseVacationManager {
	
	public void addNurseVacation(NurseVacation nV);
	
	public List<NurseVacation> getListOfNurseVacation();
	
	public void assign(int nurseVacationID, int nurseID); 
	
	public void getNurseVacationByID (int iD);
	
	public void deleteNurseVacationByID (int ID);
	
	//modify
}
