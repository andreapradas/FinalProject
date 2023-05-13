package hospital.ifaces;

import java.util.List;

import hospital.pojos.OperatingRoom;

public interface OperatingRoomManager {

	public void addOperatingRoom(OperatingRoom r);
	public List<OperatingRoom> getListOfOperatingRoom();
	public List<OperatingRoom> getListOfActiveOperatingRoom();
	public OperatingRoom getRoomById(int roomId);
	public void updateActivity(int roomId, Boolean active);
}
