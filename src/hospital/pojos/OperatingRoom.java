package hospital.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OperatingRoom implements Serializable{

	private static final long serialVersionUID = 8375381789297162943L;
	//Attributes 
	private int roomId;
	private int roomNumber;
	private int roomFloor;
	private Boolean active; 
	private List<Boolean> hoursAvailable; //Huecos del horario, crear funcion que te ponga tantos huecos como hab disponibles
	
	//Constructors
	
	public OperatingRoom() {
		super();
	}
	
	public OperatingRoom(int roomId, int roomNumber, int roomFloor) {//Para cuando la crea el CHIEF
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.roomFloor = roomFloor;
		this.active = true;//Por defecto estará activa hasta que se le meta una cirugia 
		this.hoursAvailable = createHoursRange(4);//4 franjas horarias
	}
	
	public OperatingRoom(int roomId, int roomNumber, int roomFloor, Boolean active) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.roomFloor = roomFloor;
		this.active = active;
	}
	
	private List<Boolean> createHoursRange(int range){
		List<Boolean> hoursAvailable = new ArrayList<Boolean>();
		for(int i=0; i<range; i++) {
			hoursAvailable.add(true); 
		}
		return hoursAvailable;
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
	public List<Boolean> getHoursAvailable() {
		return hoursAvailable;
	}

	public void setHoursAvailable(List<Boolean> hoursAvailable) {
		this.hoursAvailable = hoursAvailable;
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
