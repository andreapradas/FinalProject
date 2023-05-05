package hospital.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class SurgeonVacation implements Serializable{

	private static final long serialVersionUID = -6939843271309580766L;

	private int vacationId;
	private java.sql.Date starts;
	private java.sql.Date ends;
	private int surgeonId;
	
	public SurgeonVacation() {
		super();
	}
	
	public SurgeonVacation(java.sql.Date starts, java.sql.Date ends ,int surgeonId) {
		super();
		this.starts= starts;
		this.ends= ends;
		this.surgeonId= surgeonId;
	}
	
	public SurgeonVacation(int vacationId, Date starts, Date ends ,int surgeonId) {
		this.vacationId= vacationId;
		this.starts= starts;
		this.ends= ends;
		this.surgeonId= surgeonId;
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
		SurgeonVacation other = (SurgeonVacation) obj;
		return Objects.equals(this.vacationId, other.vacationId);
	}

	@Override
	public String toString() {
		return starts.getDate() + "/" + (starts.getMonth()+1) + "/" + (starts.getYear()+1900)
				+ " to " + ends.getDate() + "/" + (ends.getMonth()+1) + "/" + (ends.getYear()+1900)
				+ " (vacId: "+ vacationId + " surgId: " + surgeonId +")";
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
	
	public void setEndDate(java.sql.Date end) {
		this.ends= end;
	}
	
	public int getVacationId() {
		return vacationId;
	}
		
	public int getSurgeonOnVacationId() {
		return surgeonId;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(java.sql.Date starts) {
		this.starts = starts;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public int getSurgeonId() {
		return surgeonId;
	}

	public void setSurgeonId(int surgeonId) {
		this.surgeonId = surgeonId;
	}

	public void setVacationId(int vacationId) {
		this.vacationId = vacationId;
	}
	
}
