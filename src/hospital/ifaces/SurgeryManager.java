package hospital.ifaces;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import hospital.pojos.Surgery;

public interface SurgeryManager {
	
	public void addSurgery(Surgery s);
	public List<Surgery> getListOfSurgeries();
	public List<Surgery> getListOfSurgeriesNotDone();
	public void deleteSurgery(int surgeryId);
	void updateRoomHourDate(int surgeryId, Date surgeryDate, Time startHour, int roomId);
	void updateSurgeonId(int surgeryId, int surgeonId);
	void updateDone(int surgeryId);
	void deleteRoomHourDate(int surgeryId);
	List<Surgery> getListOfSurgeries(Date date);

}
