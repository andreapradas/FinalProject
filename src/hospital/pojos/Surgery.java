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
	private Time startHour;
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
	
	public Surgery(Integer surgeryId, String surgeryType, Date day, Time startHour, Time endHour,  
			Integer patientId, Integer surgeonId, Integer roomId) {
		super();
		this.surgeryId = surgeryId; 
		this.surgeryType = surgeryType;
		//this.duration = duration;
		this.surgeryDate = day;
		this.startHour = startHour;
		this.endHour = endHour;
		this.done = false; //Cambiar cuando se realice a true ??
		//Foreign keys
		this.patientId = patientId;
		this.surgeonId = surgeonId;
		this.roomId = roomId;
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
	public Time getStartHour() {
		return startHour;
	}
	public void setStartHour(Time startHour) {
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
