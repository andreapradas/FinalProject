package hospital.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;


public class Surgery implements Serializable{
	private static final long serialVersionUID = 7073066432309393382L;
	
	//Attributes
	private int surgeryId;
	private String surgeryType;
	private Date surgeryDate;
	private int startHour;//Se limita a 4 franjas horarias que ya tienen estipuladas las horas
	private Time endHour;
	private Boolean done;
	//Foreign keys
	private int patientId; //Many to one relationship
	private int surgeonId; //Many to one relationship
	private int roomId; //Many to one relationship
	
	//Constructors
	
	public Surgery() {
		super();
	}
	
	public Surgery(Integer surgeryId, String surgeryType, Date day, int startHour, Time endHour,  
			Integer patientId, Integer surgeonId, Integer roomId) {
		super();
		this.surgeryId = surgeryId; 
		this.surgeryType = surgeryType;
		this.surgeryDate = day;
		this.startHour = startHour;//Se crea al meter la cirugia 
		this.done = false; //Cambiar cuando se realice a true ??
		
		//Foreign keys
		this.patientId = patientId;
		this.surgeonId = surgeonId;
		this.roomId = roomId;
	}
	
	public Surgery(String surgeryType, int patientId) {
		super();
		this.surgeryType = surgeryType;
		this.patientId = patientId;
	}
	
	
	//Getters and Setters

	public Integer getSurgeryId() {
		return surgeryId;
	}
	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}
	public String getSurgeryType() {
		return surgeryType;
	}
	public void setSurgeryType(String surgeryType) {
		this.surgeryType = surgeryType;
	}
	public Date getSurgeryDate() {
		return surgeryDate;
	}
	public void setSurgeryDate(Date day) {
		this.surgeryDate = day;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public Time getEndHour() {
		return endHour;
	}
	public void setEndHour(Time endHour) {
		this.endHour = endHour;
	}
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getSurgeonId() {
		return surgeonId;
	}

	public void setSurgeonId(int surgeonId) {
		this.surgeonId = surgeonId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	//HashCode and equals
	
	@Override
	public int hashCode() {
		return Objects.hash(surgeryId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		return true;
		if (obj == null)
		return false;
		if (getClass() != obj.getClass())
		return false;
		Surgery other = (Surgery) obj;
		
		return Objects.equals(surgeryId, other.surgeryId);
	}
	
	//ToString
	
	@Override
	public String toString() {
		return "Surgery [surgeryId=" + surgeryId + ", surgeryType=" + surgeryType + ", day="
		+ surgeryDate + ", patientId=" + patientId + ", surgeonId=" + surgeonId + ", roomId=" + roomId + "]";
	}

}
