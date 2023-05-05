package hospital.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class NurseVacation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3514210278458069620L;
	private Integer vacationId;
	private Integer nurseId;
	private Date starts;
	private Date ends;
	
	
	public NurseVacation() {
		super();
	}
	
	public NurseVacation(Date starts, Date ends, Integer nurseID) {
		super();
		this.starts= starts;
		this.ends= ends;
		this.nurseId= nurseID;
	}
	
	
	public NurseVacation(Integer vacationID, Date starts, Date ends, Integer nurseID) {
		super();
		this.vacationId = vacationID;
		this.nurseId = nurseID;
		this.starts = starts;
		this.ends = ends;	
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.vacationId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NurseVacation other = (NurseVacation) obj;
		return Objects.equals(this.vacationId, other.vacationId);
	}
	
	@Override
	public String toString() {
		return starts.getDate() + "/" + (starts.getMonth()+1) + "/" + (starts.getYear()+1900)
				+ " to " + ends.getDate() + "/" + (ends.getMonth()+1) + "/" + (ends.getYear()+1900)
				+ " (vacId: "+ vacationId + " nurseId: " + nurseId +")";
	}

	public Date getStartDate() {
		return starts;
	}
	
	public void setStartDate(Date start) {
		this.starts= start;
	}
	
	public Date getEndDate() {
		return ends;
	}
	
	public void setEndDate(Date end) {
		this.ends= end;
	}
	
	public int getVacationId() {
		return vacationId;
	}
		
	public int getNurseOnVacationId() {
		return nurseId;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

	public void setVacationId(int vacationId) {
		this.vacationId = vacationId;
	}
	
}
