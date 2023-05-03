package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.WorksWith;

public interface WorksWithManager {
	
	//public void addWorksWith(WorksWith wW);
	
	public List<WorksWith> getListOfWorksWith();

	public WorksWith getWorksWithByID (int iD);
	
	public void deleteWorksWithByID (int ID);

	void assign(int nurseId, int surgeonID, Date date);

	//void assign(int nurseId, int surgeonID);
	
}

