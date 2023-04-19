package hospital.ifaces;

import java.util.List;

import hospital.pojos.WorksWith;

public interface WorksWithManager {
	
	public void addWorksWith(WorksWith wW);
	
	public List<WorksWith> getListOfWorksWith();

	public WorksWith getWorksWithByID (int iD);
	
	public void deleteWorksWithByID (int ID);
	
	//modify
}

