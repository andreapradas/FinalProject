package hospital.ifaces;

import java.util.List;

import hospital.pojos.OperatingRoom;

public interface OperatingRoomManager {

	//Add an operating room
	public void addOperatingRoom(OperatingRoom r);
	
	//Get the list of ALL rooms
	public List<OperatingRoom> getListOfOperatingRoom();
	
	//Get the list of ACTIVE rooms
	public List<OperatingRoom> getListOfActiveOperatingRoom();
	
	//Get by id
	public OperatingRoom getRoomById(int roomId);
	
	//Update
	public void updateActivity(int roomId, Boolean active);
	
	//Modify
	public void modifyOperatingRoom(int roomId, String parameterChange, String newParameter);

}
