package hospital.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OperatingRoom implements Serializable{

	private static final long serialVersionUID = 8375381789297162943L;
	//Attributes 
	private int roomId;
	private int roomNumber;
	private int roomFloor;
	private Boolean active; //El chiefSurgeon lo gestiona, para gestionar las que esten disponibles
	private List<Boolean> availableRooms; //Huecos del horario, crear funcion que te ponga tantos huecos como
	//hab disponibles
	
	//Constructor
	public OperatingRoom(int roomId, int roomNumber, int roomFloor, Boolean active) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.roomFloor = roomFloor;
		this.active = active;
		this.availableRooms.add(active); //Add the rooms wheter they are active or not
	}
	
	//Getters and Setters

	public int getRoomId() {
		return roomId;
	}

	public void setRoomID(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(int roomFloor) {
		this.roomFloor = roomFloor;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Boolean> getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(List<Boolean> availableRooms) {
		this.availableRooms = availableRooms;
	}
	
	//HashCode and Equals

	@Override
	public int hashCode() {
		return Objects.hash(roomId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperatingRoom other = (OperatingRoom) obj;
		return roomId == other.roomId;
	}
	
	//ToString

	@Override
	public String toString() {
		return "OperatingRoom [roomId=" + roomId + ", roomNumber=" + roomNumber + ", roomFloor=" + roomFloor + "]";
	}

	
	
	
	
	
	
	
}
