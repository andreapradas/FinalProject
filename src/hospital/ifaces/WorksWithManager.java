package hospital.ifaces;

import java.util.List;

import hospital.pojos.WorksWith;


public class WorksWithManager {
	
	public void addWorksWith(WorksWith wW);
	
	public List<WorksWith> getListOfWorksWith();
	
	public void assign(int nurseID, int surgeonID); //Assign a nurse to a surgeon
}

