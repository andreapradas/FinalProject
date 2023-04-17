package hospital.ifaces;

import java.util.List;
import hospital.pojos.Surgery;

public interface SurgeryManager {
	
	//Add a surgery
	public void addSurgery(Surgery s);
	
	//Get the list of surgeries
	public List<Surgery> getListOfSurgeries();
	
	//Assign a surgery to a surgeon
	//public void assign (int surgeonID, int surgeryID);
}
