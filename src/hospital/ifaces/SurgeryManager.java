package hospital.ifaces;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import hospital.pojos.Surgery;

public interface SurgeryManager {
	
	//Add a surgery
	public void addSurgery(Surgery s);
	
	//Get the list of surgeries
	public List<Surgery> getListOfSurgeries();
	
	//Get the list of surgeries DONE
	public List<Surgery> getListOfSurgeriesNotDone();

	//Get a surgery by the id
	public Surgery getSurgeryById(int id);
	
	//Create a surgery
	public void createSurgery(Surgery s);
	
	//Modify a surgery
//	public void modifySurgery(int surgeryId, String parameterChange, String newParameter);
	
	//Delete a surgery
	public void deleteSurgery(int surgeryId);

	void programTheSurgey(int surgeryId, Date date, Time time, int sugeonId, int roomId);

}
