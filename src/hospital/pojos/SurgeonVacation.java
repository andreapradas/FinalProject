package hospital.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class SurgeonVacation implements Serializable{

	private static final long serialVersionUID = -6939843271309580766L;

	private int vacationId;
	private Date starts;
	private Date ends;
	private int surgeonId;
	
	public SurgeonVacation() {
		super();
	}
	
	public SurgeonVacation(Date starts, Date ends ,int surgeonId) {
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
		return "Surgeon vacation [start date= " + starts + ", end date= " + ends + "]";
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
		
	public int getSurgeonOnVacationId() {
		return surgeonId;
	}
}
