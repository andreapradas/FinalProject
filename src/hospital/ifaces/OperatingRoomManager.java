package hospital.ifaces;

import java.util.List;

import hospital.pojos.OperatingRoom;

public interface OperatingRoomManager {

	//Add an operating room
	public void addOperatingRoom(OperatingRoom r);
	
	//Get the list of operating rooms
	public List<OperatingRoom> getListOfOperatingRoom();
	
	//
}
