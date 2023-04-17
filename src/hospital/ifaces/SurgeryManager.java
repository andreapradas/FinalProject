package hospital.ifaces;

import java.util.List;
import hospital.pojos.Surgery;

public interface SurgeryManager {
	
	//Add a surgery
	public void addSurgery(Surgery s);
	
	//Get the list of surgeries
	public List<Surgery> getListOfSurgeries();

	//Get a surgery by the id
	public Surgery getSurgeryById(int id);
	
	//Create a surgery
	public void createSurgery(Surgery s);
	
	//Assign a surgery to a surgeon
	//public void assign (int surgeonID, int surgeryID);
}
