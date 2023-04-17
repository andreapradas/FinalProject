package hospital.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OperatingRoom implements Serializable{

	private static final long serialVersionUID = 8375381789297162943L;
	//Attributes 
	private int roomID;
	private int roomNumber;
	private int roomFloor;
	private Boolean active; //El chiefSurgeon lo gestiona, para gestionar las que esten disponibles
	private List<Boolean> availableRooms; //Huecos del horario, crear funcion que te ponga tantos huecos como
	//hab disponibles
	
	//Constructor
	public OperatingRoom(int roomID, int roomNumber, int roomFloor, Boolean active) {
		super();
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.roomFloor = roomFloor;
		this.active = active;
		//this.availableRooms ;
	}
	
	//Getters and Setters

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
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

	//HashCode and Equals
	
	@Override
	public int hashCode() {
		return Objects.hash(roomID);
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
		return roomID == other.roomID;
	}
	
	//ToString

	@Override
	public String toString() {
		return "OperatingRoom [roomID=" + roomID + ", roomNumber=" + roomNumber + ", roomFloor=" + roomFloor + "]";
	}

	
	
	
	
	
	
	
}
