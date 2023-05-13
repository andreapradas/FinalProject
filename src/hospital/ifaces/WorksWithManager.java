package hospital.ifaces;

import java.sql.Date;
import java.util.List;

import hospital.pojos.WorksWith;

public interface WorksWithManager {
	
	public void assign(int nurseId, int surgeonID, Date date);
	public List<WorksWith> getListOfWorksWith(Date date);
	int getNurseIdAssignedSurgeonDate(int sId, Date date) throws Exception;
	
}

