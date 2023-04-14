package hospital.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class Surgery implements Serializable{
	private static final long serialVersionUID = 7073066432309393382L;
	
	//Attributes
	private Integer surgeryId;
	private String surgeryType;
	private Integer duration;
	private LocalDate day;
	private LocalTime startHour;
	private LocalTime endHour;
	private Boolean done;
	private Integer patientId;
	private Integer surgeonId;
	private Integer roomId;
	
	//Constructor
	
	public Surgery(Integer surgeryId, String surgeryType, Integer duration, LocalDate day, LocalTime startHour, 
			Integer patientId, Integer surgeonId, Integer roomId) {
		super();
		this.surgeryId = surgeryId;
		this.surgeryType = surgeryType;
		this.duration = duration;
		this.day = day;
		this.startHour = startHour;
		this.endHour = startHour.plusMinutes(duration);
		//this.endHour = generarEndHour(startHour, duration);
		this.done = true;
		this.patientId = patientId;
		this.surgeonId = surgeonId;
		this.roomId = roomId;
	}
	
	//Getters y Setters
	
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
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public LocalDate getDay() {
		return day;
	}
	public void setDay(LocalDate day) {
		this.day = day;
	}
	public LocalTime getStartHour() {
		return startHour;
	}
	public void setStartHour(LocalTime startHour) {
		this.startHour = startHour;
	}
	public LocalTime getEndHour() {
		return endHour;
	}
	public void setEndHour(LocalTime endHour) {
		this.endHour = endHour;
	}
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	
	//HashCode y equals
	
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
		return "Surgery [surgeryId=" + surgeryId + ", surgeryType=" + surgeryType + ", duration=" + duration + ", day="
		+ day + ", patientId=" + patientId + ", surgeonId=" + surgeonId + ", roomId=" + roomId + "]";
	}

}
